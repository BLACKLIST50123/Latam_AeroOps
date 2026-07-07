package Patrones.Facade_Observer;
import java.util.ArrayList;
import java.util.List;

public class MantenimientoPublisher {
    private static MantenimientoPublisher instancia;
    private List<MantenimientoObserver> observadores = new ArrayList<>();

    private MantenimientoPublisher() {}

    // Singleton para que cualquier pantalla acceda al mismo notificador
    public static synchronized MantenimientoPublisher getInstancia() {
        if (instancia == null) {
            instancia = new MantenimientoPublisher();
        }
        return instancia;
    }

    // Métodos clásicos del patrón Observer
    public void suscribir(MantenimientoObserver observador) {
        observadores.add(observador);
    }

    public void desuscribir(MantenimientoObserver observador) {
        observadores.remove(observador);
    }

    // Alerta a todas las pantallas conectadas
    public void notificarLiberacion(String matricula) {
        for (MantenimientoObserver obs : observadores) {
            obs.onAeronaveLiberada(matricula);
        }
    }
}