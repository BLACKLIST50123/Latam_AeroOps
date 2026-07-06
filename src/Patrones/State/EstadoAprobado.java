package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;
import Patrones.State.IEstadoVuelo;

public class EstadoAprobado implements IEstadoVuelo {

    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        System.out.println("INFO: El vuelo ya se encuentra aprobado.");
    }

    @Override
    public void declararDemora(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoEnDemora());
        vuelo.setEstadoVuelo(EstadoVuelo.EN_DEMORA.name());
        System.out.println("ALERTA: Vuelo " + vuelo.getCodVuelo() + " retrocedido a DEMORA por imprevisto.");
    }

    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoCancelado());
        vuelo.setEstadoVuelo(EstadoVuelo.CANCELADO.name());
    }

    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        // BUG corregido: antes solo cambiaba el texto y dejaba el objeto de estado
        // en EstadoAprobado. Ahora sí transiciona de verdad a EstadoEnVuelo.
        vuelo.setEstadoLogico(new EstadoEnVuelo());
        vuelo.setEstadoVuelo(EstadoVuelo.EN_VUELO.name());
        System.out.println("Vuelo " + vuelo.getCodVuelo() + " en el aire.");
    }

    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo debe despegar primero.");
    }

    @Override
    public void completarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo debe completar su ciclo OOOI antes de cerrar Logbook.");
    }

    @Override
    public String getNombreEstado() {
        return EstadoVuelo.APROBADO.name();
    }
}