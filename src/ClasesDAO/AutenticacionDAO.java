package ClasesDAO;

import BaseDeDatos.ConexionBD;
import Clases.UsuarioSistema;
import Patrones.IAutenticacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacionDAO implements IAutenticacion {

    @Override
    public String hacerLogin(String user, String pass) {
        // Solo pedimos a la BD lo que realmente necesitamos instanciar ahora
        String query = "SELECT usuario, rol_acceso FROM usuarios_sistema WHERE usuario = ? AND contrasena = ?";
        
        try {
            // 1. Obtenemos la conexión del Singleton
            Connection con = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            
            // 2. Ejecutamos la consulta directo (Sin métodos inventados)
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                // 3. Mapeamos la data de la BD a nuestra clase de negocio
                UsuarioSistema usuarioLogueado = new UsuarioSistema();
                usuarioLogueado.setUsuario(rs.getString("usuario"));
                usuarioLogueado.setRolAcceso(rs.getString("rol_acceso"));
                
                // 4. Retornamos el rol recuperado de la entidad (ej: "Oficial" o "Tecnico")
                return usuarioLogueado.getRolAcceso();
            }
        } catch (SQLException e) {
            System.out.println("Error en la autenticación: " + e.getMessage());
        }
        
        // Si no entra al if (rs.next()), significa que no existe en la BD
        return "DENEGADO";
    }
}