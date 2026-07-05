package Clases;

public class HojaDeCarga {
    private double pesoPasajeros;
    private double pesoEquipaje;
    private double pesoCarga;
    private double pesoCombustible;

    public HojaDeCarga() {}

    public double getPesoPasajeros() { return pesoPasajeros; }
    public void setPesoPasajeros(double pesoPasajeros) { this.pesoPasajeros = pesoPasajeros; }

    public double getPesoEquipaje() { return pesoEquipaje; }
    public void setPesoEquipaje(double pesoEquipaje) { this.pesoEquipaje = pesoEquipaje; }

    public double getPesoCarga() { return pesoCarga; }
    public void setPesoCarga(double pesoCarga) { this.pesoCarga = pesoCarga; }

    public double getPesoCombustible() { return pesoCombustible; }
    public void setPesoCombustible(double pesoCombustible) { this.pesoCombustible = pesoCombustible; }

    /**
     * Suma los cuatro componentes de peso de la hoja de carga.
     * El peso de combustible debe venir precargado desde
     * ManifiestoCombustible.calcularCombustibleTotal() antes de invocar este método.
     */
    public double calcularPesoTotal() {
        return pesoPasajeros + pesoEquipaje + pesoCarga + pesoCombustible;
    }

    /**
     * Regla de oro del despacho: el peso total nunca puede superar el MTOW de la aeronave asignada.
     */
    public boolean validarPesoMaximo(double pesoMaximoDespegue) {
        return calcularPesoTotal() <= pesoMaximoDespegue;
    }
}
