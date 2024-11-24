package AdminGradesManagement.AddGrade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import AdminGradesManagement.AdminGradesManagementPage;
import AdminGradesManagement.GradesDatabase;

public class AddGradeEvents implements ActionListener {
    private AddGradePage addGradePage;
    private AdminGradesManagementPage adminGradesManagementPage;

    public AddGradeEvents(AddGradePage addGradePage, AdminGradesManagementPage adminGradesManagementPage) {
        this.addGradePage = addGradePage;
        this.adminGradesManagementPage = adminGradesManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            addGradePage.dispose();
        } else if (buttonText.equals("Add")) {
            String studentID = addGradePage.getStudentIDField().getText().trim();
            String courseID = addGradePage.getCourseIDField().getText().trim();
            String grade = addGradePage.getGradeField().getText().trim();

            if (courseID.isEmpty() || studentID.isEmpty() || grade.isEmpty()) {
                JOptionPane.showMessageDialog(addGradePage, "Please enter valid information for all fields.");
                return;
            }
            int intStudentID;
            int intCourseID;
            int intGradeID;

            try {
                intStudentID = Integer.parseInt(studentID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(addGradePage, "Please enter a NUMBER for student ID.");
                return;
            }

            try {
                intCourseID = Integer.parseInt(courseID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(addGradePage, "Please enter a NUMBER for course ID.");
                return;
            }

            try {
                intGradeID = Integer.parseInt(grade);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(addGradePage, "Please enter a NUMBER for grade.");
                return;
            }

            GradesDatabase gradesDatabase = new GradesDatabase();
            boolean isAdded = gradesDatabase.addGrade(intStudentID, intCourseID, intGradeID);

            if (isAdded) {
                JOptionPane.showMessageDialog(addGradePage, "Grade added successfully.");
                addGradePage.dispose();
                adminGradesManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(addGradePage, "Failed to add grade.");
            }
        }
    }
}
