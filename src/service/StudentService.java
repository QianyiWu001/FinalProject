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

    // 构造函数初始化 DAOs
    public StudentService(StudentDAO studentDAO, UserDAO userDAO) {
        this.studentDAO = studentDAO;
        this.userDAO = userDAO;
    }

    // 添加无参构造函数
    public StudentService() {
        this.studentDAO = new StudentDAO();
        this.userDAO = new UserDAO();
    }

 public boolean addStudent(Student student) {
    // Step 1: 生成 user_id
    int userId = userDAO.generateUserId(); // 手动生成唯一 user_id
    student.setUserId(userId); // 将 user_id 赋值给 Student

    // Step 2: 添加到 users 表
    boolean userAdded = userDAO.addUser(new User(userId, student.getName(), "default_password", "ROLE_STUDENT"));
    if (!userAdded) {
        System.out.println("Failed to add user to users table.");
        return false;
    }

    // Step 3: 添加到 students 表
    boolean studentAdded = studentDAO.addStudent(student);
    if (!studentAdded) {
        System.out.println("Failed to add student to students table.");
        // 如果学生添加失败，回滚删除 users 表中的记录
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
        return maxId + 1; // 返回下一个唯一 ID
    }

    // 删除学生（同步 users 和 students 表）
    public boolean deleteStudent(int studentId) {
        // Step 1: 删除 students 表中的学生记录
        boolean studentDeleted = studentDAO.deleteStudent(studentId);
        if (!studentDeleted) {
            System.out.println("Failed to delete student from students table.");
            return false;
        }

        // Step 2: 删除 users 表中的用户记录
        boolean userDeleted = userDAO.deleteUser(studentId);
        if (!userDeleted) {
            System.out.println("Failed to delete user from users table.");
            return false;
        }

        return true;
    }

    // 更新学生信息（同步更新 users 和 students 表）
    public boolean updateStudent(Student student) {
        // Step 1: 更新 users 表中的用户信息
        boolean userUpdated = userDAO.updateUser(
                student.getUserId(),
                student.getUsername(),
                student.getPassword(),
                student.getRole());
        if (!userUpdated) {
            System.out.println("Failed to update user in users table.");
            return false;
        }

        // Step 2: 更新 students 表中的学生信息
        boolean studentUpdated = studentDAO.updateStudent(student);
        if (!studentUpdated) {
            System.out.println("Failed to update student in students table.");
            return false;
        }

        return true;
    }

    // 根据 studentId 获取完整学生信息
    public Student getStudentById(int studentId) {
        // 从 students 表中获取学生基本信息
        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            System.out.println("No student found with ID: " + studentId);
            return null;
        }

        // 从 users 表中补充用户名、密码、角色信息
        String[] userInfo = userDAO.getUserById(studentId);
        if (userInfo != null) {
            student.setUsername(userInfo[0]);
            student.setPassword(userInfo[1]);
            student.setRole(userInfo[2]);
        }

        return student;
    }

    // 搜索学生
    public List<Student> searchStudents(String keyword) {
        return studentDAO.searchStudents(keyword);
    }

    // 获取所有学生
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
}