package ClasesDTO;

/* ¿Para qué sirve?: Este es un modelo de transporte de datos (DTO) que agrupa la información de un reporte de Logbook que todavía está pendiente de revisión, lista para mostrarse en una tabla o tarjeta
   Clases que la utilizan: MantenimientoDAO, TecnicoMantenimiento_GUI
   Índice de Métodos: ReportePendienteDTO, getIdLogbook, setIdLogbook, getMatricula, setMatricula, getModelo, setModelo, getPrioridad, setPrioridad, getObservaciones, setObservaciones */
public class ReportePendienteDTO {
    private int idLogbook;
    private String matricula;
    private String modelo;
    private String prioridad;
    private String observaciones;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo ReportePendienteDTO. Existen dos formas de crearlo: una vacía, para llenarlo después con los setters, y otra que recibe de una vez todos los datos del reporte pendiente
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public ReportePendienteDTO() {
    }

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo ReportePendienteDTO. Existen dos formas de crearlo: una vacía, para llenarlo después con los setters, y otra que recibe de una vez todos los datos del reporte pendiente
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public ReportePendienteDTO(int idLogbook, String matricula, String modelo, String prioridad, String observaciones) {
        this.idLogbook = idLogbook;
        this.matricula = matricula;
        this.modelo = modelo;
        this.prioridad = prioridad;
        this.observaciones = observaciones;
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR IDLOGBOOK
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo id logbook de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public int getIdLogbook() {
        return idLogbook;
    }

    // ==========================================
    // MÉTODO PARA GUARDAR IDLOGBOOK
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo id logbook de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setIdLogbook(int idLogbook) {
        this.idLogbook = idLogbook;
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR MATRICULA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo matricula de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getMatricula() {
        return matricula;
    }

    // ==========================================
    // MÉTODO PARA GUARDAR MATRICULA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo matricula de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR MODELO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo modelo de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getModelo() {
        return modelo;
    }

    // ==========================================
    // MÉTODO PARA GUARDAR MODELO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo modelo de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR PRIORIDAD
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo prioridad de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getPrioridad() {
        return prioridad;
    }

    // ==========================================
    // MÉTODO PARA GUARDAR PRIORIDAD
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo prioridad de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR OBSERVACIONES
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo observaciones de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getObservaciones() {
        return observaciones;
    }

    // ==========================================
    // MÉTODO PARA GUARDAR OBSERVACIONES
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo observaciones de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}