package dao;

import entity.Attendance;
import DatabaseUtilities.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    public List<Attendance> getAllAttendance() {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT * FROM attendance";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Attendance attendance = new Attendance();
                    attendance.setStudentID(rs.getInt("student_id"));
                    attendance.setCourseID(rs.getInt("course_id"));
                    attendance.setDate(rs.getDate("date"));
                    attendance.setStatus(rs.getString("status"));
                    attendanceList.add(attendance);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceList;
    }

    public boolean addAttendance(Attendance attendance) {
        String query = "INSERT INTO attendance (student_id, course_id, date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {

       pstmt.setString(1, attendance.getStatus());
       pstmt.setInt(2, attendance.getStudentID()); // 修正方法名为 getStudentID
       pstmt.setInt(3, attendance.getCourseID()); // 修正方法名为 getCourseID

       // 将 Java 的 Date 转换为 SQL 日期类型
       pstmt.setDate(4, new java.sql.Date(attendance.getDate().getTime()));

       return pstmt.executeUpdate() > 0;

   } catch (SQLException e) {
       e.printStackTrace();
   }
        return false;
    }
    public boolean updateAttendance(Attendance attendance) {
        String query = "UPDATE attendance SET status = ? WHERE student_id = ? AND course_id = ? AND date = ?";
    
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, attendance.getStatus());
            pstmt.setInt(2, attendance.getStudentID()); // 修正方法名为 getStudentID
            pstmt.setInt(3, attendance.getCourseID()); // 修正方法名为 getCourseID
    
            // 将 Java 的 Date 转换为 SQL 日期类型
            pstmt.setDate(4, new java.sql.Date(attendance.getDate().getTime()));
    
            return pstmt.executeUpdate() > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return false;
    }

    public boolean deleteAttendance(int studentId, int courseId, String date) {
        String query = "DELETE FROM attendance WHERE student_id = ? AND course_id = ? AND date = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            pstmt.setString(3, date);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Attendance> searchAttendance(String searchText) {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT * FROM attendance WHERE status LIKE ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + searchText + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setStudentID(rs.getInt("student_id"));
                attendance.setCourseID(rs.getInt("course_id"));
                attendance.setDate(rs.getDate("date")); // 确保数据库中日期字段类型为 DATE
                attendance.setStatus(rs.getString("status"));
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceList;
    }
}