package operaciondevuelos;

/* ¿Para qué sirve?: Esta es la clase con el método principal (main) del proyecto, la que Java ejecuta primero al arrancar la aplicación. En este proyecto el arranque real ocurre desde Login_GUI (así está configurado en las propiedades del proyecto), pero esta clase se deja funcionando por si alguien decide ejecutar el programa directamente desde aquí
   Clases que la utilizan: Punto de entrada de la aplicación (no es llamada por ninguna otra clase, es la que arranca todo)
   Índice de Métodos: main */
public class OperacionDeVuelos {

    // ==========================================
    // MÉTODO PRINCIPAL (PUNTO DE ENTRADA)
    // ==========================================
    // Descripción: Abre la pantalla de inicio de sesión (Login_GUI) y la deja visible, arrancando así toda la aplicación
    // Clases que lo usan: Punto de entrada de la aplicación
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Interfaces.Login_GUI().setVisible(true));
    }
    
}
