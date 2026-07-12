package ClasesDAO;

import BaseDeDatos.ConexionBD;
import BaseDeDatos.ConexionBDException;
import Clases.UsuarioSistema;
import Patrones.Proxy_Factory.IAutenticacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutenticacionDAO implements IAutenticacion {

    private static final Logger LOG = Logger.getLogger(AutenticacionDAO.class.getName());

    @Override
    public UsuarioSistema hacerLogin(String user, String pass) {
        // JOIN con empleados para traer el nombre real (antes solo se traía el usuario de acceso)
        String query = "SELECT us.id_empleado, us.usuario, us.rol_acceso, e.nombre " +
                       "FROM usuarios_sistema us " +
                       "JOIN empleados e ON us.id_empleado = e.id_empleado " +
                       "WHERE us.usuario = ? AND us.contrasena = ?";
        
        try {
            Connection con = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                UsuarioSistema usuarioLogueado = new UsuarioSistema();
                // 2. Seteamos todos los valores al objeto
                usuarioLogueado.setIdEmpleado(rs.getInt("id_empleado"));
                usuarioLogueado.setUsuario(rs.getString("usuario"));
                usuarioLogueado.setRolAcceso(rs.getString("rol_acceso"));
                usuarioLogueado.setNombreCompleto(rs.getString("nombre"));
                
                // 3. Retornamos EL OBJETO COMPLETO, no solo el String
                return usuarioLogueado;
            }
        } catch (SQLException | ConexionBDException e) {
            LOG.log(Level.SEVERE, "Error en la autenticación", e);
        }
        
        // Si falla, retornamos nulo
        return null;
    }
}        
