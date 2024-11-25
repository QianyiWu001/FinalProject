package AdminCoursesManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import AdminCoursesManagement.AddCourse.AddCoursePage;
import AdminCoursesManagement.DeleteCourse.DeleteCoursePage;
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
        } else if (buttonText.equals("Refresh")) {
            adminCoursesManagementPage.updateTable();
        }else if (buttonText.equals("Exit")) {
            System.exit(0);
        } else if (buttonText.equals("Add Course")) {
            new AddCoursePage(adminCoursesManagementPage);
        } else if (buttonText.equals("Delete Course")) {
            new DeleteCoursePage(adminCoursesManagementPage);
        } else if (buttonText.equals("Update Course")) {
            DefaultTableModel model = (DefaultTableModel) adminCoursesManagementPage.getCoursesTable().getModel();
            CoursesDatabase coursesDatabase = new CoursesDatabase();
            boolean isUpdated = false;
            for(int i = 0; i < model.getRowCount(); i++) {
                int courseID = Integer.parseInt(model.getValueAt(i, 0).toString());
                String courseName = (String) model.getValueAt(i, 1);
                String description = (String) model.getValueAt(i, 2);
                int credits = Integer.parseInt(model.getValueAt(i, 3).toString()); 

                if (coursesDatabase.updateCourse(courseID, courseName, description, credits)) {
                    isUpdated = true;
                }
            }  

            if (isUpdated) {
                JOptionPane.showMessageDialog(adminCoursesManagementPage, "Course(s) updated successfully.");
                adminCoursesManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(adminCoursesManagementPage, "No course(s) updated.");
            }
        } else if (buttonText.equals("Search Course")) {
            String courseName = adminCoursesManagementPage.getSearchCourseField().getText().trim();
            if (courseName.isEmpty()) {
                JOptionPane.showMessageDialog(adminCoursesManagementPage, "Please enter a course name to search.");
                return;
            }
            CoursesDatabase courseDatabase = new CoursesDatabase();
            Object[][] data = courseDatabase.searchCourse(courseName);

            if (data.length == 0) {
                JOptionPane.showMessageDialog(adminCoursesManagementPage, "No course found.");
                return;
            } else {
                adminCoursesManagementPage.updateSearchedData(data);
            }
        } 
    }
}
