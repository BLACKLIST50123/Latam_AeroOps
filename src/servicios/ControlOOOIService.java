package servicios;

import ClasesDAO.VueloOperativoDAO;
import Clases.VueloOperativo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
    Servicio encargado de gestionar el ciclo de vida y control de tiempos OOOI de un vuelo.
  
    Centraliza la lógica transaccional para actualizar el avance de las fases operativas 
    (OUT, OFF, ON, IN), la generación de marcas de tiempo en formato Zulu (UTC) y la persistencia 
    en la base de datos. Además, coordina las transiciones automáticas del patrón de diseño State 
    de la aeronave, aislando completamente estas reglas de la interfaz gráfica.
 */
public class ControlOOOIService {

    public static final String FASE_OUT = "OUT";
    public static final String FASE_OFF = "OFF";
    public static final String FASE_ON = "ON";
    public static final String FASE_IN = "IN";

    private final VueloOperativoDAO vueloOperativoDAO;

    public ControlOOOIService(VueloOperativoDAO vueloOperativoDAO) {
        this.vueloOperativoDAO = vueloOperativoDAO;
    }

    public ControlOOOIService() {
        this(new VueloOperativoDAO());
    }

// ===============================
// MÉTODO REGISTRAR LA FASE OOOI
// ===============================       
/**
    Registra el avance de fase OOOI para el vuelo dado: persiste en BD,
    actualiza el objeto en memoria y dispara las transiciones de State
    (patrón ya existente Patrones.State) que correspondan a cada fase.
    
    @param vuelo Objeto de la entidad que representa el vuelo que avanza de fase.
    @param fase Identificador de la etapa actual (FASE_OUT, FASE_OFF, FASE_ON, FASE_IN).
    @return true si la fase se registró y persistió correctamente en el sistema.
    @throws IllegalArgumentException Si se envía una cadena de fase que no corresponde al estándar OOOI.
*/
    public boolean registrarFase(VueloOperativo vuelo, String fase) {
        String horaZulu = generarHoraZulu();

        if (!vueloOperativoDAO.registrarFaseOOOI(vuelo.getCodVuelo(), fase, horaZulu)) {
            return false;
        }

        vuelo.setEstadoOOOI(fase);

        switch (fase) {
            case FASE_OUT:
                vuelo.setHoraOut(horaZulu);
                break;
            case FASE_OFF:
                vuelo.setHoraOff(horaZulu);
                vuelo.procesarIniciarVuelo(); // State: Aprobado -> EnVuelo
                vueloOperativoDAO.actualizarEstadoVuelo(vuelo.getCodVuelo(), vuelo.getEstadoVuelo());
                break;
            case FASE_ON:
                vuelo.setHoraOn(horaZulu);
                break;
            case FASE_IN:
                vuelo.setHoraIn(horaZulu);
                vuelo.procesarFinalizarVuelo(); // State: EnVuelo -> EnTierra
                vueloOperativoDAO.actualizarEstadoVuelo(vuelo.getCodVuelo(), vuelo.getEstadoVuelo());
                break;
            default:
                throw new IllegalArgumentException("Fase OOOI desconocida: " + fase);
        }

        return true;
    }
// ==========================================
// MÉTODO PARA GENERAR EL FORMATO HORA ZULU
// ========================================== 
/**
    Calcula la hora actual del sistema en formato de aviación internacional.
     
    Configura el formato estándar de hora y minutos adjuntando el sufijo 'Z', 
    utilizando estrictamente la zona horaria UTC (Coordinated Universal Time).
     
    @return Una cadena de texto formateada (ejemplo: "14:35Z").
*/
    private String generarHoraZulu() {
        SimpleDateFormat formatoZulu = new SimpleDateFormat("HH:mm'Z'");
        formatoZulu.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatoZulu.format(new Date());
    }
}
