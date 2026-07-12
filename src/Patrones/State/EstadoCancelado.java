package Patrones.State;

import Clases.VueloOperativo;
import Enumeradores.EstadoVuelo;

/* ¿Para qué sirve?: Esta clase representa el estado final de un vuelo que fue cancelado. Es una de las siete implementaciones del patrón State, y al ser un estado terminal, ninguna acción debería sacarlo de aquí
   Clases que la utilizan: VueloOperativo, EstadoVueloFactory
   Índice de Métodos: aprobarDespacho, declararDemora, cancelarVuelo, iniciarVuelo, finalizarVuelo, completarVuelo, getNombreEstado */
public class EstadoCancelado implements IEstadoVuelo {

    // ==========================================
    // MÉTODO PARA APROBAR EL DESPACHO
    // ==========================================
    // Descripción: No permite aprobar el despacho, porque un vuelo cancelado no puede ser aprobado, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void aprobarDespacho(VueloOperativo vuelo) {
        System.out.println("ERROR: Un vuelo cancelado no puede ser aprobado.");
    }

    // ==========================================
    // MÉTODO PARA DECLARAR DEMORA
    // ==========================================
    // Descripción: No permite declarar demora, porque un vuelo cancelado no puede entrar en demora, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void declararDemora(VueloOperativo vuelo) {
        System.out.println("ERROR: Un vuelo cancelado no puede entrar en demora.");
    }

    // ==========================================
    // MÉTODO PARA CANCELAR EL VUELO
    // ==========================================
    // Descripción: No hace ningún cambio, porque el vuelo ya se encuentra cancelado, y solo muestra un mensaje informativo
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void cancelarVuelo(VueloOperativo vuelo) {
        System.out.println("INFO: El vuelo ya se encuentra cancelado.");
    }

    // ==========================================
    // MÉTODO PARA INICIAR EL VUELO
    // ==========================================
    // Descripción: No permite iniciar el vuelo, porque los vuelos cancelados no despegan, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void iniciarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: Los vuelos cancelados no despegan.");
    }

    // ==========================================
    // MÉTODO PARA FINALIZAR EL VUELO
    // ==========================================
    // Descripción: No permite finalizar el vuelo, ya que la acción no está permitida en este estado, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void finalizarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: Acción no permitida.");
    }

    // ==========================================
    // MÉTODO PARA COMPLETAR EL VUELO
    // ==========================================
    // Descripción: No permite cerrar el Logbook, porque un vuelo cancelado no tiene Logbook que cerrar, y muestra un mensaje de error
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public void completarVuelo(VueloOperativo vuelo) {
        System.out.println("ERROR: Un vuelo cancelado no tiene Logbook que cerrar.");
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR EL NOMBRE DEL ESTADO
    // ==========================================
    // Descripción: Entrega el texto CANCELADO, que identifica a este estado
    // Clases que lo usan: VueloOperativo, EstadoVueloFactory
    @Override
    public String getNombreEstado() {
        return EstadoVuelo.CANCELADO.name();
    }
}