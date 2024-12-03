package controller;

import dao.EnrollmentDAO;
import entity.Course;
import entity.Enrollment;


import java.util.List;



public class EnrollmentController {
    private EnrollmentDAO enrollmentDAO;

    public EnrollmentController() {
        this.enrollmentDAO = new EnrollmentDAO();
    }
    public List<Course> getCoursesByStudentId(int studentId) {
        return enrollmentDAO.getCoursesByStudentId(studentId);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentDAO.getAllEnrollments();
    }


    public boolean addEnrollment(Enrollment enrollment) {
        return enrollmentDAO.addEnrollment(enrollment);
    }

    public boolean deleteEnrollment(int enrollmentId) {
        return enrollmentDAO.deleteEnrollment(enrollmentId);
    }

    public boolean updateEnrollment(Enrollment updatedEnrollment) {
        return enrollmentDAO.updateEnrollment(updatedEnrollment);
    }


    public List<Enrollment> searchEnrollments(String query) {
        return enrollmentDAO.searchEnrollments(query);
    }
}