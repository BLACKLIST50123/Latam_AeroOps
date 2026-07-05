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
    

    /**
     * Regla de negocio de mantenimiento: si el piloto reportó una falla crítica en las
     * observaciones técnicas (palabras clave AOG / CRITICO / INOPERATIVO), el reporte se
     * escala automáticamente a prioridad ALTA sin importar lo que haya marcado el piloto,
     * porque un olvido humano no puede bajar la prioridad de una falla de ese tipo.
     *
     * La notificación en vivo hacia la pantalla de Mantenimiento (patrón Observer) se
     * conecta en una etapa posterior sobre este mismo método.
     */
    public void generarAlertaMantenimiento() {
        if (observacionesTecnicas == null) {
            return;
        }
        String texto = observacionesTecnicas.toUpperCase();
        boolean esCritico = texto.contains("AOG")
                || texto.contains("CRITICO")
                || texto.contains("CRÍTICO")
                || texto.contains("INOPERATIVO");

        if (esCritico) {
            this.prioridad = EstadoPrioridad.ALTA;
        }
    }

}
