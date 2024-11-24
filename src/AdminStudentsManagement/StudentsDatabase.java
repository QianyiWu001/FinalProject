package AdminStudentsManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseUtilities.ConnectDB;

public class StudentsDatabase {
    public Object[][] getStudentsData() {
        String query = "SELECT student_id, name, email, phone, address FROM students";
        
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet r = stmt.executeQuery()) {
                r.last();
                int numRows = r.getRow();
                r.beforeFirst();

                Object[][] data = new Object[numRows][5];
                int i = 0;
                while (r.next()) {
                    data[i][0] = r.getInt("student_id");
                    data[i][1] = r.getString("name");
                    data[i][2] = r.getString("email");
                    data[i][3] = r.getString("phone");
                    data[i][4] = r.getString("address");
                    i++;
                }
                return data;
            } catch (SQLException e) {
                System.out.println("Unable to get students data from database.");
                e.printStackTrace();
                return new Object[0][0];
            }
    }

    // Add a new student to the database.
    public boolean addStudent(int studentID, String name, String email, String phone, String address) {
        String query = "INSERT INTO students (student_id, name, email, phone, address) VALUES (?,?,?,?,?)";
        
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentID);
                stmt.setString(2, name);
                stmt.setString(3, email);
                stmt.setString(4, phone);
                stmt.setString(5, address);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Unable to add student to database.");
                e.printStackTrace();
                return false;
            }
    }

    // Delete a student from the database. 
    public boolean deleteStudent(int studentID) {
        String query = "DELETE FROM students WHERE student_id = ?";
        
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentID);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Unable to delete student from database.");
                e.printStackTrace();
                return false;
            }
    }
}
