package ClasesDAO;

import BaseDeDatos.ConexionBD;
import Clases.Aeronave;
import Enumeradores.EstadoAeronave;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AeronaveDAO {

    // ===================================================================
    // MÉTODO PARA LISTAR TODA LA FLOTA (pantalla Gestión de Flota)
    // ===================================================================
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
            System.out.println("Error al obtener la flota: " + e.getMessage());
        }
        return lista;
    }
}
