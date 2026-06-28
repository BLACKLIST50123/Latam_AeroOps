package Clases;
import java.sql.Timestamp;

public class VueloProgramado {
    private int idProgramacion;
    private int codProgramacion;
    private String origenDestino; 
    private String matricula;     
    private Timestamp fechaProgramada;
    private String modeloAeronave;
    private int capacidadAsientos;
    private double pesoMaximoDespegue; // Nuevo atributo
    public VueloProgramado() {}

    public int getIdProgramacion() { return idProgramacion; }
    public void setIdProgramacion(int idProgramacion) { this.idProgramacion = idProgramacion; }

    public int getCodProgramacion() { return codProgramacion; }
    public void setCodProgramacion(int codProgramacion) { this.codProgramacion = codProgramacion; }

    public String getOrigenDestino() { return origenDestino; }
    public void setOrigenDestino(String origenDestino) { this.origenDestino = origenDestino; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public Timestamp getFechaProgramada() { return fechaProgramada; }
    public void setFechaProgramada(Timestamp fechaProgramada) { this.fechaProgramada = fechaProgramada; }

    public String getModeloAeronave() { return modeloAeronave; }
    public void setModeloAeronave(String modeloAeronave) { this.modeloAeronave = modeloAeronave; }
    
    public int getCapacidadAsientos() { return capacidadAsientos; }
    public void setCapacidadAsientos(int capacidadAsientos) { this.capacidadAsientos = capacidadAsientos; }
    
    public double getPesoMaximoDespegue() { return pesoMaximoDespegue; }
    public void setPesoMaximoDespegue(double pesoMaximoDespegue) { this.pesoMaximoDespegue = pesoMaximoDespegue; }
    // ¡ESTO ES LO QUE SE VERÁ EN EL COMBOBOX!
    
    @Override
    public String toString() {
        return "LA" + codProgramacion + ": " + origenDestino + " | " + modeloAeronave + " (" + matricula + ")";
    }
}