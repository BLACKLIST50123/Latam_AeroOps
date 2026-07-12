package Clases;

import java.util.Date;

/* ¿Para qué sirve?: Este es el modelo que guarda un registro de mantenimiento hecho a una aeronave, con la fecha, la acción realizada, el estado y la firma del técnico
   Clases que la utilizan: MantenimientoDAO, TecnicoMantenimiento_GUI
   Índice de Métodos: RegistroMantenimiento, getCodRegistro, setCodRegistro, getFechaReparacion, setFechaReparacion, getAccionRealizada, setAccionRealizada, getEstadoRegistro, setEstadoRegistro, getFirmaTecnica, setFirmaTecnica */
public class RegistroMantenimiento {
    private String codRegistro;
    private Date fechaReparacion;
    private String accionRealizada;
    private String estadoRegistro;
    private String firmaTecnica;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo RegistroMantenimiento listo para usarse
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public RegistroMantenimiento() {}

    // ==========================================
    // MÉTODO PARA CONSULTAR CODREGISTRO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo cod registro de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getCodRegistro() { return codRegistro; }
    // ==========================================
    // MÉTODO PARA GUARDAR CODREGISTRO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo cod registro de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setCodRegistro(String codRegistro) { this.codRegistro = codRegistro; }

    // ==========================================
    // MÉTODO PARA CONSULTAR FECHAREPARACION
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo fecha reparacion de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public Date getFechaReparacion() { return fechaReparacion; }
    // ==========================================
    // MÉTODO PARA GUARDAR FECHAREPARACION
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo fecha reparacion de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setFechaReparacion(Date fechaReparacion) { this.fechaReparacion = fechaReparacion; }

    // ==========================================
    // MÉTODO PARA CONSULTAR ACCIONREALIZADA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo accion realizada de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getAccionRealizada() { return accionRealizada; }
    // ==========================================
    // MÉTODO PARA GUARDAR ACCIONREALIZADA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo accion realizada de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setAccionRealizada(String accionRealizada) { this.accionRealizada = accionRealizada; }

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

    // ==========================================
    // MÉTODO PARA CONSULTAR FIRMATECNICA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo firma tecnica de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public String getFirmaTecnica() { return firmaTecnica; }
    // ==========================================
    // MÉTODO PARA GUARDAR FIRMATECNICA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo firma tecnica de este objeto
    // Clases que lo usan: MantenimientoDAO, TecnicoMantenimiento_GUI
    public void setFirmaTecnica(String firmaTecnica) { this.firmaTecnica = firmaTecnica; }
}
