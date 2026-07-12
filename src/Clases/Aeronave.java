package Clases;

import Enumeradores.EstadoAeronave;

/* ¿Para qué sirve?: Este es el modelo que representa una aeronave de la flota, con su matrícula, modelo, peso máximo de despegue, estado técnico, capacidad de asientos y autonomía
   Clases que la utilizan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
   Índice de Métodos: Aeronave, getMatricula, setMatricula, getModelo, setModelo, getPesoMaximoDespegue, setPesoMaximoDespegue, getEstadoTecnico, setEstadoTecnico, getCapacidadAsientos, setCapacidadAsientos, getAutonomiaMaximaKm, setAutonomiaMaximaKm, toString */
public class Aeronave {
    private String matricula;
    private String modelo;
    private double pesoMaximoDespegue;
    private EstadoAeronave estadoTecnico;
    private int capacidadAsientos;
    private double autonomiaMaximaKm;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un objeto nuevo de tipo Aeronave listo para usarse
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public Aeronave() {}

    // ==========================================
    // MÉTODO PARA CONSULTAR MATRICULA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo matricula de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public String getMatricula() { return matricula; }
    // ==========================================
    // MÉTODO PARA GUARDAR MATRICULA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo matricula de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public void setMatricula(String matricula) { this.matricula = matricula; }

    // ==========================================
    // MÉTODO PARA CONSULTAR MODELO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo modelo de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public String getModelo() { return modelo; }
    // ==========================================
    // MÉTODO PARA GUARDAR MODELO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo modelo de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public void setModelo(String modelo) { this.modelo = modelo; }

    // ==========================================
    // MÉTODO PARA CONSULTAR PESOMAXIMODESPEGUE
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo peso maximo despegue de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public double getPesoMaximoDespegue() { return pesoMaximoDespegue; }
    // ==========================================
    // MÉTODO PARA GUARDAR PESOMAXIMODESPEGUE
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo peso maximo despegue de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public void setPesoMaximoDespegue(double pesoMaximoDespegue) { this.pesoMaximoDespegue = pesoMaximoDespegue; }

    // ==========================================
    // MÉTODO PARA CONSULTAR ESTADOTECNICO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo estado tecnico de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public EstadoAeronave getEstadoTecnico() { return estadoTecnico; }
    // ==========================================
    // MÉTODO PARA GUARDAR ESTADOTECNICO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo estado tecnico de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public void setEstadoTecnico(EstadoAeronave estadoTecnico) { this.estadoTecnico = estadoTecnico; }

    // ==========================================
    // MÉTODO PARA CONSULTAR CAPACIDADASIENTOS
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo capacidad asientos de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public int getCapacidadAsientos() { return capacidadAsientos; }
    // ==========================================
    // MÉTODO PARA GUARDAR CAPACIDADASIENTOS
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo capacidad asientos de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public void setCapacidadAsientos(int capacidadAsientos) { this.capacidadAsientos = capacidadAsientos; }

    // ==========================================
    // MÉTODO PARA CONSULTAR AUTONOMIAMAXIMAKM
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo autonomia maxima km de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public double getAutonomiaMaximaKm() { return autonomiaMaximaKm; }
    // ==========================================
    // MÉTODO PARA GUARDAR AUTONOMIAMAXIMAKM
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo autonomia maxima km de este objeto
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    public void setAutonomiaMaximaKm(double autonomiaMaximaKm) { this.autonomiaMaximaKm = autonomiaMaximaKm; }

    // ==========================================
    // MÉTODO PARA MOSTRAR LA AERONAVE COMO TEXTO
    // ==========================================
    // Descripción: Arma el texto que se muestra en las listas, juntando la matrícula con el modelo de la aeronave
    // Clases que lo usan: AeronaveDAO, VueloOperativoBuilder, OficialOperaciones_GUI, TecnicoMantenimiento_GUI
    @Override
    public String toString() {
        return matricula + " - " + modelo;
    }
}
