
package consultoriamaisvida.dao;

import consultoriamaisvida.persistencia.ConnectionFactory;
import consultoriamaisvida.model.Consultoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Paulo Marcasso
 */


/*A classe ClienteDAO é responsável pela comunicação entre a aplicação e o banco de dados, ou seja,
ela é responsável por realizar as operações no banco de dados.
*/

public class ConsultoriaDAO {
    private static final DateTimeFormatter FORMATTER_BANCO = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_INTERFACE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /*Método para cadastrar uma consultoria no banco.*/
    public int cadastrarConsultoria(Consultoria consultoria) throws SQLException {
        String sql = "INSERT INTO consultorias (data, numero, idCliente) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            LocalDate data = LocalDate.parse(consultoria.getData(), FORMATTER_INTERFACE);
            ps.setString(1, data.format(FORMATTER_BANCO));
            ps.setString(2, consultoria.getNumero());
            ps.setInt(3, consultoria.getIdCliente());

            return ps.executeUpdate();
        }
    }
    /*Método para criar uma lista e buscar as consultorias de acordo com clientes já inseridos no banco.*/ 
    public List<Consultoria> buscarConsultoriasPorCliente(String idCliente) throws SQLException {
        String sql = "SELECT * FROM consultorias WHERE idCliente LIKE ?";
        List<Consultoria> consultorias = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + idCliente + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Consultoria c = new Consultoria();
                    c.setId(rs.getInt("id"));
                    LocalDate data = LocalDate.parse(rs.getString("data"), FORMATTER_BANCO);
                    c.setData(data.format(FORMATTER_INTERFACE));
                    c.setNumero(rs.getString("numero"));
                    c.setIdCliente(rs.getInt("idCliente"));
                    consultorias.add(c);
                }
            }
        }
        return consultorias;
    }
    /*Método para excluir uma consultoria do banco.*/
    public boolean excluirConsultoria(int id) throws SQLException {
        String sql = "DELETE FROM consultorias WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int affected = ps.executeUpdate();
            return affected > 0;
        }
    }
    /*Método para verificar se existe uma consultoria específica no banco.*/
    public boolean verificarConsultoriaExiste(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM consultorias WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
