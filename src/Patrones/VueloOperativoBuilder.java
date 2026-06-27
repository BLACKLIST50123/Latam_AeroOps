package Patrones;

import Clases.VueloOperativo;
import Clases.VueloProgramado;
import Clases.TripulanteVuelo;
import Clases.TripulanteCabina;
import java.util.Date;
import java.util.List;

public class VueloOperativoBuilder {
    private String codVuelo;
    private Date fechaOperacion;
    private VueloProgramado vueloBase;
    private TripulanteVuelo capitan;
    private TripulanteVuelo primerOficial;
    private List<TripulanteCabina> tripulacionCabina;

    public VueloOperativoBuilder() {}

    public VueloOperativoBuilder setCodVuelo(String codVuelo) {
        this.codVuelo = codVuelo;
        return this;
    }

    public VueloOperativoBuilder setFechaOperacion(Date fecha) {
        this.fechaOperacion = fecha;
        return this;
    }

    public VueloOperativoBuilder setVueloBase(VueloProgramado v) {
        this.vueloBase = v;
        return this;
    }

    public VueloOperativoBuilder setCapitan(TripulanteVuelo cap) {
        this.capitan = cap;
        return this;
    }

    public VueloOperativoBuilder setPrimerOficial(TripulanteVuelo fo) {
        this.primerOficial = fo;
        return this;
    }

    public VueloOperativoBuilder setTripulacionCabina(List<TripulanteCabina> tcps) {
        this.tripulacionCabina = tcps;
        return this;
    }

    // El método central que unifica las partes y retorna la entidad terminada
    public VueloOperativo build() {
        VueloOperativo vo = new VueloOperativo();
        vo.setCodVuelo(this.codVuelo);
        vo.setFechaOperacion(this.fechaOperacion);
        vo.setVueloBase(this.vueloBase);
        vo.setCapitan(this.capitan);
        vo.setPrimerOficial(this.primerOficial);
        vo.setTripulacionCabina(this.tripulacionCabina);
        
        // Valores por defecto iniciales regulados por el negocio
        vo.setEstadoOOOI("PENDIENTE");
        vo.setEstadoVuelo("PENDIENTE_DESPACHO");
        return vo;
    }
}