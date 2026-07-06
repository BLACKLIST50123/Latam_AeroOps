package Patrones.Observer;

public interface MantenimientoObserver {
    // Este método se ejecutará automáticamente en los suscritos cuando pase algo
    void onAeronaveLiberada(String matricula);
}