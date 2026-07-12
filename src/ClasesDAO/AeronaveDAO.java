package ClasesDAO;

import BaseDeDatos.ConexionBD;
import Clases.Aeronave;
import Enumeradores.EstadoAeronave;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* ¿Para qué sirve?: Esta clase se encarga de traer desde la base de datos la información de todas las aeronaves de la flota
   Clases que la utilizan: OficialOperaciones_GUI, TecnicoMantenimiento_GUI, VueloOperativoBuilder
   Índice de Métodos: obtenerFlota */
public class AeronaveDAO {

    private static final Logger LOG = Logger.getLogger(AeronaveDAO.class.getName());

    // ==========================================
    // MÉTODO PARA LISTAR TODA LA FLOTA
    // ==========================================
    // Descripción: Consulta en la base de datos todas las aeronaves registradas, con su modelo, estado técnico, capacidad de asientos, peso máximo de despegue y autonomía, y arma una lista de objetos Aeronave con esos datos. Esta lista es la que se usa, por ejemplo, en la pantalla de Gestión de Flota
    // Clases que lo usan: OficialOperaciones_GUI, TecnicoMantenimiento_GUI, VueloOperativoBuilder
    public List<Aeronave> obtenerFlota() {
        List<Aeronave> lista = new ArrayList<>();
        String sql = "SELECT matricula, modelo, estado_tecnico, capacidad_asientos, peso_maximo_despegue, autonomia_maxima_km " +
                     "FROM aeronaves ORDER BY matricula";

        try {
            Connection con = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aeronave a = new Aeronave();
                a.setMatricula(rs.getString("matricula"));
                a.setModelo(rs.getString("modelo"));
                a.setEstadoTecnico(EstadoAeronave.valueOf(rs.getString("estado_tecnico")));
                a.setCapacidadAsientos(rs.getInt("capacidad_asientos"));
                a.setPesoMaximoDespegue(rs.getDouble("peso_maximo_despegue"));
                a.setAutonomiaMaximaKm(rs.getDouble("autonomia_maxima_km"));
                lista.add(a);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al obtener la flota", e);
        }
        return lista;
    }
}
