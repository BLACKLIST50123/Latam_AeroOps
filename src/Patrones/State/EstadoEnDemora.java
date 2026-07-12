package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;
import Patrones.State.IEstadoVuelo;

/* ¿Para qué sirve?: Esta clase representa el estado de un vuelo que quedó en demora, por ejemplo por mal clima o algún imprevisto técnico. Es una de las siete implementaciones del patrón State, y define qué pasa cuando se le pide una acción a un vuelo que está en este estado
   Clases que la utilizan: VueloOperativo, EstadoVueloFactory
   Índice de Métodos: aprobarDespacho, declararDemora, cancelarVuelo, iniciarVuelo, finalizarVuelo, completarVuelo, getNombreEstado */
public class EstadoEnDemora implements IEstadoVuelo {

    // ==========================================
    // MÉTODO PARA APROBAR EL DESPACHO
    // ==========================================
    // Descripción: Cambia el vuelo al estado Aprobado, ya que un vuelo en demora se puede resolver y quedar listo para despegar
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoAprobado());
        vuelo.setEstadoVuelo(EstadoVuelo.APROBADO.name());
        System.out.println("SOLUCIONADO: Vuelo " + vuelo.getCodVuelo() + " aprobado tras demora.");
    }

    // ==========================================
    // MÉTODO PARA DECLARAR DEMORA
    // ==========================================
    // Descripción: No hace ningún cambio, porque el vuelo ya se encuentra en demora, y solo muestra un mensaje informativo
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void declararDemora(VueloOperativo vuelo) {
        System.out.println("INFO: El vuelo ya está en demora.");
    }

    // ==========================================
    // MÉTODO PARA CANCELAR EL VUELO
    // ==========================================
    // Descripción: Cambia el vuelo al estado Cancelado, ya que un vuelo en demora todavía se puede cancelar
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoCancelado());
        vuelo.setEstadoVuelo(EstadoVuelo.CANCELADO.name());
    }

    // ==========================================
    // MÉTODO PARA INICIAR EL VUELO
    // ==========================================
    // Descripción: No permite iniciar el vuelo, porque no se puede volar mientras está en demora, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: No se puede volar en estado de demora climática/técnica.");
    }

    // ==========================================
    // MÉTODO PARA FINALIZAR EL VUELO
    // ==========================================
    // Descripción: No permite finalizar el vuelo, porque todavía no ha despegado, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: Acción no permitida.");
    }

    // ==========================================
    // MÉTODO PARA COMPLETAR EL VUELO
    // ==========================================
    // Descripción: No permite cerrar el Logbook, porque el vuelo está en demora, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void completarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo está en demora, no se puede cerrar Logbook.");
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR EL NOMBRE DEL ESTADO
    // ==========================================
    // Descripción: Entrega el texto EN_DEMORA, que identifica a este estado
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public String getNombreEstado() {
        return EstadoVuelo.EN_DEMORA.name();
    }
}