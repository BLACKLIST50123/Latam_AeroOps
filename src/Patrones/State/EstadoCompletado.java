package Patrones.State;
import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;

/* ¿Para qué sirve?: Esta clase representa el estado final de un vuelo que ya terminó todo su ciclo y tiene su Logbook cerrado. Es una de las siete implementaciones del patrón State. Al igual que EstadoCancelado, ninguna acción debería sacarlo de aquí, porque el vuelo ya quedó documentado y cerrado
   Clases que la utilizan: VueloOperativo, EstadoVueloFactory
   Índice de Métodos: aprobarDespacho, declararDemora, cancelarVuelo, iniciarVuelo, finalizarVuelo, completarVuelo, getNombreEstado */
public class EstadoCompletado implements IEstadoVuelo {

    // ==========================================
    // MÉTODO PARA APROBAR EL DESPACHO
    // ==========================================
    // Descripción: No permite ningún cambio, porque el vuelo ya está completado, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está COMPLETADO.");
    }

    // ==========================================
    // MÉTODO PARA DECLARAR DEMORA
    // ==========================================
    // Descripción: No permite ningún cambio, porque el vuelo ya está completado, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void declararDemora(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está COMPLETADO.");
    }

    // ==========================================
    // MÉTODO PARA CANCELAR EL VUELO
    // ==========================================
    // Descripción: No permite cancelar el vuelo, porque ya está completado, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está COMPLETADO, no se puede cancelar.");
    }

    // ==========================================
    // MÉTODO PARA INICIAR EL VUELO
    // ==========================================
    // Descripción: No permite ningún cambio, porque el vuelo ya está completado, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está COMPLETADO.");
    }

    // ==========================================
    // MÉTODO PARA FINALIZAR EL VUELO
    // ==========================================
    // Descripción: No permite ningún cambio, porque el vuelo ya está completado, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: El vuelo ya está COMPLETADO.");
    }

    // ==========================================
    // MÉTODO PARA COMPLETAR EL VUELO
    // ==========================================
    // Descripción: No hace ningún cambio, porque el Logbook de este vuelo ya fue cerrado antes, y solo muestra un mensaje informativo
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void completarVuelo(VueloOperativo vuelo) {
        System.out.println("INFO: El Logbook de este vuelo ya fue cerrado.");
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR EL NOMBRE DEL ESTADO
    // ==========================================
    // Descripción: Entrega el texto COMPLETADO, que identifica a este estado
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public String getNombreEstado() {
        return EstadoVuelo.COMPLETADO.name();
    }
}
