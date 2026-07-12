package servicios;

import ClasesDAO.VueloOperativoDAO;
import Clases.VueloOperativo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/* ¿Para qué sirve?: Este servicio se encarga de todo el control de las fases OOOI de un vuelo (salida, despegue, aterrizaje y llegada). Cada vez que el Oficial de Operaciones marca que el vuelo avanzó a la siguiente fase, esta clase guarda la hora exacta, actualiza el vuelo y avisa al patrón State para que cambie el comportamiento del vuelo según corresponda
   Clases que la utilizan: OficialOperaciones_GUI
   Índice de Métodos: ControlOOOIService, registrarFase, generarHoraZulu */
public class ControlOOOIService {

    public static final String FASE_OUT = "OUT";
    public static final String FASE_OFF = "OFF";
    public static final String FASE_ON = "ON";
    public static final String FASE_IN = "IN";

    private final VueloOperativoDAO vueloOperativoDAO;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara el servicio de control OOOI. Existen dos formas de crearlo: una recibiendo el DAO de vuelos ya armado (útil para pruebas), y otra vacía que arma su propio DAO por defecto
    // Clases que lo usan: OficialOperaciones_GUI
    public ControlOOOIService(VueloOperativoDAO vueloOperativoDAO) {
        this.vueloOperativoDAO = vueloOperativoDAO;
    }

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara el servicio de control OOOI. Existen dos formas de crearlo: una recibiendo el DAO de vuelos ya armado (útil para pruebas), y otra vacía que arma su propio DAO por defecto
    // Clases que lo usan: OficialOperaciones_GUI
    public ControlOOOIService() {
        this(new VueloOperativoDAO());
    }

    // ==========================================
    // MÉTODO PARA REGISTRAR UNA FASE OOOI
    // ==========================================
    // Descripción: Genera la hora actual en formato Zulu (hora internacional de aviación) y la guarda en la base de datos como la hora de la fase indicada (OUT, OFF, ON o IN). Además actualiza el vuelo en memoria y, en las fases OFF e IN, avisa al patrón State para que el vuelo cambie de comportamiento (por ejemplo, de Aprobado a En Vuelo). Entrega verdadero si todo se guardó correctamente
    // Clases que lo usan: OficialOperaciones_GUI
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
    // MÉTODO PARA GENERAR LA HORA EN FORMATO ZULU
    // ==========================================
    // Descripción: Calcula la hora actual del sistema en la zona horaria UTC y la entrega con el formato que se usa en aviación, por ejemplo '14:35Z'
    // Clases que lo usan: OficialOperaciones_GUI
    private String generarHoraZulu() {
        SimpleDateFormat formatoZulu = new SimpleDateFormat("HH:mm'Z'");
        formatoZulu.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatoZulu.format(new Date());
    }
}
