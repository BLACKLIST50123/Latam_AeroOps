package Patrones.Facade_Observer;

/* ¿Para qué sirve?: Esta es una interfaz del patrón Observer. Define el contrato que debe cumplir cualquier pantalla u objeto que quiera enterarse cuando se libera una aeronave del mantenimiento
   Clases que la utilizan: MantenimientoPublisher, OficialOperaciones_GUI
   Índice de Métodos: onAeronaveLiberada (solo se declara aquí, cada clase que implementa esta interfaz escribe su propia versión) */

public interface MantenimientoObserver {
    // Este método se ejecutará automáticamente en los suscritos cuando pase algo
    void onAeronaveLiberada(String matricula);
}