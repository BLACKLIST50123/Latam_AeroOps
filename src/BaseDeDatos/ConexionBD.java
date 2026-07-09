package BaseDeDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // 1. Instancia estática y privada (Singleton)
    private static ConexionBD instancia;
    private Connection conexion;
    
    // Credenciales de PostgreSQL
    private final String URL = "jdbc:postgresql://localhost:5432/LATAM_AeroOps";
    private final String USUARIO = "postgres"; // Cambia esto por tu usuario de pgAdmin
    private final String CLAVE = "50123";       // Cambia esto por tu clave de pgAdmin
    
    // 2. Constructor privado para evitar 'new ConexionBD()'
    private ConexionBD() {
        try {
            // Registrar el driver de PostgreSQL (Asegúrate de agregar el .jar de PostgreSQL a las Libraries de NetBeans)
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("¡Conexión a LATAM_AeroOps exitosa!");
        } catch (ClassNotFoundException | SQLException e) {
            // parent = null: todavía no existe ninguna ventana en este punto del arranque
            ElementosDiseño.NotificacionDialog.error(null, "Error de conexión: " + e.getMessage(), "Error de Base de Datos");
        }
    }
    
    // 3. Método estático para obtener la única instancia
    public static ConexionBD getInstancia() {
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
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}