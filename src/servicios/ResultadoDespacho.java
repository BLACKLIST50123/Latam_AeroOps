package servicios;
import Clases.VueloOperativo;

/**
    Objeto de transferencia de datos (DTO) que encapsula el resultado del despacho de un vuelo.
  
    Actúa como un contenedor inmutable que centraliza el estado del proceso (éxito o falla), 
    el mensaje de error correspondiente y los datos del vuelo. Permite que la GUI reciba un 
    diagnóstico directo desde la capa de servicios sin necesidad de duplicar lógica de control 
    ni evaluar múltiples condiciones manuales.
 */
public class ResultadoDespacho {

    private final boolean exito;        // Indica si el proceso de despacho se completó con éxito y sin restricciones.
    private final String mensajeError;  //  Detalle textual del motivo del fallo; será nulo si la operación fue exitosa.
    private final VueloOperativo vuelo; // Instancia del vuelo operativo procesado; será nula si la operación falló

    private ResultadoDespacho(boolean exito, String mensajeError, VueloOperativo vuelo) {
        this.exito = exito;
        this.mensajeError = mensajeError;
        this.vuelo = vuelo;
    }

    public static ResultadoDespacho exito(VueloOperativo vuelo) {
        return new ResultadoDespacho(true, null, vuelo);
    }

    public static ResultadoDespacho fallo(String mensajeError) {
        return new ResultadoDespacho(false, mensajeError, null);
    }

    public boolean esExito() { return exito; }
    public String getMensajeError() { return mensajeError; }
    public VueloOperativo getVuelo() { return vuelo; }
}
