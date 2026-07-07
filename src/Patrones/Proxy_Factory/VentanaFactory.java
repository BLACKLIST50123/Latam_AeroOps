package Patrones.Proxy_Factory;

import Clases.UsuarioSistema;
import javax.swing.JFrame;

public class VentanaFactory {
    // Recibimos el objeto completo
    public static JFrame crearVentana(UsuarioSistema usuarioLogueado) {
        String rol = usuarioLogueado.getRolAcceso().toUpperCase();
        
        if (rol.equals("OFICIAL")) {
            // Le pasas los datos al constructor del Oficial (Deberás actualizar el constructor del Oficial también)
            return new Interfaces.OficialOperaciones_GUI(usuarioLogueado.getIdEmpleado(), usuarioLogueado.getUsuario(), usuarioLogueado.getRolAcceso());
            
        } else if (rol.equals("TECNICO")) {
            // Le pasas los datos a tu ventana de Técnico
            return new Interfaces.TecnicoMantenimiento_GUI(usuarioLogueado.getIdEmpleado(), usuarioLogueado.getUsuario(), usuarioLogueado.getRolAcceso());
        }
        return null;
    }
}