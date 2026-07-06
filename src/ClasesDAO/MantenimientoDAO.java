package ClasesDAO;

import BaseDeDatos.ConexionBD;
import ClasesDTO.ReportePendienteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MantenimientoDAO {

    // Método para obtener los reportes que necesitan mantenimiento
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
        } catch (SQLException e) {
            System.err.println("Error al obtener reportes pendientes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexiones: " + e.getMessage());
            }
        }
        return listaReportes;
    }

// Método transaccional complejo para liberar el avión
    public boolean registrarLiberacionAeronave(int idLogbook, String matricula, int idTecnico, String accionTomada) {
        Connection conn = null;
        PreparedStatement psMant = null;
        PreparedStatement psAeronave = null;
        
        // Query 1: Insertar la acción tomada en el registro de mantenimiento
        String sqlInsertMant = "INSERT INTO registros_mantenimiento (id_logbook, id_empleado, fecha_mantenimiento, descripcion_trabajo) " +
                               "VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
        
        // Query 2: Cambiar el estado de la aeronave a OPERATIVO
        String sqlUpdateAeronave = "UPDATE aeronaves SET estado_tecnico = 'APTO' WHERE matricula = ?";

        try {
            conn = ConexionBD.getInstancia().getConexion();
            conn.setAutoCommit(false); // ¡CRÍTICO! Iniciamos la transacción manual

            // 1. Ejecutar inserción de mantenimiento
            psMant = conn.prepareStatement(sqlInsertMant);
            psMant.setInt(1, idLogbook);
            psMant.setInt(2, idTecnico);
            psMant.setString(3, accionTomada); // Este es el texto que escribió el técnico
            psMant.executeUpdate();

            // 2. Ejecutar actualización del avión
            psAeronave = conn.prepareStatement(sqlUpdateAeronave);
            psAeronave.setString(1, matricula);
            psAeronave.executeUpdate();

            // Si ambos procesos corren perfectamente, guardamos en disco de forma segura
            conn.commit();
            return true;

        } catch (SQLException e) {
            // Esto nos dirá exactamente QUÉ columna falla y POR QUÉ
            System.err.println("--- ERROR SQL DETALLADO ---");
            System.err.println("Mensaje: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código Error: " + e.getErrorCode());

            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Rollback exitoso.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            try {
                if (psMant != null) psMant.close();
                if (psAeronave != null) psAeronave.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    } 
    
}

//System.err.println("Error en transacción de liberación: " + e.getMessage());
//            if (conn != null) {
//                try {
//                    conn.rollback(); // Si algo falla, deshacemos todo para evitar corrupción de datos
//                    System.out.println("Rollback ejecutado exitosamente.");
//                } catch (SQLException ex) {
//                    System.err.println("Error al hacer rollback: " + ex.getMessage());
//                }
//            }
//            return false;