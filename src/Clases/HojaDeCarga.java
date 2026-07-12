package Clases;

/* ¿Para qué sirve?: Este es el modelo que guarda los pesos de una hoja de carga de un vuelo (pasajeros, equipaje, carga y combustible) y permite calcular y validar el peso total contra el límite de la aeronave
   Clases que la utilizan: DespachoService, OficialOperaciones_GUI
   Índice de Métodos: HojaDeCarga, getPesoPasajeros, setPesoPasajeros, getPesoEquipaje, setPesoEquipaje, getPesoCarga, setPesoCarga, getPesoCombustible, setPesoCombustible, calcularPesoTotal, validarPesoMaximo */
public class HojaDeCarga {
    private double pesoPasajeros;
    private double pesoEquipaje;
    private double pesoCarga;
    private double pesoCombustible;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo HojaDeCarga listo para usarse
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public HojaDeCarga() {}

    // ==========================================
    // MÉTODO PARA CONSULTAR PESOPASAJEROS
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo peso pasajeros de este objeto
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public double getPesoPasajeros() { return pesoPasajeros; }
    // ==========================================
    // MÉTODO PARA GUARDAR PESOPASAJEROS
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo peso pasajeros de este objeto
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public void setPesoPasajeros(double pesoPasajeros) { this.pesoPasajeros = pesoPasajeros; }

    // ==========================================
    // MÉTODO PARA CONSULTAR PESOEQUIPAJE
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo peso equipaje de este objeto
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public double getPesoEquipaje() { return pesoEquipaje; }
    // ==========================================
    // MÉTODO PARA GUARDAR PESOEQUIPAJE
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo peso equipaje de este objeto
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public void setPesoEquipaje(double pesoEquipaje) { this.pesoEquipaje = pesoEquipaje; }

    // ==========================================
    // MÉTODO PARA CONSULTAR PESOCARGA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo peso carga de este objeto
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public double getPesoCarga() { return pesoCarga; }
    // ==========================================
    // MÉTODO PARA GUARDAR PESOCARGA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo peso carga de este objeto
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public void setPesoCarga(double pesoCarga) { this.pesoCarga = pesoCarga; }

    // ==========================================
    // MÉTODO PARA CONSULTAR PESOCOMBUSTIBLE
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo peso combustible de este objeto
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public double getPesoCombustible() { return pesoCombustible; }
    // ==========================================
    // MÉTODO PARA GUARDAR PESOCOMBUSTIBLE
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo peso combustible de este objeto
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public void setPesoCombustible(double pesoCombustible) { this.pesoCombustible = pesoCombustible; }

    // ==========================================
    // MÉTODO PARA SUMAR EL PESO TOTAL
    // ==========================================
    // Descripción: Suma los cuatro pesos de la hoja de carga (pasajeros, equipaje, carga y combustible) y entrega el total. El peso de combustible debe llegar ya calculado desde ManifiestoCombustible antes de llamar a este método
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public double calcularPesoTotal() {
        return pesoPasajeros + pesoEquipaje + pesoCarga + pesoCombustible;
    }

    // ==========================================
    // MÉTODO PARA VALIDAR EL PESO MÁXIMO
    // ==========================================
    // Descripción: Compara el peso total de la hoja de carga contra el peso máximo de despegue permitido para la aeronave (MTOW), y dice si el vuelo puede despegar o no
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public boolean validarPesoMaximo(double pesoMaximoDespegue) {
        return calcularPesoTotal() <= pesoMaximoDespegue;
    }
}
