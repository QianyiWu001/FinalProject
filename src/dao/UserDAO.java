package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseUtilities.ConnectDB;
import entity.User;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        try {
            this.connection = ConnectDB.getConnection();
        } catch (SQLException e) {
            System.err.println("Database connection failed. Unable to proceed.");
            e.printStackTrace();
            throw new RuntimeException("Unable to establish database connection.", e);
        }
    }

    public boolean addUser(User user) {
        String query = "INSERT INTO users (user_id, username, password, role) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, user.getUserId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Unable to add user to database.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Unable to delete user from database.");
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateUser(int userId, String username, String password, String role) {
        String query = "UPDATE users SET username = ?, password = ?, role = ? WHERE user_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.setInt(4, userId);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Unable to update user in database.");
            e.printStackTrace();
            return false;
        }
    }

    public String getUserRole(int userId) {
        String query = "SELECT role FROM users WHERE user_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }
            return null;

        } catch (SQLException e) {
            System.out.println("Unable to fetch user role from database.");
            e.printStackTrace();
            return null;
        }
    }
    //generate id automatically
    public int generateUserId() {
        int maxId = 0;
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT MAX(user_id) AS max_id FROM users");
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                maxId = rs.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId + 1; 
    }

    // Retrieves user information by userId
    public String[] getUserById(int userId) {
        String query = "SELECT username, password, role FROM users WHERE user_id = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                
                return new String[] { username, password, role };
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user by ID from users table.");
            e.printStackTrace();
        }
        
        return null; // Return null if no user found or an error occurs
    }

public User validateLogin(String username, String password) {
    String query = "SELECT * FROM users WHERE username = ? AND password = ?";

    try (Connection conn = ConnectDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        }
        return null;

    } catch (SQLException e) {
        System.out.println("Unable to validate login.");
        e.printStackTrace();
        return null;
    }
}

    public int getUserIdByUsername(String username) {
        String query = "SELECT user_id FROM users WHERE username = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id");
            }
            return -1;

        } catch (SQLException e) {
            System.out.println("Unable to fetch user ID from database.");
            e.printStackTrace();
            return -1;
        }
    }
}