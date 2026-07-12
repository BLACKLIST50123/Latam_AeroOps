package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;
import Patrones.State.IEstadoVuelo;

/* ¿Para qué sirve?: Esta clase representa el estado inicial de un vuelo, cuando todavía no ha sido despachado. Es una de las siete implementaciones del patrón State para el ciclo de vida del vuelo, y define qué pasa cuando se le pide una acción a un vuelo que está en este estado
   Clases que la utilizan: VueloOperativo, EstadoVueloFactory
   Índice de Métodos: aprobarDespacho, declararDemora, cancelarVuelo, iniciarVuelo, finalizarVuelo, completarVuelo, getNombreEstado */
public class EstadoPendienteDespacho implements IEstadoVuelo {

    // ==========================================
    // MÉTODO PARA APROBAR EL DESPACHO
    // ==========================================
    // Descripción: Cambia el vuelo al estado Aprobado, ya que un vuelo pendiente sí puede pasar a estar aprobado
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoAprobado());
        vuelo.setEstadoVuelo(EstadoVuelo.APROBADO.name());
        System.out.println("ÉXITO: Vuelo " + vuelo.getCodVuelo() + " aprobado para despacho.");
    }

    // ==========================================
    // MÉTODO PARA DECLARAR DEMORA
    // ==========================================
    // Descripción: Cambia el vuelo al estado En Demora, ya que un vuelo pendiente puede quedar demorado antes de despachar
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void declararDemora(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoEnDemora());
        vuelo.setEstadoVuelo(EstadoVuelo.EN_DEMORA.name());
        System.out.println("ALERTA: Vuelo " + vuelo.getCodVuelo() + " puesto en demora.");
    }

    // ==========================================
    // MÉTODO PARA CANCELAR EL VUELO
    // ==========================================
    // Descripción: Cambia el vuelo al estado Cancelado, ya que un vuelo pendiente todavía se puede cancelar
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoCancelado());
        vuelo.setEstadoVuelo(EstadoVuelo.CANCELADO.name());
        System.out.println("CRÍTICO: Vuelo " + vuelo.getCodVuelo() + " ha sido cancelado (NO-GO).");
    }

    // ==========================================
    // MÉTODO PARA INICIAR EL VUELO
    // ==========================================
    // Descripción: No permite iniciar el vuelo, porque todavía no ha sido aprobado por Despacho, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: No se puede iniciar un vuelo que no ha sido aprobado por Despacho.");
    }

    // ==========================================
    // MÉTODO PARA FINALIZAR EL VUELO
    // ==========================================
    // Descripción: No permite finalizar el vuelo, porque ni siquiera ha despegado, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ni siquiera ha despegado.");
    }

    // ==========================================
    // MÉTODO PARA COMPLETAR EL VUELO
    // ==========================================
    // Descripción: No permite cerrar el Logbook, porque el vuelo ni siquiera ha despegado, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void completarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ni siquiera ha despegado, no se puede cerrar Logbook.");
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR EL NOMBRE DEL ESTADO
    // ==========================================
    // Descripción: Entrega el texto PENDIENTE_DESPACHO, que identifica a este estado
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public String getNombreEstado() {
        return EstadoVuelo.PENDIENTE_DESPACHO.name();
    }
}