package Patrones;
import javax.swing.JFrame;

public class VentanaFactory {
    
    public static JFrame crearVentana(String rol) {
        if (rol == null) return null;
        
        switch (rol.toUpperCase()) {
            case "OFICIAL":
                return new Interfaces.OficialOperaciones_GUI();
            case "TECNICO":
                return new Interfaces.TecnicoMantenimiento_GUI();
            default:
                return null;
        }
    }
}