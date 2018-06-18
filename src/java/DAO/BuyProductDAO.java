package DAO;

import Model.Address;
import Model.BuyProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jefferson
 */
public class BuyProductDAO {
    private Connection conn;
    
    public BuyProductDAO()
    {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public boolean add(BuyProduct buyProduct)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO buy_products (amount, total_price, product_id, buy_id) "
                    + "VALUES (?, ?, ?, ?)");
            pstmt.setInt(1, buyProduct.getAmount());
            pstmt.setDouble(2, buyProduct.getTotalPrice());
            pstmt.setInt(3, buyProduct.getProductId());
            pstmt.setString(4, buyProduct.getBuyId());
            pstmt.executeUpdate();
            pstmt.close();
            
            this.conn.close();
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
