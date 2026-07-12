package Clases;
import java.sql.Timestamp;

/* ¿Para qué sirve?: Este es el modelo que representa un vuelo ya programado en el sistema, con su ruta, la aeronave asignada, la fecha programada y la capacidad de la aeronave
   Clases que la utilizan: VueloOperativoDAO, OficialOperaciones_GUI
   Índice de Métodos: VueloProgramado, getIdProgramacion, setIdProgramacion, getCodProgramacion, setCodProgramacion, getOrigenDestino, setOrigenDestino, getMatricula, setMatricula, getFechaProgramada, setFechaProgramada, getModeloAeronave, setModeloAeronave, getCapacidadAsientos, setCapacidadAsientos, getPesoMaximoDespegue, setPesoMaximoDespegue, toString */
public class VueloProgramado {
    private int idProgramacion;
    private int codProgramacion;
    private String origenDestino; 
    private String matricula;     
    private Timestamp fechaProgramada;
    private String modeloAeronave;
    private int capacidadAsientos;
    private double pesoMaximoDespegue; // Nuevo atributo
    
    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo VueloProgramado listo para usarse
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public VueloProgramado() {}

    // ==========================================
    // MÉTODO PARA CONSULTAR IDPROGRAMACION
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo id programacion de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public int getIdProgramacion() { return idProgramacion; }
    // ==========================================
    // MÉTODO PARA GUARDAR IDPROGRAMACION
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo id programacion de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public void setIdProgramacion(int idProgramacion) { this.idProgramacion = idProgramacion; }

    // ==========================================
    // MÉTODO PARA CONSULTAR CODPROGRAMACION
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo cod programacion de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public int getCodProgramacion() { return codProgramacion; }
    // ==========================================
    // MÉTODO PARA GUARDAR CODPROGRAMACION
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo cod programacion de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public void setCodProgramacion(int codProgramacion) { this.codProgramacion = codProgramacion; }

    // ==========================================
    // MÉTODO PARA CONSULTAR ORIGENDESTINO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo origen destino de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public String getOrigenDestino() { return origenDestino; }
    // ==========================================
    // MÉTODO PARA GUARDAR ORIGENDESTINO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo origen destino de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public void setOrigenDestino(String origenDestino) { this.origenDestino = origenDestino; }

    // ==========================================
    // MÉTODO PARA CONSULTAR MATRICULA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo matricula de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public String getMatricula() { return matricula; }
    // ==========================================
    // MÉTODO PARA GUARDAR MATRICULA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo matricula de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public void setMatricula(String matricula) { this.matricula = matricula; }

    // ==========================================
    // MÉTODO PARA CONSULTAR FECHAPROGRAMADA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo fecha programada de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public Timestamp getFechaProgramada() { return fechaProgramada; }
    // ==========================================
    // MÉTODO PARA GUARDAR FECHAPROGRAMADA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo fecha programada de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public void setFechaProgramada(Timestamp fechaProgramada) { this.fechaProgramada = fechaProgramada; }

    // ==========================================
    // MÉTODO PARA CONSULTAR MODELOAERONAVE
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo modelo aeronave de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public String getModeloAeronave() { return modeloAeronave; }
    // ==========================================
    // MÉTODO PARA GUARDAR MODELOAERONAVE
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo modelo aeronave de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public void setModeloAeronave(String modeloAeronave) { this.modeloAeronave = modeloAeronave; }
    
    // ==========================================
    // MÉTODO PARA CONSULTAR CAPACIDADASIENTOS
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo capacidad asientos de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public int getCapacidadAsientos() { return capacidadAsientos; }
    // ==========================================
    // MÉTODO PARA GUARDAR CAPACIDADASIENTOS
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo capacidad asientos de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public void setCapacidadAsientos(int capacidadAsientos) { this.capacidadAsientos = capacidadAsientos; }
    
    // ==========================================
    // MÉTODO PARA CONSULTAR PESOMAXIMODESPEGUE
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo peso maximo despegue de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public double getPesoMaximoDespegue() { return pesoMaximoDespegue; }
    // ==========================================
    // MÉTODO PARA GUARDAR PESOMAXIMODESPEGUE
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo peso maximo despegue de este objeto
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    public void setPesoMaximoDespegue(double pesoMaximoDespegue) { this.pesoMaximoDespegue = pesoMaximoDespegue; }
    // ¡ESTO ES LO QUE SE VERÁ EN EL COMBOBOX!
    
    // ==========================================
    // MÉTODO PARA MOSTRAR EL VUELO COMO TEXTO
    // ==========================================
    // Descripción: Arma el texto que se muestra en el combobox, juntando el código del vuelo, la ruta, el modelo de la aeronave y la matrícula
    // Clases que lo usan: VueloOperativoDAO, OficialOperaciones_GUI
    @Override
    public String toString() {
        return "LA" + codProgramacion + ": " + origenDestino + " | " + modeloAeronave + " (" + matricula + ")";
    }
}