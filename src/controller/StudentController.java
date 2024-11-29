package controller;

import entity.Student;
import service.StudentService;

import java.util.List;

public class StudentController {
    private StudentService studentService;

    public StudentController() {
        this.studentService = new StudentService();
    }

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public boolean addStudent(Student student) {
        return studentService.addStudent(student);
    }

    public boolean deleteStudent(int studentId) {
        return studentService.deleteStudent(studentId);
    }

    public boolean updateStudent(Student student) {
        return studentService.updateStudent(student);
    }
    public Student getStudentById(int studentId) {
        return studentService.getStudentById(studentId);
    }
    public List<Student> searchStudents(String searchText) {
    
        throw new UnsupportedOperationException("Unimplemented method 'searchStudents'");
    }
}