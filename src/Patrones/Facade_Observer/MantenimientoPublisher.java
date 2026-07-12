package Patrones.Facade_Observer;
import java.util.ArrayList;
import java.util.List;

/* ¿Para qué sirve?: Esta clase es el publicador central del patrón Observer para los eventos de mantenimiento. Está armada como un Singleton, así que existe un solo publicador compartido por toda la aplicación. Permite que distintas pantallas se suscriban para enterarse automáticamente cada vez que se libera una aeronave
   Clases que la utilizan: MantenimientoFacade, OficialOperaciones_GUI
   Índice de Métodos: MantenimientoPublisher, getInstancia, suscribir, desuscribir, notificarLiberacion */
public class MantenimientoPublisher {
    private static MantenimientoPublisher instancia;
    private List<MantenimientoObserver> observadores = new ArrayList<>();

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un publicador nuevo con su lista de observadores vacía. Este constructor es privado a propósito, para que solo se pueda crear a través del método getInstancia()
    // Clases que lo usan: MantenimientoFacade, OficialOperaciones_GUI
    private MantenimientoPublisher() {}

    // ==========================================
    // MÉTODO PARA OBTENER EL ÚNICO PUBLICADOR
    // ==========================================
    // Descripción: Entrega la única instancia de MantenimientoPublisher que existe en todo el sistema. Si todavía no se ha creado ninguna, la crea en ese momento; si ya existe una, la reutiliza
    // Clases que lo usan: MantenimientoFacade, OficialOperaciones_GUI
    public static synchronized MantenimientoPublisher getInstancia() {
        if (instancia == null) {
            instancia = new MantenimientoPublisher();
        }
        return instancia;
    }

    // ==========================================
    // MÉTODO PARA SUSCRIBIRSE A LAS NOTIFICACIONES
    // ==========================================
    // Descripción: Agrega un nuevo observador a la lista, para que a partir de ahora también reciba el aviso cuando se libere una aeronave
    // Clases que lo usan: MantenimientoFacade, OficialOperaciones_GUI
    public void suscribir(MantenimientoObserver observador) {
        observadores.add(observador);
    }

    // ==========================================
    // MÉTODO PARA DEJAR DE RECIBIR NOTIFICACIONES
    // ==========================================
    // Descripción: Quita a un observador de la lista, para que ya no reciba más avisos de liberación de aeronaves
    // Clases que lo usan: MantenimientoFacade, OficialOperaciones_GUI
    public void desuscribir(MantenimientoObserver observador) {
        observadores.remove(observador);
    }

    // ==========================================
    // MÉTODO PARA AVISAR QUE SE LIBERÓ UNA AERONAVE
    // ==========================================
    // Descripción: Recorre a todos los observadores suscritos y les avisa, uno por uno, que la aeronave indicada ya quedó liberada del mantenimiento
    // Clases que lo usan: MantenimientoFacade, OficialOperaciones_GUI
    public void notificarLiberacion(String matricula) {
        for (MantenimientoObserver obs : observadores) {
            obs.onAeronaveLiberada(matricula);
        }
    }
}