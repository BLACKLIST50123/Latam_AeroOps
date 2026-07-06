package Patrones.State;

import Enumeradores.EstadoVuelo;

/**
 * Cada vez que un VueloOperativo se carga desde la base de datos (cualquier
 * combo, tabla o tarjeta de cualquier pantalla), Java crea un objeto nuevo
 * con "new VueloOperativo()", y ese constructor por defecto siempre arranca
 * en EstadoPendienteDespacho. Eso significa que un vuelo que en la BD está,
 * por ejemplo, EN_DEMORA, se reconstruía en memoria como si estuviera recién
 * creado — el objeto de estado (IEstadoVuelo) quedaba desincronizado del
 * campo estadoVuelo (String).
 *
 * Esta fábrica resuelve eso: dado el texto guardado en la columna
 * estado_vuelo, devuelve la instancia de IEstadoVuelo que le corresponde.
 * VueloOperativo.setEstadoVuelo() la usa automáticamente, así que cualquier
 * DAO que llame a ese setter con el valor real de la BD queda sincronizado
 * sin tener que tocar cada punto de carga uno por uno.
 */
public class EstadoVueloFactory {

    public static IEstadoVuelo crear(String estadoVuelo) {
        if (estadoVuelo == null) {
            return new EstadoPendienteDespacho();
        }
        try {
            switch (EstadoVuelo.valueOf(estadoVuelo)) {
                case PENDIENTE_DESPACHO: return new EstadoPendienteDespacho();
                case EN_DEMORA:          return new EstadoEnDemora();
                case APROBADO:           return new EstadoAprobado();
                case EN_VUELO:           return new EstadoEnVuelo();
                case EN_TIERRA:          return new EstadoEnTierra();
                case COMPLETADO:         return new EstadoCompletado();
                case CANCELADO:          return new EstadoCancelado();
                default:                 return new EstadoPendienteDespacho();
            }
        } catch (IllegalArgumentException e) {
            // Por si algún día llega un valor inesperado desde la BD
            System.out.println("Estado de vuelo desconocido: " + estadoVuelo + ". Se usa PENDIENTE_DESPACHO por defecto.");
            return new EstadoPendienteDespacho();
        }
    }
}
