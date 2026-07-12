package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;
import Patrones.State.IEstadoVuelo;

/* ¿Para qué sirve?: Esta clase representa el estado de un vuelo que ya fue aprobado para despachar, pero que todavía no ha despegado. Es una de las siete implementaciones del patrón State, y define qué pasa cuando se le pide una acción a un vuelo que está en este estado
   Clases que la utilizan: VueloOperativo, EstadoVueloFactory
   Índice de Métodos: aprobarDespacho, declararDemora, cancelarVuelo, iniciarVuelo, finalizarVuelo, completarVuelo, getNombreEstado */
public class EstadoAprobado implements IEstadoVuelo {

    // ==========================================
    // MÉTODO PARA APROBAR EL DESPACHO
    // ==========================================
    // Descripción: No hace ningún cambio, porque el vuelo ya se encuentra aprobado, y solo muestra un mensaje informativo
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        System.out.println("INFO: El vuelo ya se encuentra aprobado.");
    }

    // ==========================================
    // MÉTODO PARA DECLARAR DEMORA
    // ==========================================
    // Descripción: Cambia el vuelo de vuelta al estado En Demora, por si surge un imprevisto después de haber sido aprobado
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void declararDemora(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoEnDemora());
        vuelo.setEstadoVuelo(EstadoVuelo.EN_DEMORA.name());
        System.out.println("ALERTA: Vuelo " + vuelo.getCodVuelo() + " retrocedido a DEMORA por imprevisto.");
    }

    // ==========================================
    // MÉTODO PARA CANCELAR EL VUELO
    // ==========================================
    // Descripción: Cambia el vuelo al estado Cancelado, ya que un vuelo aprobado todavía se puede cancelar antes de despegar
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoCancelado());
        vuelo.setEstadoVuelo(EstadoVuelo.CANCELADO.name());
    }

    // ==========================================
    // MÉTODO PARA INICIAR EL VUELO
    // ==========================================
    // Descripción: Cambia el vuelo al estado En Vuelo, ya que un vuelo aprobado sí puede despegar
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoEnVuelo());
        vuelo.setEstadoVuelo(EstadoVuelo.EN_VUELO.name());
        System.out.println("Vuelo " + vuelo.getCodVuelo() + " en el aire.");
    }

    // ==========================================
    // MÉTODO PARA FINALIZAR EL VUELO
    // ==========================================
    // Descripción: No permite finalizar el vuelo, porque primero debe despegar, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo debe despegar primero.");
    }

    // ==========================================
    // MÉTODO PARA COMPLETAR EL VUELO
    // ==========================================
    // Descripción: No permite cerrar el Logbook, porque el vuelo debe completar su ciclo OOOI primero, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void completarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo debe completar su ciclo OOOI antes de cerrar Logbook.");
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR EL NOMBRE DEL ESTADO
    // ==========================================
    // Descripción: Entrega el texto APROBADO, que identifica a este estado
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public String getNombreEstado() {
        return EstadoVuelo.APROBADO.name();
    }
}