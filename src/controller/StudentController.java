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

    public List<Student> searchStudents(String searchText) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchStudents'");
    }
}