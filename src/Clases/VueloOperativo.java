package Clases;
import Enumeradores.EstadoVuelo;
import Patrones.State.EstadoPendienteDespacho;
import Patrones.State.IEstadoVuelo;
import java.util.Date;
import java.util.List;

/* ¿Para qué sirve?: Este es el modelo más importante del sistema: representa un vuelo operativo, con su tripulación, sus horarios OOOI, su hoja de carga, su manifiesto de combustible, el clima y el reporte de Logbook. Además guarda dentro de sí el objeto de estado (patrón State) que controla qué acciones se pueden hacer según la etapa en la que se encuentre el vuelo
   Clases que la utilizan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
   Índice de Métodos: VueloOperativo, procesarAprobacion, procesarDemora, procesarCancelacion, procesarIniciarVuelo, procesarFinalizarVuelo, procesarCompletarVuelo, getIdVueloOperativo, setIdVueloOperativo, getCodVuelo, setCodVuelo, getFechaOperacion, setFechaOperacion, getVueloBase, setVueloBase, getEstadoOOOI, setEstadoOOOI, getEstadoLogico, setEstadoLogico, getEstadoVuelo, setEstadoVuelo, getCapitan, setCapitan, getPrimerOficial, setPrimerOficial, getTripulacionCabina, setTripulacionCabina, getHoraOut, setHoraOut, getHoraOff, setHoraOff, getHoraOn, setHoraOn, getHoraIn, setHoraIn, getManifiesto, setManifiesto, getClima, setClima, getHojaCarga, setHojaCarga, getReporteLogbook, setReporteLogbook, getFechaOperation, setFechaOperation, toString */
public class VueloOperativo {
    private int idVueloOperativo;
    private String codVuelo;
    private Date fechaOperation;
    private VueloProgramado vueloBase;
    private String estadoVuelo; // 'PENDIENTE_DESPACHO', 'EN_DEMORA', 'APROBADO', 'EN_VUELO', 'EN_TIERRA', 'COMPLETADO', 'CANCELADO'
    private IEstadoVuelo estadoLogico;  //Interfaz para la asigancion y accion por estado actual del vuelo
    private String estadoOOOI; // 'PENDIENTE', 'OUT', 'OFF', 'ON', 'IN'
    private String horaOut;
    private String horaOff;
    private String horaOn;
    private String horaIn;
    // Tripulación consolidada
    private TripulanteVuelo capitan;
    private TripulanteVuelo primerOficial;
    private List<TripulanteCabina> tripulacionCabina;

    // Composiciones del despacho técnico (Weight & Balance / Meteorología / Logbook)
    private ManifiestoCombustible manifiesto;
    private ReporteMeteorologico clima;
    private HojaDeCarga hojaCarga;
    private ReporteLogbook reporteLogbook;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Prepara un vuelo nuevo dejándolo, por defecto, en el estado Pendiente de Despacho, tanto en el objeto de estado (State) como en el texto que se guarda en la base de datos
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public VueloOperativo() {
    // Todo vuelo nace por defecto en estado Pendiente
        this.estadoLogico = new EstadoPendienteDespacho();
        this.estadoVuelo = EstadoVuelo.PENDIENTE_DESPACHO.name();
    }
    
    // ==========================================
    // MÉTODO PARA PROCESAR LA APROBACIÓN DEL VUELO
    // ==========================================
    // Descripción: Le pide al estado actual del vuelo que ejecute la acción de aprobar el despacho; el resultado depende de en qué estado se encuentre el vuelo en ese momento
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void procesarAprobacion() {
        this.estadoLogico.aprobarDespacho(this);
    }
    
    // ==========================================
    // MÉTODO PARA PROCESAR LA DEMORA DEL VUELO
    // ==========================================
    // Descripción: Le pide al estado actual del vuelo que ejecute la acción de declarar demora
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void procesarDemora() {
        this.estadoLogico.declararDemora(this);
    }
    
    // ==========================================
    // MÉTODO PARA PROCESAR LA CANCELACIÓN DEL VUELO
    // ==========================================
    // Descripción: Le pide al estado actual del vuelo que ejecute la acción de cancelar el vuelo
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void procesarCancelacion() {
        this.estadoLogico.cancelarVuelo(this);
    }

    // ==========================================
    // MÉTODO PARA PROCESAR EL INICIO DEL VUELO
    // ==========================================
    // Descripción: Le pide al estado actual del vuelo que ejecute la acción de iniciar el vuelo (despegue)
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void procesarIniciarVuelo() {
        this.estadoLogico.iniciarVuelo(this);
    }

    // ==========================================
    // MÉTODO PARA PROCESAR LA FINALIZACIÓN DEL VUELO
    // ==========================================
    // Descripción: Le pide al estado actual del vuelo que ejecute la acción de finalizar el vuelo (aterrizaje)
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void procesarFinalizarVuelo() {
        this.estadoLogico.finalizarVuelo(this);
    }

    // ==========================================
    // MÉTODO PARA PROCESAR EL CIERRE DEL VUELO
    // ==========================================
    // Descripción: Le pide al estado actual del vuelo que ejecute la acción de completar el vuelo (cierre del Logbook)
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void procesarCompletarVuelo() {
        this.estadoLogico.completarVuelo(this);
    }
    

    // ==========================================
    // MÉTODO PARA CONSULTAR IDVUELOOPERATIVO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo id vuelo operativo de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public int getIdVueloOperativo() { return idVueloOperativo;}
    // ==========================================
    // MÉTODO PARA GUARDAR IDVUELOOPERATIVO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo id vuelo operativo de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setIdVueloOperativo(int idVueloOperativo) { this.idVueloOperativo = idVueloOperativo;}
    
    // ==========================================
    // MÉTODO PARA CONSULTAR CODVUELO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo cod vuelo de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public String getCodVuelo() { return codVuelo; }
    // ==========================================
    // MÉTODO PARA GUARDAR CODVUELO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo cod vuelo de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setCodVuelo(String codVuelo) { this.codVuelo = codVuelo; }

    // ==========================================
    // MÉTODO PARA CONSULTAR FECHAOPERACION
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo fecha operacion de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public Date getFechaOperacion() { return fechaOperation; }
    // ==========================================
    // MÉTODO PARA GUARDAR FECHAOPERACION
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo fecha operacion de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setFechaOperacion(Date fechaOperation) { this.fechaOperation = fechaOperation; }

    // ==========================================
    // MÉTODO PARA CONSULTAR VUELOBASE
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo vuelo base de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public VueloProgramado getVueloBase() { return vueloBase; }
    // ==========================================
    // MÉTODO PARA GUARDAR VUELOBASE
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo vuelo base de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setVueloBase(VueloProgramado vueloBase) { this.vueloBase = vueloBase; }

    // ==========================================
    // MÉTODO PARA CONSULTAR ESTADOOOOI
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo estado oooi de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public String getEstadoOOOI() { return estadoOOOI; }
    // ==========================================
    // MÉTODO PARA GUARDAR ESTADOOOOI
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo estado oooi de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setEstadoOOOI(String estadoOOOI) { this.estadoOOOI = estadoOOOI; }
    
    // ==========================================
    // MÉTODO PARA CONSULTAR ESTADOLOGICO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo estado logico de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public IEstadoVuelo getEstadoLogico() { return estadoLogico; }
    // ==========================================
    // MÉTODO PARA GUARDAR ESTADOLOGICO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo estado logico de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setEstadoLogico(IEstadoVuelo estadoLogico) { this.estadoLogico = estadoLogico; }
    
    // ==========================================
    // MÉTODO PARA CONSULTAR ESTADOVUELO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo estado vuelo de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public String getEstadoVuelo() { return estadoVuelo; }
    // ==========================================
    // MÉTODO PARA GUARDAR EL ESTADO DEL VUELO
    // ==========================================
    // Descripción: Guarda el nuevo texto del estado del vuelo, y además actualiza automáticamente el objeto de estado (State) para que quede sincronizado con ese texto. Esto es importante porque cuando un vuelo se reconstruye desde la base de datos, este método asegura que quede en el estado lógico correcto, no siempre en el estado inicial
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setEstadoVuelo(String estadoVuelo) {
        this.estadoVuelo = estadoVuelo;
        this.estadoLogico = Patrones.State.EstadoVueloFactory.crear(estadoVuelo);
    }

    // ==========================================
    // MÉTODO PARA CONSULTAR CAPITAN
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo capitan de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public TripulanteVuelo getCapitan() { return capitan; }
    // ==========================================
    // MÉTODO PARA GUARDAR CAPITAN
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo capitan de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setCapitan(TripulanteVuelo capitan) { this.capitan = capitan; }

    // ==========================================
    // MÉTODO PARA CONSULTAR PRIMEROFICIAL
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo primer oficial de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public TripulanteVuelo getPrimerOficial() { return primerOficial; }
    // ==========================================
    // MÉTODO PARA GUARDAR PRIMEROFICIAL
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo primer oficial de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setPrimerOficial(TripulanteVuelo primerOficial) { this.primerOficial = primerOficial; }

    // ==========================================
    // MÉTODO PARA CONSULTAR TRIPULACIONCABINA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo tripulacion cabina de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public List<TripulanteCabina> getTripulacionCabina() { return tripulacionCabina; }
    // ==========================================
    // MÉTODO PARA GUARDAR TRIPULACIONCABINA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo tripulacion cabina de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setTripulacionCabina(List<TripulanteCabina> tripulacionCabina) { this.tripulacionCabina = tripulacionCabina; }
    
    // ==========================================
    // MÉTODO PARA CONSULTAR HORAOUT
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo hora out de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public String getHoraOut() { return horaOut; }
    // ==========================================
    // MÉTODO PARA GUARDAR HORAOUT
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo hora out de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setHoraOut(String horaOut) { this.horaOut = horaOut; }

    // ==========================================
    // MÉTODO PARA CONSULTAR HORAOFF
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo hora off de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public String getHoraOff() { return horaOff; }
    // ==========================================
    // MÉTODO PARA GUARDAR HORAOFF
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo hora off de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setHoraOff(String horaOff) { this.horaOff = horaOff; }

    // ==========================================
    // MÉTODO PARA CONSULTAR HORAON
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo hora on de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public String getHoraOn() { return horaOn; }
    // ==========================================
    // MÉTODO PARA GUARDAR HORAON
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo hora on de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setHoraOn(String horaOn) { this.horaOn = horaOn; }

    // ==========================================
    // MÉTODO PARA CONSULTAR HORAIN
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo hora in de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public String getHoraIn() { return horaIn; }
    // ==========================================
    // MÉTODO PARA GUARDAR HORAIN
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo hora in de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setHoraIn(String horaIn) { this.horaIn = horaIn; }

    // ==========================================
    // MÉTODO PARA CONSULTAR MANIFIESTO
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo manifiesto de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public ManifiestoCombustible getManifiesto() { return manifiesto; }
    // ==========================================
    // MÉTODO PARA GUARDAR MANIFIESTO
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo manifiesto de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setManifiesto(ManifiestoCombustible manifiesto) { this.manifiesto = manifiesto; }

    // ==========================================
    // MÉTODO PARA CONSULTAR CLIMA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo clima de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public ReporteMeteorologico getClima() { return clima; }
    // ==========================================
    // MÉTODO PARA GUARDAR CLIMA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo clima de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setClima(ReporteMeteorologico clima) { this.clima = clima; }

    // ==========================================
    // MÉTODO PARA CONSULTAR HOJACARGA
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo hoja carga de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public HojaDeCarga getHojaCarga() { return hojaCarga; }
    // ==========================================
    // MÉTODO PARA GUARDAR HOJACARGA
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo hoja carga de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setHojaCarga(HojaDeCarga hojaCarga) { this.hojaCarga = hojaCarga; }

    // ==========================================
    // MÉTODO PARA CONSULTAR REPORTELOGBOOK
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo reporte logbook de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public ReporteLogbook getReporteLogbook() { return reporteLogbook; }
    // ==========================================
    // MÉTODO PARA GUARDAR REPORTELOGBOOK
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo reporte logbook de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setReporteLogbook(ReporteLogbook reporteLogbook) { this.reporteLogbook = reporteLogbook; }

    // ==========================================
    // MÉTODO PARA CONSULTAR FECHAOPERATION
    // ==========================================
    // Descripción: Entrega el valor que está guardado en el atributo fecha operation de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public Date getFechaOperation() { return fechaOperation; }
    // ==========================================
    // MÉTODO PARA GUARDAR FECHAOPERATION
    // ==========================================
    // Descripción: Guarda un nuevo valor en el atributo fecha operation de este objeto
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    public void setFechaOperation(Date fechaOperation) { this.fechaOperation = fechaOperation; }

    // ==========================================
    // MÉTODO PARA MOSTRAR EL VUELO COMO TEXTO
    // ==========================================
    // Descripción: Arma el texto que se muestra en las listas, juntando el código del vuelo con la matrícula de la aeronave asignada
    // Clases que lo usan: VueloOperativoDAO, VueloOperativoBuilder, DespachoService, ControlOOOIService, OficialOperaciones_GUI
    @Override
    public String toString() {
        return this.codVuelo + " | " + this.vueloBase.getMatricula();
    }
}