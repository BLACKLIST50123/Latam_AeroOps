package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;

/**
 * Estado terminal del ciclo de vida del vuelo. Igual que EstadoCancelado,
 * ninguna acción debería moverlo de aquí: el vuelo ya está cerrado y
 * documentado en el Logbook.
 */
public class EstadoCompletado implements IEstadoVuelo {

    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está COMPLETADO.");
    }

    @Override
    public void declararDemora(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está COMPLETADO.");
    }

    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está COMPLETADO, no se puede cancelar.");
    }

    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está COMPLETADO.");
    }

    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está COMPLETADO.");
    }

    @Override
    public void completarVuelo(VueloOperativo vuelo) {
        System.out.println("INFO: El Logbook de este vuelo ya fue cerrado.");
    }

    @Override
    public String getNombreEstado() {
        return EstadoVuelo.COMPLETADO.name();
    }
}
