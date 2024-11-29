package service;

import dao.AttendanceDAO;
import entity.Attendance;

import java.util.List;

public class AttendanceService {
    private AttendanceDAO attendanceDAO;

    public AttendanceService() {
        this.attendanceDAO = new AttendanceDAO();
    }

    public List<Attendance> getAllAttendance() {
        return attendanceDAO.getAllAttendance();
    }

    public boolean addAttendance(Attendance attendance) {
        return attendanceDAO.addAttendance(attendance);
    }

    public boolean updateAttendance(Attendance attendance) {
        return attendanceDAO.updateAttendance(attendance);
    }

    public boolean deleteAttendance(int studentId, int courseId, String date) {
        return attendanceDAO.deleteAttendance(studentId, courseId, date);
    }

    public List<Attendance> searchAttendance(String searchText) {
        return attendanceDAO.searchAttendance(searchText);
    }
}