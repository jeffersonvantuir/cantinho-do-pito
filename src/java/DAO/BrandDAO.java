
package DAO;

import Model.Brand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class BrandDAO {
    private Connection conn;
    
    public BrandDAO()
    {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public List index()
    {
       try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM brands ORDER BY NAME");

            ResultSet rs = pstmt.executeQuery();

            List<Brand> listBrands = new ArrayList<Brand>();

            while (rs.next()) {
                Brand brand = new Brand();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));

                listBrands.add(brand);
            }

            pstmt.close();
            rs.close();

            return listBrands;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean add(Brand brand)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO brands (name) VALUES (?)");

            pstmt.setString(1, brand.getName());
            pstmt.executeUpdate();
            pstmt.close();
            
            return true;
            
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
    
    public boolean edit(Brand brand)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE brands SET name = ? WHERE id = ?");

            pstmt.setString(1, brand.getName());
            pstmt.setInt(2, brand.getId());
            pstmt.executeUpdate();
            pstmt.close();
            
            return true;
            
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
    
    public Brand view(int id)
    {
       try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM brands WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            Brand brand = new Brand();

            while (rs.next()) {
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));
            }

            pstmt.close();
            rs.close();

            return brand;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean delete(Brand brand)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM brands WHERE id = ?");

            pstmt.setInt(1, brand.getId());
            pstmt.executeUpdate();
            pstmt.close();
            
            return true;
            
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
}
