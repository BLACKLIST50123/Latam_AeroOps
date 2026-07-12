package ClasesDAO;

import BaseDeDatos.ConexionBD;
import BaseDeDatos.ConexionBDException;
import ClasesDTO.ReportePendienteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* ¿Para qué sirve?: Esta clase se encarga de todo lo relacionado con el mantenimiento de las aeronaves en la base de datos: traer los reportes de fallas pendientes, traer el historial completo de reparaciones ya hechas, y registrar cuando un técnico termina de reparar un avión y lo libera para que vuelva a volar
   Clases que la utilizan: TecnicoMantenimiento_GUI, OficialOperaciones_GUI
   Índice de Métodos: obtenerReportesPendientes, obtenerHistorialMantenimiento, registrarLiberacionAeronave */
public class MantenimientoDAO {

    private static final Logger LOG = Logger.getLogger(MantenimientoDAO.class.getName());

    // ==========================================
    // MÉTODO PARA OBTENER LOS REPORTES PENDIENTES
    // ==========================================
    // Descripción: Busca en la base de datos las fallas que quedaron reportadas en el Logbook de aviones que están actualmente en mantenimiento, y que todavía no han sido resueltas. Junta los datos de la aeronave con la falla reportada y arma la lista que se muestra como tarjetas en la pantalla de reportes
    // Clases que lo usan: TecnicoMantenimiento_GUI, OficialOperaciones_GUI
    public List<ReportePendienteDTO> obtenerReportesPendientes() {
        List<ReportePendienteDTO> listaReportes = new ArrayList<>();
        
        // Unimos las tablas y filtramos que la aeronave esté en mantenimiento 
        // Y que las observaciones NO sean 'SIN_FALLA' (usamos UPPER para evitar fallas por mayúsculas/minúsculas)
        String sql = "SELECT rl.id_logbook, a.matricula, a.modelo, rl.prioridad, rl.observaciones_tecnicas " +
                     "FROM reportes_logbook rl " +
                     "INNER JOIN vuelos_operativos vo ON rl.id_vuelo_operativo = vo.id_vuelo_operativo " +
                     "INNER JOIN vuelos_programados vp ON vo.id_programacion = vp.id_programacion " +
                     "INNER JOIN aeronaves a ON vp.id_aeronave = a.id_aeronave " +
                     "WHERE a.estado_tecnico = 'MANTENIMIENTO' " +
                     "AND UPPER(TRIM(rl.prioridad)) != 'SIN_FALLAS'";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConexionBD.getInstancia().getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ReportePendienteDTO reporte = new ReportePendienteDTO();
                reporte.setIdLogbook(rs.getInt("id_logbook"));
                reporte.setMatricula(rs.getString("matricula"));
                reporte.setModelo(rs.getString("modelo"));
                reporte.setPrioridad(rs.getString("prioridad"));
                reporte.setObservaciones(rs.getString("observaciones_tecnicas"));
                
                listaReportes.add(reporte);
            }
        } catch (SQLException | ConexionBDException e) {
            LOG.log(Level.SEVERE, "Error al obtener reportes pendientes", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                // OJO: NO cerramos "conn" aquí. Es la conexión Singleton compartida
                // por toda la app (ConexionBD) - cerrarla aquí deja sin BD a todo lo
                // demás que se ejecute después (por eso fallaba al firmar la liberación).
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return listaReportes;
    }

    // ==========================================
    // MÉTODO PARA OBTENER EL HISTORIAL DE MANTENIMIENTO
    // ==========================================
    // Descripción: Busca en la base de datos todas las reparaciones que ya se hicieron, juntando la fecha, la aeronave, la falla original, la acción que se tomó, el técnico que la resolvió y el estado final del registro. Esta lista es la que se muestra en la pantalla de historial del Logbook
    // Clases que lo usan: TecnicoMantenimiento_GUI, OficialOperaciones_GUI
    public List<ClasesDTO.RegistroMantenimientoDTO> obtenerHistorialMantenimiento() {
        List<ClasesDTO.RegistroMantenimientoDTO> lista = new ArrayList<>();

        String sql = "SELECT rm.fecha_reparacion, a.matricula, rl.observaciones_tecnicas, rl.prioridad, " +
                     "rm.accion_realizada, e.nombre AS tecnico, rm.estado_registro " +
                     "FROM registros_mantenimiento rm " +
                     "INNER JOIN reportes_logbook rl ON rm.id_logbook = rl.id_logbook " +
                     "INNER JOIN vuelos_operativos vo ON rl.id_vuelo_operativo = vo.id_vuelo_operativo " +
                     "INNER JOIN vuelos_programados vp ON vo.id_programacion = vp.id_programacion " +
                     "INNER JOIN aeronaves a ON vp.id_aeronave = a.id_aeronave " +
                     "INNER JOIN empleados e ON rm.id_tecnico = e.id_empleado " +
                     "ORDER BY rm.id_registro DESC";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConexionBD.getInstancia().getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ClasesDTO.RegistroMantenimientoDTO r = new ClasesDTO.RegistroMantenimientoDTO();
                java.sql.Date fecha = rs.getDate("fecha_reparacion");
                r.setFecha(fecha != null ? fecha.toString() : "--");
                r.setMatricula(rs.getString("matricula"));
                r.setFallaReportada(rs.getString("observaciones_tecnicas"));
                r.setPrioridad(rs.getString("prioridad"));
                r.setAccionMantenimiento(rs.getString("accion_realizada"));
                r.setTecnico(rs.getString("tecnico"));
                r.setEstadoRegistro(rs.getString("estado_registro"));
                lista.add(r);
            }
        } catch (SQLException | ConexionBDException e) {
            LOG.log(Level.SEVERE, "Error al obtener historial de mantenimiento", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                // OJO: NO cerramos "conn" (es la conexión Singleton compartida de toda la app).
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return lista;
    }

    // ==========================================
    // MÉTODO PARA REGISTRAR LA LIBERACIÓN DE UNA AERONAVE
    // ==========================================
    // Descripción: Guarda en la base de datos la acción que tomó el técnico para reparar la falla, y cambia el estado de la aeronave de vuelta a APTO para que pueda volver a operar. Este método hace las dos operaciones como una sola transacción: si algo falla en el camino, deshace todo (rollback) para que la base de datos no quede en un estado a medias. Entrega verdadero si todo salió bien, o falso si hubo algún error
    // Clases que lo usan: TecnicoMantenimiento_GUI, OficialOperaciones_GUI
    public boolean registrarLiberacionAeronave(int idLogbook, String matricula, int idTecnico, String accionTomada, String firmaTecnica) {
        Connection conn = null;
        PreparedStatement psMant = null;
        PreparedStatement psAeronave = null;

        // Query 1: Insertar la acción tomada en el registro de mantenimiento
        // (columnas reales de la tabla: id_registro es PK autogenerado, no se manda)
        String sqlInsertMant = "INSERT INTO registros_mantenimiento (cod_registro, id_logbook, id_tecnico, fecha_reparacion, accion_realizada, estado_registro, firma_tecnica) " +
                               "VALUES (?, ?, ?, CURRENT_DATE, ?, ?, ?)";

        // Query 2: Cambiar el estado de la aeronave a OPERATIVO
        String sqlUpdateAeronave = "UPDATE aeronaves SET estado_tecnico = 'APTO' WHERE matricula = ?";

        try {
            conn = ConexionBD.getInstancia().getConexion();
            conn.setAutoCommit(false); // ¡CRÍTICO! Iniciamos la transacción manual

            // Generamos un código de registro simple y único (cod_registro es NOT NULL en la tabla)
            String codRegistro = "REG-" + System.currentTimeMillis();

            // 1. Ejecutar inserción de mantenimiento
            psMant = conn.prepareStatement(sqlInsertMant);
            psMant.setString(1, codRegistro);
            psMant.setInt(2, idLogbook);
            psMant.setInt(3, idTecnico);
            psMant.setString(4, accionTomada); // Este es el texto que escribió el técnico
            psMant.setString(5, "COMPLETADO");
            psMant.setString(6, firmaTecnica);
            psMant.executeUpdate();

            // 2. Ejecutar actualización del avión
            psAeronave = conn.prepareStatement(sqlUpdateAeronave);
            psAeronave.setString(1, matricula);
            psAeronave.executeUpdate();

            // Si ambos procesos corren perfectamente, guardamos en disco de forma segura
            conn.commit();
            return true;

        } catch (SQLException | ConexionBDException e) {
            LOG.log(Level.SEVERE, "Error en transacción de liberación de aeronave (logbook={0}, matricula={1})",
                    new Object[]{idLogbook, matricula});
            LOG.log(Level.SEVERE, "Detalle de la excepción", e);

            if (conn != null) {
                try {
                    conn.rollback();
                    LOG.info("Rollback exitoso.");
                } catch (SQLException ex) {
                    LOG.log(Level.SEVERE, "Error en rollback", ex);
                }
            }
            return false;
        } finally {
            try {
                if (psMant != null) psMant.close();
                if (psAeronave != null) psAeronave.close();
                // OJO: NO cerramos "conn" (es la conexión Singleton compartida de toda la app).
                // Sí hay que devolverla a autoCommit=true, porque la dejamos en modo manual arriba
                // y si no se restaura, el resto de la app queda operando sin auto-commit.
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    } 
    
}

