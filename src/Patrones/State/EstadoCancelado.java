package Patrones.State;

import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;

public class EstadoCancelado implements IEstadoVuelo {

    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        System.out.println("ERROR: Un vuelo cancelado no puede ser aprobado.");
    }

    @Override
    public void declararDemora(VueloOperativo vuelo) {
        System.out.println("ERROR: Un vuelo cancelado no puede entrar en demora.");
    }

    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        System.out.println("INFO: El vuelo ya se encuentra cancelado.");
    }

    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: Los vuelos cancelados no despegan.");
    }

    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: Acción no permitida.");
    }

    @Override
    public String getNombreEstado() {
        return EstadoVuelo.CANCELADO.name();
    }
}