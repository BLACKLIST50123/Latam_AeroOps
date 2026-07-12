package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;

/* ¿Para qué sirve?: Esta clase representa el estado de un vuelo que ya despegó y está en el aire. Es una de las siete implementaciones del patrón State, y define qué pasa cuando se le pide una acción a un vuelo que está en este estado
   Clases que la utilizan: VueloOperativo, EstadoVueloFactory
   Índice de Métodos: aprobarDespacho, declararDemora, cancelarVuelo, iniciarVuelo, finalizarVuelo, completarVuelo, getNombreEstado */
public class EstadoEnVuelo implements IEstadoVuelo {

    // ==========================================
    // MÉTODO PARA APROBAR EL DESPACHO
    // ==========================================
    // Descripción: No permite volver a aprobar el despacho, porque el vuelo ya está en el aire, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está en el aire, no se puede volver a aprobar.");
    }

    // ==========================================
    // MÉTODO PARA DECLARAR DEMORA
    // ==========================================
    // Descripción: No permite declarar demora, porque el vuelo ya está en el aire, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void declararDemora(VueloOperativo vuelo) {
        System.out.println("ERROR: No se puede declarar demora a un vuelo que ya está en el aire.");
    }

    // ==========================================
    // MÉTODO PARA CANCELAR EL VUELO
    // ==========================================
    // Descripción: No permite cancelar el vuelo, porque un vuelo que ya está en el aire no se puede cancelar, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: Un vuelo en el aire no se puede cancelar (NO-GO ya no aplica).");
    }

    // ==========================================
    // MÉTODO PARA INICIAR EL VUELO
    // ==========================================
    // Descripción: No hace ningún cambio, porque el vuelo ya se encuentra en el aire, y solo muestra un mensaje informativo
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("INFO: El vuelo ya se encuentra en el aire.");
    }

    // ==========================================
    // MÉTODO PARA FINALIZAR EL VUELO
    // ==========================================
    // Descripción: Cambia el vuelo al estado En Tierra, ya que un vuelo en el aire puede aterrizar y quedar listo para el cierre del Logbook
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        vuelo.setEstadoLogico(new EstadoEnTierra());
        vuelo.setEstadoVuelo(EstadoVuelo.EN_TIERRA.name());
        System.out.println("Vuelo " + vuelo.getCodVuelo() + " en tierra, listo para cierre de Logbook.");
    }

    // ==========================================
    // MÉTODO PARA COMPLETAR EL VUELO
    // ==========================================
    // Descripción: No permite cerrar el Logbook, porque el vuelo debe aterrizar primero, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void completarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo debe aterrizar (IN) antes de cerrar Logbook.");
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR EL NOMBRE DEL ESTADO
    // ==========================================
    // Descripción: Entrega el texto EN_VUELO, que identifica a este estado
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public String getNombreEstado() {
        return EstadoVuelo.EN_VUELO.name();
    }
}
