package AdminAttendanceManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import AdminAttendanceManagement.AddAttendance.AddAttendancePage;
import AdminAttendanceManagement.DeleteAttendance.DeleteAttendancePage;
import AdminAttendanceManagement.SearchAttendance.SearchAttendancePage;
import AdminAttendanceManagement.UpdateAttendance.UpdateAttendancePage;
import AdminJFrame.AdminLoginPage;

public class AdminAttendanceManagementEvents implements ActionListener {
    private AdminAttendanceManagementPage adminAttendanceManagementPage;

    public AdminAttendanceManagementEvents(AdminAttendanceManagementPage adminAttendanceManagementPage) {
        this.adminAttendanceManagementPage = adminAttendanceManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Back")) {
            new AdminLoginPage();
            adminAttendanceManagementPage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        } else if (buttonText.equals("Add Attendance")) {
            new AddAttendancePage(adminAttendanceManagementPage);
        } else if (buttonText.equals("Delete Attendance")) {
            new DeleteAttendancePage(adminAttendanceManagementPage);
        } else if (buttonText.equals("Update Attendance")) {
            new UpdateAttendancePage();
        } else if (buttonText.equals("Search Attendance")) {
            new SearchAttendancePage();
        }
    }

}
