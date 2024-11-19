package AdminStudentsManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import AdminJFrame.AdminLoginPage;
import AdminStudentsManagement.AddStudent.AddStudentPage;
import AdminStudentsManagement.DeleteStudent.DeleteStudentPage;
import AdminStudentsManagement.SearchStudent.SearchStudentPage;
import AdminStudentsManagement.UpdateStudent.UpdateStudentPage;

public class AdminStudentsManagementEvents implements ActionListener {
    private AdminStudentsManagementPage adminStudentsManagementPage;

    public AdminStudentsManagementEvents(AdminStudentsManagementPage adminStudentsManagementPage) {
        this.adminStudentsManagementPage = adminStudentsManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Back")) {
            new AdminLoginPage();
            adminStudentsManagementPage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        } else if (buttonText.equals("Add Student")) {
            new AddStudentPage();
        } else if (buttonText.equals("Delete Student")) {
            new DeleteStudentPage();
        } else if (buttonText.equals("Update Student")) {
            new UpdateStudentPage();
        } else if (buttonText.equals("Search Students")) {
            new SearchStudentPage();
        }
    }
    
}
