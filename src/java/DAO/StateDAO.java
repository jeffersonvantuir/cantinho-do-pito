package DAO;

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
public class StateDAO {
    private Connection conn;
    
    public StateDAO()
    {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public List list()
    {
       try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM states ORDER BY name");
            ResultSet rs = pstmt.executeQuery();

            List<State> listStates = new ArrayList<State>();

            while (rs.next()) {
                State state = new State();
                state.setId(rs.getInt("id"));
                state.setName(rs.getString("name"));

                listStates.add(state);
            }

            pstmt.close();
            rs.close();

            return listStates;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public State view(int id)
    {
       try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM states WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            State state = new State();
            while (rs.next()) {
                state.setId(rs.getInt("id"));
                state.setName(rs.getString("name"));
                state.setUf(rs.getString("uf"));
            }
            
            pstmt.close();
            rs.close();

            return state;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
