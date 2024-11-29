package controller;

import entity.Grade;
import service.GradesService;


import java.util.List;

import dao.GradesDAO;

public class GradesController {
    private final GradesService gradesService;


    public GradesController() {
        this.gradesService = new GradesService();
    }

    public boolean addGrade(Grade grade) {
        return gradesService.addGrades(grade);
    }

    public boolean deleteGrade(int studentId, int courseId) {
        return gradesService.deleteGrades(studentId, courseId);
    }

    public boolean updateGrade(Grade grade) {
        return gradesService.updateGrades(grade);
    }

 // 模糊搜索：根据字符串查询
 public List<Grade> searchGrades(String query) {
    return gradesService.searchGrades(query);
}

    public List<Grade> getAllGrades() {
        // 获取所有 Grade 数据
        List<Grade> grades = gradesService.getAllGrades();

        // 将数据转换为二维数组格式
        Object[][] data = new Object[grades.size()][3]; // 3 列：student_id, course_id, grade
        for (int i = 0; i < grades.size(); i++) {
            Grade grade = grades.get(i);
            data[i][0] = grade.getStudentId();
            data[i][1] = grade.getCourseId();
            data[i][2] = grade.getGrade();
        }
        return gradesService.getAllGrades();
    }

}