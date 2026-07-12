package BaseDeDatos;

/* ¿Para qué sirve?: Esta es una excepción propia del sistema, que se usa para avisar cuando ocurre un problema al conectarse o al usar la base de datos. Reemplaza la forma anterior en la que la propia clase de conexión mostraba directamente una ventana de error, mezclando la capa de datos con la capa visual
   Clases que la utilizan: ConexionBD, capa de Servicios y DAOs, OperacionDeVuelos
   Índice de Métodos: ConexionBDException */
public class ConexionBDException extends Exception {

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara una nueva excepción de conexión a la base de datos. Se puede crear indicando solo un mensaje de error, o indicando el mensaje junto con la causa original del problema (otra excepción técnica que lo originó)
    // Clases que lo usan: ConexionBD, capa de Servicios y DAOs, OperacionDeVuelos
    public ConexionBDException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara una nueva excepción de conexión a la base de datos. Se puede crear indicando solo un mensaje de error, o indicando el mensaje junto con la causa original del problema (otra excepción técnica que lo originó)
    // Clases que lo usan: ConexionBD, capa de Servicios y DAOs, OperacionDeVuelos
    public ConexionBDException(String mensaje) {
        super(mensaje);
    }
}
