package servicios;
import Clases.VueloOperativo;

/**
    Objeto de transferencia de datos (DTO) que encapsula el resultado del despacho de un vuelo.
  
    Actúa como un contenedor inmutable que centraliza el estado del proceso (éxito o falla), 
    el mensaje de error correspondiente y los datos del vuelo. Permite que la GUI reciba un 
    diagnóstico directo desde la capa de servicios sin necesidad de duplicar lógica de control 
    ni evaluar múltiples condiciones manuales.
 */
/* ¿Para qué sirve?: Esta clase guarda el resultado de intentar despachar un vuelo: si salió bien o mal, el mensaje de error en caso de falla, y el vuelo ya armado en caso de éxito. Sirve para que la pantalla reciba un solo objeto claro en vez de tener que revisar varias condiciones por separado
   Clases que la utilizan: DespachoService, OficialOperaciones_GUI
   Índice de Métodos: ResultadoDespacho, exito, fallo, esExito, getMensajeError, getVuelo */
public class ResultadoDespacho {

    private final boolean exito;        // Indica si el proceso de despacho se completó con éxito y sin restricciones.
    private final String mensajeError;  //  Detalle textual del motivo del fallo; será nulo si la operación fue exitosa.
    private final VueloOperativo vuelo; // Instancia del vuelo operativo procesado; será nula si la operación falló

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un resultado de despacho con los tres datos que lo componen: si fue exitoso, el mensaje de error y el vuelo procesado. Este constructor es privado porque solo se debe crear a través de los métodos exito() y fallo()
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    private ResultadoDespacho(boolean exito, String mensajeError, VueloOperativo vuelo) {
        this.exito = exito;
        this.mensajeError = mensajeError;
        this.vuelo = vuelo;
    }

    // ==========================================
    // MÉTODO PARA CREAR UN RESULTADO EXITOSO
    // ==========================================
    // Descripción: Arma un resultado de despacho marcado como exitoso, guardando el vuelo que ya quedó listo
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public static ResultadoDespacho exito(VueloOperativo vuelo) {
        return new ResultadoDespacho(true, null, vuelo);
    }

    // ==========================================
    // MÉTODO PARA CREAR UN RESULTADO FALLIDO
    // ==========================================
    // Descripción: Arma un resultado de despacho marcado como fallido, guardando el mensaje que explica por qué no se pudo despachar
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public static ResultadoDespacho fallo(String mensajeError) {
        return new ResultadoDespacho(false, mensajeError, null);
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR SI EL DESPACHO FUE EXITOSO
    // ==========================================
    // Descripción: Entrega verdadero si el despacho se completó sin problemas, o falso si falló
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public boolean esExito() { return exito; }
    // ==========================================
    // MÉTODO PARA CONSULTAR MENSAJEERROR
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo mensaje error de este objeto
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public String getMensajeError() { return mensajeError; }
    // ==========================================
    // MÉTODO PARA CONSULTAR VUELO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo vuelo de este objeto
    // Clases que lo usan: DespachoService, OficialOperaciones_GUI
    public VueloOperativo getVuelo() { return vuelo; }
}
