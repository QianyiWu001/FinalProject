package controller;

import entity.Grade;
import service.GradesService;

import java.util.List;

public class GradesController {
    private GradesService gradesService;

    public GradesController() {
        this.gradesService = new GradesService();
    }

    public List<Grade> getAllGrades() {
        return gradesService.getAllGrades();
    }

    public boolean addGrade(Grade grade) {
        return gradesService.addGrade(grade);
    }

    public boolean deleteGrade(int enrollmentId) {
        return gradesService.deleteGrade(enrollmentId);
    }

    public boolean updateGrade(Grade grade) {
        return gradesService.updateGrade(grade);
    }

    public List<Grade> searchGrades(String query) {
        return gradesService.searchGrades(query);
    }
}