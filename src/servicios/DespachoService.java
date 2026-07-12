package servicios;

import ClasesDAO.VueloOperativoDAO;
import Clases.HojaDeCarga;
import Clases.ManifiestoCombustible;
import Clases.ReporteMeteorologico;
import Clases.ValidadorMetar;
import Clases.VueloOperativo;
import Patrones.Builder.VueloOperativoBuilder;

/**
    Servicio coordinador del flujo principal para la aprobación y despacho de vuelos operativos.
  
    Orquesta la validación de pesos (MTOW), el análisis técnico de condiciones climáticas (METAR) 
    y la persistencia de datos finales. Controla el ciclo de vida del vuelo delegando cambios de 
    comportamiento en el patrón State y consolidando los registros a través de un Builder, aislando 
    por completo las decisiones de negocio de la interfaz de usuario.
 */
public class DespachoService {

    private final VueloOperativoDAO vueloOperativoDAO;
    private final ClimaAeropuertoService climaAeropuertoService;

    public DespachoService(VueloOperativoDAO vueloOperativoDAO, ClimaAeropuertoService climaAeropuertoService) {
        this.vueloOperativoDAO = vueloOperativoDAO; 
        this.climaAeropuertoService = climaAeropuertoService;
    }

    public DespachoService() {
        this(new VueloOperativoDAO(), new ClimaAeropuertoService());
    }

// ==========================================
// MÉTODOS PARA APROBAR EL DESPACHO
// ==========================================      
/**
    Ejecuta de forma integral las reglas de negocio aeronáuticas para autorizar la salida de un vuelo.
     
    Valida secuencialmente que el peso no exceda los límites, que el clima esté apto para operar 
    y que el reporte corresponda al destino. Si todo es correcto, transiciona el estado del vuelo, 
    ensambla los documentos técnicos y guarda la información en la base de datos.
    
    @param vuelo Instancia base del vuelo operativo que se desea despachar.
    @param hoja Documento técnico con el desglose y balance de pesos de la aeronave.
    @param manifiesto Registro formal del combustible cargado y planificado para la ruta.
    @param codigoMetar Cadena de texto con el reporte meteorológico actual (ej. "SPJC...").
    @param mtowActual Peso Máximo de Despegue autorizado y configurado para este vuelo específico.
    @return Un objeto ResultadoDespacho indicando el éxito con el vuelo armado, o la causa exacta del rechazo.
*/
    public ResultadoDespacho aprobarDespacho(VueloOperativo vuelo, HojaDeCarga hoja,
                                              ManifiestoCombustible manifiesto,
                                              String codigoMetar, double mtowActual) {

        String metar = codigoMetar == null ? "" : codigoMetar.trim().toUpperCase();

        if (!hoja.validarPesoMaximo(mtowActual)) {
            return ResultadoDespacho.fallo(
                "No se puede aprobar el despacho. El peso actual supera el MTOW de la aeronave.");
        }

        if (!ValidadorMetar.esClimaApto(metar)) {
            return ResultadoDespacho.fallo(
                "El METAR reporta clima adverso. Debe declarar demora o cancelar el vuelo.");
        }

        if (!climaAeropuertoService.metarCorrespondeADestino(metar, vuelo.getVueloBase().getOrigenDestino())) {
            return ResultadoDespacho.fallo(
                "El código METAR ingresado no pertenece al aeropuerto de destino de este vuelo.");
        }

        // Patrón State: PendienteDespacho -> Aprobado
        vuelo.procesarAprobacion();

        ReporteMeteorologico clima = new ReporteMeteorologico();
        clima.setCodigoMETAR(metar);

        VueloOperativo vueloEnsamblado = new VueloOperativoBuilder(vuelo)
            .setHojaCarga(hoja)
            .setManifiesto(manifiesto)
            .setClima(clima)
            .build();

        boolean persistido = vueloOperativoDAO.aprobarDespachoConDatosOperativos(vueloEnsamblado);
        if (!persistido) {
            return ResultadoDespacho.fallo("Ocurrió un error al guardar los datos de despacho en la base de datos.");
        }

        return ResultadoDespacho.exito(vueloEnsamblado);
    }

// ==================================================
// MÉTODOS PARA DECLARAR VUELO EN ESTADO "EN_DEMORA"
// ==================================================
/**
    Cambia formalmente el estado de operación de un vuelo a la categoría de demora.
     
    Modifica el comportamiento dinámico del vuelo a través de su patrón State interno 
    y actualiza de inmediato el nuevo estado de la aeronave en el sistema de base de datos.
    
    @param vuelo El vuelo operativo afectado que sufrirá el retraso en el itinerario.
    @return true si la base de datos confirmó y guardó la actualización del nuevo estado correctamente.
*/
    public boolean declararDemora(VueloOperativo vuelo) {
        vuelo.procesarDemora(); // El State cambia el comportamiento a EstadoEnDemora
        return vueloOperativoDAO.actualizarEstadoVuelo(vuelo.getCodVuelo(), vuelo.getEstadoVuelo());
    }
}
