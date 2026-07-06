package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;

public class EstadoEnVuelo implements IEstadoVuelo {

    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está en el aire, no se puede volver a aprobar.");
    }

    @Override
    public void declararDemora(VueloOperativo vuelo) {
        System.out.println("ERROR: No se puede declarar demora a un vuelo que ya está en el aire.");
    }

    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: Un vuelo en el aire no se puede cancelar (NO-GO ya no aplica).");
    }

    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("INFO: El vuelo ya se encuentra en el aire.");
    }

    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        // Se dispara con el OOOI "IN" (llegó y se parqueó en la manga)
        vuelo.setEstadoLogico(new EstadoEnTierra());
        vuelo.setEstadoVuelo(EstadoVuelo.EN_TIERRA.name());
        System.out.println("Vuelo " + vuelo.getCodVuelo() + " en tierra, listo para cierre de Logbook.");
    }

    @Override
    public void completarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo debe aterrizar (IN) antes de cerrar Logbook.");
    }

    @Override
    public String getNombreEstado() {
        return EstadoVuelo.EN_VUELO.name();
    }
}
