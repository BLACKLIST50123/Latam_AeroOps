package Patrones.State;
import Clases.VueloOperativo;

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