package service;

import dao.StudentDAO;
import dao.UserDAO;
import entity.Student;

import java.util.List;

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
    // add student
    public boolean addStudent(Student student) {
        // Step 1: 确保 user 信息有效，先添加到 users 表
        boolean userAdded = userDAO.addUser(
                student.getUserId(),
                student.getUsername(),
                student.getPassword(),
                student.getRole()
        );
        if (!userAdded) {
            System.out.println("Failed to add user to users table.");
            return false;
        }

        // Step 2: 确保 student 信息有效，添加到 students 表
        boolean studentAdded = studentDAO.addStudent(student);
        if (!studentAdded) {
            System.out.println("Failed to add student to students table.");
            // 如果 students 表添加失败，则从 users 表回滚
            userDAO.deleteUser(student.getUserId());
            return false;
        }

        return true;
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
                student.getRole()
        );
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