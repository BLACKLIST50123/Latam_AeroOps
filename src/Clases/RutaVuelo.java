package Clases;

/* ¿Para qué sirve?: Este es el modelo que guarda los datos de una ruta de vuelo, como el código, el origen y destino, y la distancia en kilómetros
   Clases que la utilizan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
   Índice de Métodos: RutaVuelo, getCodRuta, setCodRuta, getOrigenDestino, setOrigenDestino, getDistanciaKm, setDistanciaKm, toString */
public class RutaVuelo {
    private String codRuta;
    private String origenDestino;
    private double distanciaKm;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo RutaVuelo listo para usarse
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public RutaVuelo() {}

    // ==========================================
    // MÉTODO PARA CONSULTAR CODRUTA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo cod ruta de este objeto
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public String getCodRuta() { return codRuta; }
    // ==========================================
    // MÉTODO PARA GUARDAR CODRUTA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo cod ruta de este objeto
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public void setCodRuta(String codRuta) { this.codRuta = codRuta; }

    // ==========================================
    // MÉTODO PARA CONSULTAR ORIGENDESTINO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo origen destino de este objeto
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public String getOrigenDestino() { return origenDestino; }
    // ==========================================
    // MÉTODO PARA GUARDAR ORIGENDESTINO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo origen destino de este objeto
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public void setOrigenDestino(String origenDestino) { this.origenDestino = origenDestino; }

    // ==========================================
    // MÉTODO PARA CONSULTAR DISTANCIAKM
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo distancia km de este objeto
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public double getDistanciaKm() { return distanciaKm; }
    // ==========================================
    // MÉTODO PARA GUARDAR DISTANCIAKM
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo distancia km de este objeto
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm; }

    // ==========================================
    // MÉTODO PARA MOSTRAR LA RUTA COMO TEXTO
    // ==========================================
    // Descripción: Arma el texto que se muestra en las listas, juntando el código de la ruta con el origen y destino
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    @Override
    public String toString() {
        return codRuta + ": " + origenDestino;
    }
}
