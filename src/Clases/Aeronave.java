package Clases;

import Enumeradores.EstadoAeronave;

public class Aeronave {
    private String matricula;
    private String modelo;
    private double pesoMaximoDespegue;
    private EstadoAeronave estadoTecnico;
    private int capacidadAsientos;
    private double autonomiaMaximaKm;

    public Aeronave() {}

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public double getPesoMaximoDespegue() { return pesoMaximoDespegue; }
    public void setPesoMaximoDespegue(double pesoMaximoDespegue) { this.pesoMaximoDespegue = pesoMaximoDespegue; }

    public EstadoAeronave getEstadoTecnico() { return estadoTecnico; }
    public void setEstadoTecnico(EstadoAeronave estadoTecnico) { this.estadoTecnico = estadoTecnico; }

    public int getCapacidadAsientos() { return capacidadAsientos; }
    public void setCapacidadAsientos(int capacidadAsientos) { this.capacidadAsientos = capacidadAsientos; }

    public double getAutonomiaMaximaKm() { return autonomiaMaximaKm; }
    public void setAutonomiaMaximaKm(double autonomiaMaximaKm) { this.autonomiaMaximaKm = autonomiaMaximaKm; }

    @Override
    public String toString() {
        return matricula + " - " + modelo;
    }
}
