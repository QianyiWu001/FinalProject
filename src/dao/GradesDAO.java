package dao;

import entity.Grade;

import java.util.ArrayList;
import java.util.List;
import DatabaseUtilities.ConnectDB;

import java.sql.*;

public class GradesDAO {

    public List<Grade> getAllGrades() {
        List<Grade> grades = new ArrayList<>();
        String query = "SELECT student_id, course_id, grade FROM grades";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                int courseId = rs.getInt("course_id");
                int gradeValue = rs.getInt("grade");
                grades.add(new Grade(studentId, courseId, gradeValue));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    public boolean insertGrades(Grade grade) {
        String query = "INSERT INTO grades (student_id, course_id, grade) VALUES (?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, grade.getStudentId());
            pstmt.setInt(2, grade.getCourseId());
            pstmt.setInt(3, grade.getGrade());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteGrades(int studentId, int courseId) {
        String query = "DELETE FROM grades WHERE student_id = ? AND course_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateGrades(Grade grade) {
        String query = "UPDATE grades SET grade = ? WHERE student_id = ? AND course_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, grade.getGrade());
            pstmt.setInt(2, grade.getStudentId());
            pstmt.setInt(3, grade.getCourseId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Grade> searchGrades(String query) {
        List<Grade> grades = new ArrayList<>();
        String sqlQuery = "SELECT student_id, course_id, grade FROM grades " +
                          "WHERE student_id LIKE ? OR course_id LIKE ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

            pstmt.setString(1, "%" + query + "%");
            pstmt.setString(2, "%" + query + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int studentId = rs.getInt("student_id");
                    int courseId = rs.getInt("course_id");
                    int gradeValue = rs.getInt("grade");
                    grades.add(new Grade(studentId, courseId, gradeValue));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }
}