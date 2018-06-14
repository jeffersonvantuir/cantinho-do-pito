package DAO;

import Model.Client;
import Model.Product;
import Model.Request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ricardo
 */
public class RequestDAO {

    private Connection conn;

    public RequestDAO() 
    {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean add(Request request) 
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO requests (product_id, client_id) VALUES (?, ?)");

            pstmt.setString(1, String.valueOf(request.getProduct_id()));
            pstmt.setString(2, String.valueOf(request.getClient_id()));
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Request view(int id) 
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT re.*, pr.*, cli.* FROM requests re, products pr, clients cl WHERE re.product_id = pr.id AND re.client_id = cl.id AND re.id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            Request request = new Request();
            Product product = new Product();
            Client client = new Client();

            while (rs.next()) {
                request.setId(rs.getInt("id"));
                request.setStatus(Boolean.parseBoolean(rs.getString("re.status")));
                request.setProduct_id(Integer.parseInt(rs.getString("re.product_id")));
                request.setClient_id(Integer.parseInt(rs.getString("re.client_id")));

                product.setId(Integer.parseInt(rs.getString("pr.id")));
                product.setName(rs.getString("pr.name"));
                product.setImage(rs.getString("pr.image"));
                product.setPrice(Integer.parseInt(rs.getString("pr.price")));

                client.setId(Integer.parseInt(rs.getString("pr.id")));
                client.setName(rs.getString("pr.name"));
                client.setEmail(rs.getString("pr.email"));

                request.setProduct(product);
                request.setClient(client);
            }

            pstmt.close();
            rs.close();

            return request;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
