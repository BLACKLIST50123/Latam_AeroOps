package Patrones.Builder;

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

/* ¿Para qué sirve?: Esta clase arma paso a paso un objeto VueloOperativo completo (patrón Builder), ya que este objeto tiene muchas partes (tripulación, ruta, manifiesto, hoja de carga, clima, logbook) y armarlo todo de una sola vez sería complicado. Tiene dos formas de trabajar: armar un vuelo nuevo desde cero, o seguir completando un vuelo que ya existe y ya fue guardado, sin perder los datos que ya tenía
   Clases que la utilizan: OficialOperaciones_GUI, DespachoService
   Índice de Métodos: VueloOperativoBuilder, setCodVuelo, setFechaOperacion, setVueloBase, setCapitan, setPrimerOficial, setTripulacionCabina, setManifiesto, setClima, setHojaCarga, setReporteLogbook, build */
public class VueloOperativoBuilder {
    private final VueloOperativo vueloOperativo;

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Existen dos formas de crear el builder: la vacía arma un vuelo operativo completamente nuevo y le pone los valores iniciales por defecto; la que recibe un vuelo existente sigue completando ese mismo vuelo sin perder sus datos ni su estado actual
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder() {
        this.vueloOperativo = new VueloOperativo();
        this.vueloOperativo.setEstadoOOOI(EstadoOOOI.PENDIENTE.name());
    }

    // ==========================================
    // MÉTODO CONSTRUCTOR
    // ==========================================
    // Descripción: Existen dos formas de crear el builder: la vacía arma un vuelo operativo completamente nuevo y le pone los valores iniciales por defecto; la que recibe un vuelo existente sigue completando ese mismo vuelo sin perder sus datos ni su estado actual
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder(VueloOperativo vueloExistente) {
        this.vueloOperativo = vueloExistente;
    }

    // ==========================================
    // MÉTODO PARA ASIGNAR EL CÓDIGO DE VUELO
    // ==========================================
    // Descripción: Guarda el código del vuelo dentro del objeto que se está armando, y devuelve el mismo builder para poder seguir encadenando más datos
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder setCodVuelo(String codVuelo) {
        vueloOperativo.setCodVuelo(codVuelo);
        return this;
    }

    // ==========================================
    // MÉTODO PARA ASIGNAR LA FECHA DE OPERACIÓN
    // ==========================================
    // Descripción: Guarda la fecha en la que opera el vuelo dentro del objeto que se está armando, y devuelve el mismo builder para poder seguir encadenando más datos
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder setFechaOperacion(Date fecha) {
        vueloOperativo.setFechaOperacion(fecha);
        return this;
    }

    // ==========================================
    // MÉTODO PARA ASIGNAR EL VUELO PROGRAMADO BASE
    // ==========================================
    // Descripción: Guarda la ruta y aeronave del vuelo programado del que proviene este vuelo operativo, y devuelve el mismo builder para poder seguir encadenando más datos
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder setVueloBase(VueloProgramado v) {
        vueloOperativo.setVueloBase(v);
        return this;
    }

    // ==========================================
    // MÉTODO PARA ASIGNAR EL CAPITÁN
    // ==========================================
    // Descripción: Guarda el capitán asignado a este vuelo, y devuelve el mismo builder para poder seguir encadenando más datos
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder setCapitan(TripulanteVuelo cap) {
        vueloOperativo.setCapitan(cap);
        return this;
    }

    // ==========================================
    // MÉTODO PARA ASIGNAR EL PRIMER OFICIAL
    // ==========================================
    // Descripción: Guarda el primer oficial (copiloto) asignado a este vuelo, y devuelve el mismo builder para poder seguir encadenando más datos
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder setPrimerOficial(TripulanteVuelo fo) {
        vueloOperativo.setPrimerOficial(fo);
        return this;
    }

    // ==========================================
    // MÉTODO PARA ASIGNAR LA TRIPULACIÓN DE CABINA
    // ==========================================
    // Descripción: Guarda la lista de tripulantes de cabina asignados a este vuelo, y devuelve el mismo builder para poder seguir encadenando más datos
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder setTripulacionCabina(List<TripulanteCabina> tcps) {
        vueloOperativo.setTripulacionCabina(tcps);
        return this;
    }

    // ==========================================
    // MÉTODO PARA ASIGNAR EL MANIFIESTO DE COMBUSTIBLE
    // ==========================================
    // Descripción: Guarda el manifiesto de combustible del vuelo, y devuelve el mismo builder para poder seguir encadenando más datos
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder setManifiesto(ManifiestoCombustible manifiesto) {
        vueloOperativo.setManifiesto(manifiesto);
        return this;
    }

    // ==========================================
    // MÉTODO PARA ASIGNAR EL REPORTE DEL CLIMA
    // ==========================================
    // Descripción: Guarda el reporte meteorológico asociado a este vuelo, y devuelve el mismo builder para poder seguir encadenando más datos
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder setClima(ReporteMeteorologico clima) {
        vueloOperativo.setClima(clima);
        return this;
    }

    // ==========================================
    // MÉTODO PARA ASIGNAR LA HOJA DE CARGA
    // ==========================================
    // Descripción: Guarda la hoja de carga con los pesos del vuelo, y devuelve el mismo builder para poder seguir encadenando más datos
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder setHojaCarga(HojaDeCarga hojaCarga) {
        vueloOperativo.setHojaCarga(hojaCarga);
        return this;
    }

    // ==========================================
    // MÉTODO PARA ASIGNAR EL REPORTE DE LOGBOOK
    // ==========================================
    // Descripción: Guarda el reporte final de Logbook del vuelo, y devuelve el mismo builder para poder seguir encadenando más datos
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativoBuilder setReporteLogbook(ReporteLogbook reporteLogbook) {
        vueloOperativo.setReporteLogbook(reporteLogbook);
        return this;
    }

    // ==========================================
    // MÉTODO PARA ENTREGAR EL VUELO YA ARMADO
    // ==========================================
    // Descripción: Entrega el objeto VueloOperativo final, ya con todos los datos que se le fueron agregando paso a paso
    // Clases que lo usan: OficialOperaciones_GUI, DespachoService
    public VueloOperativo build() {
        return vueloOperativo;
    }
}
