package Patrones.Proxy_Factory;

import Clases.UsuarioSistema;

/* ¿Para qué sirve?: Esta clase guarda el resultado de un intento de inicio de sesión, indicando si fue exitoso, si los campos estaban vacíos, o si las credenciales eran inválidas, junto con el usuario que inició sesión en caso de éxito. Sirve para que la pantalla de login sepa exactamente qué mensaje mostrar sin tener que adivinar comparando textos
   Clases que la utilizan: ProxyAcceso, Login_GUI
   Índice de Métodos: ResultadoLogin, exitoso, camposVacios, credencialesInvalidas, esExitoso, getMotivo, getUsuario */
public class ResultadoLogin {

    public enum Motivo { OK, CAMPOS_VACIOS, CREDENCIALES_INVALIDAS }

    private final Motivo motivo;
    private final UsuarioSistema usuario;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un resultado de login guardando el motivo del resultado y el usuario (si es que lo hay). Este constructor es privado porque solo se debe crear a través de los métodos exitoso(), camposVacios() y credencialesInvalidas()
    // Clases que lo usan: ProxyAcceso, Login_GUI
    private ResultadoLogin(Motivo motivo, UsuarioSistema usuario) {
        this.motivo = motivo;
        this.usuario = usuario;
    }

    // ==========================================
    // MÉTODO PARA CREAR UN RESULTADO EXITOSO
    // ==========================================
    // Descripción: Arma un resultado de login marcado como exitoso, guardando el usuario que inició sesión
    // Clases que lo usan: ProxyAcceso, Login_GUI
    public static ResultadoLogin exitoso(UsuarioSistema usuario) {
        return new ResultadoLogin(Motivo.OK, usuario);
    }

    // ==========================================
    // MÉTODO PARA CREAR UN RESULTADO DE CAMPOS VACÍOS
    // ==========================================
    // Descripción: Arma un resultado de login marcado con el motivo de que el usuario o la contraseña quedaron vacíos
    // Clases que lo usan: ProxyAcceso, Login_GUI
    public static ResultadoLogin camposVacios() {
        return new ResultadoLogin(Motivo.CAMPOS_VACIOS, null);
    }

    // ==========================================
    // MÉTODO PARA CREAR UN RESULTADO DE CREDENCIALES INVÁLIDAS
    // ==========================================
    // Descripción: Arma un resultado de login marcado con el motivo de que el usuario o la contraseña ingresados no son correctos
    // Clases que lo usan: ProxyAcceso, Login_GUI
    public static ResultadoLogin credencialesInvalidas() {
        return new ResultadoLogin(Motivo.CREDENCIALES_INVALIDAS, null);
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR SI EL LOGIN FUE EXITOSO
    // ==========================================
    // Descripción: Entrega verdadero si el resultado corresponde a un login exitoso, o falso en cualquier otro caso
    // Clases que lo usan: ProxyAcceso, Login_GUI
    public boolean esExitoso() { return motivo == Motivo.OK; }
    // ==========================================
    // MÉTODO PARA CONSULTAR MOTIVO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo motivo de este objeto
    // Clases que lo usan: ProxyAcceso, Login_GUI
    public Motivo getMotivo() { return motivo; }
    // ==========================================
    // MÉTODO PARA CONSULTAR USUARIO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo usuario de este objeto
    // Clases que lo usan: ProxyAcceso, Login_GUI
    public UsuarioSistema getUsuario() { return usuario; }
}
