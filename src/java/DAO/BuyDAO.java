package DAO;

import Model.Buy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jefferson
 */
public class BuyDAO {
    private Connection conn;
    
    public BuyDAO()
    {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public boolean add(Buy buy)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO buy (id, date, client_id, address_id, total_price) "
                    + "VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, buy.getId());
            pstmt.setString(2, buy.getDate());
            pstmt.setInt(3, buy.getClientId());
            pstmt.setString(4, buy.getAddressId());
            pstmt.setDouble(5, buy.getTotalPrice());
            
            pstmt.executeUpdate();
            pstmt.close();
            
            return true;
            
        } catch (SQLException e) {
            
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean buyExists(String id)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM buy WHERE id = ?");
            pstmt.setString(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                pstmt.close();
                return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateTotalPrice(String id, double totalPrice)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE buy SET total_price = ? WHERE id = ?");
            pstmt.setDouble(1, totalPrice);
            pstmt.setString(2, id);
            
            pstmt.executeUpdate();
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
