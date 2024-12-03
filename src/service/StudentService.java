package service;

import dao.StudentDAO;
import dao.UserDAO;
import entity.Student;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DatabaseUtilities.ConnectDB;

public class StudentService {
    private final StudentDAO studentDAO;
    private final UserDAO userDAO;


    public StudentService(StudentDAO studentDAO, UserDAO userDAO) {
        this.studentDAO = studentDAO;
        this.userDAO = userDAO;
    }


    public StudentService() {
        this.studentDAO = new StudentDAO();
        this.userDAO = new UserDAO();
    }

 public boolean addStudent(Student student) {

    int userId = userDAO.generateUserId(); 
    student.setUserId(userId);

    // Add to user table also
    boolean userAdded = userDAO.addUser(new User(userId, student.getName(), "default_password", "ROLE_STUDENT"));
    if (!userAdded) {
        System.out.println("Failed to add user to users table.");
        return false;
    }

    // Add to student table
    boolean studentAdded = studentDAO.addStudent(student);
    if (!studentAdded) {
        System.out.println("Failed to add student to students table.");
        // If fail, delete from user table
        userDAO.deleteUser(userId);
        return false;
    }

    return true;
}
    public int generateUserId() {
        int maxId = 0;
        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT MAX(user_id) AS max_id FROM users");
                ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                maxId = rs.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId + 1; 
    }

    // Delete
    public boolean deleteStudent(int studentId) {
        // Delete from student 
        boolean studentDeleted = studentDAO.deleteStudent(studentId);
        if (!studentDeleted) {
            System.out.println("Failed to delete student from students table.");
            return false;
        }

        // Delete form user
        boolean userDeleted = userDAO.deleteUser(studentId);
        if (!userDeleted) {
            System.out.println("Failed to delete user from users table.");
            return false;
        }

        return true;
    }

    // Update
    public boolean updateStudent(Student student) {
        // Update user
        boolean userUpdated = userDAO.updateUser(
                student.getUserId(),
                student.getUsername(),
                student.getPassword(),
                student.getRole());
        if (!userUpdated) {
            System.out.println("Failed to update user in users table.");
            return false;
        }

        // Update student
        boolean studentUpdated = studentDAO.updateStudent(student);
        if (!studentUpdated) {
            System.out.println("Failed to update student in students table.");
            return false;
        }

        return true;
    }

 
    public Student getStudentById(int studentId) {
   
        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            System.out.println("No student found with ID: " + studentId);
            return null;
        }

     
        String[] userInfo = userDAO.getUserById(studentId);
        if (userInfo != null) {
            student.setUsername(userInfo[0]);
            student.setPassword(userInfo[1]);
            student.setRole(userInfo[2]);
        }

        return student;
    }


    public List<Student> searchStudents(String keyword) {
        return studentDAO.searchStudents(keyword);
    }


    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
}