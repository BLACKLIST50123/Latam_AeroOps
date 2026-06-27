package Clases;
import java.util.Date;
import java.util.List;

public class VueloOperativo {
    private String codVuelo;
    private Date fechaOperation;
    private VueloProgramado vueloBase;
    private String estadoOOOI;
    private String estadoVuelo;
    
    // Tripulación consolidada
    private TripulanteVuelo capitan;
    private TripulanteVuelo primerOficial;
    private List<TripulanteCabina> tripulacionCabina;

    public VueloOperativo() {}

    // Getters y Setters
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

    @Override
    public String toString() {
        return this.codVuelo + " | " + this.vueloBase.getMatricula();
    }
}