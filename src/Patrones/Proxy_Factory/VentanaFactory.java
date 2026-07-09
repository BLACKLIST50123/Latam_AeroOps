package Patrones.Proxy_Factory;

import Clases.UsuarioSistema;
import javax.swing.JFrame;

public class VentanaFactory {
    // Recibimos el objeto completo
    public static JFrame crearVentana(UsuarioSistema usuarioLogueado) {
        String rol = usuarioLogueado.getRolAcceso().toUpperCase();
        
        if (rol.equals("OFICIAL")) {
            return new Interfaces.OficialOperaciones_GUI(usuarioLogueado.getIdEmpleado(), usuarioLogueado.getUsuario(), usuarioLogueado.getRolAcceso(), usuarioLogueado.getNombreCompleto());
            
        } else if (rol.equals("TECNICO")) {
            return new Interfaces.TecnicoMantenimiento_GUI(usuarioLogueado.getIdEmpleado(), usuarioLogueado.getUsuario(), usuarioLogueado.getRolAcceso(), usuarioLogueado.getNombreCompleto());
        }
        return null;
    }
}