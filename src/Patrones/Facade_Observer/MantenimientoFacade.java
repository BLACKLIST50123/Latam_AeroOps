package Patrones.Facade_Observer;
import ClasesDAO.MantenimientoDAO;

/* ¿Para qué sirve?: Esta clase actúa como una fachada (patrón Facade) que simplifica el proceso de liberar una aeronave que salió de mantenimiento. Por dentro coordina al DAO de mantenimiento para guardar los datos, y al publicador de eventos (Observer) para avisar a las pantallas conectadas que la aeronave ya quedó libre
   Clases que la utilizan: TecnicoMantenimiento_GUI
   Índice de Métodos: MantenimientoFacade, liberarAeronaveAServicio */
public class MantenimientoFacade {
    private MantenimientoDAO mantenimientoDAO;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara la fachada creando por dentro el DAO de mantenimiento que va a usar para guardar los datos
    // Clases que lo usan: TecnicoMantenimiento_GUI
    public MantenimientoFacade() {
        this.mantenimientoDAO = new MantenimientoDAO();
    }

    // ==========================================
    // MÉTODO PARA LIBERAR UNA AERONAVE
    // ==========================================
    // Descripción: Revisa primero que se haya escrito la acción tomada por el técnico. Si todo está bien, guarda la liberación en la base de datos a través del DAO, y si se guardó con éxito, avisa a todas las pantallas suscritas (a través del Observer) que esa aeronave ya está disponible de nuevo. Entrega verdadero si todo el proceso salió bien
    // Clases que lo usan: TecnicoMantenimiento_GUI
    public boolean liberarAeronaveAServicio(int idLogbook, String matricula, int idTecnico, String accionTomada, String firmaTecnica) {
        if (accionTomada == null || accionTomada.trim().isEmpty()) {
            return false;
        }
        
        boolean exito = mantenimientoDAO.registrarLiberacionAeronave(idLogbook, matricula, idTecnico, accionTomada, firmaTecnica);
        
        if (exito) {
            MantenimientoPublisher.getInstancia().notificarLiberacion(matricula);
        }
        
        return exito;
    }
}