package Clases;

/* ¿Para qué sirve?: Este es el modelo que guarda las cantidades de combustible de ruta y de reserva de un vuelo, y permite calcular el total
   Clases que la utilizan: DespachoService, VueloOperativoBuilder, OficialOperaciones_GUI
   Índice de Métodos: ManifiestoCombustible, getCombustibleRutaKg, setCombustibleRutaKg, getCombustibleReservaKg, setCombustibleReservaKg, calcularCombustibleTotal */
public class ManifiestoCombustible {
    private double combustibleRutaKg;
    private double combustibleReservaKg;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo ManifiestoCombustible listo para usarse
    // Clases que lo usan: DespachoService, VueloOperativoBuilder, OficialOperaciones_GUI
    public ManifiestoCombustible() {}

    // ==========================================
    // MÉTODO PARA CONSULTAR COMBUSTIBLERUTAKG
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo combustible ruta kg de este objeto
    // Clases que lo usan: DespachoService, VueloOperativoBuilder, OficialOperaciones_GUI
    public double getCombustibleRutaKg() { return combustibleRutaKg; }
    // ==========================================
    // MÉTODO PARA GUARDAR COMBUSTIBLERUTAKG
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo combustible ruta kg de este objeto
    // Clases que lo usan: DespachoService, VueloOperativoBuilder, OficialOperaciones_GUI
    public void setCombustibleRutaKg(double combustibleRutaKg) { this.combustibleRutaKg = combustibleRutaKg; }

    // ==========================================
    // MÉTODO PARA CONSULTAR COMBUSTIBLERESERVAKG
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo combustible reserva kg de este objeto
    // Clases que lo usan: DespachoService, VueloOperativoBuilder, OficialOperaciones_GUI
    public double getCombustibleReservaKg() { return combustibleReservaKg; }
    // ==========================================
    // MÉTODO PARA GUARDAR COMBUSTIBLERESERVAKG
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo combustible reserva kg de este objeto
    // Clases que lo usan: DespachoService, VueloOperativoBuilder, OficialOperaciones_GUI
    public void setCombustibleReservaKg(double combustibleReservaKg) { this.combustibleReservaKg = combustibleReservaKg; }

    // ==========================================
    // MÉTODO PARA SUMAR EL COMBUSTIBLE TOTAL
    // ==========================================
    // Descripción: Suma el combustible de ruta más el combustible de reserva y entrega el total en kilogramos
    // Clases que lo usan: DespachoService, VueloOperativoBuilder, OficialOperaciones_GUI
    public double calcularCombustibleTotal() {
        return combustibleRutaKg + combustibleReservaKg;
    }
}
