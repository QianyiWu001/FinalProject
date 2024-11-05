package AdminJFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;

import AdminAddAttendance.AdminAddAttendancePage;
import AdminAddCourses.AdminAddCoursesPage;
import AdminAddFinalGrade.AdminAddFinalGradePage;
import AdminAddStudent.AdminAddStudentPage;
import AdminDeleteCourse.AdminDeleteCoursePage;
import AdminUpdateStudent.AdminUpdateStudentPage;
import AdminDeleteStudent.AdminDeleteStudentPage;
import AdminListStudent.AdminListStudentPage;

public class AdminLoginEvents implements ActionListener {
    private AdminLoginPage adminLoginPage;

    public AdminLoginEvents(AdminLoginPage adminLoginPage) {
        this.adminLoginPage = adminLoginPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();
        
        if (buttonText.equals("Add Student")) {
            new AdminAddStudentPage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("Update Student")) {
            new AdminUpdateStudentPage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("Delete Student")) {
            new AdminDeleteStudentPage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("List Student")) {
            new AdminListStudentPage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("Add Course")) {
            new AdminAddCoursesPage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("Delete Course")) {
            new AdminDeleteCoursePage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("Add Attendance")) {
            new AdminAddAttendancePage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("Add Final Grade")) {
            new AdminAddFinalGradePage();
            adminLoginPage.dispose();
        }
    }
}
