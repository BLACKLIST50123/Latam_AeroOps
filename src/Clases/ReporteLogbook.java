package Clases;

import Enumeradores.EstadoPrioridad;

/* ¿Para qué sirve?: Este es el modelo que guarda el reporte final del Logbook de un vuelo, con el combustible sobrante, las observaciones técnicas y la prioridad de la falla reportada
   Clases que la utilizan: DespachoDAO, MantenimientoDAO, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
   Índice de Métodos: ReporteLogbook, getIdVueloOperativo, setIdVueloOperativo, getCombustibleSobrante, setCombustibleSobrante, getObservacionesTecnicas, setObservacionesTecnicas, getPrioridad, setPrioridad */
public class ReporteLogbook {
    private int idVueloOperativo;
    private double combustibleSobrante;
    private String observacionesTecnicas;
    private EstadoPrioridad prioridad;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo ReporteLogbook listo para usarse
    // Clases que lo usan: DespachoDAO, MantenimientoDAO, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public ReporteLogbook() {}
   
    // ==========================================
    // MÉTODO PARA CONSULTAR IDVUELOOPERATIVO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo id vuelo operativo de este objeto
    // Clases que lo usan: DespachoDAO, MantenimientoDAO, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public int getIdVueloOperativo() { return idVueloOperativo;}
    // ==========================================
    // MÉTODO PARA GUARDAR IDVUELOOPERATIVO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo id vuelo operativo de este objeto
    // Clases que lo usan: DespachoDAO, MantenimientoDAO, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public void setIdVueloOperativo(int idVueloOperativo) { this.idVueloOperativo = idVueloOperativo;}
    
    // ==========================================
    // MÉTODO PARA CONSULTAR COMBUSTIBLESOBRANTE
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo combustible sobrante de este objeto
    // Clases que lo usan: DespachoDAO, MantenimientoDAO, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public double getCombustibleSobrante() { return combustibleSobrante; }
    // ==========================================
    // MÉTODO PARA GUARDAR COMBUSTIBLESOBRANTE
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo combustible sobrante de este objeto
    // Clases que lo usan: DespachoDAO, MantenimientoDAO, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public void setCombustibleSobrante(double combustibleSobrante) { this.combustibleSobrante = combustibleSobrante; }

    // ==========================================
    // MÉTODO PARA CONSULTAR OBSERVACIONESTECNICAS
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo observaciones tecnicas de este objeto
    // Clases que lo usan: DespachoDAO, MantenimientoDAO, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public String getObservacionesTecnicas() { return observacionesTecnicas; }
    // ==========================================
    // MÉTODO PARA GUARDAR OBSERVACIONESTECNICAS
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo observaciones tecnicas de este objeto
    // Clases que lo usan: DespachoDAO, MantenimientoDAO, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public void setObservacionesTecnicas(String observacionesTecnicas) { this.observacionesTecnicas = observacionesTecnicas; }

    // ==========================================
    // MÉTODO PARA CONSULTAR PRIORIDAD
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo prioridad de este objeto
    // Clases que lo usan: DespachoDAO, MantenimientoDAO, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public EstadoPrioridad getPrioridad() { return prioridad; }
    // ==========================================
    // MÉTODO PARA GUARDAR PRIORIDAD
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo prioridad de este objeto
    // Clases que lo usan: DespachoDAO, MantenimientoDAO, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public void setPrioridad(EstadoPrioridad prioridad) { this.prioridad = prioridad; }
    
}
