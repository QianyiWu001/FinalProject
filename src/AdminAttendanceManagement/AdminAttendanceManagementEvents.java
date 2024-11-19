package AdminAttendanceManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import AdminJFrame.AdminLoginPage;

public class AdminAttendanceManagementEvents implements ActionListener {
    private AdminAttendanceManagementPage attendanceManagementPage;

    public AdminAttendanceManagementEvents(AdminAttendanceManagementPage attendanceManagementPage) {
        this.attendanceManagementPage = attendanceManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Back")) {
            new AdminLoginPage();
            attendanceManagementPage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        } else if (buttonText.equals("Clear")) {
            attendanceManagementPage.getCourseIDField().setText("");
            attendanceManagementPage.getStudentIDField().setText("");
            attendanceManagementPage.getDateField().setText("");
            attendanceManagementPage.getStatusComboBox().setSelectedIndex(0);
        }
    }

}
