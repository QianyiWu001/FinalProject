package dao;

import entity.Attendance;
import DatabaseUtilities.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    public List<Attendance> getAttendanceByStudentId(int studentId) {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT a.enrollment_id, a.date, a.status " +
                       "FROM attendance a " +
                       "JOIN enrollments e ON a.enrollment_id = e.enrollment_id " +
                       "WHERE e.student_id = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setEnrollmentId(rs.getInt("enrollment_id"));

            
                Date date = rs.getDate("date");
                if (date != null) {
                    attendance.setDate(date);
                }

               //status cannot be null
                attendance.setStatus(rs.getString("status").toUpperCase());

                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }
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
                attendance.setStudentId(rs.getInt("student_id")); // get student id from enrollments
                attendance.setCourseId(rs.getInt("course_id"));   // get course id from enrollments table 
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
            pstmt.setDate(2, new java.sql.Date(attendance.getDate().getTime()));
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
            pstmt.setDate(3, new java.sql.Date(attendance.getDate().getTime())); 

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

            pstmt.setInt(1, enrollmentId); 
            pstmt.setDate(2, java.sql.Date.valueOf(date)); 

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return false; 
    }
    public List<Attendance> searchAttendance(String keyword) {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT a.enrollment_id, e.student_id, e.course_id, a.date, a.status " +
                       "FROM attendance a " +
                       "JOIN enrollments e ON a.enrollment_id = e.enrollment_id " +
                       "WHERE CAST(a.enrollment_id AS CHAR) LIKE ? " +
                       "   OR CAST(e.student_id AS CHAR) LIKE ? " +
                       "   OR CAST(e.course_id AS CHAR) LIKE ? " +
                       "   OR a.status LIKE ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            String likeKeyword = "%" + keyword + "%";
            pstmt.setString(1, likeKeyword);
            pstmt.setString(2, likeKeyword);
            pstmt.setString(3, likeKeyword);
            pstmt.setString(4, likeKeyword);
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setEnrollmentId(rs.getInt("enrollment_id"));
                attendance.setStudentId(rs.getInt("student_id"));
                attendance.setCourseId(rs.getInt("course_id"));
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
    
        return -1; 
    }
    public boolean isEnrollmentValid(int enrollmentId) {
        String query = "SELECT COUNT(*) FROM enrollments WHERE enrollment_id = ?";
    
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setInt(1, enrollmentId);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    
        return false; 
    }
}