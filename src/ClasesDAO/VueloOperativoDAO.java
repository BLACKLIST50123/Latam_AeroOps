package ClasesDAO;
import BaseDeDatos.ConexionBD;
import BaseDeDatos.ConexionBDException;
import Clases.VueloOperativo;
import Clases.TripulanteCabina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VueloOperativoDAO {

    private static final Logger LOG = Logger.getLogger(VueloOperativoDAO.class.getName());
// ===============================
// 1. METODO PARA REGISTRAR UN VUELO   
// ===============================
    public boolean registrarVueloOperativo(VueloOperativo vo) {
        String sqlVuelo = "INSERT INTO vuelos_operativos (cod_vuelo, id_programacion, fecha_operacion, estado_oooi, estado_vuelo) VALUES (?, ?, ?, ?, ?)";
        String sqlTrip = "INSERT INTO tripulacion_asignada (id_vuelo_operativo, id_empleado, rol_en_vuelo) VALUES (?, ?, ?)";
        String sqlEstado = "UPDATE empleados SET estado_asignacion = 'ASIGNADO' WHERE id_empleado = ?"; 
        
        Connection con = null;
        try {
            con = ConexionBD.getInstancia().getConexion();
            con.setAutoCommit(false); 
            
            // 1. Guardamos el vuelo operativo
            PreparedStatement psVuelo = con.prepareStatement(sqlVuelo, PreparedStatement.RETURN_GENERATED_KEYS);
            psVuelo.setString(1, vo.getCodVuelo());
            psVuelo.setInt(2, vo.getVueloBase().getIdProgramacion());
            psVuelo.setDate(3, new java.sql.Date(vo.getFechaOperacion().getTime()));
            psVuelo.setString(4, vo.getEstadoOOOI());
            psVuelo.setString(5, vo.getEstadoVuelo());
            psVuelo.executeUpdate();
            
            ResultSet rs = psVuelo.getGeneratedKeys();
            int idVueloOperativo = 0;
            if (rs.next()) {
                idVueloOperativo = rs.getInt(1);
            }
            
            // Preparar el statement para la asignación y el cambio de estado
            PreparedStatement psTrip = con.prepareStatement(sqlTrip);
            PreparedStatement psEstado = con.prepareStatement(sqlEstado); // 🔥 Preparar el update
            
            // 2. Registramos al Capitán y cambiamos su estado
            psTrip.setInt(1, idVueloOperativo);
            psTrip.setInt(2, vo.getCapitan().getIdEmpleado());
            psTrip.setString(3, "Piloto");
            psTrip.executeUpdate();
            
            psEstado.setInt(1, vo.getCapitan().getIdEmpleado()); // 🔥
            psEstado.executeUpdate();                            // 🔥
            
            // 3. Registramos al Copiloto y cambiamos su estado
            psTrip.setInt(2, vo.getPrimerOficial().getIdEmpleado());
            psTrip.setString(3, "Copiloto");
            psTrip.executeUpdate();
            
            psEstado.setInt(1, vo.getPrimerOficial().getIdEmpleado()); // 🔥
            psEstado.executeUpdate();                                  // 🔥
            
            // 4. Registramos a los TCPs y cambiamos sus estados
            for (int i = 0; i < vo.getTripulacionCabina().size(); i++) {
                TripulanteCabina tcp = vo.getTripulacionCabina().get(i);
                psTrip.setInt(2, tcp.getIdEmpleado());
                
                if (i == 0) {
                    psTrip.setString(3, "Jefe de Cabina");
                } else {
                    psTrip.setString(3, "TCP");
                }
                psTrip.executeUpdate();
                
                psEstado.setInt(1, tcp.getIdEmpleado()); // 🔥
                psEstado.executeUpdate();                // 🔥
            }
            
            con.commit(); // Si todo sale bien, guarda los INSERTS y los UPDATES juntos
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error de transacción", e);
            try {
                if (con != null) con.rollback(); 
            } catch (Exception ex) {}
            return false;
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (Exception ex) {}
        }
    }

// =============================================================================    
// 2. MÉTODO PARA OBTENER LOS VUELOS QUE ESTAN PENDIENTES A DESPACHO TÉCNICO (W&B)
// =============================================================================
    public java.util.List<VueloOperativo> obtenerVuelosPendientesDespacho() {
        java.util.List<VueloOperativo> lista = new java.util.ArrayList<>();
        String sql = "SELECT vo.cod_vuelo, vo.estado_vuelo, r.origen_destino, a.matricula " +
                     "FROM vuelos_operativos vo " +
                     "JOIN vuelos_programados vp ON vo.id_programacion = vp.id_programacion " +
                     "JOIN rutas_vuelo r ON vp.id_ruta = r.id_ruta " +
                     "JOIN aeronaves a ON vp.id_aeronave = a.id_aeronave " +
                     "WHERE vo.estado_vuelo IN ('PENDIENTE_DESPACHO', 'EN_DEMORA') " +
                     "ORDER BY vo.fecha_operacion ASC";
        try {
            Connection con = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                VueloOperativo vo = new VueloOperativo();
                vo.setCodVuelo(rs.getString("cod_vuelo"));
                vo.setEstadoVuelo(rs.getString("estado_vuelo"));
                
                // Usamos el objeto interno para transportar los datos del JOIN
                Clases.VueloProgramado vp = new Clases.VueloProgramado();
                vp.setOrigenDestino(rs.getString("origen_destino"));
                vp.setMatricula(rs.getString("matricula"));
                vo.setVueloBase(vp);
                
                lista.add(vo);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al cargar pendientes", e);
        }
        return lista;
    }
// ====================================================================
// 3. MÉTODO PARA OBTENER LOS VUELOS DETALLADOS PARA LA VISTA DE DESPACHO
// ====================================================================
    public java.util.List<Clases.VueloOperativo> obtenerVuelosDetalladosParaDespacho() {
        java.util.List<Clases.VueloOperativo> lista = new java.util.ArrayList<>();
        String sql = "SELECT vo.id_vuelo_operativo, vo.cod_vuelo, vo.estado_vuelo, r.origen_destino, a.matricula, a.modelo, a.capacidad_asientos, a.peso_maximo_despegue " +
                     "FROM vuelos_operativos vo " +
                     "JOIN vuelos_programados vp ON vo.id_programacion = vp.id_programacion " +
                     "JOIN rutas_vuelo r ON vp.id_ruta = r.id_ruta " +
                     "JOIN aeronaves a ON vp.id_aeronave = a.id_aeronave " +
                     "WHERE vo.estado_vuelo IN ('PENDIENTE_DESPACHO', 'EN_DEMORA') " + 
                     "ORDER BY vo.fecha_operacion ASC";
        try {
            java.sql.Connection con = BaseDeDatos.ConexionBD.getInstancia().getConexion();
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Clases.VueloOperativo vo = new Clases.VueloOperativo();
                vo.setCodVuelo(rs.getString("cod_vuelo"));
                vo.setEstadoVuelo(rs.getString("estado_vuelo"));
                
                Clases.VueloProgramado vp = new Clases.VueloProgramado();
                vp.setOrigenDestino(rs.getString("origen_destino"));
                vp.setMatricula(rs.getString("matricula"));
                vp.setModeloAeronave(rs.getString("modelo"));
                vp.setCapacidadAsientos(rs.getInt("capacidad_asientos"));
                vo.setVueloBase(vp);
                vp.setPesoMaximoDespegue(rs.getDouble("peso_maximo_despegue"));
                
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
            LOG.log(Level.SEVERE, "Error al recuperar detalle de despacho", e);
        }
        return lista;
    }

// ===============================================================================
// 4. MÉTODOS PARA LIBERAR TRIPULACION Y ACTUALIZAR ESTADO VUELO (CANCELAR VUELO)
// ===============================================================================
    public boolean liberarTripulacion(String codVuelo) {
        String sqlCancelarVuelo = "UPDATE vuelos_operativos SET estado_vuelo = 'CANCELADO' WHERE cod_vuelo = ?";
        
        // Subconsulta potente: Actualiza a DISPONIBLE solo a los empleados que estaban en este vuelo
        String sqlLiberarTripulacion = "UPDATE empleados SET estado_asignacion = 'DISPONIBLE' " +
                                       "WHERE id_empleado IN (SELECT id_empleado FROM tripulacion_asignada WHERE id_vuelo_operativo = " +
                                       "(SELECT id_vuelo_operativo FROM vuelos_operativos WHERE cod_vuelo = ?))";

        java.sql.Connection con = null;
        try {
            con = BaseDeDatos.ConexionBD.getInstancia().getConexion();
            con.setAutoCommit(false); // Iniciamos transacción atómica

            // 1. Cancelamos el vuelo
            java.sql.PreparedStatement psVuelo = con.prepareStatement(sqlCancelarVuelo);
            psVuelo.setString(1, codVuelo);
            psVuelo.executeUpdate();

            // 2. Liberamos a la tripulación
            java.sql.PreparedStatement psTrip = con.prepareStatement(sqlLiberarTripulacion);
            psTrip.setString(1, codVuelo);
            psTrip.executeUpdate();

            con.commit(); // Confirmamos todos los cambios
            return true;

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error crítico al cancelar vuelo", e);
            try { if (con != null) con.rollback(); } catch (Exception ex) {} // Deshacemos todo si falla
            return false;
        } finally {
            try { if (con != null) con.setAutoCommit(true); } catch (Exception ex) {}
        }
    }

    public boolean actualizarEstadoVuelo(String codVuelo, String nuevoEstado) {
        String sql = "UPDATE vuelos_operativos SET estado_vuelo = ? WHERE cod_vuelo = ?";
        try {
            java.sql.Connection con = BaseDeDatos.ConexionBD.getInstancia().getConexion();
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setString(2, codVuelo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al actualizar estado en BD", e);
            return false;
        }
    }
// ====================================================================================
// 5. MÉTODO TRANSACCIONAL: PERSISTE HOJA DE CARGA + MANIFIESTO + CLIMA Y APRUEBA EL DESPACHO
// ====================================================================================
// ON CONFLICT: si el vuelo ya tenía datos de un intento previo (ej. volvió de EN_DEMORA
// y se vuelve a calcular el W&B), actualizamos en vez de fallar por la restricción UNIQUE
// sobre id_vuelo_operativo.
    public boolean aprobarDespachoConDatosOperativos(Clases.VueloOperativo vo) {
        String sqlHoja =
            "INSERT INTO hojas_carga (id_vuelo_operativo, peso_pasajeros, peso_equipaje, peso_carga, peso_combustible) " +
            "VALUES ((SELECT id_vuelo_operativo FROM vuelos_operativos WHERE cod_vuelo = ?), ?, ?, ?, ?) " +
            "ON CONFLICT (id_vuelo_operativo) DO UPDATE SET " +
            "peso_pasajeros = EXCLUDED.peso_pasajeros, peso_equipaje = EXCLUDED.peso_equipaje, " +
            "peso_carga = EXCLUDED.peso_carga, peso_combustible = EXCLUDED.peso_combustible";

        String sqlManifiesto =
            "INSERT INTO manifiestos_combustible (id_vuelo_operativo, combustible_ruta_kg, combustible_reserva_kg) " +
            "VALUES ((SELECT id_vuelo_operativo FROM vuelos_operativos WHERE cod_vuelo = ?), ?, ?) " +
            "ON CONFLICT (id_vuelo_operativo) DO UPDATE SET " +
            "combustible_ruta_kg = EXCLUDED.combustible_ruta_kg, combustible_reserva_kg = EXCLUDED.combustible_reserva_kg";

        String sqlClima =
            "INSERT INTO reportes_meteorologicos (id_vuelo_operativo, codigo_metar) " +
            "VALUES ((SELECT id_vuelo_operativo FROM vuelos_operativos WHERE cod_vuelo = ?), ?) " +
            "ON CONFLICT (id_vuelo_operativo) DO UPDATE SET codigo_metar = EXCLUDED.codigo_metar";

        String sqlEstado = "UPDATE vuelos_operativos SET estado_vuelo = ? WHERE cod_vuelo = ?";

        java.sql.Connection con = null;
        try {
            con = BaseDeDatos.ConexionBD.getInstancia().getConexion();
            con.setAutoCommit(false);

            Clases.HojaDeCarga hoja = vo.getHojaCarga();
            java.sql.PreparedStatement psHoja = con.prepareStatement(sqlHoja);
            psHoja.setString(1, vo.getCodVuelo());
            psHoja.setDouble(2, hoja.getPesoPasajeros());
            psHoja.setDouble(3, hoja.getPesoEquipaje());
            psHoja.setDouble(4, hoja.getPesoCarga());
            psHoja.setDouble(5, hoja.getPesoCombustible());
            psHoja.executeUpdate();

            Clases.ManifiestoCombustible manifiesto = vo.getManifiesto();
            java.sql.PreparedStatement psManifiesto = con.prepareStatement(sqlManifiesto);
            psManifiesto.setString(1, vo.getCodVuelo());
            psManifiesto.setDouble(2, manifiesto.getCombustibleRutaKg());
            psManifiesto.setDouble(3, manifiesto.getCombustibleReservaKg());
            psManifiesto.executeUpdate();

            Clases.ReporteMeteorologico clima = vo.getClima();
            java.sql.PreparedStatement psClima = con.prepareStatement(sqlClima);
            psClima.setString(1, vo.getCodVuelo());
            psClima.setString(2, clima.getCodigoMETAR());
            psClima.executeUpdate();

            java.sql.PreparedStatement psEstado = con.prepareStatement(sqlEstado);
            psEstado.setString(1, vo.getEstadoVuelo());
            psEstado.setString(2, vo.getCodVuelo());
            psEstado.executeUpdate();

            con.commit();
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error de transacción al aprobar despacho con datos operativos", e);
            try { if (con != null) con.rollback(); } catch (Exception ex) {}
            return false;
        } finally {
            try { if (con != null) con.setAutoCommit(true); } catch (Exception ex) {}
        }
    }

//----------------------
// PARA EL CONTROL OOOI
//----------------------
// ===================================================================
// 6. MÉTODO PARA EL HISTORIAL COMPLETO DE VUELOS (pantalla Historial de Vuelos)
// ===================================================================
    public java.util.List<VueloOperativo> obtenerHistorialVuelos() {
        java.util.List<VueloOperativo> lista = new ArrayList<>();

        String sql = "SELECT vo.id_vuelo_operativo, vo.cod_vuelo, vo.estado_vuelo, " +
                     "vo.hora_out, vo.hora_off, vo.hora_on, vo.hora_in, " +
                     "r.origen_destino, a.matricula " +
                     "FROM vuelos_operativos vo " +
                     "JOIN vuelos_programados vp ON vo.id_programacion = vp.id_programacion " +
                     "JOIN rutas_vuelo r ON vp.id_ruta = r.id_ruta " +
                     "JOIN aeronaves a ON vp.id_aeronave = a.id_aeronave " +
                     "ORDER BY vo.id_vuelo_operativo DESC";

        try {
            Connection con = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VueloOperativo vo = new VueloOperativo();
                vo.setIdVueloOperativo(rs.getInt("id_vuelo_operativo"));
                vo.setCodVuelo(rs.getString("cod_vuelo"));
                vo.setEstadoVuelo(rs.getString("estado_vuelo"));
                vo.setHoraOut(rs.getString("hora_out"));
                vo.setHoraOff(rs.getString("hora_off"));
                vo.setHoraOn(rs.getString("hora_on"));
                vo.setHoraIn(rs.getString("hora_in"));

                Clases.VueloProgramado vueloBase = new Clases.VueloProgramado();
                vueloBase.setOrigenDestino(rs.getString("origen_destino"));
                vueloBase.setMatricula(rs.getString("matricula"));
                vo.setVueloBase(vueloBase);

                // Sub-consulta para el Capitán (mismo criterio que en Despacho)
                String sqlCap = "SELECT e.nombre FROM tripulacion_asignada ta JOIN empleados e ON ta.id_empleado = e.id_empleado WHERE ta.id_vuelo_operativo = ? AND ta.rol_en_vuelo = 'Piloto'";
                PreparedStatement psC = con.prepareStatement(sqlCap);
                psC.setInt(1, vo.getIdVueloOperativo());
                ResultSet rsC = psC.executeQuery();
                if (rsC.next()) {
                    Clases.TripulanteVuelo cap = new Clases.TripulanteVuelo();
                    cap.setNombre(rsC.getString("nombre"));
                    vo.setCapitan(cap);
                }

                // Sub-consulta para el Jefe de Cabina
                String sqlJefe = "SELECT e.nombre FROM tripulacion_asignada ta JOIN empleados e ON ta.id_empleado = e.id_empleado WHERE ta.id_vuelo_operativo = ? AND ta.rol_en_vuelo = 'Jefe de Cabina'";
                PreparedStatement psJ = con.prepareStatement(sqlJefe);
                psJ.setInt(1, vo.getIdVueloOperativo());
                ResultSet rsJ = psJ.executeQuery();
                if (rsJ.next()) {
                    java.util.List<Clases.TripulanteCabina> listaJefe = new ArrayList<>();
                    Clases.TripulanteCabina jefe = new Clases.TripulanteCabina();
                    jefe.setNombre(rsJ.getString("nombre"));
                    listaJefe.add(jefe);
                    vo.setTripulacionCabina(listaJefe);
                }

                lista.add(vo);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al obtener historial de vuelos", e);
        }
        return lista;
    }
// ===================================================================================================
// 7. MÉTODO PARA OBTENER LOS VUELOS QUE ESTAN LISTOS PARA CONTROL OOOI (Aprobados, En Vuelo o En Tierra)
// ===================================================================================================
    public java.util.List<Clases.VueloOperativo> obtenerVuelosParaControlOOOI() {
        java.util.List<Clases.VueloOperativo> lista = new java.util.ArrayList<>();
        String sql = "SELECT vo.*, r.origen_destino, a.matricula " +
                     "FROM vuelos_operativos vo " +
                     "JOIN vuelos_programados vp ON vo.id_programacion = vp.id_programacion " +
                     "JOIN rutas_vuelo r ON vp.id_ruta = r.id_ruta " +
                     "JOIN aeronaves a ON vp.id_aeronave = a.id_aeronave " +
                     "WHERE vo.estado_vuelo IN ('APROBADO', 'EN_VUELO', 'EN_TIERRA') " +
                     "ORDER BY vo.fecha_operacion ASC";
        try {
            java.sql.Connection con = BaseDeDatos.ConexionBD.getInstancia().getConexion();
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Clases.VueloOperativo vo = new Clases.VueloOperativo();
                vo.setCodVuelo(rs.getString("cod_vuelo"));
                vo.setEstadoVuelo(rs.getString("estado_vuelo"));
                vo.setEstadoOOOI(rs.getString("estado_oooi"));
                // Recuperamos las horas si es que ya se marcaron
                vo.setHoraOut(rs.getString("hora_out"));
                vo.setHoraOff(rs.getString("hora_off"));
                vo.setHoraOn(rs.getString("hora_on"));
                vo.setHoraIn(rs.getString("hora_in"));
                
                Clases.VueloProgramado vp = new Clases.VueloProgramado();
                vp.setOrigenDestino(rs.getString("origen_destino"));
                vp.setMatricula(rs.getString("matricula"));
                vo.setVueloBase(vp);
                
                lista.add(vo);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al obtener vuelos para Control OOOI", e);
        }
        return lista;
    }
// ===================================================================
// 8. MÉTODO DINÁMICO PARA GUARDAR LA HORA Y LA FASE EN LA BASE DE DATOS
// ===================================================================
    public boolean registrarFaseOOOI(String codVuelo, String fase, String horaZulu) {
        // Armamos la consulta dinámica según el botón que presionen
        String columnaHora = "hora_" + fase.toLowerCase(); // Se vuelve hora_out, hora_off, etc.
        String sql = "UPDATE vuelos_operativos SET estado_oooi = ?, " + columnaHora + " = ? WHERE cod_vuelo = ?";
        try {
            java.sql.Connection con = BaseDeDatos.ConexionBD.getInstancia().getConexion();
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fase);
            ps.setString(2, horaZulu);
            ps.setString(3, codVuelo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al registrar OOOI", e);
            return false;
        }
    }

// ===================================================================
// 9. MÉTODO PARA CARGAR LOS VUELOS EN EL LOGBOOK
// ===================================================================       
    public ArrayList<Clases.VueloOperativo> obtenerVuelosParaLogbook() {
        ArrayList<Clases.VueloOperativo> lista = new ArrayList<>();

        // 1. Hacemos el JOIN con vuelos_programados y aeronaves para conseguir la matrícula
        String sql = "SELECT vo.id_vuelo_operativo, vo.cod_vuelo, vo.estado_vuelo, a.matricula " +
                     "FROM vuelos_operativos vo " +
                     "INNER JOIN vuelos_programados vp ON vo.id_programacion = vp.id_programacion " +
                     "INNER JOIN aeronaves a ON vp.id_aeronave = a.id_aeronave " +
                     "WHERE vo.estado_oooi = 'IN' AND vo.estado_vuelo != 'COMPLETADO'";

        try {
            java.sql.Connection con = BaseDeDatos.ConexionBD.getInstancia().getConexion();
            try (java.sql.PreparedStatement ps = con.prepareStatement(sql);
                 java.sql.ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Clases.VueloOperativo vo = new Clases.VueloOperativo();
                    vo.setIdVueloOperativo(rs.getInt("id_vuelo_operativo"));
                    vo.setCodVuelo(rs.getString("cod_vuelo"));
                    vo.setEstadoVuelo(rs.getString("estado_vuelo"));

                    // 2. ¡EL TRUCO PARA QUE TU TOSTRING NO EXPLOTE!
                    // Instanciamos el vueloBase y le seteamos la matrícula
                    Clases.VueloProgramado vueloBase = new Clases.VueloProgramado();
                    vueloBase.setMatricula(rs.getString("matricula")); // Ajusta el setter si se llama distinto
                    vo.setVueloBase(vueloBase);

                    lista.add(vo);
                }
            }
        } catch (java.sql.SQLException | ConexionBDException e) {
            LOG.log(Level.SEVERE, "Error al obtener vuelos Logbook", e);
        }
        return lista;
    }

// ===================================================================
// 10. MÉTODO PARA EL LLENADO DE LA TABLA REPORTES_LOGBOOK
// ===================================================================    
    public boolean cerrarVueloConLogbook(Clases.ReporteLogbook logbook) {
        boolean exito = false;
        java.sql.Connection con = null;
        
        try {
            // Reemplaza esto con tu método de conexión Singleton real
            con = BaseDeDatos.ConexionBD.getInstancia().getConexion();
            con.setAutoCommit(false); // 🚀 INICIO DE LA TRANSACCIÓN

            // 1. Insertar el Logbook
            String sqlLogbook = "INSERT INTO reportes_logbook (id_vuelo_operativo, combustible_sobrante, observaciones_tecnicas, prioridad) VALUES (?, ?, ?, ?)";
            try (java.sql.PreparedStatement psLogbook = con.prepareStatement(sqlLogbook)) {
                psLogbook.setInt(1, logbook.getIdVueloOperativo());
                psLogbook.setDouble(2, logbook.getCombustibleSobrante());
                psLogbook.setString(3, logbook.getObservacionesTecnicas());
                psLogbook.setString(4, logbook.getPrioridad().name()); // .name() extrae el String exacto del Enum para la BD
                psLogbook.executeUpdate();
            }

            // 2. Actualizar el estado del vuelo operativo
            String sqlVuelo = "UPDATE vuelos_operativos SET estado_oooi = 'IN', estado_vuelo = 'COMPLETADO' WHERE id_vuelo_operativo = ?";
            try (java.sql.PreparedStatement psVuelo = con.prepareStatement(sqlVuelo)) {
                psVuelo.setInt(1, logbook.getIdVueloOperativo());
                psVuelo.executeUpdate();
            }

            // 3. Liberar a la tripulación (Devolverlos a DISPONIBLE)
            String sqlTripulacion = "UPDATE empleados SET estado_asignacion = 'DISPONIBLE' WHERE id_empleado IN (SELECT id_empleado FROM tripulacion_asignada WHERE id_vuelo_operativo = ?)";
            try (java.sql.PreparedStatement psTrip = con.prepareStatement(sqlTripulacion)) {
                psTrip.setInt(1, logbook.getIdVueloOperativo());
                psTrip.executeUpdate();
            }

            // 4. Si se reportó CUALQUIER falla (ALTA, MEDIA o BAJA), mandar el avión a MANTENIMIENTO
            if (!logbook.getPrioridad().name().equals("SIN_FALLAS")) {
                String sqlAeronave = "UPDATE aeronaves SET estado_tecnico = 'MANTENIMIENTO' WHERE id_aeronave = (SELECT id_aeronave FROM vuelos_programados vp INNER JOIN vuelos_operativos vo ON vp.id_programacion = vo.id_programacion WHERE vo.id_vuelo_operativo = ?)";
                try (java.sql.PreparedStatement psAeronave = con.prepareStatement(sqlAeronave)) {
                    psAeronave.setInt(1, logbook.getIdVueloOperativo());
                    psAeronave.executeUpdate();
                }
            }

            con.commit(); // ✅ SI TODO SALIÓ BIEN, SE GUARDA TODO
            exito = true;
            
        } catch (java.sql.SQLException | ConexionBDException e) {
            LOG.log(Level.SEVERE, "Error en transacción Cierre Vuelo", e);
            if (con != null) {
                try {
                    con.rollback(); // ❌ SI ALGO FALLÓ, SE DESHACE TODO
                } catch (java.sql.SQLException ex) {
                    LOG.log(Level.SEVERE, "Error en Rollback", ex);
                }
            }
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Restaurar autocommit
                } catch (java.sql.SQLException e) {
                    LOG.log(Level.SEVERE, "Error restaurando AutoCommit", e);
                }
            }
        }
        
        return exito;
    }
    
    
// ==================================================================================
// MÉTODO PARA REINICIAR LA BASE DE DATOS DURANTE LAS PRUEBAS DE DESARROLLO
// ==================================================================================
    public boolean reiniciarDatosPrueba() throws ConexionBDException {
        // Definimos las 3 consultas SQL
        String sqlTruncate = "TRUNCATE TABLE registros_mantenimiento, reportes_logbook, hojas_carga, "
                           + "manifiestos_combustible, reportes_meteorologicos, tripulacion_asignada, "
                           + "vuelos_operativos RESTART IDENTITY CASCADE";

        String sqlUpdateEmpleados = "UPDATE empleados SET estado_asignacion = 'DISPONIBLE'";
        String sqlUpdateAeronaves = "UPDATE aeronaves SET estado_tecnico = 'APTO'";

        java.sql.Connection con = BaseDeDatos.ConexionBD.getInstancia().getConexion();

        try {
            // 1. Iniciamos la transacción manual para asegurar que se ejecute TODO o NADA
            con.setAutoCommit(false);

            // 2. Ejecutamos el TRUNCATE CASCADE
            try (java.sql.PreparedStatement psTruncate = con.prepareStatement(sqlTruncate)) {
                psTruncate.executeUpdate();
            }

            // 3. Ejecutamos el UPDATE de empleados
            try (java.sql.PreparedStatement psEmpleados = con.prepareStatement(sqlUpdateEmpleados)) {
                psEmpleados.executeUpdate();
            }

            // 4. Ejecutamos el UPDATE de aeronaves
            try (java.sql.PreparedStatement psAeronaves = con.prepareStatement(sqlUpdateAeronaves)) {
                psAeronaves.executeUpdate();
            }

            // 5. Si todo salió bien, guardamos definitivamente los cambios en PostgreSQL
            con.commit();
            System.out.println("La base de datos ha sido restablecida con éxito");
            return true; // Éxito total

        } catch (java.sql.SQLException e) {
            // Si algo falla, cancelamos todo para no dejar la BD a medias o corrupta
            try {
                if (con != null) con.rollback();
            } catch (java.sql.SQLException ex) {
                System.out.println("Error en Rollback: " + ex.getMessage());
            }
            System.out.println("Error al reiniciar datos de prueba: " + e.getMessage());
            return false; // Falló el reinicio

        } finally {
            // Devolvemos la conexión a su estado por defecto
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (java.sql.SQLException ex) {
                System.out.println("Error al restaurar autoCommit: " + ex.getMessage());
            }
        }
    }

}