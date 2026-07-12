package BaseDeDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton de conexión a PostgreSQL.
 *
 * IMPORTANTE (refactor de acoplamiento): esta clase pertenece a la capa de
 * persistencia y NO debe conocer nada de la capa de presentación (Swing).
 * Antes, el constructor llamaba directamente a
 * ElementosDiseño.NotificacionDialog.error(...) cuando fallaba la conexión,
 * lo cual acoplaba la infraestructura de BD a la UI y hacía imposible
 * reutilizar/testear esta clase fuera de un entorno gráfico.
 *
 * Ahora, ante un fallo, se lanza ConexionBDException. Es responsabilidad de
 * quien arranca la aplicación (AeroOpsApp / operaciondevuelos.OperacionDeVuelos)
 * decidir cómo mostrar ese error al usuario.
 */
public class ConexionBD {

    private static final Logger LOG = Logger.getLogger(ConexionBD.class.getName());

    private static ConexionBD instancia;
    private Connection conexion;

    // Credenciales de PostgreSQL
    // TODO: mover a un archivo de configuración externo (application.properties)
    // o variables de entorno; no deben vivir hardcodeadas en el código fuente.
    private final String URL = "jdbc:postgresql://localhost:5432/LATAM_AeroOps";
    private final String USUARIO = "postgres";
    private final String CLAVE = "50123";

    // Constructor privado para evitar 'new ConexionBD()'
    private ConexionBD() throws ConexionBDException {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            LOG.info("Conexión a LATAM_AeroOps establecida correctamente.");
        } catch (ClassNotFoundException | SQLException e) {
            LOG.log(Level.SEVERE, "No se pudo establecer conexión con la base de datos", e);
            throw new ConexionBDException("No se pudo establecer conexión con la base de datos: " + e.getMessage(), e);
        }
    }

    // Método estático para obtener la única instancia
    public static ConexionBD getInstancia() throws ConexionBDException {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    // Método para obtener el objeto Connection y hacer las consultas
    public Connection getConexion() {
        return conexion;
    }

    // Método para cerrar la conexión si es necesario
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "Error al cerrar conexión", e);
        }
    }
}
