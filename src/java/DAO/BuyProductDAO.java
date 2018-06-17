package DAO;

import Model.BuyProduct;
import java.sql.Connection;

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
}
