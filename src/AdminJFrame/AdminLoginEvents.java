package AdminJFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import AdminAttendanceManagement.AdminAttendanceManagementPage;
import AdminCoursesManagement.AdminCoursesManagementPage;
import view.AdminGradesManagementPage;
import view.AdminStudentsManagementPage;
import view.BasicLoginPage;
public class AdminLoginEvents implements ActionListener {
    private AdminLoginPage adminLoginPage;

    public AdminLoginEvents(AdminLoginPage adminLoginPage) {
        this.adminLoginPage = adminLoginPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();
        
        if (buttonText.equals("Courses Management")) {
            new AdminCoursesManagementPage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("Students Management")) {
            new AdminStudentsManagementPage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("Attendance Management")) {
            new AdminAttendanceManagementPage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("Grades Management")) {
            new AdminGradesManagementPage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("Back")) {
            new BasicLoginPage();
            adminLoginPage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        }
    }
}
