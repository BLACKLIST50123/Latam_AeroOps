package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;
import Patrones.State.IEstadoVuelo;

public class EstadoPendienteDespacho implements IEstadoVuelo {

    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoAprobado());
        vuelo.setEstadoVuelo(EstadoVuelo.APROBADO.name());
        System.out.println("ÉXITO: Vuelo " + vuelo.getCodVuelo() + " aprobado para despacho.");
    }

    @Override
    public void declararDemora(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoEnDemora());
        vuelo.setEstadoVuelo(EstadoVuelo.EN_DEMORA.name());
        System.out.println("ALERTA: Vuelo " + vuelo.getCodVuelo() + " puesto en demora.");
    }

    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoCancelado());
        vuelo.setEstadoVuelo(EstadoVuelo.CANCELADO.name());
        System.out.println("CRÍTICO: Vuelo " + vuelo.getCodVuelo() + " ha sido cancelado (NO-GO).");
    }

    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: No se puede iniciar un vuelo que no ha sido aprobado por Despacho.");
    }

    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ni siquiera ha despegado.");
    }

    @Override
    public String getNombreEstado() {
        return EstadoVuelo.PENDIENTE_DESPACHO.name();
    }
}