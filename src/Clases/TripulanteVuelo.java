package Clases;

/* ¿Para qué sirve?: Este es el modelo que representa a un tripulante asignado a un vuelo. Hereda los datos generales de un empleado y agrega la forma en que se muestra su nombre en las listas desplegables
   Clases que la utilizan: OficialOperaciones_GUI, VueloOperativoBuilder
   Índice de Métodos: TripulanteVuelo, toString */
public class TripulanteVuelo extends Empleado {
    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo TripulanteVuelo listo para usarse
    // Clases que lo usan: OficialOperaciones_GUI, VueloOperativoBuilder
    public TripulanteVuelo() {
        super();
    }
    
    // ¡ESTO ES LO QUE SE VERÁ EN EL COMBOBOX!
    // ==========================================
    // MÉTODO PARA MOSTRAR EL TRIPULANTE COMO TEXTO
    // ==========================================
    // Descripción: Arma el texto que se muestra en el combobox, juntando el nombre del tripulante con su cargo base
    // Clases que lo usan: OficialOperaciones_GUI, VueloOperativoBuilder
    @Override
    public String toString() {
        return this.nombre + " (" + this.cargoBase + ")";
    }
}