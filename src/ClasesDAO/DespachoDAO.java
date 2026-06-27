package ClasesDAO;

import BaseDeDatos.ConexionBD;
import Clases.TripulanteVuelo;
import Clases.TripulanteCabina;
import Clases.VueloProgramado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DespachoDAO {
    
    public List<VueloProgramado> obtenerVuelosDisponibles() {
        List<VueloProgramado> lista = new ArrayList<>();
        // Agregamos modelo y capacidad a la consulta
        String sql = "SELECT vp.id_programacion, vp.cod_programacion, r.origen_destino, a.matricula, a.modelo, a.capacidad_asientos, vp.fecha_programada " +
                     "FROM vuelos_programados vp JOIN rutas_vuelo r ON vp.id_ruta = r.id_ruta " +
                     "JOIN aeronaves a ON vp.id_aeronave = a.id_aeronave " +
                     "WHERE vp.id_programacion NOT IN (SELECT id_programacion FROM vuelos_operativos)";
        try {
            Connection con = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                VueloProgramado vp = new VueloProgramado();
                vp.setIdProgramacion(rs.getInt("id_programacion"));
                vp.setCodProgramacion(rs.getInt("cod_programacion"));
                vp.setOrigenDestino(rs.getString("origen_destino"));
                vp.setMatricula(rs.getString("matricula"));
                vp.setModeloAeronave(rs.getString("modelo"));
                vp.setCapacidadAsientos(rs.getInt("capacidad_asientos"));
                vp.setFechaProgramada(rs.getTimestamp("fecha_programada"));
                lista.add(vp);
            }
        } catch (Exception e) {}
        return lista;
    }

    // AHORA FILTRA POR MODELO DE AVION (JOIN con habilitaciones)
    public List<TripulanteVuelo> obtenerPilotosHabilitados(String cargoBuscado, String modeloAvion) {
        List<TripulanteVuelo> lista = new ArrayList<>();
        String sql = "SELECT e.id_empleado, e.cod_empleado, e.nombre, e.cargo_base FROM empleados e " +
                     "JOIN habilitaciones_empleado h ON e.id_empleado = h.id_empleado " +
                     "WHERE e.tipo_empleado = 'Tripulacion Vuelo' AND e.cargo_base = ? " +
                     "AND e.estado_asignacion = 'DISPONIBLE' AND h.modelo_avion = ?";
        try {
            Connection con = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cargoBuscado);
            ps.setString(2, modeloAvion);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TripulanteVuelo tv = new TripulanteVuelo();
                tv.setIdEmpleado(rs.getInt("id_empleado"));
                tv.setNombre(rs.getString("nombre"));
                tv.setCargoBase(rs.getString("cargo_base"));
                lista.add(tv);
            }
        } catch (Exception e) {}
        return lista;
    }

    public List<TripulanteCabina> obtenerTCPsHabilitados(String modeloAvion) {
        List<TripulanteCabina> lista = new ArrayList<>();
        String sql = "SELECT e.id_empleado, e.cod_empleado, e.nombre FROM empleados e " +
                     "JOIN habilitaciones_empleado h ON e.id_empleado = h.id_empleado " +
                     "WHERE e.tipo_empleado = 'Tripulacion Cabina' AND e.estado_asignacion = 'DISPONIBLE' AND h.modelo_avion = ?";
        try {
            Connection con = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, modeloAvion);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TripulanteCabina tcp = new TripulanteCabina();
                tcp.setIdEmpleado(rs.getInt("id_empleado"));
                tcp.setNombre(rs.getString("nombre"));
                lista.add(tcp);
            }
        } catch (Exception e) {}
        return lista;
    }
}