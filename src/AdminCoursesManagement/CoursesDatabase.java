package AdminCoursesManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseUtilities.ConnectDB;

public class CoursesDatabase {
    public Object[][] getCoursesData() {
        String query = "SELECT course_id, course_name, credits, description FROM courses";

        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet r = stmt.executeQuery()) {
                r.last();
                int numRows = r.getRow();
                r.beforeFirst();

                Object[][] data = new Object[numRows][4];
                int i = 0;
                while (r.next()) {
                    data[i][0] = r.getInt("course_id");
                    data[i][1] = r.getString("course_name");
                    data[i][2] = r.getInt("credits");
                    data[i][3] = r.getString("description");
                    i++;
                }
                return data;
            } catch (SQLException e) {
                System.out.println("Unable to get courses data from database.");
                e.printStackTrace();
                return new Object[0][0];
            }
    }

    // Add a new course to database.
    public boolean addCourse(int courseID, String courseName, Integer credit, String description) {
        String query = "INSERT INTO courses (course_id, course_name, credits, description) VALUES (?,?,?,?)";

        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, courseID);
                stmt.setString(2, courseName);
                stmt.setInt(3, credit);
                stmt.setString(4, description);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Unable to add course to database.");
                e.printStackTrace();
                return false;
            }
    }

    // Delete a course from database. 
    public boolean deleteCourse(int courseID) {
        String query = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, courseID);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Unable to delete course from database.");
                e.printStackTrace();
                return false;
            }
    }
}
