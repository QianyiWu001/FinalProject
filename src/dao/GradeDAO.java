package dao;

import entity.Grade;
import DatabaseUtilities.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {

    public List<Grade> getAllGrades() {
        List<Grade> grades = new ArrayList<>();
        String query = "SELECT g.enrollment_id, e.student_id, e.course_id, g.grade " +
                       "FROM grades g " +
                       "JOIN enrollments e ON g.enrollment_id = e.enrollment_id";
    
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
                Grade grade = new Grade(
                    rs.getInt("enrollment_id"),
                    rs.getInt("student_id"),
                    rs.getInt("course_id"),
                    rs.getInt("grade")
                );
                grades.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    public boolean addGrade(Grade grade) {
        String query = "INSERT INTO grades (enrollment_id, grade) VALUES (?, ?)";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, grade.getEnrollmentId());
            pstmt.setInt(2, grade.getGrade());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteGrade(int enrollmentId) {
        String query = "DELETE FROM grades WHERE enrollment_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, enrollmentId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateGrade(Grade grade) {
        String query = "UPDATE grades SET grade = ? WHERE enrollment_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, grade.getGrade());
            pstmt.setInt(2, grade.getEnrollmentId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Grade> getGradesByStudentId(int studentId) {
        List<Grade> grades = new ArrayList<>();
        String query = "SELECT g.enrollment_id, e.course_id, g.grade " +
                       "FROM grades g " +
                       "JOIN enrollments e ON g.enrollment_id = e.enrollment_id " +
                       "WHERE e.student_id = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                grades.add(new Grade(
                    rs.getInt("enrollment_id"),
                    studentId,
                    rs.getInt("course_id"),
                    rs.getInt("grade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }
    public List<Grade> searchGrades(String keyword) {
        List<Grade> gradesList = new ArrayList<>();
        String query = "SELECT g.grade, e.enrollment_id, e.student_id, e.course_id " +
                       "FROM grades g " +
                       "JOIN enrollments e ON g.enrollment_id = e.enrollment_id " +
                       "WHERE CAST(e.student_id AS CHAR) LIKE ? " +
                       "   OR CAST(e.course_id AS CHAR) LIKE ? " +
                       "   OR CAST(e.enrollment_id AS CHAR) LIKE ? " +
                       "   OR CAST(g.grade AS CHAR) LIKE ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            String likeKeyword = "%" + keyword + "%";
            pstmt.setString(1, likeKeyword);
            pstmt.setString(2, likeKeyword);
            pstmt.setString(3, likeKeyword);
            pstmt.setString(4, likeKeyword);
    
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setGrade(rs.getInt("grade"));
                grade.setEnrollmentId(rs.getInt("enrollment_id"));
                grade.setStudentId(rs.getInt("student_id"));
                grade.setCourseId(rs.getInt("course_id"));
                gradesList.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradesList;
    }
}