package AdminCoursesManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseUtilities.ConnectDB;

public class CoursesDatabase {
    public Object[][] getCoursesData() {
        String query = "SELECT course_id, course_name, description, credits FROM courses";

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
                    data[i][2] = r.getString("description");
                    data[i][3] = r.getInt("credits");
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
    public boolean addCourse(int courseID, String courseName, int credit, String description) {
        String query = "INSERT INTO courses (course_id, course_name, description, credits) VALUES (?,?,?,?)";

        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, courseID);
                stmt.setString(2, courseName);
                stmt.setString(3, description);
                stmt.setInt(4, credit);
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

    // Search for a course by course name.
    public Object[][] searchCourse(String courseIDOrName) {
        String query = "SELECT course_id, course_name, description, credits FROM courses WHERE course_id LIKE ? OR course_name LIKE ?";
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setString(1, "%" + courseIDOrName + "%");
                stmt.setString(2, "%" + courseIDOrName + "%");
                ResultSet r = stmt.executeQuery();
                r.last();
                int numRows = r.getRow();
                r.beforeFirst();

                Object[][] data = new Object[numRows][4];
                int i = 0;
                while (r.next()) {
                    data[i][0] = r.getInt("course_id");
                    data[i][1] = r.getString("course_name");
                    data[i][2] = r.getString("description");
                    data[i][3] = r.getInt("credits");
                    
                    i++;
                }
                return data;
            } catch (SQLException e) {
                System.out.println("Unable to find course in database.");
                e.printStackTrace();
                return new Object[0][0];
            }
        }
    
    // Update course information.
    public boolean updateCourse(int courseID, String updatedCourseName, String updatedDescription, int updatedCredits) {
        String query = "UPDATE courses SET course_name = ?, description = ?, credits = ? WHERE course_id = ?";
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt  = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setString(1, updatedCourseName);
                stmt.setString(2, updatedDescription);
                stmt.setInt(3, updatedCredits);
                stmt.setInt(4, courseID);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Unable to update course in database.");
                e.printStackTrace();
                return false;
            }    
    }
}
