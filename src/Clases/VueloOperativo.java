package Clases;
import Enumeradores.EstadoVuelo;
import Patrones.State.EstadoPendienteDespacho;
import Patrones.State.IEstadoVuelo;
import java.util.Date;
import java.util.List;

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

    public VueloOperativo() {
    // Todo vuelo nace por defecto en estado Pendiente
        this.estadoLogico = new EstadoPendienteDespacho();
        this.estadoVuelo = EstadoVuelo.PENDIENTE_DESPACHO.name();
    }
    
    // ====================================================
    // MÉTODOS DELEGADOS DEL PATRÓN STATE
    // ====================================================
    public void procesarAprobacion() {
        this.estadoLogico.aprobarDespacho(this);
    }
    
    public void procesarDemora() {
        this.estadoLogico.declararDemora(this);
    }
    
    public void procesarCancelacion() {
        this.estadoLogico.cancelarVuelo(this);
    }
    
    public IEstadoVuelo getEstadoLogico() { return estadoLogico; }
    public void setEstadoLogico(IEstadoVuelo estadoLogico) { this.estadoLogico = estadoLogico; }
    // ====================================================
    
    // Getters y Setters
    public int getIdVueloOperativo() { return idVueloOperativo;}
    public void setIdVueloOperativo(int idVuelo) { this.idVueloOperativo = idVueloOperativo;}
    
    public String getCodVuelo() { return codVuelo; }
    public void setCodVuelo(String codVuelo) { this.codVuelo = codVuelo; }

    public Date getFechaOperacion() { return fechaOperation; }
    public void setFechaOperacion(Date fechaOperation) { this.fechaOperation = fechaOperation; }

    public VueloProgramado getVueloBase() { return vueloBase; }
    public void setVueloBase(VueloProgramado vueloBase) { this.vueloBase = vueloBase; }

    public String getEstadoOOOI() { return estadoOOOI; }
    public void setEstadoOOOI(String estadoOOOI) { this.estadoOOOI = estadoOOOI; }

    public String getEstadoVuelo() { return estadoVuelo; }
    public void setEstadoVuelo(String estadoVuelo) { this.estadoVuelo = estadoVuelo; }

    public TripulanteVuelo getCapitan() { return capitan; }
    public void setCapitan(TripulanteVuelo capitan) { this.capitan = capitan; }

    public TripulanteVuelo getPrimerOficial() { return primerOficial; }
    public void setPrimerOficial(TripulanteVuelo primerOficial) { this.primerOficial = primerOficial; }

    public List<TripulanteCabina> getTripulacionCabina() { return tripulacionCabina; }
    public void setTripulacionCabina(List<TripulanteCabina> tripulacionCabina) { this.tripulacionCabina = tripulacionCabina; }
    
    public String getHoraOut() { return horaOut; }
    public void setHoraOut(String horaOut) { this.horaOut = horaOut; }

    public String getHoraOff() { return horaOff; }
    public void setHoraOff(String horaOff) { this.horaOff = horaOff; }

    public String getHoraOn() { return horaOn; }
    public void setHoraOn(String horaOn) { this.horaOn = horaOn; }

    public String getHoraIn() { return horaIn; }
    public void setHoraIn(String horaIn) { this.horaIn = horaIn; }

    public ManifiestoCombustible getManifiesto() { return manifiesto; }
    public void setManifiesto(ManifiestoCombustible manifiesto) { this.manifiesto = manifiesto; }

    public ReporteMeteorologico getClima() { return clima; }
    public void setClima(ReporteMeteorologico clima) { this.clima = clima; }

    public HojaDeCarga getHojaCarga() { return hojaCarga; }
    public void setHojaCarga(HojaDeCarga hojaCarga) { this.hojaCarga = hojaCarga; }

    public ReporteLogbook getReporteLogbook() { return reporteLogbook; }
    public void setReporteLogbook(ReporteLogbook reporteLogbook) { this.reporteLogbook = reporteLogbook; }

    public Date getFechaOperation() { return fechaOperation; }
    public void setFechaOperation(Date fechaOperation) { this.fechaOperation = fechaOperation; }

    @Override
    public String toString() {
        return this.codVuelo + " | " + this.vueloBase.getMatricula();
    }
}