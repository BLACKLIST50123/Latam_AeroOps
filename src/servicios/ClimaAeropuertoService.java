package servicios;
import java.util.Map;

/* ¿Para qué sirve?: Este servicio se encarga de todo lo relacionado con el clima de los aeropuertos. Por un lado revisa que el código METAR ingresado realmente corresponda al aeropuerto de destino del vuelo, y por otro lado, como el sistema no está conectado a un proveedor real de clima, genera códigos METAR simulados para poder probar la aplicación
   Clases que la utilizan: DespachoService, OficialOperaciones_GUI
   Índice de Métodos: metarCorrespondeADestino, obtenerMetarSimulado */
public class ClimaAeropuertoService {

    private static final Map<String, String> ICAO_POR_CIUDAD = Map.ofEntries(
        // --- Destinos Nacionales ---
        Map.entry("CUSCO", "SPZO"),      Map.entry("CUZ", "SPZO"),
        Map.entry("CHIMBOTE", "SPHZ"),   Map.entry("CHM", "SPHZ"),
        Map.entry("AREQUIPA", "SPQU"),   Map.entry("AQP", "SPQU"),
        Map.entry("JULIACA", "SPJL"),    Map.entry("JUL", "SPJL"),
        // --- Destinos Internacionales ---
        Map.entry("SANTIAGO", "SCEL"),   Map.entry("SCL", "SCEL"),
        Map.entry("BOGOTA", "SKBO"),     Map.entry("BOG", "SKBO"),
        Map.entry("MIAMI", "KMIA"),      Map.entry("MIA", "KMIA"),
        Map.entry("MADRID", "LEMD"),     Map.entry("MAD", "LEMD")
    );


    // ==========================================
    // MÉTODO PARA VALIDAR QUE EL METAR CORRESPONDA AL DESTINO
    // ==========================================
    // Descripción: Revisa si el código METAR ingresado menciona el aeropuerto correcto para la ruta del vuelo. Si la ruta no está en la lista conocida, o si no se ingresó ningún METAR, se considera válido por defecto para no bloquear la operación
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public boolean metarCorrespondeADestino(String codigoMetar, String origenDestino) {
        if (origenDestino == null) {
            return true;
        }
        String ruta = origenDestino.toUpperCase();
        String metar = codigoMetar == null ? "" : codigoMetar.trim().toUpperCase();

        String icaoEsperado = ICAO_POR_CIUDAD.entrySet().stream()
            .filter(entry -> ruta.contains(entry.getKey()))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(null);

        if (icaoEsperado == null || metar.isEmpty()) {
            return true;
        }
        return metar.contains(icaoEsperado);
    }

    // ==========================================
    // MÉTODO PARA SIMULAR UN REPORTE METAR
    // ==========================================
    // Descripción: Genera un código METAR de ejemplo según la ciudad de destino de la ruta, simulando lo que entregaría un proveedor real de clima. Se puede pedir una versión con buen clima o con mal clima, según el parámetro que se reciba
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public String obtenerMetarSimulado(String origenDestino, boolean forzarBueno) {
        if (origenDestino == null) {
            return "";
        }
        String ruta = origenDestino.toUpperCase();

        if (ruta.contains("CUSCO") || ruta.contains("CUZ")) {
            return forzarBueno ? "METAR SPZO 091700Z VRB03KT 9999 NSC 18/02 Q1031 NOSIG="
                                : "METAR SPZO 091700Z 12015KT 0800 FG BKN008 12/10 Q1025=";
        }
        if (ruta.contains("CHIMBOTE") || ruta.contains("CHM")) {
            return "METAR SPHZ 111200Z 18005KT 9999 SCT020 20/15 Q1013 NOSIG=";
        }
        if (ruta.contains("AREQUIPA") || ruta.contains("AQP")) {
            return forzarBueno ? "METAR SPQU 101400Z 21008KT 9999 FEW030 22/10 Q1015 NOSIG="
                                : "METAR SPQU 101400Z 25020G30KT 3000 TSRA OVC005 15/13 Q1010=";
        }
        if (ruta.contains("SANTIAGO") || ruta.contains("SCL")) {
            return "METAR SCEL 121000Z 18010KT 9999 CAVOK 15/05 Q1020 NOSIG=";
        }
        if (ruta.contains("BOGOTA") || ruta.contains("BOG")) {
            return forzarBueno ? "METAR SKBO 131500Z 11005KT 9999 SCT030 19/12 Q1025 NOSIG="
                                : "METAR SKBO 131500Z 27015KT 2500 TS BKN008 14/13 Q1020=";
        }
        if (ruta.contains("MIAMI") || ruta.contains("MIA")) {
            return forzarBueno ? "METAR KMIA 142000Z 09012KT 9999 FEW025 30/24 Q1015 NOSIG="
                                : "METAR KMIA 142000Z 15040G60KT 1000 +RA BKN000 25/24 Q0998=";
        }
        if (ruta.contains("MADRID") || ruta.contains("MAD")) {
            return "METAR LEMD 150800Z 35006KT 9999 CAVOK 12/04 Q1024 NOSIG=";
        }
        return "";
    }
}
