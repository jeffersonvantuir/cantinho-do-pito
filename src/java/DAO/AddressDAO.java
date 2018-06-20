package DAO;

import Model.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jefferson
 */
public class AddressDAO {
    private Connection conn;
    
    public AddressDAO()
    {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public boolean add(Address address)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO address (id, address, zipcode, home_number, complement, district, city_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);");
            pstmt.setString(1, address.getId());
            pstmt.setString(2, address.getAddress());
            pstmt.setString(3, address.getZipcode());
            pstmt.setInt(4, address.getHomeNumber());
            pstmt.setString(5, address.getComplement());
            pstmt.setString(6, address.getDistrict());
            pstmt.setInt(7, address.getCityId());
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
