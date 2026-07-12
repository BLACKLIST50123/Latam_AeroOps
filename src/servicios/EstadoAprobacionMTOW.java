package servicios;
/*
    Clase que encapsula las reglas de negocio para evaluar la viabilidad de un plan de despacho.
 
    Determina el estado de aprobación según el porcentaje de peso máximo de despegue (MTOW) 
    y las restricciones meteorológicas. Actúa como un modelo puro aislado de la interfaz, 
    permitiendo que OficialOperaciones_GUI invoque su método de evaluación para obtener un 
    diagnóstico inmutable, facilitando así la aplicación de estilos visuales dinámicos.
*/
public class EstadoAprobacionMTOW {

    public enum Categoria { SEGURO, LIMITE, EXCEDIDO }

    private final Categoria categoria;
    private final boolean aprobado;
    private final String textoEstado;
    private final int porcentajeRedondeado;
    private final double excedenteKg;

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

    public Categoria getCategoria() { return categoria; }
    public boolean isAprobado() { return aprobado; }
    public String getTextoEstado() { return textoEstado; }
    public int getPorcentajeRedondeado() { return porcentajeRedondeado; }
    public double getExcedenteKg() { return excedenteKg; }
}
