package Clases;
import java.util.Date;
public abstract class Empleado {
    protected int idEmpleado;
    protected String codEmpleado;
    protected String nombre;
    protected String cargoBase;
    protected Date licenciaVencimiento;
    protected String tipoEmpleado;

    public Empleado() {}

    // Getters y Setters
    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getCodEmpleado() { return codEmpleado; }
    public void setCodEmpleado(String codEmpleado) { this.codEmpleado = codEmpleado; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCargoBase() { return cargoBase; }
    public void setCargoBase(String cargoBase) { this.cargoBase = cargoBase; }

    public Date getLicenciaVencimiento() { return licenciaVencimiento; }
    public void setLicenciaVencimiento(Date licenciaVencimiento) { this.licenciaVencimiento = licenciaVencimiento; }

    public String getTipoEmpleado() { return tipoEmpleado; }
    public void setTipoEmpleado(String tipoEmpleado) { this.tipoEmpleado = tipoEmpleado; }
}