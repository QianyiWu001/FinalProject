package AdminGradesManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import AdminGradesManagement.AddGrade.AddGradePage;
import AdminGradesManagement.DeleteGrade.DeleteGradePage;
import AdminGradesManagement.SearchGrade.SearchGradePage;
import AdminGradesManagement.UpdateGrade.UpdateGradePage;
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
        } else if (buttonText.equals("Add Grade")) {
            new AddGradePage(adminGradesManagementPage);
        } else if (buttonText.equals("Delete Grade")) {
            new DeleteGradePage(adminGradesManagementPage);
        } else if (buttonText.equals("Update Grade")) {
            new UpdateGradePage();
        } else if (buttonText.equals("Search Grade")) {
            new SearchGradePage();
        }
    }
}
