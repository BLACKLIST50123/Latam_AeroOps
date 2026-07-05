package Clases;

public class ManifiestoCombustible {
    private double combustibleRutaKg;
    private double combustibleReservaKg;

    public ManifiestoCombustible() {}

    public double getCombustibleRutaKg() { return combustibleRutaKg; }
    public void setCombustibleRutaKg(double combustibleRutaKg) { this.combustibleRutaKg = combustibleRutaKg; }

    public double getCombustibleReservaKg() { return combustibleReservaKg; }
    public void setCombustibleReservaKg(double combustibleReservaKg) { this.combustibleReservaKg = combustibleReservaKg; }

    public double calcularCombustibleTotal() {
        return combustibleRutaKg + combustibleReservaKg;
    }
}
