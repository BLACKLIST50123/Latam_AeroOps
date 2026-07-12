package Patrones.Proxy_Factory;

import Clases.UsuarioSistema;
import ClasesDAO.AutenticacionDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/* ¿Para qué sirve?: Esta clase actúa como un intermediario (patrón Proxy) delante de AutenticacionDAO. Antes de dejar pasar un intento de login hacia la base de datos, revisa que los campos no estén vacíos y que no parezca un intento de ataque por inyección SQL. Así, la validación de seguridad queda separada de la consulta real a la base de datos
   Clases que la utilizan: Login_GUI, VentanaFactory
   Índice de Métodos: ProxyAcceso, hacerLogin, intentarLogin */
public class ProxyAcceso implements IAutenticacion {

    private static final Logger LOG = Logger.getLogger(ProxyAcceso.class.getName());

    private final AutenticacionDAO autenticacionReal;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara el proxy de acceso, creando por dentro el AutenticacionDAO real al que le va a delegar los logins válidos
    // Clases que lo usan: Login_GUI, VentanaFactory
    public ProxyAcceso() {
        this.autenticacionReal = new AutenticacionDAO();
    }

    // ==========================================
    // MÉTODO PARA INICIAR SESIÓN DE FORMA SEGURA
    // ==========================================
    // Descripción: Revisa que el usuario y la contraseña no estén vacíos y que no contengan símbolos sospechosos de un ataque de inyección SQL. Si todo está bien, delega el login al AutenticacionDAO real; si algo falla en las validaciones, entrega un valor vacío (nulo) sin siquiera consultar la base de datos
    // Clases que lo usan: Login_GUI, VentanaFactory
    @Override
    public UsuarioSistema hacerLogin(String user, String pass) {
        if (user == null || pass == null || user.trim().isEmpty() || pass.trim().isEmpty()) {
            return null;
        }
        if (user.contains("'") || pass.contains("'") || user.contains("--")) {
            LOG.warning("Intento de inyección SQL detectado y bloqueado por el Proxy.");
            return null;
        }
        return autenticacionReal.hacerLogin(user, pass);
    }

    // ==========================================
    // MÉTODO PARA INTENTAR EL LOGIN CON DETALLE DEL RESULTADO
    // ==========================================
    // Descripción: Hace las mismas validaciones que hacerLogin, pero en vez de solo entregar el usuario o un valor vacío, entrega un objeto ResultadoLogin que indica exactamente el motivo del resultado (campos vacíos, credenciales inválidas, o éxito), para que la pantalla de login pueda mostrar el mensaje correcto
    // Clases que lo usan: Login_GUI, VentanaFactory
    public ResultadoLogin intentarLogin(String user, String pass) {
        if (user == null || pass == null || user.trim().isEmpty() || pass.trim().isEmpty()) {
            return ResultadoLogin.camposVacios();
        }
        if (user.contains("'") || pass.contains("'") || user.contains("--")) {
            LOG.warning("Intento de inyección SQL detectado y bloqueado por el Proxy.");
            return ResultadoLogin.credencialesInvalidas();
        }

        UsuarioSistema usuarioReal = autenticacionReal.hacerLogin(user, pass);
        if (usuarioReal == null) {
            return ResultadoLogin.credencialesInvalidas();
        }
        return ResultadoLogin.exitoso(usuarioReal);
    }
}
