package BasicLogin;

import java.sql.*;

import DatabaseUtilities.ConnectDB;


public class loginDatabase {
    private static final String query = "SELECT user_id FROM users WHERE role = ? AND username = ? AND password = ?";
    
    public static int checkLogin(String username, String password, String userType) {
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                System.out.println("Connect database successfully!");
                
                stmt.setString(1, userType);
                stmt.setString(2, username);
                stmt.setString(3, password);
            
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return result.getInt("user_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
