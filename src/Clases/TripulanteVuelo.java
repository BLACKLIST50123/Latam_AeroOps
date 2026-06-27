package Clases;

public class TripulanteVuelo extends Empleado {
    public TripulanteVuelo() {
        super();
    }
    
    // ¡ESTO ES LO QUE SE VERÁ EN EL COMBOBOX!
    @Override
    public String toString() {
        return this.nombre + " (" + this.cargoBase + ")";
    }
}