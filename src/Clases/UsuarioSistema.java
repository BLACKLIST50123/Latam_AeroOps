package Clases;

public class UsuarioSistema {
    private int idUsuario;
    private String usuario;
    private String contrasena;
    private String rolAcceso;
    private Empleado empleado;
    public UsuarioSistema() {}

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRolAcceso() { return rolAcceso; }
    public void setRolAcceso(String rolAcceso) { this.rolAcceso = rolAcceso; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }
}