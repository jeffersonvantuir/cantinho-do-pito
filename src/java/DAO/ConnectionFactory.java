package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jefferson
 */
public class ConnectionFactory {
    public static Connection getConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cantinho_do_pito_db", "root", "root");
            System.out.println("Conectado");

            return conn;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
