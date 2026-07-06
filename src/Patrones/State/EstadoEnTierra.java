package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;

public class EstadoEnTierra implements IEstadoVuelo {

    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya cumplió su ciclo, no se puede volver a aprobar.");
    }

    @Override
    public void declararDemora(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya aterrizó, no aplica demora.");
    }

    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: Un vuelo que ya aterrizó no se puede cancelar.");
    }

    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya aterrizó, no puede volver a despegar.");
    }

    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("INFO: El vuelo ya se encuentra en tierra.");
    }

    @Override
    public void completarVuelo(VueloOperativo vuelo) {
        // Se dispara cuando el Oficial cierra el Logbook (con o sin fallas)
        vuelo.setEstadoLogico(new EstadoCompletado());
        vuelo.setEstadoVuelo(EstadoVuelo.COMPLETADO.name());
        System.out.println("Vuelo " + vuelo.getCodVuelo() + " COMPLETADO. Logbook cerrado.");
    }

    @Override
    public String getNombreEstado() {
        return EstadoVuelo.EN_TIERRA.name();
    }
}
