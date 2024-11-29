package controller;

import AdminAttendanceManagement.AdminAttendanceManagementPage;
import view.AdminCoursesManagementPage;
import view.AdminGradesManagementPage;
import view.AdminLoginPage;
import view.AdminStudentsManagementPage;
import view.BasicLoginPage;


public class AdminLoginController {
    private AdminLoginPage adminLoginPage;

    public AdminLoginController(AdminLoginPage adminLoginPage) {
        this.adminLoginPage = adminLoginPage;
    }

    // 处理课程管理
    public void handleCourseManagement() {
        new AdminCoursesManagementPage();
        adminLoginPage.dispose();
    }

    // 处理学生管理
    public void handleStudentManagement() {
        new AdminStudentsManagementPage();
        adminLoginPage.dispose();
    }

    // 处理考勤管理
    public void handleAttendanceManagement() {
        new AdminAttendanceManagementPage();
        adminLoginPage.dispose();
    }

    // 处理成绩管理
    public void handleGradesManagement() {
        new AdminGradesManagementPage();
        adminLoginPage.dispose();
    }

    // 返回到登录页面
    public void handleBack() {
        new BasicLoginPage();
        adminLoginPage.dispose();
    }

    // 退出程序
    public void handleExit() {
        System.exit(0);
    }
}