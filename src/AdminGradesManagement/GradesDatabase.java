package AdminGradesManagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseUtilities.ConnectDB;

public class GradesDatabase {
    public Object[][] getGradesData() {
        String query = "SELECT student_id, course_id, grade FROM grades";

        try (Connection conn = ConnectDB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet r = stmt.executeQuery()) {
            r.last();
            int numRows = r.getRow();
            r.beforeFirst();

            Object[][] data = new Object[numRows][3];
            int i = 0;
            while (r.next()) {
                data[i][0] = r.getInt("student_id");
                data[i][1] = r.getInt("course_id");
                data[i][2] = r.getInt("grade");
                i++;
            }
            return data;
        } catch (SQLException e) {
            System.out.println("Unable to get grades data from database.");
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    // Add a new grade to the database.
    public boolean addGrade(int studentID, int courseID, int grade) {
        String query = "INSERT INTO grades (student_id, course_id, grade) VALUES (?,?,?)";

        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentID);
                stmt.setInt(2, courseID);
                stmt.setInt(3, grade);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Unable to add grade to database.");
                e.printStackTrace();
                return false;
            }
    }

    // Delete a grade from the database.
    public boolean deleteCourse(int studentID, int courseID) {
        String query = "DELETE FROM grades WHERE student_id = ? AND course_id = ?";
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentID);
                stmt.setInt(2, courseID);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Unable to delete grade from database.");
                e.printStackTrace();
                return false;
            }
    }

    // Search for a grade by student ID and/or course ID in the database.
    public Object[][] searchGrade(int studentID, int courseID) {
        String query;
        boolean hasStudentID = studentID != 0;
        boolean hasCourseID = courseID != 0;

        // 3 different situations to search for grades.
        if (hasStudentID && hasCourseID) {
            query = "SELECT student_id, course_id, grade FROM grades WHERE student_id = ? AND course_id = ?";
        } else if (hasStudentID) {
            query = "SELECT student_id, course_id, grade FROM grades WHERE student_id = ?";
        } else if (hasCourseID) {
            query = "SELECT student_id, course_id, grade FROM grades WHERE course_id = ?";
        } else {
            return new Object[0][0];
        }
        try (Connection conn = ConnectDB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                if (hasStudentID && hasCourseID) {
                    stmt.setInt(1, studentID);
                    stmt.setInt(2, courseID);
                } else if (hasStudentID) {
                    stmt.setInt(1, studentID);
                } else if (hasCourseID) {
                    stmt.setInt(1, courseID);
                }
                ResultSet r = stmt.executeQuery();
                r.last();
                int numRows = r.getRow();
                r.beforeFirst();

                Object[][] data = new Object[numRows][3];
                int i = 0;
                while (r.next()) {
                    data[i][0] = r.getInt("student_id");
                    data[i][1] = r.getInt("course_id");
                    data[i][2] = r.getInt("grade");
                    i++;
                }
                return data;
            } catch (SQLException e) {
                System.out.println("Unable to search for grade in database.");
                e.printStackTrace();
                return new Object[0][0];
            }
    }

    // Update a grade in the database.
    public boolean updateGrade(int studentID, int courseID, int updatedGrade) {
        String query = "UPDATE grades SET grade = ? WHERE student_id = ? AND course_id = ?";
        try (Connection conn = ConnectDB.getConnection();
        PreparedStatement stmt  = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setInt(1, updatedGrade);
                stmt.setInt(2, studentID);
                stmt.setInt(3, courseID);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Unable to update grade in database.");
                e.printStackTrace();
                return false;
            }
    }
}
