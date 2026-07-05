package Patrones.State;
import Clases.VueloOperativo;

public interface IEstadoVuelo {
    // Acciones posibles en la interfaz gráfica
    void aprobarDespacho(VueloOperativo vuelo);
    void declararDemora(VueloOperativo vuelo);
    void cancelarVuelo(VueloOperativo vuelo);
    void iniciarVuelo(VueloOperativo vuelo); // Para cuando presione OUT
    void finalizarVuelo(VueloOperativo vuelo); // Para cuando cierre el Logbook
    
    String getNombreEstado();
}