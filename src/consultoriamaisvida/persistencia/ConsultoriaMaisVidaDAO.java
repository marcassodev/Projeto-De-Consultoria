/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consultoriamaisvida.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author T-GAMER
 */
public class ConsultoriaMaisVidaDAO {
    
    private Connection conn;
    
    public void conectar() {
    try {
        if (conn == null || conn.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/consultoriaMaisVida", "root", "Paulinho967");
            System.out.println("Conexão realizada com sucesso");
        }
    } catch (ClassNotFoundException | SQLException ex) {
        System.out.println("Erro ao conectar: " + ex.getMessage());
    }
}


    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao desconectar: " + ex.getMessage());
        }
    }

    private String formatarData(String dataEntrada) throws ParseException {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
        return formatoBanco.format(formatoEntrada.parse(dataEntrada));
    }



    public List<Consultoria> getConsultorias(String idCliente) {
        String sql = "SELECT * FROM consultorias WHERE idCliente LIKE ?";
        try {
            conectar();// Garante que a conexão está estabelecida
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + idCliente + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                List<Consultoria> listaConsultorias = new ArrayList<>();
                while (rs.next()) {
                    Consultoria con = new Consultoria();
                    con.setId(rs.getInt("id"));
                    con.setData(rs.getString("data"));
                    con.setNumero(rs.getString("numero"));
                    con.setIdCliente(rs.getInt("idCliente"));
                    listaConsultorias.add(con);
                }
                return listaConsultorias;
            }
            //Catch para erro inexperado
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }


    public int CadastrarCon(Consultoria con) {
        int status;
        try {
            conectar();// Garante que a conexão está estabelecida
            String dataFormatada = formatarData(con.getData());

            String sql = "INSERT INTO consultorias (data, numero, idCliente) VALUES(?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, dataFormatada);
                st.setString(2, con.getNumero());
                st.setInt(3, con.getIdCliente());
                status = st.executeUpdate();
            }
            return status;
            //Catch para erro inexperado da Data
        } catch (ParseException e) {
            System.out.println("Erro no formato da data: " + e.getMessage());
            return -2;
            //Catch para erro inexperado
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar Consultoria: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
    public boolean excluirConsultoria(int id) {
    String sql = "DELETE FROM consultorias WHERE id = ?";
    
    try {
        conectar();

        // Verifica se a consultoria existe antes de excluir
        if (!verificarConsultoriaExiste(id)) {
            System.out.println("A consultoria com ID " + id + " não existe.");
            return false;
        }

        // Prepare o DELETE sem fechar a conexão antes do tempo
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        int rowsAffected = stmt.executeUpdate();

        stmt.close(); //  Fechar apenas o statement, mas manter a conexão ativa
        conn.close();

        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Erro ao excluir consultoria: " + e.getMessage());
        return false;
    }
}
    
    public boolean verificarConsultoriaExiste(int id) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) FROM consultorias WHERE id = ?";
    
        try {
            if (conn == null || conn.isClosed()) {
            conectar();
        }

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            existe = true;
        }

        rs.close();
        ps.close(); 
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao verificar consultoria: " + e.getMessage());
    }
    return existe;
}
    
    
    public boolean verificarClienteExiste(int idCliente) {
    boolean existe = false;
    try {
        String sql = "SELECT COUNT(*) FROM clientes WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idCliente);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next() && rs.getInt(1) > 0) {
            existe = true;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao verificar cliente: " + e.getMessage());
    }
    return existe;
}


    
    public int CadastrarCliente(Clientes cli) {
        int status;
        try {
            conectar();// Garante que a conexão está estabelecida
            String dataFormatadaCli = formatarData(cli.getData());

            String sql = "INSERT INTO clientes (nome, data, rg, cpf, telefone) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, cli.getNome());
                st.setString(2, dataFormatadaCli);
                st.setString(3, cli.getRg());
                st.setString(4, cli.getCpf());
                st.setString(5, cli.getTelefone());
                
                status = st.executeUpdate();
            }
            return status;
            //Catch para erro inexperado da Data
        } catch (ParseException e) {
            System.out.println("Erro no formato da data: " + e.getMessage());
            return -2;
            //Catch para erro inexperado
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar Cliente: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
    
    public List<Clientes> getClientes(String nome) {
        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
        try {
            conectar();// Garante que a conexão está estabelecida
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                List<Clientes> listaClientes = new ArrayList<>();
                while (rs.next()) {
                    Clientes c = new Clientes();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setData(rs.getString("data"));
                    c.setRg(rs.getString("rg"));
                    c.setCpf(rs.getString("cpf"));
                    c.setTelefone(rs.getString("telefone"));
                    
                    listaClientes.add(c);
                }
                return listaClientes;
            }
            //Catch para erro inexperado
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
    
    public boolean excluirCliente(int id) {
    String sql = "DELETE FROM clientes WHERE id = ?";
    try { 
        conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        int rowsAffected = stmt.executeUpdate();
        
        stmt.close();
        conn.close();
        
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Erro ao excluir cliente: " + e.getMessage());
        return false;
    }
}
    
    public int CadastrarFunc(Funcionarios fun) {
        int status;
        try {
            conectar();// Garante que a conexão está estabelecida
           

            String sql = "INSERT INTO funcionarios (nome, cpf, area, telefone) VALUES(?, ?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, fun.getNome());
                st.setString(2, fun.getCpf());
                st.setString(3, fun.getAreaAtuacao());
                st.setString(4, fun.getTelefone());
                
                status = st.executeUpdate();
            }
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar Funcionario: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
    public List<Funcionarios> getFuncionarios(String areaAtuacao) {
        String sql = "SELECT * FROM funcionarios WHERE area LIKE ?";
        try {
            conectar();// Garante que a conexão está estabelecida
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + areaAtuacao + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                List<Funcionarios> listaFunc = new ArrayList<>();
                while (rs.next()) {
                    Funcionarios f = new Funcionarios();
                    f.setId(rs.getInt("id"));
                    f.setNome(rs.getString("nome"));
                    f.setCpf(rs.getString("cpf"));
                    f.setAreaAtuacao(rs.getString("area"));
                    f.setTelefone(rs.getString("telefone"));
                    
                    listaFunc.add(f);
                }
                return listaFunc;
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
    
    public boolean excluirFuncionario(int id) {
    String sql = "DELETE FROM funcionarios WHERE id = ?";
    try { 
        conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        int rowsAffected = stmt.executeUpdate();
        
        stmt.close(); //  Fechar apenas o statement, mas manter a conexão ativa
        conn.close();
        
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Erro ao excluir funcionario: " + e.getMessage());
        return false;
    }
}
    
    public boolean verificarFuncionarioExiste(int id) {
    boolean existe = false;
    try {
        String sql = "SELECT COUNT(*) FROM funcionarios WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next() && rs.getInt(1) > 0) {
            existe = true;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao verificar funcionario: " + e.getMessage());
    }
    return existe;
}
    
}
