package AdminGradesManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import AdminGradesManagement.AddGrade.AddGradePage;
import AdminGradesManagement.DeleteGrade.DeleteGradePage;
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
        } else if (buttonText.equals("Refresh")) {
            adminGradesManagementPage.updateTable();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        } else if (buttonText.equals("Add Grade")) {
            new AddGradePage(adminGradesManagementPage);
        } else if (buttonText.equals("Delete Grade")) {
            new DeleteGradePage(adminGradesManagementPage);
        } else if (buttonText.equals("Update Grade")) {
            DefaultTableModel model = (DefaultTableModel) adminGradesManagementPage.getGradesTable().getModel();
            GradesDatabase gradesDatabase = new GradesDatabase();
            boolean isUpdated = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                int studentID = Integer.parseInt(model.getValueAt(i, 0).toString());
                int courseID = Integer.parseInt(model.getValueAt(i, 1).toString());
                int grade = Integer.parseInt(model.getValueAt(i, 2).toString());
                if (gradesDatabase.updateGrade(studentID, courseID, grade)) {
                    isUpdated = true;
                }
            }

            if (isUpdated) {
                JOptionPane.showMessageDialog(adminGradesManagementPage, "Grade(s) updated successfully.");
                adminGradesManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(adminGradesManagementPage, "No grade(s) updated.");
            }
        } else if (buttonText.equals("Search Grade")) {
            String studentID = adminGradesManagementPage.getSearchGradeByStudentIDField().getText().trim();
            String courseID = adminGradesManagementPage.getSearchGradeByCourseIDField().getText().trim();

            if (studentID.isEmpty() && courseID.isEmpty()){
                JOptionPane.showMessageDialog(adminGradesManagementPage, "Please fill in at least one fields");
            }

            int intStudentID = 0;
            int intCourseID = 0;

            if (!studentID.isEmpty()) {
                try {
                    intStudentID = Integer.parseInt(studentID);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(adminGradesManagementPage, "Please enter a valid student ID.");
                    return;
                }
            }

            if (!courseID.isEmpty()) {
                try {
                    intCourseID = Integer.parseInt(courseID);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(adminGradesManagementPage, "Please enter a valid course ID.");
                    return;
                }
            }
            GradesDatabase gradesDatabase = new GradesDatabase();
            Object[][] data = gradesDatabase.searchGrade(intStudentID, intCourseID);

            if (data.length == 0) {
                JOptionPane.showMessageDialog(adminGradesManagementPage, "No matching grades found.");
                return;
            } else {
                adminGradesManagementPage.updateSearchedData(data);
            }
        }
    }
}
