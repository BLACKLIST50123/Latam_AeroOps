package ClasesDTO;

/* ¿Para qué sirve?: Este es un modelo de transporte de datos (DTO) que agrupa la información de un registro de mantenimiento ya lista para mostrarse en una tabla o pantalla, sin necesidad de consultar varias tablas de la base de datos por separado
   Clases que la utilizan: MantenimientoDAO, TecnicoMantenimiento_GUI
   Índice de Métodos: RegistroMantenimientoDTO, getFecha, setFecha, getMatricula, setMatricula, getFallaReportada, setFallaReportada, getPrioridad, setPrioridad, getAccionMantenimiento, setAccionMantenimiento, getTecnico, setTecnico, getEstadoRegistro, setEstadoRegistro */
public class RegistroMantenimientoDTO {
    private String fecha;
    private String matricula;
    private String fallaReportada;
    private String prioridad;
    private String accionMantenimiento;
    private String tecnico;
    private String estadoRegistro;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo RegistroMantenimientoDTO listo para usarse
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public RegistroMantenimientoDTO() {}

    // ==========================================
    // MÉTODO PARA CONSULTAR FECHA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo fecha de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getFecha() { return fecha; }
    // ==========================================
    // MÉTODO PARA GUARDAR FECHA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo fecha de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setFecha(String fecha) { this.fecha = fecha; }

    // ==========================================
    // MÉTODO PARA CONSULTAR MATRICULA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo matricula de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getMatricula() { return matricula; }
    // ==========================================
    // MÉTODO PARA GUARDAR MATRICULA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo matricula de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setMatricula(String matricula) { this.matricula = matricula; }

    // ==========================================
    // MÉTODO PARA CONSULTAR FALLAREPORTADA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo falla reportada de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getFallaReportada() { return fallaReportada; }
    // ==========================================
    // MÉTODO PARA GUARDAR FALLAREPORTADA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo falla reportada de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setFallaReportada(String fallaReportada) { this.fallaReportada = fallaReportada; }

    // ==========================================
    // MÉTODO PARA CONSULTAR PRIORIDAD
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo prioridad de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getPrioridad() { return prioridad; }
    // ==========================================
    // MÉTODO PARA GUARDAR PRIORIDAD
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo prioridad de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    // ==========================================
    // MÉTODO PARA CONSULTAR ACCIONMANTENIMIENTO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo accion mantenimiento de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getAccionMantenimiento() { return accionMantenimiento; }
    // ==========================================
    // MÉTODO PARA GUARDAR ACCIONMANTENIMIENTO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo accion mantenimiento de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setAccionMantenimiento(String accionMantenimiento) { this.accionMantenimiento = accionMantenimiento; }

    // ==========================================
    // MÉTODO PARA CONSULTAR TECNICO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo tecnico de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getTecnico() { return tecnico; }
    // ==========================================
    // MÉTODO PARA GUARDAR TECNICO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo tecnico de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setTecnico(String tecnico) { this.tecnico = tecnico; }

    // ==========================================
    // MÉTODO PARA CONSULTAR ESTADOREGISTRO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo estado registro de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getEstadoRegistro() { return estadoRegistro; }
    // ==========================================
    // MÉTODO PARA GUARDAR ESTADOREGISTRO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo estado registro de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setEstadoRegistro(String estadoRegistro) { this.estadoRegistro = estadoRegistro; }
}
