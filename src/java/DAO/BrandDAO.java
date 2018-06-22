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

    public BrandDAO() {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List index() throws SQLException {
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
            this.conn.close();
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public boolean add(Brand brand) throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO brands (name) VALUES (?)");

            pstmt.setString(1, brand.getName());
            pstmt.executeUpdate();
            pstmt.close();
            this.conn.close();
            return true;

        } catch (SQLException e) {
            this.conn.close();
            e.printStackTrace();
            return false;
        }
    }

    public boolean edit(Brand brand) throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE brands SET name = ? WHERE id = ?");

            pstmt.setString(1, brand.getName());
            pstmt.setInt(2, brand.getId());
            pstmt.executeUpdate();
            pstmt.close();
            this.conn.close();
            return true;

        } catch (SQLException e) {
            this.conn.close();
            e.printStackTrace();
            return false;
        }
    }

    public Brand view(int id) throws SQLException {
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
            this.conn.close();
            return brand;

        } catch (SQLException e) {
            this.conn.close();
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public boolean delete(Brand brand) throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM brands WHERE id = ?");

            pstmt.setInt(1, brand.getId());
            pstmt.executeUpdate();
            pstmt.close();
            this.conn.close();
            return true;

        } catch (SQLException e) {
            this.conn.close();
            e.printStackTrace();
            return false;
        }
    }
}
