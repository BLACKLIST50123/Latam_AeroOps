package Clases;

/* ¿Para qué sirve?: Este es el modelo que guarda el reporte del clima de un aeropuerto, usando el código METAR que se recibe del servicio de clima
   Clases que la utilizan: ClimaAeropuertoService, ValidadorMetar, OficialOperaciones_GUI
   Índice de Métodos: ReporteMeteorologico, getCodigoMETAR, setCodigoMETAR */
public class ReporteMeteorologico {
    private String codigoMETAR;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo ReporteMeteorologico listo para usarse
    // Clases que lo usan: ClimaAeropuertoService, ValidadorMetar, OficialOperaciones_GUI
    public ReporteMeteorologico() {}

    // ==========================================
    // MÉTODO PARA CONSULTAR CODIGOMETAR
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo codigo metar de este objeto
    // Clases que lo usan: ClimaAeropuertoService, ValidadorMetar, OficialOperaciones_GUI
    public String getCodigoMETAR() { return codigoMETAR; }
    // ==========================================
    // MÉTODO PARA GUARDAR CODIGOMETAR
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo codigo metar de este objeto
    // Clases que lo usan: ClimaAeropuertoService, ValidadorMetar, OficialOperaciones_GUI
    public void setCodigoMETAR(String codigoMETAR) { this.codigoMETAR = codigoMETAR; }
}
