package AdminCoursesManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import AdminCoursesManagement.AddCourse.AddCoursePage;
import AdminCoursesManagement.DeleteCourse.DeleteCoursePage;
import AdminCoursesManagement.SearchCourse.SearchCoursePage;
import AdminCoursesManagement.UpdateCourse.UpdateCoursePage;
import AdminJFrame.AdminLoginPage;

public class AdminCoursesManagementEvents implements ActionListener {
    private AdminCoursesManagementPage adminCoursesManagementPage;

    public AdminCoursesManagementEvents(AdminCoursesManagementPage adminCoursesManagementPage) {
        this.adminCoursesManagementPage = adminCoursesManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Back")) {
            new AdminLoginPage();
            adminCoursesManagementPage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        } else if (buttonText.equals("Add Course")) {
            new AddCoursePage();
        } else if (buttonText.equals("Delete Course")) {
            new DeleteCoursePage();
        } else if (buttonText.equals("Update Course")) {
            new UpdateCoursePage();
        } else if (buttonText.equals("Search Course")) {
            new SearchCoursePage();
        }
    }
}
