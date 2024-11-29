package controller;

import entity.Course;
import service.CourseService;

import java.util.List;

public class CourseController {
    private CourseService courseService;

    public CourseController() {
        this.courseService = new CourseService();
    }

    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    public boolean addCourse(Course course) {
        return courseService.addCourse(course);
    }

    public boolean updateCourse(Course course) {
        return courseService.updateCourse(course);
    }

    public boolean deleteCourse(int courseId) {
        return courseService.deleteCourse(courseId);
    }

    public List<Course> searchCourses(String searchText) {
        return courseService.searchCourses(searchText);
    }
}