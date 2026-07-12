package Patrones.Proxy_Factory;

import Clases.UsuarioSistema;
import javax.swing.JFrame;

/* ¿Para qué sirve?: Esta clase se encarga de crear la ventana principal correcta según el rol del usuario que acaba de iniciar sesión (patrón Factory). Si el usuario es Oficial de Operaciones, crea esa pantalla; si es Técnico de Mantenimiento, crea la otra
   Clases que la utilizan: Login_GUI
   Índice de Métodos: crearVentana */
public class VentanaFactory {
    // Recibimos el objeto completo
    // ==========================================
    // MÉTODO PARA CREAR LA VENTANA SEGÚN EL ROL
    // ==========================================
    // Descripción: Recibe el usuario que acaba de iniciar sesión y revisa su rol de acceso. Si el rol es OFICIAL, crea y entrega la pantalla de Oficial de Operaciones; si es TECNICO, crea y entrega la pantalla de Técnico de Mantenimiento; si el rol no coincide con ninguno de los dos, entrega un valor vacío (nulo)
    // Clases que lo usan: Login_GUI
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