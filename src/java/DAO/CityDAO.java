package DAO;

import Model.City;
import Model.State;
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
public class CityDAO {
    private Connection conn;
    
    public CityDAO()
    {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public List list(State state)
    {
       try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cities WHERE state_id = ? ORDER BY name ASC");
            pstmt.setInt(1, state.getId());
            ResultSet rs = pstmt.executeQuery();

            List<City> listCities = new ArrayList<City>();

            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));

                listCities.add(city);
            }

            pstmt.close();
            rs.close();

            return listCities;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public int getStateIdByCityId(int id)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT s.id as state_id FROM states s, cities c WHERE s.id = c.state_id AND c.id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            int state_id = 0;
            while (rs.next()) {
                state_id = rs.getInt("state_id");
            }
            
            pstmt.close();
            rs.close();
            
            return state_id;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return 0;
    }
}
