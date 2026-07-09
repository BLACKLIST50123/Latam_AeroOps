package Clases;

public class UsuarioSistema {
    private int idUsuario;
    private int idEmpleado;
    private String usuario;
    private String contrasena;
    private String rolAcceso;
    private String nombreCompleto;
    private Empleado empleado;
    public UsuarioSistema() {}

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado;}
    
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRolAcceso() { return rolAcceso; }
    public void setRolAcceso(String rolAcceso) { this.rolAcceso = rolAcceso; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    
}