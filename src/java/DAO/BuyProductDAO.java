package DAO;

import Model.Address;
import Model.Buy;
import Model.BuyProduct;
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
public class BuyProductDAO {

    private Connection conn;

    public BuyProductDAO() {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean add(BuyProduct buyProduct) throws SQLException {
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
            this.conn.close();
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public List<BuyProduct> getClientRequests(String buyId) throws SQLException {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM buy_products bp WHERE bp.buy_id = ?");
            pstmt.setString(1, buyId);

            ResultSet rs = pstmt.executeQuery();

            List<BuyProduct> listBuyProducts = new ArrayList<BuyProduct>();

            while (rs.next()) {
                BuyProduct buyProduct = new BuyProduct();
                buyProduct.setId(rs.getInt("id"));
                buyProduct.setAmount(rs.getInt("amount"));
                buyProduct.setBuyId(rs.getString("buy_id"));
                buyProduct.setProductId(rs.getInt("product_id"));
                buyProduct.setTotalPrice(rs.getDouble("total_price"));

                ProductDAO productDAO = new ProductDAO();
                buyProduct.setProduct(productDAO.view(rs.getInt("product_id")));

                listBuyProducts.add(buyProduct);
            }
            pstmt.execute();
            pstmt.close();

            return listBuyProducts;

        } catch (SQLException e) {
            this.conn.close();
            System.out.println("Error: " + e.getMessage());

        }

        return null;
    }
}
