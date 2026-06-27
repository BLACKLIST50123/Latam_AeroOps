package ClasesDAO;

import BaseDeDatos.ConexionBD;
import Clases.VueloOperativo;
import Clases.TripulanteCabina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VueloOperativoDAO {
// ===============================
// METODO PARA REGISTRAR UN VUELO   
// ===============================
    public boolean registrarVueloOperativo(VueloOperativo vo) {
        String sqlVuelo = "INSERT INTO vuelos_operativos (cod_vuelo, id_programacion, fecha_operacion, estado_oooi, estado_vuelo) VALUES (?, ?, ?, ?, ?)";
        String sqlTrip = "INSERT INTO tripulacion_asignada (id_vuelo_operativo, id_empleado, rol_en_vuelo) VALUES (?, ?, ?)";
        
        Connection con = null;
        try {
            con = ConexionBD.getInstancia().getConexion();
            con.setAutoCommit(false); // 🔥 Iniciamos la transacción atómica
            
            // 1. Guardamos el vuelo operativo
            PreparedStatement psVuelo = con.prepareStatement(sqlVuelo, PreparedStatement.RETURN_GENERATED_KEYS);
            psVuelo.setString(1, vo.getCodVuelo());
            psVuelo.setInt(2, vo.getVueloBase().getIdProgramacion());
            psVuelo.setDate(3, new java.sql.Date(vo.getFechaOperacion().getTime()));
            psVuelo.setString(4, vo.getEstadoOOOI());
            psVuelo.setString(5, vo.getEstadoVuelo());
            psVuelo.executeUpdate();
            
            // Recuperamos el ID autoincremental generado por PostgreSQL
            ResultSet rs = psVuelo.getGeneratedKeys();
            int idVueloOperativo = 0;
            if (rs.next()) {
                idVueloOperativo = rs.getInt(1);
            }
            
            // 2. Registramos al Capitán
            PreparedStatement psTrip = con.prepareStatement(sqlTrip);
            psTrip.setInt(1, idVueloOperativo);
            psTrip.setInt(2, vo.getCapitan().getIdEmpleado());
            psTrip.setString(3, "Piloto");
            psTrip.executeUpdate();
            
            // 3. Registramos al Copiloto
            psTrip.setInt(2, vo.getPrimerOficial().getIdEmpleado());
            psTrip.setString(3, "Copiloto");
            psTrip.executeUpdate();
            
            // 4. Registramos a toda la lista de TCPs seleccionados
            for (int i = 0; i < vo.getTripulacionCabina().size(); i++) {
                TripulanteCabina tcp = vo.getTripulacionCabina().get(i);
                psTrip.setInt(2, tcp.getIdEmpleado());
                
                // La regla: El primer chip presionado en la GUI es el Jefe
                if (i == 0) {
                    psTrip.setString(3, "Jefe de Cabina");
                } else {
                    psTrip.setString(3, "TCP");
                }
                psTrip.executeUpdate();
            }
            
            con.commit(); // Fin de la transacción con éxito
            return true;
        } catch (Exception e) {
            System.out.println("Error de transacción: " + e.getMessage());
            try {
                if (con != null) con.rollback(); // Deshacemos todo ante cualquier fallo
            } catch (Exception ex) {}
            return false;
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (Exception ex) {}
        }
    }
// =============================================================================    
// MÉTODO PARA OBTENER LOS VUELOS QUE ESTAN PENDIENTES A DESPACHO TÉCNICO (W&B)
// =============================================================================
    public java.util.List<VueloOperativo> obtenerVuelosPendientesDespacho() {
        java.util.List<VueloOperativo> lista = new java.util.ArrayList<>();
        String sql = "SELECT vo.cod_vuelo, r.origen_destino, a.matricula " +
                     "FROM vuelos_operativos vo " +
                     "JOIN vuelos_programados vp ON vo.id_programacion = vp.id_programacion " +
                     "JOIN rutas_vuelo r ON vp.id_ruta = r.id_ruta " +
                     "JOIN aeronaves a ON vp.id_aeronave = a.id_aeronave " +
                     "WHERE vo.estado_vuelo = 'PENDIENTE_DESPACHO' ORDER BY vo.fecha_operacion ASC";
        try {
            Connection con = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                VueloOperativo vo = new VueloOperativo();
                vo.setCodVuelo(rs.getString("cod_vuelo"));
                
                // Usamos el objeto interno para transportar los datos del JOIN
                Clases.VueloProgramado vp = new Clases.VueloProgramado();
                vp.setOrigenDestino(rs.getString("origen_destino"));
                vp.setMatricula(rs.getString("matricula"));
                vo.setVueloBase(vp);
                
                lista.add(vo);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar pendientes: " + e.getMessage());
        }
        return lista;
    }
// ====================================================================
// MÉTODO PARA OBTENER LOS VUELOS DETALLADOS PARA LA VISTA DE DESPACHO
// ====================================================================
    public java.util.List<Clases.VueloOperativo> obtenerVuelosDetalladosParaDespacho() {
        java.util.List<Clases.VueloOperativo> lista = new java.util.ArrayList<>();
        String sql = "SELECT vo.id_vuelo_operativo, vo.cod_vuelo, r.origen_destino, a.matricula, a.modelo, a.capacidad_asientos " +
                     "FROM vuelos_operativos vo " +
                     "JOIN vuelos_programados vp ON vo.id_programacion = vp.id_programacion " +
                     "JOIN rutas_vuelo r ON vp.id_ruta = r.id_ruta " +
                     "JOIN aeronaves a ON vp.id_aeronave = a.id_aeronave " +
                     "WHERE vo.estado_vuelo = 'PENDIENTE_DESPACHO' ORDER BY vo.fecha_operacion ASC";
        try {
            java.sql.Connection con = BaseDeDatos.ConexionBD.getInstancia().getConexion();
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Clases.VueloOperativo vo = new Clases.VueloOperativo();
                vo.setCodVuelo(rs.getString("cod_vuelo"));

                Clases.VueloProgramado vp = new Clases.VueloProgramado();
                vp.setOrigenDestino(rs.getString("origen_destino"));
                vp.setMatricula(rs.getString("matricula"));
                vp.setModeloAeronave(rs.getString("modelo"));
                vp.setCapacidadAsientos(rs.getInt("capacidad_asientos"));
                vo.setVueloBase(vp);

                // Sub-consulta rápida para traer el nombre del Capitán asignado
                String sqlCap = "SELECT e.nombre FROM tripulacion_asignada ta JOIN empleados e ON ta.id_empleado = e.id_empleado WHERE ta.id_vuelo_operativo = ? AND ta.rol_en_vuelo = 'Piloto'";
                java.sql.PreparedStatement psC = con.prepareStatement(sqlCap);
                psC.setInt(1, rs.getInt("id_vuelo_operativo"));
                java.sql.ResultSet rsC = psC.executeQuery();
                if(rsC.next()){
                    Clases.TripulanteVuelo cap = new Clases.TripulanteVuelo();
                    cap.setNombre(rsC.getString("nombre"));
                    vo.setCapitan(cap);
                }

                // Sub-consulta rápida para traer el nombre del Jefe de Cabina asignado
                String sqlJefe = "SELECT e.nombre FROM tripulacion_asignada ta JOIN empleados e ON ta.id_empleado = e.id_empleado WHERE ta.id_vuelo_operativo = ? AND ta.rol_en_vuelo = 'Jefe de Cabina'";
                java.sql.PreparedStatement psJ = con.prepareStatement(sqlJefe);
                psJ.setInt(1, rs.getInt("id_vuelo_operativo"));
                java.sql.ResultSet rsJ = psJ.executeQuery();
                if(rsJ.next()){
                    java.util.List<Clases.TripulanteCabina> listaJefe = new java.util.ArrayList<>();
                    Clases.TripulanteCabina jefe = new Clases.TripulanteCabina();
                    jefe.setNombre(rsJ.getString("nombre"));
                    listaJefe.add(jefe);
                    vo.setTripulacionCabina(listaJefe);
                }

                lista.add(vo);
            }
        } catch (Exception e) {
            System.out.println("Error al recuperar detalle de despacho: " + e.getMessage());
        }
        return lista;
    }
}