package service;

import dao.CourseDAO;
import entity.Course;

import java.util.List;

public class CourseService {
    private CourseDAO courseDAO;

    public CourseService() {
        this.courseDAO = new CourseDAO();
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);
    }

    public boolean updateCourse(Course course) {
        return courseDAO.updateCourse(course);
    }

    public boolean deleteCourse(int courseId) {
        return courseDAO.deleteCourse(courseId);
    }

    public List<Course> searchCourses(String searchText) {
        return courseDAO.searchCourses(searchText);
    }
}
