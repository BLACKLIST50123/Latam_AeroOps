package Clases;

public class RutaVuelo {
    private String codRuta;
    private String origenDestino;
    private double distanciaKm;

    public RutaVuelo() {}

    public String getCodRuta() { return codRuta; }
    public void setCodRuta(String codRuta) { this.codRuta = codRuta; }

    public String getOrigenDestino() { return origenDestino; }
    public void setOrigenDestino(String origenDestino) { this.origenDestino = origenDestino; }

    public double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm; }

    @Override
    public String toString() {
        return codRuta + ": " + origenDestino;
    }
}
