package Patrones.Facade;
import ClasesDAO.MantenimientoDAO;
import Patrones.Observer.MantenimientoPublisher;

public class MantenimientoFacade {
    private MantenimientoDAO mantenimientoDAO;

    public MantenimientoFacade() {
        this.mantenimientoDAO = new MantenimientoDAO();
    }

    /**
     * Fachada unificada para procesar el despacho técnico de liberación
     * Devuelve true si se completó con éxito toda la operación.
     */
    public boolean liberarAeronaveAervicio(int idLogbook, String matricula, int idTecnico, String accionTomada) {
        // Aquí la fachada podría incluir validaciones adicionales de negocio antes de tocar el DAO
        if (accionTomada == null || accionTomada.trim().isEmpty()) {
            return false;
        }
        
        // Ejecuta la transacción en la BD
        boolean exito = mantenimientoDAO.registrarLiberacionAeronave(idLogbook, matricula, idTecnico, accionTomada);
        
        // ¡AQUÍ ENTRA EL OBSERVER!
        if (exito) {
            MantenimientoPublisher.getInstancia().notificarLiberacion(matricula);
        }
        
        return exito;
    }
}