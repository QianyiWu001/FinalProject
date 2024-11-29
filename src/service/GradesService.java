package service;

import dao.GradesDAO;
import entity.Grade;

import java.util.List;

public class GradesService {
    private GradesDAO gradesDAO;

    public GradesService() {
        this.gradesDAO = new GradesDAO();
    }

    public List<Grade> getAllGrades() {
        return gradesDAO.getAllGrades();
    }

    public boolean addGrade(Grade grade) {
        return gradesDAO.addGrade(grade);
    }

    public boolean deleteGrade(int enrollmentId) {
        return gradesDAO.deleteGrade(enrollmentId);
    }

    public boolean updateGrade(Grade grade) {
        return gradesDAO.updateGrade(grade);
    }

    public List<Grade> searchGrades(String query) {
        return gradesDAO.searchGrades(query);
    }
}