package Patrones;
import ClasesDAO.AutenticacionDAO;

public class ProxyAcceso implements IAutenticacion {
    private AutenticacionDAO autenticacionReal;

    public ProxyAcceso() {
        this.autenticacionReal = new AutenticacionDAO();
    }

    @Override
    public String hacerLogin(String user, String pass) {
        // Filtro de Seguridad Inicial (Defensa básica contra inyección de código)
        if (user.isEmpty() || pass.isEmpty()) {
            return "VACIO";
        }
        
        if (user.contains("'") || pass.contains("'") || user.contains("--")) {
            System.out.println("⚠ Intento de Inyección SQL detectado y bloqueado por el Proxy.");
            return "DENEGADO";
        }
        
        // Si pasa los filtros de seguridad, delega la validación real a la BD
        return autenticacionReal.hacerLogin(user, pass);
    }
}