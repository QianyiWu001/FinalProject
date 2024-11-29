package service;
import java.util.List;

import dao.GradesDAO;
import entity.Grade;

public class GradesService {
    private GradesDAO gradesDao;

    public GradesService() {
        this.gradesDao = new GradesDAO(); // 初始化 DAO 层
    }

    // 获取所有成绩
    public List<Grade> getAllGrades() {
        return gradesDao.getAllGrades();
    }

    // 添加成绩
    public boolean addGrades(Grade grades) {
        return gradesDao.insertGrades(grades);
    }

    // 删除成绩
    public boolean deleteGrades(int studentId, int courseId) {
        return gradesDao.deleteGrades(studentId, courseId);
    }

    // 更新成绩
    public boolean updateGrades(Grade grades) {
        return gradesDao.updateGrades(grades);
    }

    // 搜索成绩
    public List<Grade> searchGrades(String query) {
        return gradesDao.searchGrades(query);
    }
}