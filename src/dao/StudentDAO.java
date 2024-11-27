package dao;

import entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DatabaseUtilities.ConnectDB;

public class StudentDAO {
    private Connection connection;

    public StudentDAO() {
        try {
            this.connection = ConnectDB.getConnection();
        } catch (SQLException e) {
            System.err.println("Database connection failed. Unable to proceed.");
            e.printStackTrace();
            throw new RuntimeException("Unable to establish database connection.", e);
        }
    }
 // 添加学生到 students 表
 public boolean addStudent(Student student) {
    String query = "INSERT INTO students (student_id, name, email, phone, address) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = ConnectDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, student.getUserId()); // student_id 是 user_id 的外键
        stmt.setString(2, student.getName());
        stmt.setString(3, student.getEmail());
        stmt.setString(4, student.getPhone());
        stmt.setString(5, student.getAddress());

        stmt.executeUpdate();
        return true;

    } catch (SQLException e) {
        System.out.println("Unable to add student to database.");
        e.printStackTrace();
        return false;
    }
}

// 根据 student_id 删除学生
public boolean deleteStudent(int studentId) {
    String query = "DELETE FROM students WHERE student_id = ?";

    try (Connection conn = ConnectDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, studentId);

        stmt.executeUpdate();
        return true;

    } catch (SQLException e) {
        System.out.println("Unable to delete student from database.");
        e.printStackTrace();
        return false;
    }
}

// 更新学生信息
public boolean updateStudent(Student student) {
    String query = "UPDATE students SET name = ?, email = ?, phone = ?, address = ? WHERE student_id = ?";

    try (Connection conn = ConnectDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, student.getName());
        stmt.setString(2, student.getEmail());
        stmt.setString(3, student.getPhone());
        stmt.setString(4, student.getAddress());
        stmt.setInt(5, student.getUserId());

        stmt.executeUpdate();
        return true;

    } catch (SQLException e) {
        System.out.println("Unable to update student in database.");
        e.printStackTrace();
        return false;
    }
}

// 根据 student_id 获取学生详细信息
public Student getStudentById(int studentId) {
    String query = "SELECT * FROM students WHERE student_id = ?";

    try (Connection conn = ConnectDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, studentId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Student(
                    rs.getInt("student_id"),
                    null, // Username
                    null, // Password
                    null, // Role
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address")
            );
        }
        return null;

    } catch (SQLException e) {
        System.out.println("Unable to fetch student from database.");
        e.printStackTrace();
        return null;
    }
}

// 搜索学生（按 student_id 或 name）
public List<Student> searchStudents(String keyword) {
    String query = "SELECT * FROM students WHERE student_id LIKE ? OR name LIKE ?";
    List<Student> students = new ArrayList<>();

    try (Connection conn = ConnectDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, "%" + keyword + "%");
        stmt.setString(2, "%" + keyword + "%");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            students.add(new Student(
                    rs.getInt("student_id"),
                    null, // Username
                    null, // Password
                    null, // Role
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address")
            ));
        }
    } catch (SQLException e) {
        System.out.println("Unable to search students in database.");
        e.printStackTrace();
    }
    return students;
}

// 获取所有学生信息
public List<Student> getAllStudents() {
    String query = "SELECT * FROM students";
    List<Student> students = new ArrayList<>();

    try (Connection conn = ConnectDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            students.add(new Student(
                    rs.getInt("student_id"),
                    null, // Username
                    null, // Password
                    null, // Role
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address")
            ));
        }
    } catch (SQLException e) {
        System.out.println("Unable to fetch all students from database.");
        e.printStackTrace();
    }
    return students;
}
}