package Patrones.Proxy_Factory;

import Clases.UsuarioSistema;
import ClasesDAO.AutenticacionDAO;

public class ProxyAcceso implements IAutenticacion {
    private AutenticacionDAO autenticacionReal;

    public ProxyAcceso() {
        this.autenticacionReal = new AutenticacionDAO();
    }

    @Override
    public UsuarioSistema hacerLogin(String user, String pass) {
        
        // Objeto temporal para manejar errores
        UsuarioSistema usuarioError = new UsuarioSistema();
        
        if (user.isEmpty() || pass.isEmpty()) {
            usuarioError.setRolAcceso("VACIO");
            return usuarioError;
        }
        
        if (user.contains("'") || pass.contains("'") || user.contains("--")) {
            System.out.println(" Intento de Inyección SQL detectado y bloqueado por el Proxy.");
            usuarioError.setRolAcceso("DENEGADO");
            return usuarioError;
        }
        
        // Delegamos al DAO Real
        UsuarioSistema usuarioReal = autenticacionReal.hacerLogin(user, pass);
        
        if (usuarioReal == null) {
            usuarioError.setRolAcceso("DENEGADO");
            return usuarioError;
        }
        
        return usuarioReal;
    }
}