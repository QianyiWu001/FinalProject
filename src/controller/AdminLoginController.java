package controller;

import view.BasicLoginPage;
import view.Admin.*;


public class AdminLoginController {
    private AdminLoginPage adminLoginPage;

    public AdminLoginController(AdminLoginPage adminLoginPage) {
        this.adminLoginPage = adminLoginPage;
    }

    // course management
    public void handleCourseManagement() {
        new AdminCoursesManagementPage();
        adminLoginPage.dispose();
    }

    // student management
    public void handleStudentManagement() {
        new AdminStudentsManagementPage();
        adminLoginPage.dispose();
    }

    // attendance 
    public void handleAttendanceManagement() {
        new AdminAttendanceManagementPage();
        adminLoginPage.dispose();
    }

    // grades
    public void handleGradesManagement() {
        new AdminGradesManagementPage();
        adminLoginPage.dispose();
    }

    public void handleEnrollmentManagement() {
        new AdminEnrollmentManagementPage(); 
        adminLoginPage.dispose();
    }

    public void handleBillManagement() {
        new AdminBillManagementPage(); 
        adminLoginPage.dispose(); 
    }
    // login
    public void handleBack() {
        new BasicLoginPage();
        adminLoginPage.dispose();
    }

  //exit
    public void handleExit() {
        System.exit(0);
    }
}