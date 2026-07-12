package Clases;

/* ¿Para qué sirve?: Esta clase revisa el código METAR (el reporte del clima de un aeropuerto) y decide si las condiciones son seguras para operar un vuelo
   Clases que la utilizan: ClimaAeropuertoService, DespachoService, OficialOperaciones_GUI
   Índice de Métodos: esClimaApto */
public class ValidadorMetar {

    // ==========================================
    // MÉTODO PARA REVISAR SI EL CLIMA ES SEGURO
    // ==========================================
    // Descripción: Revisa el texto del reporte METAR buscando señales de mal clima, como tormentas, niebla, ceniza volcánica, lluvia fuerte, poca visibilidad o nubes muy bajas. Si encuentra alguna de estas señales, dice que el clima no es apto para volar; si no encuentra ninguna, dice que el clima está bien
    // Clases que lo usan: ClimaAeropuertoService, DespachoService, OficialOperaciones_GUI
    public static boolean esClimaApto(String metar) {
        if (metar == null || metar.trim().isEmpty()) {
            return false;
        }
        
        String metarUpper = metar.toUpperCase();

        // 1. CONDICIÓN CRÍTICA: Fenómenos climáticos peligrosos (Tormentas, Niebla densa, Ceniza)
        // TS = Thunderstorm, FG = Fog, VA = Volcanic Ash, +RA = Heavy Rain, +SN = Heavy Snow
        if (metarUpper.contains("TS") || metarUpper.contains("FG") || 
            metarUpper.contains("VA") || metarUpper.contains("+RA")) {
            return false; // Clima adverso automático (NO GO / DEMORA)
        }

        // 2. CONDICIÓN: Visibilidad Reducida (Patrón de visibilidad en metros de 4 dígitos)
        // Si detecta visibilidad menor a 1500 metros (ej: 0500, 1000, 0800), deniega el despacho visual básico
        try {
            // Buscamos un bloque de 4 números independientes que no terminen en KT (nudos) ni Z (hora)
            String[] tokens = metarUpper.split(" ");
            for (String token : tokens) {
                if (token.matches("\\d{4}") && !token.equals("0000")) {
                    int visibilidad = Integer.parseInt(token);
                    if (visibilidad < 1500) {
                        return false; // Visibilidad muy baja
                    }
                }
            }
        } catch (Exception e) {
            // Si falla el parseo, continuamos con las demás validaciones
        }

        // 3. CONDICIÓN: Techo de nubes crítico (BKN = Broken o OVC = Overcast por debajo de 1000 pies)
        // Ejemplo: BKN005 (500 pies) o OVC008 (800 pies)
        if (metarUpper.contains("BKN00") || metarUpper.contains("OVC00")) {
            return false; // Techo de nubes peligrosamente bajo
        }

        return true; // Si pasa todos los filtros, el clima está apto
    }
}