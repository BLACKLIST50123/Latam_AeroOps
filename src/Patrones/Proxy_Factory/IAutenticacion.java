package Patrones.Proxy_Factory;
import Clases.UsuarioSistema; // Importamos la clase
/* ¿Para qué sirve?: Esta es una interfaz que define el contrato que debe cumplir cualquier clase que se encargue de autenticar usuarios, es decir, obliga a que exista un método para hacer login
   Clases que la utilizan: AutenticacionDAO, ProxyAcceso
   Índice de Métodos: hacerLogin (solo se declara aquí, cada clase que implementa esta interfaz escribe su propia versión) */


public interface IAutenticacion {
    UsuarioSistema hacerLogin(String user, String pass);
}