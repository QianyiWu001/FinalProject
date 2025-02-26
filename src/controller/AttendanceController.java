package controller;

import entity.Attendance;
import service.AttendanceService;

import java.util.List;

public class AttendanceController {
    private AttendanceService attendanceService;

    public AttendanceController() {
        this.attendanceService = new AttendanceService();
    }

    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    public boolean addAttendance(Attendance attendance) {
        return attendanceService.addAttendance(attendance);
    }

    public boolean updateAttendance(Attendance attendance) {
        return attendanceService.updateAttendance(attendance);
    }

    public boolean deleteAttendance(int enrollmentId, String date) {
        return attendanceService.deleteAttendance(enrollmentId, date);
    }

    public List<Attendance> searchAttendance(String searchText) {
        return attendanceService.searchAttendance(searchText);
    }
    public int getEnrollmentId(int studentId, int courseId) {
        return attendanceService.getEnrollmentId(studentId, courseId);
    }
}