package controller;

import dao.EnrollmentDAO;
import entity.Course;
import entity.Enrollment;
import service.EnrollmentService;

import java.util.List;



public class EnrollmentController {
    private EnrollmentDAO enrollmentDAO;

    public EnrollmentController() {
        this.enrollmentDAO = new EnrollmentDAO();
    }
    public List<Course> getCoursesByStudentId(int studentId) {
        return enrollmentDAO.getCoursesByStudentId(studentId);
    }
    // 获取所有 Enrollment
    public List<Enrollment> getAllEnrollments() {
        return enrollmentDAO.getAllEnrollments();
    }

    // 添加 Enrollment
    public boolean addEnrollment(Enrollment enrollment) {
        return enrollmentDAO.addEnrollment(enrollment);
    }

    // 删除 Enrollment
    public boolean deleteEnrollment(int enrollmentId) {
        return enrollmentDAO.deleteEnrollment(enrollmentId);
    }

    // 更新 Enrollment
    public boolean updateEnrollment(Enrollment updatedEnrollment) {
        return enrollmentDAO.updateEnrollment(updatedEnrollment);
    }

    // 搜索 Enrollment
    public List<Enrollment> searchEnrollments(String query) {
        return enrollmentDAO.searchEnrollments(query);
    }
}