package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;

/* ¿Para qué sirve?: Esta clase representa el estado de un vuelo que ya aterrizó y está en tierra, listo para que se cierre su Logbook. Es una de las siete implementaciones del patrón State, y define qué pasa cuando se le pide una acción a un vuelo que está en este estado
   Clases que la utilizan: VueloOperativo, EstadoVueloFactory
   Índice de Métodos: aprobarDespacho, declararDemora, cancelarVuelo, iniciarVuelo, finalizarVuelo, completarVuelo, getNombreEstado */
public class EstadoEnTierra implements IEstadoVuelo {

    // ==========================================
    // MÉTODO PARA APROBAR EL DESPACHO
    // ==========================================
    // Descripción: No permite aprobar el despacho, porque el vuelo ya cumplió su ciclo, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya cumplió su ciclo, no se puede volver a aprobar.");
    }

    // ==========================================
    // MÉTODO PARA DECLARAR DEMORA
    // ==========================================
    // Descripción: No permite declarar demora, porque el vuelo ya aterrizó, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void declararDemora(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya aterrizó, no aplica demora.");
    }

    // ==========================================
    // MÉTODO PARA CANCELAR EL VUELO
    // ==========================================
    // Descripción: No permite cancelar el vuelo, porque un vuelo que ya aterrizó no se puede cancelar, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: Un vuelo que ya aterrizó no se puede cancelar.");
    }

    // ==========================================
    // MÉTODO PARA INICIAR EL VUELO
    // ==========================================
    // Descripción: No permite iniciar el vuelo de nuevo, porque ya aterrizó y no puede volver a despegar, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya aterrizó, no puede volver a despegar.");
    }

    // ==========================================
    // MÉTODO PARA FINALIZAR EL VUELO
    // ==========================================
    // Descripción: No hace ningún cambio, porque el vuelo ya se encuentra en tierra, y solo muestra un mensaje informativo
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("INFO: El vuelo ya se encuentra en tierra.");
    }

    // ==========================================
    // MÉTODO PARA COMPLETAR EL VUELO
    // ==========================================
    // Descripción: Cambia el vuelo al estado Completado, ya que un vuelo en tierra puede cerrar su Logbook y terminar su ciclo, sin importar si se reportó alguna falla
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void completarVuelo(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoCompletado());
        vuelo.setEstadoVuelo(EstadoVuelo.COMPLETADO.name());
        System.out.println("Vuelo " + vuelo.getCodVuelo() + " COMPLETADO. Logbook cerrado.");
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR EL NOMBRE DEL ESTADO
    // ==========================================
    // Descripción: Entrega el texto EN_TIERRA, que identifica a este estado
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public String getNombreEstado() {
        return EstadoVuelo.EN_TIERRA.name();
    }
}
