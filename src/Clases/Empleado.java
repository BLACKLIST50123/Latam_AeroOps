package Clases;
import java.util.Date;
/* ¿Para qué sirve?: Esta es la clase base de la que heredan todos los tipos de empleados del sistema (oficiales, técnicos y tripulantes). Guarda los datos que todo empleado tiene en común, como su nombre, código y cargo
   Clases que la utilizan: AutenticacionDAO, UsuarioSistema, Login_GUI
   Índice de Métodos: Empleado, getIdEmpleado, setIdEmpleado, getCodEmpleado, setCodEmpleado, getNombre, setNombre, getCargoBase, setCargoBase, getLicenciaVencimiento, setLicenciaVencimiento, getTipoEmpleado, setTipoEmpleado */
public abstract class Empleado {
    protected int idEmpleado;
    protected String codEmpleado;
    protected String nombre;
    protected String cargoBase;
    protected Date licenciaVencimiento;
    protected String tipoEmpleado;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo Empleado listo para usarse, y sirve como base para los constructores de las clases que heredan de aquí
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public Empleado() {}

    // Getters y Setters
    // ==========================================
    // MÉTODO PARA CONSULTAR IDEMPLEADO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo id empleado de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public int getIdEmpleado() { return idEmpleado; }
    // ==========================================
    // MÉTODO PARA GUARDAR IDEMPLEADO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo id empleado de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    // ==========================================
    // MÉTODO PARA CONSULTAR CODEMPLEADO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo cod empleado de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public String getCodEmpleado() { return codEmpleado; }
    // ==========================================
    // MÉTODO PARA GUARDAR CODEMPLEADO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo cod empleado de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public void setCodEmpleado(String codEmpleado) { this.codEmpleado = codEmpleado; }

    // ==========================================
    // MÉTODO PARA CONSULTAR NOMBRE
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo nombre de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public String getNombre() { return nombre; }
    // ==========================================
    // MÉTODO PARA GUARDAR NOMBRE
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo nombre de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public void setNombre(String nombre) { this.nombre = nombre; }

    // ==========================================
    // MÉTODO PARA CONSULTAR CARGOBASE
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo cargo base de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public String getCargoBase() { return cargoBase; }
    // ==========================================
    // MÉTODO PARA GUARDAR CARGOBASE
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo cargo base de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public void setCargoBase(String cargoBase) { this.cargoBase = cargoBase; }

    // ==========================================
    // MÉTODO PARA CONSULTAR LICENCIAVENCIMIENTO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo licencia vencimiento de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public Date getLicenciaVencimiento() { return licenciaVencimiento; }
    // ==========================================
    // MÉTODO PARA GUARDAR LICENCIAVENCIMIENTO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo licencia vencimiento de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public void setLicenciaVencimiento(Date licenciaVencimiento) { this.licenciaVencimiento = licenciaVencimiento; }

    // ==========================================
    // MÉTODO PARA CONSULTAR TIPOEMPLEADO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo tipo empleado de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public String getTipoEmpleado() { return tipoEmpleado; }
    // ==========================================
    // MÉTODO PARA GUARDAR TIPOEMPLEADO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo tipo empleado de este objeto
    // Clases que lo usan: AutenticacionDAO, UsuarioSistema, Login_GUI
    public void setTipoEmpleado(String tipoEmpleado) { this.tipoEmpleado = tipoEmpleado; }
}