package servicios;

import ClasesDAO.VueloOperativoDAO;
import Clases.HojaDeCarga;
import Clases.ManifiestoCombustible;
import Clases.ReporteMeteorologico;
import Clases.ValidadorMetar;
import Clases.VueloOperativo;
import Patrones.Builder.VueloOperativoBuilder;

/* ¿Para qué sirve?: Este servicio se encarga de todo el proceso de aprobar el despacho de un vuelo. Revisa que el peso no supere el límite permitido, que el clima esté apto para volar y que el reporte del clima corresponda al aeropuerto correcto; si todo está bien, arma el vuelo completo y lo guarda en la base de datos. También permite declarar un vuelo en demora
   Clases que la utilizan: OficialOperaciones_GUI
   Índice de Métodos: DespachoService, aprobarDespacho, declararDemora */
public class DespachoService {

    private final VueloOperativoDAO vueloOperativoDAO;
    private final ClimaAeropuertoService climaAeropuertoService;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara el servicio de despacho. Existen dos formas de crearlo: una recibiendo el DAO de vuelos y el servicio de clima ya armados (útil para pruebas), y otra vacía que arma sus propias dependencias por defecto
    // Clases que lo usan: OficialOperaciones_GUI
    public DespachoService(VueloOperativoDAO vueloOperativoDAO, ClimaAeropuertoService climaAeropuertoService) {
        this.vueloOperativoDAO = vueloOperativoDAO; 
        this.climaAeropuertoService = climaAeropuertoService;
    }

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara el servicio de despacho. Existen dos formas de crearlo: una recibiendo el DAO de vuelos y el servicio de clima ya armados (útil para pruebas), y otra vacía que arma sus propias dependencias por defecto
    // Clases que lo usan: OficialOperaciones_GUI
    public DespachoService() {
        this(new VueloOperativoDAO(), new ClimaAeropuertoService());
    }

    // ==========================================
    // MÉTODO PARA APROBAR EL DESPACHO DE UN VUELO
    // ==========================================
    // Descripción: Revisa en orden tres condiciones: que el peso no supere el máximo permitido, que el clima esté apto según el METAR, y que el METAR corresponda al aeropuerto de destino. Si alguna falla, entrega un resultado con el mensaje del problema. Si todas pasan, cambia el estado del vuelo a Aprobado, arma el vuelo completo con la hoja de carga, el manifiesto de combustible y el clima, lo guarda en la base de datos, y entrega un resultado exitoso con el vuelo ya armado
    // Clases que lo usan: OficialOperaciones_GUI
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

    // ==========================================
    // MÉTODO PARA DECLARAR UN VUELO EN DEMORA
    // ==========================================
    // Descripción: Cambia el comportamiento del vuelo a Estado En Demora a través del patrón State, y guarda ese nuevo estado en la base de datos. Entrega verdadero si se guardó correctamente
    // Clases que lo usan: OficialOperaciones_GUI
    public boolean declararDemora(VueloOperativo vuelo) {
        vuelo.procesarDemora(); // El State cambia el comportamiento a EstadoEnDemora
        return vueloOperativoDAO.actualizarEstadoVuelo(vuelo.getCodVuelo(), vuelo.getEstadoVuelo());
    }
}
