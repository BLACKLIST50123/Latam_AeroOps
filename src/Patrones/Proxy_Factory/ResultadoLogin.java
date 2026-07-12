package Patrones.Proxy_Factory;

import Clases.UsuarioSistema;

/**
 * Resultado tipado de un intento de login.
 *
 * REFACTOR: antes, ProxyAcceso.hacerLogin() devolvía siempre un
 * UsuarioSistema, usando valores mágicos en rolAcceso ("VACIO", "DENEGADO")
 * como si fueran códigos de error. Eso obligaba a quien llamaba (Login_GUI)
 * a comparar Strings para saber si el login había fallado y por qué — un
 * "excepción disfrazada de valor de retorno" clásico. Con este objeto, el
 * Proxy expresa el resultado explícitamente y el compilador ayuda a no
 * olvidar ningún caso.
 */
public class ResultadoLogin {

    public enum Motivo { OK, CAMPOS_VACIOS, CREDENCIALES_INVALIDAS }

    private final Motivo motivo;
    private final UsuarioSistema usuario;

    private ResultadoLogin(Motivo motivo, UsuarioSistema usuario) {
        this.motivo = motivo;
        this.usuario = usuario;
    }

    public static ResultadoLogin exitoso(UsuarioSistema usuario) {
        return new ResultadoLogin(Motivo.OK, usuario);
    }

    public static ResultadoLogin camposVacios() {
        return new ResultadoLogin(Motivo.CAMPOS_VACIOS, null);
    }

    public static ResultadoLogin credencialesInvalidas() {
        return new ResultadoLogin(Motivo.CREDENCIALES_INVALIDAS, null);
    }

    public boolean esExitoso() { return motivo == Motivo.OK; }
    public Motivo getMotivo() { return motivo; }
    public UsuarioSistema getUsuario() { return usuario; }
}
