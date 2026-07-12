package BaseDeDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* ¿Para qué sirve?: Esta clase se encarga de abrir y mantener la conexión con la base de datos PostgreSQL del sistema. Está armada como un Singleton, es decir, en toda la aplicación solo existe una sola conexión compartida, en vez de abrir una nueva cada vez que se necesita. Esta clase pertenece a la capa de datos y no debe mostrar mensajes de error directamente en pantalla; cuando algo falla, avisa lanzando una ConexionBDException, y es la parte del programa que la reciba quien decide cómo mostrarle el problema al usuario
   Clases que la utilizan: AeronaveDAO, AutenticacionDAO, DespachoDAO, MantenimientoDAO, VueloOperativoDAO, OperacionDeVuelos
   Índice de Métodos: ConexionBD, getInstancia, getConexion, cerrarConexion */
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

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara la conexión hacia la base de datos PostgreSQL usando la dirección, el usuario y la clave configurados. Este constructor es privado a propósito, para que nadie pueda crear conexiones sueltas por fuera del Singleton; si algo falla al conectarse, se lanza una ConexionBDException
    // Clases que lo usan: AeronaveDAO, AutenticacionDAO, DespachoDAO, MantenimientoDAO, VueloOperativoDAO, OperacionDeVuelos
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

    // ==========================================
    // MÉTODO PARA OBTENER LA ÚNICA CONEXIÓN
    // ==========================================
    // Descripción: Entrega la única instancia de ConexionBD que existe en todo el sistema. Si todavía no se ha creado ninguna, la crea en ese momento; si ya existe una, simplemente la reutiliza
    // Clases que lo usan: AeronaveDAO, AutenticacionDAO, DespachoDAO, MantenimientoDAO, VueloOperativoDAO, OperacionDeVuelos
    public static ConexionBD getInstancia() throws ConexionBDException {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    // ==========================================
    // MÉTODO PARA OBTENER EL OBJETO DE CONEXIÓN
    // ==========================================
    // Descripción: Entrega el objeto Connection ya conectado, que es el que usan las clases DAO para poder enviar sus consultas SQL a la base de datos
    // Clases que lo usan: AeronaveDAO, AutenticacionDAO, DespachoDAO, MantenimientoDAO, VueloOperativoDAO, OperacionDeVuelos
    public Connection getConexion() {
        return conexion;
    }

    // ==========================================
    // MÉTODO PARA CERRAR LA CONEXIÓN
    // ==========================================
    // Descripción: Cierra la conexión con la base de datos si es que todavía sigue abierta, para liberar los recursos usados
    // Clases que lo usan: AeronaveDAO, AutenticacionDAO, DespachoDAO, MantenimientoDAO, VueloOperativoDAO, OperacionDeVuelos
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
