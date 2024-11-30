package service;

import dao.EnrollmentDAO;
import entity.Course;
import entity.Enrollment;

import java.util.List;

public class EnrollmentService {
    private EnrollmentDAO enrollmentDAO;

    public EnrollmentService() {
        this.enrollmentDAO = new EnrollmentDAO();
    }

    // 获取所有 Enrollment
    public List<Enrollment> getAllEnrollments() {
        return enrollmentDAO.getAllEnrollments();
    }

    // 删除 Enrollment（数据库会自动级联删除关联数据）
    public void deleteEnrollment(int enrollmentId) {
        enrollmentDAO.deleteEnrollment(enrollmentId);
    }

    // 获取指定学生的课程列表
    public List<Course> getCoursesByStudentId(int studentId) {
        return enrollmentDAO.getCoursesByStudentId(studentId);
    }

    
}

