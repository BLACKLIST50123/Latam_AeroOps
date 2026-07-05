package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;
import Patrones.State.IEstadoVuelo;

public class EstadoEnDemora implements IEstadoVuelo {

    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoAprobado());
        vuelo.setEstadoVuelo(EstadoVuelo.APROBADO.name());
        System.out.println("SOLUCIONADO: Vuelo " + vuelo.getCodVuelo() + " aprobado tras demora.");
    }

    @Override
    public void declararDemora(VueloOperativo vuelo) {
        System.out.println("INFO: El vuelo ya está en demora.");
    }

    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoCancelado());
        vuelo.setEstadoVuelo(EstadoVuelo.CANCELADO.name());
    }

    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: No se puede volar en estado de demora climática/técnica.");
    }

    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: Acción no permitida.");
    }

    @Override
    public String getNombreEstado() {
        return EstadoVuelo.EN_DEMORA.name();
    }
}