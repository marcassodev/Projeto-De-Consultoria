/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consultoriamaisvida.dao;

import consultoriamaisvida.persistencia.ConnectionFactory;
import consultoriamaisvida.model.Cliente;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Marcasso
 */


/*A classe ClienteDAO é responsável pela comunicação entre a aplicação e o banco de dados, ou seja,
ela é responsável por realizar as operações no banco de dados.
*/

public class ClienteDAO {
    
     /*Método para cadastrar um cliente no banco.*/
    public int cadastrarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, data, rg, cpf, telefone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setDate(2, new java.sql.Date(cliente.getData().getTime()));
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getCpf());
            ps.setString(5, cliente.getTelefone());

            return ps.executeUpdate();
        }
    }
    
    /*Método para criar uma lista e buscar clientes por seus nomes.*/
    public List<Cliente> buscarClientesPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nome + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setData(rs.getDate("data"));
                    c.setRg(rs.getString("rg"));
                    c.setCpf(rs.getString("cpf"));
                    c.setTelefone(rs.getString("telefone"));
                    clientes.add(c);
                }
            }
        }
        return clientes;
    }
    
    /*Método para excluir um cliente do banco.*/
    public boolean excluirCliente(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int affected = ps.executeUpdate();
            return affected > 0;
        }
    }
    
    /*Método para verificar se existe um cliente específico no banco.*/
    public boolean verificarClienteExiste(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM clientes WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}

