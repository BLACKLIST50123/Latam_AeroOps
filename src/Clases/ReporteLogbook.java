package Clases;

import Enumeradores.EstadoPrioridad;

public class ReporteLogbook {
    private int idVueloOperativo;
    private double combustibleSobrante;
    private String observacionesTecnicas;
    private EstadoPrioridad prioridad;

    public ReporteLogbook() {}
   
    public int getIdVueloOperativo() { return idVueloOperativo;}
    public void setIdVueloOperativo(int idVueloOperativo) { this.idVueloOperativo = idVueloOperativo;}
    
    public double getCombustibleSobrante() { return combustibleSobrante; }
    public void setCombustibleSobrante(double combustibleSobrante) { this.combustibleSobrante = combustibleSobrante; }

    public String getObservacionesTecnicas() { return observacionesTecnicas; }
    public void setObservacionesTecnicas(String observacionesTecnicas) { this.observacionesTecnicas = observacionesTecnicas; }

    public EstadoPrioridad getPrioridad() { return prioridad; }
    public void setPrioridad(EstadoPrioridad prioridad) { this.prioridad = prioridad; }
    
}
