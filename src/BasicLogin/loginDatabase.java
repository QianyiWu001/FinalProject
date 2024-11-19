package BasicLogin;

import java.sql.*;


public class loginDatabase {
    public static int checkLogin(String username, String password, String userType) {
        String dbURL = "jdbc:mysql://localhost:3306/your_database"; //不知道要不要改
        String dbUser = "root";//要不要改
        String dbPassword = "password";//要不要改
        String query = "SELECT user_id FROM users WHERE role = ? AND username = ? AND password = ?";
        
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

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
