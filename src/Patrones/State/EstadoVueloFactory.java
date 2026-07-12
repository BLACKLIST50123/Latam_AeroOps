package Patrones.State;

import Enumeradores.EstadoVuelo;

/* ¿Para qué sirve?: Esta es una fábrica (patrón Factory) que, dado el texto del estado guardado en la base de datos, entrega el objeto de estado (IEstadoVuelo) que le corresponde. Esto es necesario porque cuando un vuelo se carga desde la base de datos, Java lo crea con el constructor por defecto, que siempre empieza en EstadoPendienteDespacho; esta fábrica corrige eso y deja el objeto de estado sincronizado con el texto real guardado en la base de datos
   Clases que la utilizan: VueloOperativo
   Índice de Métodos: crear */
public class EstadoVueloFactory {

    // ==========================================
    // MÉTODO PARA CREAR EL ESTADO CORRESPONDIENTE
    // ==========================================
    // Descripción: Recibe el texto del estado del vuelo guardado en la base de datos y entrega el objeto de estado (IEstadoVuelo) que le corresponde. Si el texto es vacío o no coincide con ningún estado conocido, entrega por defecto el estado Pendiente de Despacho
    // Clases que lo usan: VueloOperativo
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
