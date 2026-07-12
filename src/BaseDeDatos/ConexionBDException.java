package BaseDeDatos;

/**
 * Excepción de dominio para errores al establecer o usar la conexión a la
 * base de datos. Reemplaza el manejo anterior, en el que ConexionBD llamaba
 * directamente a un diálogo de Swing (ElementosDiseño.NotificacionDialog)
 * desde la capa de persistencia — un acoplamiento prohibido entre capas.
 *
 * Quien decide CÓMO mostrarle este error al usuario (diálogo, log, consola,
 * etc.) es la capa de presentación / arranque de la app, no esta clase.
 */
public class ConexionBDException extends Exception {

    public ConexionBDException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ConexionBDException(String mensaje) {
        super(mensaje);
    }
}
