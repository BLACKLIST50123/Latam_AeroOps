package Patrones.State;
import Clases.VueloOperativo;
/* ¿Para qué sirve?: Esta es la interfaz del patrón State que define todas las acciones que se le pueden pedir a un vuelo, sin importar en qué estado se encuentre en ese momento. Cada estado (EstadoPendienteDespacho, EstadoAprobado, etc.) responde a estas acciones de forma distinta, según lo que tenga sentido para ese momento del vuelo
   Clases que la utilizan: Todas las clases del paquete Patrones.State, VueloOperativo, EstadoVueloFactory
   Índice de Métodos: aprobarDespacho, declararDemora, cancelarVuelo, iniciarVuelo, finalizarVuelo, completarVuelo, getNombreEstado (solo se declaran aquí, cada estado escribe su propia versión) */


public interface IEstadoVuelo {
    // Acciones posibles en la interfaz gráfica
    void aprobarDespacho(VueloOperativo vuelo);
    void declararDemora(VueloOperativo vuelo);
    void cancelarVuelo(VueloOperativo vuelo);
    void iniciarVuelo(VueloOperativo vuelo); // Para cuando presione OFF (despegue)
    void finalizarVuelo(VueloOperativo vuelo); // Para cuando presione IN (llegada a rampa)
    void completarVuelo(VueloOperativo vuelo); // Para cuando se cierra el Logbook

    String getNombreEstado();
}