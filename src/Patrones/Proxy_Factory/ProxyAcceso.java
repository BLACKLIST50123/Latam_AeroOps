package Patrones.Proxy_Factory;

import Clases.UsuarioSistema;
import ClasesDAO.AutenticacionDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Proxy de acceso: intercepta el login para aplicar validaciones previas
 * (campos vacíos, patrones de inyección SQL básicos) antes de delegar al
 * DAO real.
 *
 * REFACTOR: hacerLogin() ahora respeta el mismo contrato que ya usaba
 * AutenticacionDAO (implementación real de IAutenticacion): devuelve
 * UsuarioSistema o null si el login falla, en vez de un "objeto sentinela"
 * con un String mágico en rolAcceso. Para la GUI, que sí necesita distinguir
 * "campos vacíos" de "credenciales inválidas" (dos mensajes distintos), se
 * expone intentarLogin(), que devuelve un ResultadoLogin explícito.
 */
public class ProxyAcceso implements IAutenticacion {

    private static final Logger LOG = Logger.getLogger(ProxyAcceso.class.getName());

    private final AutenticacionDAO autenticacionReal;

    public ProxyAcceso() {
        this.autenticacionReal = new AutenticacionDAO();
    }

    /**
     * Cumple el contrato de IAutenticacion: UsuarioSistema si el login es
     * válido, null en cualquier otro caso (campos vacíos, inyección
     * detectada, o credenciales incorrectas).
     */
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

    /**
     * Versión enriquecida para la capa de presentación: distingue el motivo
     * exacto del fallo, sin recurrir a Strings mágicos.
     */
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
