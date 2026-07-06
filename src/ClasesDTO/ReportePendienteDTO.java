package ClasesDTO;

public class ReportePendienteDTO {
    private int idLogbook;
    private String matricula;
    private String modelo;
    private String prioridad;
    private String observaciones;

    // Constructor vacío
    public ReportePendienteDTO() {
    }

    // Constructor con parámetros
    public ReportePendienteDTO(int idLogbook, String matricula, String modelo, String prioridad, String observaciones) {
        this.idLogbook = idLogbook;
        this.matricula = matricula;
        this.modelo = modelo;
        this.prioridad = prioridad;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public int getIdLogbook() {
        return idLogbook;
    }

    public void setIdLogbook(int idLogbook) {
        this.idLogbook = idLogbook;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}