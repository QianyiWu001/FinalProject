package dao;

import entity.Attendance;
import DatabaseUtilities.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    public List<Attendance> getAllAttendance() {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT a.enrollment_id, a.date, a.status, e.student_id, e.course_id " +
                       "FROM attendance a " +
                       "JOIN enrollments e ON a.enrollment_id = e.enrollment_id";
    
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setEnrollmentId(rs.getInt("enrollment_id"));
                attendance.setDate(rs.getDate("date"));
                attendance.setStatus(rs.getString("status"));
                attendance.setStudentId(rs.getInt("student_id")); // 从 enrollments 表获取 student_id
                attendance.setCourseId(rs.getInt("course_id"));   // 从 enrollments 表获取 course_id
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return attendanceList;
    }

    public boolean addAttendance(Attendance attendance) {
        String query = "INSERT INTO attendance (enrollment_id, date, status) VALUES (?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, attendance.getEnrollmentId());
            pstmt.setDate(2, new java.sql.Date(attendance.getDate().getTime())); // 转换为 SQL 日期
            pstmt.setString(3, attendance.getStatus());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAttendance(Attendance attendance) {
        String query = "UPDATE attendance SET status = ? WHERE enrollment_id = ? AND date = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, attendance.getStatus());
            pstmt.setInt(2, attendance.getEnrollmentId());
            pstmt.setDate(3, new java.sql.Date(attendance.getDate().getTime())); // 转换为 SQL 日期

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteAttendance(int enrollmentId, String date) {
        String query = "DELETE FROM attendance WHERE enrollment_id = ? AND date = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, enrollmentId); // 设置 enrollment_id 参数
            pstmt.setDate(2, java.sql.Date.valueOf(date)); // 将日期字符串转换为 SQL 日期类型

            return pstmt.executeUpdate() > 0; // 如果删除的行数大于 0，则表示删除成功

        } catch (SQLException e) {
            e.printStackTrace(); // 捕获并打印异常
        }

        return false; // 如果操作失败，返回 false
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
                attendance.setEnrollmentId(rs.getInt("enrollment_id"));
                attendance.setDate(rs.getDate("date"));
                attendance.setStatus(rs.getString("status"));
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceList;
    }
    public int getEnrollmentId(int studentId, int courseId) {
        String query = "SELECT enrollment_id FROM enrollments WHERE student_id = ? AND course_id = ?";
    
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                return rs.getInt("enrollment_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return -1; // 未找到对应的 enrollment_id
    }
}