package DAO;

import Model.Buy;
import Model.Client;
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

    public boolean updateAuthorized(String id)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE buy SET authorized = TRUE WHERE id = ?");
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
        
    public Buy view(String id)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM buy WHERE id = ?");
            pstmt.setString(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            Buy buy = new Buy();
            while (rs.next()) {
                buy.setId(rs.getString("id"));
                buy.setDate(rs.getString("date"));
                buy.setTotalPrice(rs.getDouble("total_price"));
                buy.setAuthorized(rs.getBoolean("authorized"));
                buy.setAddressId(rs.getString("address_id"));
                buy.setClientId(rs.getInt("client_id"));
            }
            
            pstmt.close();
            this.conn.close();
            
            return buy;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public List<Buy> getClientRequests(int clientId)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM buy WHERE client_id = ?");
            pstmt.setInt(1, clientId);
            
            ResultSet rs = pstmt.executeQuery();

            List<Buy> listBuy = new ArrayList<Buy>();
            
            while (rs.next()) {
                Buy buy = new Buy();
                buy.setId(rs.getString("id"));
                buy.setAuthorized(rs.getBoolean("authorized"));
                buy.setDate(rs.getString("date"));
                buy.setClientId(rs.getInt("client_id"));
                buy.setTotalPrice(rs.getDouble("total_price"));
                buy.setAddressId(rs.getString("address_id"));
                
                BuyProductDAO buyProductDAO = new BuyProductDAO();
                buy.setListBuyProduct(buyProductDAO.getClientRequests(buy.getId()));
                
                listBuy.add(buy);
            }
            pstmt.execute();
            pstmt.close();
            
            return listBuy;
            
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public List<Buy> getBuyRequests()
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("select buy.*, clients.* from buy, clients WHERE buy.client_id = clients.id AND buy.authorized = FALSE");
            
            ResultSet rs = pstmt.executeQuery();

            List<Buy> listBuy = new ArrayList<Buy>();
            
            while (rs.next()) {
                Buy buy = new Buy();
                Client client = new Client();
                buy.setId(rs.getString("buy.id"));
                buy.setAuthorized(rs.getBoolean("buy.authorized"));
                buy.setDate(rs.getString("buy.date"));
                buy.setClientId(rs.getInt("buy.client_id"));
                buy.setTotalPrice(rs.getDouble("buy.total_price"));
                buy.setAddressId(rs.getString("buy.address_id"));
                
                client.setName(rs.getString("clients.name"));
                client.setCpf(rs.getString("clients.cpf"));
                client.setEmail(rs.getString("clients.email"));
                client.setCellphone(rs.getString("clients.cellphone"));

                buy.setClient(client);
                
                listBuy.add(buy);
            }
            pstmt.execute();
            pstmt.close();
            
            return listBuy;
            
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
        public double getTotalMonthlyAmount() throws SQLException 
        {
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(total_price) as total_price FROM buy WHERE YEAR(date) = YEAR(now()) AND MONTH(date) =  MONTH(now())");
                ResultSet rs = pstmt.executeQuery();
                double count = 0;
                while (rs.next()) {
                    count = rs.getDouble("total_price");
                }
                pstmt.execute();
                pstmt.close();
                return count;
            } catch (SQLException e) {
                this.conn.close();
                System.out.println("Error: " + e.getMessage());
                return 0;
            }
        }
    
        public int getTotalMonthlyPurchases() throws SQLException 
        {
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id) as buys FROM buy WHERE YEAR(date) = YEAR(now()) AND MONTH(date) =  MONTH(now())");
                ResultSet rs = pstmt.executeQuery();
                int count = 0;
                while (rs.next()) {
                    count = rs.getInt("buys");
                }
                pstmt.execute();
                pstmt.close();
                return count;
            } catch (SQLException e) {
                this.conn.close();
                System.out.println("Error: " + e.getMessage());
                return 0;
            }
        }
        
        public int getTotalPurchasesToAccept() throws SQLException 
        {
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id) as buys FROM buy WHERE buy.authorized = FALSE");
                ResultSet rs = pstmt.executeQuery();
                int count = 0;
                while (rs.next()) {
                    count = rs.getInt("buys");
                }
                pstmt.execute();
                pstmt.close();
                this.conn.close();
                return count;
            } catch (SQLException e) {
                this.conn.close();
                System.out.println("Error: " + e.getMessage());
                return 0;
            }
        }
}
