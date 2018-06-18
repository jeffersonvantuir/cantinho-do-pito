package DAO;

import Helpers.Helper;
import Model.City;
import Model.Client;
import Model.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jefferson
 */
public class ClientDAO {
    private Connection conn;
    private Helper helper = new Helper();
    
    public ClientDAO()
    {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public Client login(String email, String password)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM clients WHERE email = ? AND password = ?;");
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            Client client = new Client();
            while (rs.next()) {
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setEmail(rs.getString("email"));
                client.setAddress(rs.getString("address"));
                client.setDistrict(rs.getString("district"));
                client.setComplement(rs.getString("complement"));
                client.setHomeNumber(rs.getInt("home_number"));
                client.setZipcode(rs.getString("zipcode"));
                client.setCityId(rs.getInt("city_id"));
                
                CityDAO cityDAO = new CityDAO();
                client.setCity(cityDAO.view(rs.getInt("city_id")));
                
                client.setIsAdmin(rs.getBoolean("is_admin"));
            }

            pstmt.close();
            rs.close();
            
            return client;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }        
    }
    
    public List index()
    {
       try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT cl.*, ci.*, st.* FROM clients cl, cities ci, states st "
                            + "WHERE ci.id = cl.city_id AND ci.state_id = st.id;");

            ResultSet rs = pstmt.executeQuery();

            List<Client> listClients = new ArrayList<Client>();

            while (rs.next()) {
                Client client = new Client();
                City city = new City();
                State state = new State();
                
                client.setId(rs.getInt("cl.id"));
                client.setName(rs.getString("cl.name"));
                client.setEmail(rs.getString("cl.email"));
                client.setCpf(rs.getString("cl.cpf"));
                client.setCellphone(rs.getString("cl.cellphone"));
                client.setPassword(rs.getString("cl.password"));
                try {
                    client.setBirthday(helper.formatterDateUsage(rs.getString("cl.birthday")));
                } catch (ParseException ex) {
                    Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                client.setAddress(rs.getString("cl.address"));
                client.setZipcode(rs.getString("cl.zipcode"));
                client.setHomeNumber(Integer.parseInt(rs.getString("cl.home_number")));
                client.setComplement(rs.getString("cl.complement"));
                client.setDistrict(rs.getString("cl.district"));
                
                state.setId(rs.getInt("st.id"));
                state.setName(rs.getString("st.name"));
                state.setUf(rs.getString("st.uf"));
                
                city.setId(rs.getInt("ci.id"));
                city.setName(rs.getString("ci.name"));
                city.setStateId(rs.getInt("ci.state_id"));

                city.setState(state);
                client.setCity(city);
                
                listClients.add(client);
            }

            pstmt.close();
            rs.close();

            return listClients;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean add(Client client)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO clients (name, cpf, email, password, cellphone, birthday, address, zipcode, district, complement, home_number, city_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, client.getName());
            pstmt.setString(2, client.getCpf());
            pstmt.setString(3, client.getEmail());
            pstmt.setString(4, client.getPassword());
            pstmt.setString(5, client.getCellphone());
            try {
                pstmt.setString(6, helper.formatterDateDB(client.getBirthday()));
            } catch (ParseException ex) {
                Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            pstmt.setString(7, client.getAddress());
            pstmt.setString(8, client.getZipcode());
            pstmt.setString(9, client.getDistrict());
            pstmt.setString(10, client.getComplement());
            pstmt.setInt(11, client.getHomeNumber());
            pstmt.setInt(12, client.getCityId());
            pstmt.executeUpdate();
            
            pstmt.close();
            return true;
            
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
    
    public boolean edit(Client client)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE clients SET name = ?, cpf = ?, email = ?, cellphone = ?,"
                    + " birthday = ?, address = ?, zipcode = ?, district = ?, complement = ?, "
                    + "home_number = ?, city_id = ? WHERE id = ?");

            pstmt.setString(1, client.getName());
            pstmt.setString(2, client.getCpf());
            pstmt.setString(3, client.getEmail());
            pstmt.setString(4, client.getCellphone());
            try {
                pstmt.setString(5, helper.formatterDateDB(client.getBirthday()));
            } catch (ParseException ex) {
                Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            pstmt.setString(6, client.getAddress());
            pstmt.setString(7, client.getZipcode());
            pstmt.setString(8, client.getDistrict());
            pstmt.setString(9, client.getComplement());
            pstmt.setInt(10, client.getHomeNumber());
            pstmt.setInt(11, client.getCityId());
            pstmt.setInt(12, client.getId());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
    
    public Client view(int id)
    {
       try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM clients WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            Client client = new Client();

            while (rs.next()) {
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setCpf(rs.getString("cpf"));
                client.setEmail(rs.getString("email"));
                client.setCellphone(rs.getString("cellphone"));
                client.setPassword(rs.getString("password"));
                try {
                    client.setBirthday(helper.formatterDateUsage(rs.getString("birthday")));
                } catch (ParseException ex) {
                    Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                client.setAddress(rs.getString("address"));
                client.setZipcode(rs.getString("zipcode"));
                client.setComplement(rs.getString("complement"));
                client.setHomeNumber(rs.getInt("home_number"));
                client.setDistrict(rs.getString("district"));
                client.setCityId(rs.getInt("city_id"));
            }

            pstmt.close();
            rs.close();

            return client;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public boolean delete(Client client)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM clients WHERE id = ?");
            pstmt.setInt(1, client.getId());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
           e.printStackTrace();
            return false;
        }
    }
    
    public boolean cpfAlready(int id, String cpf)
    {
       try {
            PreparedStatement pstmt = null;
            if (id != 0) {
                pstmt = conn.prepareStatement("SELECT * FROM clients WHERE cpf = ? AND id != ?");
                pstmt.setString(1, cpf);
                pstmt.setInt(2, id);
            } else {
                pstmt = conn.prepareStatement("SELECT * FROM clients WHERE cpf = ?");
                pstmt.setString(1, cpf);
            }
            
            ResultSet rs = pstmt.executeQuery();

            Client client = new Client();

            if (rs.next()) {
                pstmt.close();
                rs.close();
                return true;
            }

            pstmt.close();
            rs.close();

            return false;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean emailAlready(int id, String email)
    {
       try {
            PreparedStatement pstmt = null;
            if (id != 0) {
                pstmt = conn.prepareStatement("SELECT * FROM clients WHERE email = ? AND id != ?");
                pstmt.setString(1, email);
                pstmt.setInt(2, id);
            } else {
                pstmt = conn.prepareStatement("SELECT * FROM clients WHERE email = ?");
                pstmt.setString(1, email);
            }
            ResultSet rs = pstmt.executeQuery();

            Client client = new Client();

            if (rs.next()) {
                pstmt.close();
                rs.close();
                return true;
            }

            pstmt.close();
            rs.close();

            return false;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean passwordMathConfirmPassword(String password, String confirmPassword)
    {
       if (password.equals(confirmPassword)) {
           return true;
       }
       
       return false;
    }
    
    public String removeMaskCPF(String cpf)
    {
        cpf = cpf.replaceAll("[.-]", "");
        return cpf;
    }
}
