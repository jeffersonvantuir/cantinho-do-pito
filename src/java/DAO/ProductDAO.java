package DAO;

import Model.Product;
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
public class ProductDAO {

    private Connection conn;

    public ProductDAO() {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List index() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM products");

            ResultSet rs = pstmt.executeQuery();

            List<Product> listProducts = new ArrayList<Product>();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImage(rs.getString("image"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setBrandId(rs.getInt("brand_id"));

                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategory(categoryDAO.view(product.getCategoryId()));

                BrandDAO brandDAO = new BrandDAO();
                product.setBrand(brandDAO.view(product.getBrandId()));

                listProducts.add(product);
            }

            pstmt.close();
            rs.close();

            this.conn.close();

            return listProducts;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public List index(int categoryId) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM products WHERE category_id = ?");
            pstmt.setInt(1, categoryId);
            
            ResultSet rs = pstmt.executeQuery();

            List<Product> listProducts = new ArrayList<Product>();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImage(rs.getString("image"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setBrandId(rs.getInt("brand_id"));

                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategory(categoryDAO.view(product.getCategoryId()));

                BrandDAO brandDAO = new BrandDAO();
                product.setBrand(brandDAO.view(product.getBrandId()));

                listProducts.add(product);
            }

            pstmt.close();
            rs.close();

            this.conn.close();

            return listProducts;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public List index(String search) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM products p, categories c, brands b "
                    + "WHERE p.category_id = c.id AND p.brand_id = b.id AND"
                    + " (p.name LIKE ? OR c.name LIKE ? OR b.name LIKE ?)");
            pstmt.setString(1, '%'+search+'%');
            pstmt.setString(2, '%'+search+'%');
            pstmt.setString(3, '%'+search+'%');
            
            ResultSet rs = pstmt.executeQuery();

            List<Product> listProducts = new ArrayList<Product>();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImage(rs.getString("image"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setBrandId(rs.getInt("brand_id"));

                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategory(categoryDAO.view(product.getCategoryId()));

                BrandDAO brandDAO = new BrandDAO();
                product.setBrand(brandDAO.view(product.getBrandId()));

                listProducts.add(product);
            }

            pstmt.close();
            rs.close();

            this.conn.close();

            return listProducts;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public boolean add(Product product) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO products (name, description, price, stock, image, category_id, brand_id)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setString(5, product.getImage());
            pstmt.setInt(6, product.getCategoryId());
            pstmt.setInt(7, product.getBrandId());
            pstmt.executeUpdate();
            pstmt.close();

            this.conn.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean edit(Product product) {
        try {
            PreparedStatement pstmt = null;

            if (product.getImage() == null) {
                pstmt = conn.prepareStatement("UPDATE products SET name = ?, description = ?, price = ?, stock = ?, category_id = ?, brand_id = ?"
                        + " WHERE id = ?");
                pstmt.setString(1, product.getName());
                pstmt.setString(2, product.getDescription());
                pstmt.setDouble(3, product.getPrice());
                pstmt.setInt(4, product.getStock());
                pstmt.setInt(5, product.getCategoryId());
                pstmt.setInt(6, product.getBrandId());
                pstmt.setInt(7, product.getId());
            } else {
                pstmt = conn.prepareStatement("UPDATE products SET name = ?, description = ?, price = ?, stock = ?, image = ?, category_id = ?, brand_id = ?"
                        + " WHERE id = ?");
                pstmt.setString(1, product.getName());
                pstmt.setString(2, product.getDescription());
                pstmt.setDouble(3, product.getPrice());
                pstmt.setInt(4, product.getStock());
                pstmt.setString(5, product.getImage());
                pstmt.setInt(6, product.getCategoryId());
                pstmt.setInt(7, product.getBrandId());
                pstmt.setInt(8, product.getId());
            }

            pstmt.executeUpdate();
            pstmt.close();

            this.conn.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Product view(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            Product product = new Product();

            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImage(rs.getString("image"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setBrandId(rs.getInt("brand_id"));

                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategory(categoryDAO.view(product.getCategoryId()));

                BrandDAO brandDAO = new BrandDAO();
                product.setBrand(brandDAO.view(product.getBrandId()));

            }

            pstmt.close();
            rs.close();


            return product;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public boolean delete(Product product) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM products WHERE id = ?");

            pstmt.setInt(1, product.getId());
            pstmt.executeUpdate();
            pstmt.close();

            this.conn.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List requests() throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT pr.id, pr.name, pr.description, pr.price, pr.stock, pr.brand_id, pr.category_id, pr.image, count(re.product_id) as requests FROM requests re, products pr  WHERE re.product_id = pr.id AND re.status = FALSE GROUP BY pr.id, pr.name, pr.description, pr.price, pr.stock, pr.brand_id, pr.category_id, pr.image ORDER BY pr.name");

            ResultSet rs = pstmt.executeQuery();

            List<Product> listProducts = new ArrayList<Product>();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("pr.id"));
                product.setName(rs.getString("pr.name"));
                product.setDescription(rs.getString("pr.description"));
                product.setPrice(rs.getDouble("pr.price"));
                product.setStock(rs.getInt("pr.stock"));
                product.setImage(rs.getString("pr.image"));
                product.setCategoryId(rs.getInt("pr.category_id"));
                product.setBrandId(rs.getInt("pr.brand_id"));
                product.setRequests(rs.getInt("requests"));

                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategory(categoryDAO.view(product.getCategoryId()));

                BrandDAO brandDAO = new BrandDAO();
                product.setBrand(brandDAO.view(product.getBrandId()));

                listProducts.add(product);
            }

            pstmt.close();
            rs.close();
            this.conn.close();

            return listProducts;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            this.conn.close();
            return null;
        }
    }

    public int lowStock() throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(pr.id) as products FROM products pr WHERE pr.stock < 10");
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("products");
            }

            pstmt.close();
            rs.close();
            this.conn.close();

            return count;
        } catch (SQLException e) {
            this.conn.close();
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }
    
    public List listLowStock() throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM products WHERE stock < 10");

            ResultSet rs = pstmt.executeQuery();

            List<Product> listProducts = new ArrayList<Product>();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImage(rs.getString("image"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setBrandId(rs.getInt("brand_id"));

                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategory(categoryDAO.view(product.getCategoryId()));

                BrandDAO brandDAO = new BrandDAO();
                product.setBrand(brandDAO.view(product.getBrandId()));

                listProducts.add(product);
            }

            pstmt.close();
            rs.close();

            this.conn.close();

            return listProducts;

        } catch (SQLException e) {
            this.conn.close();
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
