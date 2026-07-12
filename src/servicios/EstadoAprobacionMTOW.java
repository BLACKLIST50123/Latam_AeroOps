package servicios;
/*
    Clase que encapsula las reglas de negocio para evaluar la viabilidad de un plan de despacho.
 
    Determina el estado de aprobación según el porcentaje de peso máximo de despegue (MTOW) 
    y las restricciones meteorológicas. Actúa como un modelo puro aislado de la interfaz, 
    permitiendo que OficialOperaciones_GUI invoque su método de evaluación para obtener un 
    diagnóstico inmutable, facilitando así la aplicación de estilos visuales dinámicos.
*/
/* ¿Para qué sirve?: Esta clase guarda las reglas de negocio para saber si el plan de despacho de un vuelo es seguro, según el porcentaje de peso máximo de despegue (MTOW) que se está usando y las condiciones del clima. Se usa para pintar de forma dinámica los indicadores visuales en la pantalla del Oficial de Operaciones
   Clases que la utilizan: OficialOperaciones_GUI
   Índice de Métodos: EstadoAprobacionMTOW, evaluar, getCategoria, isAprobado, getTextoEstado, getPorcentajeRedondeado, getExcedenteKg */
public class EstadoAprobacionMTOW {

    public enum Categoria { SEGURO, LIMITE, EXCEDIDO }

    private final Categoria categoria;
    private final boolean aprobado;
    private final String textoEstado;
    private final int porcentajeRedondeado;
    private final double excedenteKg;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un resultado de evaluación con la categoría de peso, si quedó aprobado, el texto del estado, el porcentaje redondeado y el excedente en kilogramos. Este constructor es privado porque solo se debe crear a través del método evaluar()
    // Clases que lo usan: OficialOperaciones_GUI
    private EstadoAprobacionMTOW(Categoria categoria, boolean aprobado, String textoEstado,
                                  int porcentajeRedondeado, double excedenteKg) {
        this.categoria = categoria;
        this.aprobado = aprobado;
        this.textoEstado = textoEstado;
        this.porcentajeRedondeado = porcentajeRedondeado;
        this.excedenteKg = excedenteKg;
    }
// =================================================================
// 
// =================================================================
/**
    Evalúa las variables críticas de la operación para generar el diagnóstico del despacho.
    
    Este método procesa los datos numéricos y climáticos en vivo de la aeronave, calcula los 
    porcentajes de carga y clasifica el riesgo del vuelo. Es invocado desde la GUI para 
    obtener de forma centralizada la categoría de peso y los textos informativos del sistema.
   
    @param pesoTotalKg Peso actual calculado de la aeronave.
    @param mtowKg Peso máximo de despegue permitido para el modelo de avión.
    @param climaApto Verdadero si el clima actual permite la operación.
    @param metarCorresponde Verdadero si el reporte meteorológico es válido y vigente.
    @return Una instancia inmutable con el resultado completo de la evaluación.
*/
    // ==========================================
    // MÉTODO PARA EVALUAR EL RIESGO DEL DESPACHO
    // ==========================================
    // Descripción: Recibe el peso total de la aeronave, el peso máximo permitido (MTOW) y si el clima está apto y el METAR es válido. Calcula el porcentaje de peso usado y decide la categoría del vuelo: seguro, en el límite o excedido, además del texto que se debe mostrar en pantalla (por ejemplo APROBADO, ALERTA CLIMA o ¡EXCEDIDO!)
    // Clases que lo usan: OficialOperaciones_GUI
    public static EstadoAprobacionMTOW evaluar(double pesoTotalKg, double mtowKg,
                                                boolean climaApto, boolean metarCorresponde) {
        double porcentajeReal = (pesoTotalKg / mtowKg) * 100;
        int porcentajeRedondeado = (int) Math.round(porcentajeReal);
        boolean condicionesOk = climaApto && metarCorresponde;

        if (porcentajeReal <= 100.0) {
            Categoria categoria = porcentajeReal <= 85.0 ? Categoria.SEGURO : Categoria.LIMITE;

            if (condicionesOk) {
                return new EstadoAprobacionMTOW(categoria, true, "APROBADO", porcentajeRedondeado, 0);
            }
            String texto = !metarCorresponde ? "METAR INVÁLIDO" : "ALERTA CLIMA";
            return new EstadoAprobacionMTOW(categoria, false, texto, porcentajeRedondeado, 0);
        }

        // Sobrepeso crítico: siempre bloquea, sin importar el clima
        double excedente = pesoTotalKg - mtowKg;
        String texto;
        if (!metarCorresponde) {
            texto = "EXCEDIDO & METAR INVÁLIDO";
        } else if (!climaApto) {
            texto = "CRÍTICO: PESO Y CLIMA";
        } else {
            texto = "¡EXCEDIDO!";
        }
        return new EstadoAprobacionMTOW(Categoria.EXCEDIDO, false, texto, porcentajeRedondeado, excedente);
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR LA CATEGORÍA
    // ==========================================
    // Descripción: Entrega la categoría de riesgo calculada: SEGURO, LIMITE o EXCEDIDO
    // Clases que lo usan: OficialOperaciones_GUI
    public Categoria getCategoria() { return categoria; }
    // ==========================================
    // MÉTODO PARA CONSULTAR SI QUEDÓ APROBADO
    // ==========================================
    // Descripción: Entrega verdadero si el despacho quedó aprobado según la evaluación, o falso si no
    // Clases que lo usan: OficialOperaciones_GUI
    public boolean isAprobado() { return aprobado; }
    // ==========================================
    // MÉTODO PARA CONSULTAR EL TEXTO DEL ESTADO
    // ==========================================
    // Descripción: Entrega el texto que se debe mostrar en pantalla describiendo el resultado de la evaluación
    // Clases que lo usan: OficialOperaciones_GUI
    public String getTextoEstado() { return textoEstado; }
    // ==========================================
    // MÉTODO PARA CONSULTAR EL PORCENTAJE DE PESO
    // ==========================================
    // Descripción: Entrega el porcentaje de peso usado respecto al máximo permitido, redondeado a un número entero
    // Clases que lo usan: OficialOperaciones_GUI
    public int getPorcentajeRedondeado() { return porcentajeRedondeado; }
    // ==========================================
    // MÉTODO PARA CONSULTAR EL EXCEDENTE DE PESO
    // ==========================================
    // Descripción: Entrega la cantidad de kilogramos que se pasaron del peso máximo permitido; si no hay exceso, entrega cero
    // Clases que lo usan: OficialOperaciones_GUI
    public double getExcedenteKg() { return excedenteKg; }
}
