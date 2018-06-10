
package DAO;

import Model.Category;
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
public class CategoryDAO {
    private Connection conn;
    
    public CategoryDAO()
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM categories ORDER BY NAME");

            ResultSet rs = pstmt.executeQuery();

            List<Category> listCategories = new ArrayList<Category>();

            while (rs.next()) {
                Category client = new Category();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setDescription(rs.getString("description"));

                listCategories.add(client);
            }

            pstmt.close();
            rs.close();

            return listCategories;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean add(Category category)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO categories (name, description) VALUES (?, ?)");

            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
    
    public boolean edit(Category category)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE categories SET name = ?, description = ? WHERE id = ?");

            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.setInt(3, category.getId());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
    
    public Category view(int id)
    {
       try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM categories WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            Category category = new Category();

            while (rs.next()) {
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
            }

            pstmt.close();
            rs.close();

            return category;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean delete(Category category)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM categories WHERE id = ?");

            pstmt.setInt(1, category.getId());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
}
