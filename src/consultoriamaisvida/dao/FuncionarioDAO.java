
package consultoriamaisvida.dao;

import consultoriamaisvida.persistencia.ConnectionFactory;
import consultoriamaisvida.model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Marcasso
 */


/*A classe FuncionárioDAO é responsável pela comunicação entre a aplicação e o banco de dados, ou seja,
ela é responsável por realizar as operações no banco de dados.
*/

public class FuncionarioDAO {
    
    /*Método para cadastrar um usuário no banco.*/
    public int cadastrarFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, cpf, area, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getAreaAtuacao());
            ps.setString(4, funcionario.getTelefone());

            return ps.executeUpdate();
        }
    }

    /*Método para criar uma lista e buscar os usuários já inseridos no banco.*/   
    public List<Funcionario> buscarFuncionariosPorArea(String areaAtuacao) throws SQLException {
        String sql = "SELECT * FROM funcionarios WHERE area LIKE ?";
        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + areaAtuacao + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Funcionario f = new Funcionario();
                    f.setId(rs.getInt("id"));
                    f.setNome(rs.getString("nome"));
                    f.setCpf(rs.getString("cpf"));
                    f.setAreaAtuacao(rs.getString("area"));
                    f.setTelefone(rs.getString("telefone"));
                    funcionarios.add(f);
                }
            }
        }
        return funcionarios;
    }
    /*Método para excluir um usuário do banco.*/
    public boolean excluirFuncionario(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int affected = ps.executeUpdate();
            return affected > 0;
        }
    }
    
    /*Método para verificar se existe um funcionário específico no banco.*/
    public boolean verificarFuncionarioExiste(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM funcionarios WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }   
}
