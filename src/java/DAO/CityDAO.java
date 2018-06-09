package DAO;

import Model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jefferson
 */
public class CityDAO {
    private Connection conn;
    
    public CityDAO()
    {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public List list()
    {
       try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM clients");

            ResultSet rs = pstmt.executeQuery();

            List<Client> listClients = new ArrayList<Client>();

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));

                listClients.add(client);
            }

            pstmt.close();
            rs.close();

            return listClients;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean insert(Client client)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO clients (id) VALUES (?)");

            pstmt.setInt(1, client.getId());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(Client client)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE clients SET name = ? WHERE id = ?");

            pstmt.setString(1, client.getName());
            pstmt.setInt(2, client.getId());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
    
    public Client view(int id)
    {
       try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM clients WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            Client client = new Client();

            while (rs.next()) {
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
            }

            pstmt.close();
            rs.close();

            return client;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean delete(Client client)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM clients WHERE id = ?");

            pstmt.setInt(1, client.getId());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
}
