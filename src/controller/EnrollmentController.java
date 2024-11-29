package controller;

import dao.EnrollmentDAO;
import entity.Course;

import java.util.List;

public class EnrollmentController {
    private EnrollmentDAO enrollmentDAO;

    public EnrollmentController() {
        this.enrollmentDAO = new EnrollmentDAO();
    }

    public List<Course> getCoursesByStudentId(int studentId) {
        return enrollmentDAO.getCoursesByStudentId(studentId);
    }

    public boolean enrollStudentInCourse(int studentId, int courseId) {
        return enrollmentDAO.enrollStudentInCourse(studentId, courseId);
    }

    public boolean unenrollStudentFromCourse(int studentId, int courseId) {
        return enrollmentDAO.unenrollStudentFromCourse(studentId, courseId);
    }
}