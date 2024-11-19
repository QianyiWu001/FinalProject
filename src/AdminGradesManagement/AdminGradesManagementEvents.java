package AdminGradesManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import AdminJFrame.AdminLoginPage;

public class AdminGradesManagementEvents implements ActionListener {
    private AdminGradesManagementPage adminGradesManagementPage;

    public AdminGradesManagementEvents(AdminGradesManagementPage adminGradesManagementPage) {
        this.adminGradesManagementPage = adminGradesManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Back")) {
            new AdminLoginPage();
            adminGradesManagementPage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        } else if (buttonText.equals("Clear")) {
            adminGradesManagementPage.getCourseIDField().setText("");
            adminGradesManagementPage.getStudentIDField().setText("");
            adminGradesManagementPage.getGradeField().setText("");
            adminGradesManagementPage.getLevelComboBox().setSelectedIndex(0);
        }
    }
}
