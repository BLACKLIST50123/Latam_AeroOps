package Clases;

/* ¿Para qué sirve?: Este es el modelo que guarda el código de la asignación y el rol que cumple un tripulante dentro de un vuelo específico
   Clases que la utilizan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
   Índice de Métodos: TripulacionAsignada, getCodAsignacion, setCodAsignacion, getRolEnVuelo, setRolEnVuelo */
public class TripulacionAsignada {
    private String codAsignacion;
    private String rolEnVuelo;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo TripulacionAsignada listo para usarse
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public TripulacionAsignada() {}

    // ==========================================
    // MÉTODO PARA CONSULTAR CODASIGNACION
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo cod asignacion de este objeto
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public String getCodAsignacion() { return codAsignacion; }
    // ==========================================
    // MÉTODO PARA GUARDAR CODASIGNACION
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo cod asignacion de este objeto
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public void setCodAsignacion(String codAsignacion) { this.codAsignacion = codAsignacion; }

    // ==========================================
    // MÉTODO PARA CONSULTAR ROLENVUELO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo rol en vuelo de este objeto
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public String getRolEnVuelo() { return rolEnVuelo; }
    // ==========================================
    // MÉTODO PARA GUARDAR ROLENVUELO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo rol en vuelo de este objeto
    // Clases que lo usan: VueloOperativoBuilder, VueloOperativoDAO, OficialOperaciones_GUI
    public void setRolEnVuelo(String rolEnVuelo) { this.rolEnVuelo = rolEnVuelo; }
}
