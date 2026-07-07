package ClasesDTO;

public class RegistroMantenimientoDTO {
    private String fecha;
    private String matricula;
    private String fallaReportada;
    private String prioridad;
    private String accionMantenimiento;
    private String tecnico;
    private String estadoRegistro;

    public RegistroMantenimientoDTO() {}

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getFallaReportada() { return fallaReportada; }
    public void setFallaReportada(String fallaReportada) { this.fallaReportada = fallaReportada; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    public String getAccionMantenimiento() { return accionMantenimiento; }
    public void setAccionMantenimiento(String accionMantenimiento) { this.accionMantenimiento = accionMantenimiento; }

    public String getTecnico() { return tecnico; }
    public void setTecnico(String tecnico) { this.tecnico = tecnico; }

    public String getEstadoRegistro() { return estadoRegistro; }
    public void setEstadoRegistro(String estadoRegistro) { this.estadoRegistro = estadoRegistro; }
}
