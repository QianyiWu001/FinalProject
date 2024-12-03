package dao;

import entity.Enrollment;
import entity.Course;
import DatabaseUtilities.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    public List<Course> getCoursesByStudentId(int studentId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.course_id, c.course_name, c.description, c.credits " +
                       "FROM courses c " +
                       "JOIN enrollments e ON c.course_id = e.course_id " +
                       "WHERE e.student_id = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                course.setCredits(rs.getInt("credits"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

  // Get all enrollment
  public List<Enrollment> getAllEnrollments() {
    List<Enrollment> enrollments = new ArrayList<>();
    String query = "SELECT enrollment_id, student_id, course_id FROM enrollments";

    try (Connection connection = ConnectDB.getConnection();
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            Enrollment enrollment = new Enrollment();
            enrollment.setEnrollmentId(resultSet.getInt("enrollment_id"));
            enrollment.setStudentId(resultSet.getInt("student_id"));
            enrollment.setCourseId(resultSet.getInt("course_id"));
            enrollments.add(enrollment);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return enrollments;
}

// Add new
public boolean addEnrollment(Enrollment enrollment) {
    String query = "INSERT INTO enrollments (enrollment_id, student_id, course_id) VALUES (?, ?, ?)";

    try (Connection connection = ConnectDB.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, enrollment.getEnrollmentId());
        statement.setInt(2, enrollment.getStudentId());
        statement.setInt(3, enrollment.getCourseId());

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}


public boolean deleteEnrollment(int enrollmentId) {
    String query = "DELETE FROM enrollments WHERE enrollment_id = ?";

    try (Connection connection = ConnectDB.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, enrollmentId);

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}

public boolean updateEnrollment(Enrollment updatedEnrollment) {
    String query = "UPDATE enrollments SET student_id = ?, course_id = ? WHERE enrollment_id = ?";

    try (Connection connection = ConnectDB.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, updatedEnrollment.getStudentId());
        statement.setInt(2, updatedEnrollment.getCourseId());
        statement.setInt(3, updatedEnrollment.getEnrollmentId());

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}

// Enrollment
public List<Enrollment> searchEnrollments(String queryText) {
    List<Enrollment> enrollments = new ArrayList<>();
    String query = "SELECT enrollment_id, student_id, course_id " +
                   "FROM enrollments " +
                   "WHERE CONCAT(enrollment_id, student_id, course_id) LIKE ?";

    try (Connection connection = ConnectDB.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setString(1, "%" + queryText + "%");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Enrollment enrollment = new Enrollment();
            enrollment.setEnrollmentId(resultSet.getInt("enrollment_id"));
            enrollment.setStudentId(resultSet.getInt("student_id"));
            enrollment.setCourseId(resultSet.getInt("course_id"));
            enrollments.add(enrollment);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return enrollments;
}
}