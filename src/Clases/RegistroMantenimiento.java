package Clases;

import java.util.Date;

public class RegistroMantenimiento {
    private String codRegistro;
    private Date fechaReparacion;
    private String accionRealizada;
    private String estadoRegistro;
    private String firmaTecnica;

    public RegistroMantenimiento() {}

    public String getCodRegistro() { return codRegistro; }
    public void setCodRegistro(String codRegistro) { this.codRegistro = codRegistro; }

    public Date getFechaReparacion() { return fechaReparacion; }
    public void setFechaReparacion(Date fechaReparacion) { this.fechaReparacion = fechaReparacion; }

    public String getAccionRealizada() { return accionRealizada; }
    public void setAccionRealizada(String accionRealizada) { this.accionRealizada = accionRealizada; }

    public String getEstadoRegistro() { return estadoRegistro; }
    public void setEstadoRegistro(String estadoRegistro) { this.estadoRegistro = estadoRegistro; }

    public String getFirmaTecnica() { return firmaTecnica; }
    public void setFirmaTecnica(String firmaTecnica) { this.firmaTecnica = firmaTecnica; }
}
