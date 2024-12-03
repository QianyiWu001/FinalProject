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


    public List<Enrollment> getAllEnrollments() {
        return enrollmentDAO.getAllEnrollments();
    }

  
    public void deleteEnrollment(int enrollmentId) {
        enrollmentDAO.deleteEnrollment(enrollmentId);
    }

    public List<Course> getCoursesByStudentId(int studentId) {
        return enrollmentDAO.getCoursesByStudentId(studentId);
    }

    
}

