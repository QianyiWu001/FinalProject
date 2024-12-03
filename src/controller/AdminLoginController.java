package controller;

import view.BasicLoginPage;
import view.Admin.*;


public class AdminLoginController {
    private AdminLoginPage adminLoginPage;

    public AdminLoginController(AdminLoginPage adminLoginPage) {
        this.adminLoginPage = adminLoginPage;
    }

    // Course management
    public void handleCourseManagement() {
        new AdminCoursesManagementPage();
        adminLoginPage.dispose();
    }

    // Student management
    public void handleStudentManagement() {
        new AdminStudentsManagementPage();
        adminLoginPage.dispose();
    }

    // Attendance 
    public void handleAttendanceManagement() {
        new AdminAttendanceManagementPage();
        adminLoginPage.dispose();
    }

    // Grades
    public void handleGradesManagement() {
        new AdminGradesManagementPage();
        adminLoginPage.dispose();
    }

    // Enrollment
    public void handleEnrollmentManagement() {
        new AdminEnrollmentManagementPage(); 
        adminLoginPage.dispose();
    }

    // Bill
    public void handleBillManagement() {
        new AdminBillManagementPage(); 
        adminLoginPage.dispose(); 
    }
    // Login
    public void handleBack() {
        new BasicLoginPage();
        adminLoginPage.dispose();
    }

    // Exit
    public void handleExit() {
        System.exit(0);
    }
}