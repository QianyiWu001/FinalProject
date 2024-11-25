package AdminAttendanceManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseUtilities.ConnectDB;

public class AttendanceDatabase {
    public Object[][] getAttendanceData() {
        String query = "SELECT student_id, course_id, date, status FROM attendance";

        try (Connection conn = ConnectDB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet r = stmt.executeQuery()) {
            r.last();
            int numRows = r.getRow();
            r.beforeFirst();

            Object[][] data = new Object[numRows][4];
            int i = 0;
            while (r.next()) {
                data[i][0] = r.getInt("student_id");
                data[i][1] = r.getInt("course_id");
                data[i][2] = r.getDate("date");
                data[i][3] = r.getString("status");
                i++;
            }
            return data;
        } catch (SQLException e) {
            System.out.println("Unable to get attendance data from database.");
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    // Add a new attendance record to the database.
    public boolean addAttendance(int studentID, int courseID, java.sql.Date date, String status) {
        String query = "INSERT INTO attendance (student_id, course_id, date, status) VALUES (?,?,?,?)";

        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentID);
                stmt.setInt(2, courseID);
                stmt.setDate(3, date);
                stmt.setString(4, status);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Unable to add attendance record to database.");
                e.printStackTrace();
                return false;
            }
    }

    // Delete a course from database. 
    public boolean deleteAttendance(int studentID, int courseID, java.sql.Date date) {
        String query = "DELETE FROM attendance WHERE student_id = ? AND course_id = ? AND date = ?";
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentID);
                stmt.setInt(2, courseID);
                stmt.setDate(3, date);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Unable to delete attendance record from database.");
                e.printStackTrace();
                return false;
            }
    }

    // Search for attendance records based on student ID, course ID or date. (at least fill one of the parameters)
    public Object[][] searchAttendance(int studentID, int courseID, java.sql.Date date) {
        String query;
        boolean hasStudentID = studentID != 0;
        boolean hasCourseID = courseID != 0;
        boolean hasDate = date != null;

        // 7 different situations to search for attendance records.
        if (hasStudentID && hasCourseID && hasDate) {
            query = "SELECT student_id, course_id, date, status FROM attendance WHERE student_id = ? AND course_id = ? AND date = ?";
        } else if (hasStudentID && hasCourseID) {
            query = "SELECT student_id, course_id, date, status FROM attendance WHERE student_id = ? AND course_id = ?";
        } else if (hasStudentID && hasDate) {
            query = "SELECT student_id, course_id, date, status FROM attendance WHERE student_id = ? AND date = ?";
        } else if (hasCourseID && hasDate) {
            query = "SELECT student_id, course_id, date, status FROM attendance WHERE course_id = ? AND date = ?";
        } else if (hasStudentID) {
            query = "SELECT student_id, course_id, date, status FROM attendance WHERE student_id = ?";
        } else if (hasCourseID) {
            query = "SELECT student_id, course_id, date, status FROM attendance WHERE course_id = ?";
        } else if (hasDate) {
            query = "SELECT student_id, course_id, date, status FROM attendance WHERE date = ?";
        } else {
            return new Object[0][0];
        }

        try (Connection conn = ConnectDB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                if (hasStudentID && hasCourseID && hasDate) {
                    stmt.setInt(1, studentID);
                    stmt.setInt(2, courseID);
                    stmt.setDate(3, date);
                } else if (hasStudentID && hasCourseID) {
                    stmt.setInt(1, studentID);
                    stmt.setInt(2, courseID);
                } else if (hasStudentID && hasDate) {
                    stmt.setInt(1, studentID);
                    stmt.setDate(2, date);
                } else if (hasCourseID && hasDate) {
                    stmt.setInt(1, courseID);
                    stmt.setDate(2, date);
                } else if (hasStudentID) {
                    stmt.setInt(1, studentID);
                } else if (hasCourseID) {
                    stmt.setInt(1, courseID);
                } else if (hasDate) {
                    stmt.setDate(1, date);
                }
                ResultSet r = stmt.executeQuery();
                r.last();
                int numRows = r.getRow();
                r.beforeFirst();

                Object[][] data = new Object[numRows][4];
                int i = 0;
                while (r.next()) {
                    data[i][0] = r.getInt("student_id");
                    data[i][1] = r.getInt("course_id");
                    data[i][2] = r.getDate("date");
                    data[i][3] = r.getString("status");
                    i++;
                }
                return data;
            } catch (SQLException e) {
                System.out.println("Unable to search for attendance record from database.");
                e.printStackTrace();
                return new Object[0][0];
            }
    }

    // Update attendance information.
    public boolean updateAttendance(int studentID, int courseID, java.sql.Date updatedDate, String updatedStatus) {
        String query = "UPDATE attendance SET date = ?, status = ? WHERE student_id = ? AND course_id = ?";
        try (Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setDate(1, updatedDate);
                stmt.setString(2, updatedStatus);
                stmt.setInt(3, studentID);
                stmt.setInt(4, courseID);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Unable to update attendance in database.");
                e.printStackTrace();
                return false;
            }
    }
}
