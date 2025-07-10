/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consultoriamaisvida.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Paulo Marcasso
 */


/*A classe ConnectionFactory é responsável pela conexão entre o projeto e o banco de dados.*/
public class ConnectionFactory {
    
    private static Connection connection;

    private ConnectionFactory() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/consultoriaMaisVida", "root", "");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver JDBC não encontrado.", e);
            }
        }
        return connection;
    }
    
}
