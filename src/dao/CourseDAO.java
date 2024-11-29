package dao;

import entity.Course;
import DatabaseUtilities.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT course_id, course_name, description, credits FROM courses";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setcourse_name(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                course.setCredits(rs.getInt("credits"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public boolean addCourse(Course course) {
        String query = "INSERT INTO courses (course_id, course_name, description, credits) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, course.getCourseId());
            pstmt.setString(2, course.getcourse_name());
            pstmt.setString(3, course.getDescription());
            pstmt.setInt(4, course.getCredits());
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateCourse(Course course) {
        String query = "UPDATE courses SET course_name = ?, description = ?, credits = ? WHERE course_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, course.getcourse_name());
            pstmt.setString(2, course.getDescription());
            pstmt.setInt(3, course.getCredits());
            pstmt.setInt(4, course.getCourseId());
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteCourse(int courseId) {
        String query = "DELETE FROM courses WHERE course_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, courseId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Course> searchCourses(String searchText) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT course_id, course_name, description, credits FROM courses WHERE course_name LIKE ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + searchText + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setcourse_name(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                course.setCredits(rs.getInt("credits"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }
}