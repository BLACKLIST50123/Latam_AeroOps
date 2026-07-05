package Patrones;

import Clases.VueloOperativo;
import Clases.VueloProgramado;
import Clases.TripulanteVuelo;
import Clases.TripulanteCabina;
import Clases.ManifiestoCombustible;
import Clases.ReporteMeteorologico;
import Clases.HojaDeCarga;
import Clases.ReporteLogbook;
import Enumeradores.EstadoOOOI;
import java.util.Date;
import java.util.List;

/**
 * Builder del agregado VueloOperativo.
 *
 * Tiene dos modos de trabajo:
 *  - new VueloOperativoBuilder(): arma un vuelo operativo DESDE CERO (usado al
 *    asignar tripulación por primera vez). Aplica los valores por defecto de
 *    negocio (PENDIENTE_DESPACHO / PENDIENTE).
 *  - new VueloOperativoBuilder(vueloExistente): continúa ensamblando un vuelo
 *    YA CREADO y persistido (usado en el panel de Despacho/W&B para adjuntar
 *    HojaDeCarga, ManifiestoCombustible y ReporteMeteorologico sin perder el
 *    estado (IEstadoVuelo) ni la tripulación ya asignada).
 */
public class VueloOperativoBuilder {
    private final VueloOperativo vueloOperativo;

    public VueloOperativoBuilder() {
        this.vueloOperativo = new VueloOperativo();
        // Valores por defecto iniciales regulados por el negocio.
        // (estadoVuelo y estadoLogico ya se inicializan dentro del constructor de VueloOperativo)
        this.vueloOperativo.setEstadoOOOI(EstadoOOOI.PENDIENTE.name());
    }

    public VueloOperativoBuilder(VueloOperativo vueloExistente) {
        this.vueloOperativo = vueloExistente;
    }

    public VueloOperativoBuilder setCodVuelo(String codVuelo) {
        vueloOperativo.setCodVuelo(codVuelo);
        return this;
    }

    public VueloOperativoBuilder setFechaOperacion(Date fecha) {
        vueloOperativo.setFechaOperacion(fecha);
        return this;
    }

    public VueloOperativoBuilder setVueloBase(VueloProgramado v) {
        vueloOperativo.setVueloBase(v);
        return this;
    }

    public VueloOperativoBuilder setCapitan(TripulanteVuelo cap) {
        vueloOperativo.setCapitan(cap);
        return this;
    }

    public VueloOperativoBuilder setPrimerOficial(TripulanteVuelo fo) {
        vueloOperativo.setPrimerOficial(fo);
        return this;
    }

    public VueloOperativoBuilder setTripulacionCabina(List<TripulanteCabina> tcps) {
        vueloOperativo.setTripulacionCabina(tcps);
        return this;
    }

    public VueloOperativoBuilder setManifiesto(ManifiestoCombustible manifiesto) {
        vueloOperativo.setManifiesto(manifiesto);
        return this;
    }

    public VueloOperativoBuilder setClima(ReporteMeteorologico clima) {
        vueloOperativo.setClima(clima);
        return this;
    }

    public VueloOperativoBuilder setHojaCarga(HojaDeCarga hojaCarga) {
        vueloOperativo.setHojaCarga(hojaCarga);
        return this;
    }

    public VueloOperativoBuilder setReporteLogbook(ReporteLogbook reporteLogbook) {
        vueloOperativo.setReporteLogbook(reporteLogbook);
        return this;
    }

    public VueloOperativo build() {
        return vueloOperativo;
    }
}
