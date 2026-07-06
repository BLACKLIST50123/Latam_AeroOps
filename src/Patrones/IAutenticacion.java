package Patrones;
import Clases.UsuarioSistema; // Importamos la clase

public interface IAutenticacion {
    UsuarioSistema hacerLogin(String user, String pass);
}