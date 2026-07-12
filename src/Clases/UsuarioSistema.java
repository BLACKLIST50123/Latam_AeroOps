package Clases;

/* ¿Para qué sirve?: Este es el modelo que representa a un usuario que puede iniciar sesión en el sistema, con su usuario, contraseña, rol de acceso y el empleado al que está ligado
   Clases que la utilizan: AutenticacionDAO, Login_GUI, ProxyAcceso
   Índice de Métodos: UsuarioSistema, getIdUsuario, setIdUsuario, getIdEmpleado, setIdEmpleado, getUsuario, setUsuario, getContrasena, setContrasena, getRolAcceso, setRolAcceso, getNombreCompleto, setNombreCompleto, getEmpleado, setEmpleado */
public class UsuarioSistema {
    private int idUsuario;
    private int idEmpleado;
    private String usuario;
    private String contrasena;
    private String rolAcceso;
    private String nombreCompleto;
    private Empleado empleado;
    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo UsuarioSistema listo para usarse
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public UsuarioSistema() {}

    // Getters y Setters
    // ==========================================
    // MÉTODO PARA CONSULTAR IDUSUARIO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo id usuario de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public int getIdUsuario() { return idUsuario; }
    // ==========================================
    // MÉTODO PARA GUARDAR IDUSUARIO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo id usuario de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    // ==========================================
    // MÉTODO PARA CONSULTAR IDEMPLEADO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo id empleado de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public int getIdEmpleado() { return idEmpleado; }
    // ==========================================
    // MÉTODO PARA GUARDAR IDEMPLEADO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo id empleado de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado;}
    
    // ==========================================
    // MÉTODO PARA CONSULTAR USUARIO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo usuario de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public String getUsuario() { return usuario; }
    // ==========================================
    // MÉTODO PARA GUARDAR USUARIO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo usuario de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public void setUsuario(String usuario) { this.usuario = usuario; }

    // ==========================================
    // MÉTODO PARA CONSULTAR CONTRASENA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo contrasena de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public String getContrasena() { return contrasena; }
    // ==========================================
    // MÉTODO PARA GUARDAR CONTRASENA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo contrasena de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    // ==========================================
    // MÉTODO PARA CONSULTAR ROLACCESO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo rol acceso de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public String getRolAcceso() { return rolAcceso; }
    // ==========================================
    // MÉTODO PARA GUARDAR ROLACCESO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo rol acceso de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public void setRolAcceso(String rolAcceso) { this.rolAcceso = rolAcceso; }

    // ==========================================
    // MÉTODO PARA CONSULTAR NOMBRECOMPLETO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo nombre completo de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public String getNombreCompleto() { return nombreCompleto; }
    // ==========================================
    // MÉTODO PARA GUARDAR NOMBRECOMPLETO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo nombre completo de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    // ==========================================
    // MÉTODO PARA CONSULTAR EMPLEADO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo empleado de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public Empleado getEmpleado() { return empleado; }
    // ==========================================
    // MÉTODO PARA GUARDAR EMPLEADO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo empleado de este objeto
    // Clases que lo usan: AutenticacionDAO, Login_GUI, ProxyAcceso
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    
}