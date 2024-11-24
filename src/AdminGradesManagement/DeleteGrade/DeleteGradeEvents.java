package AdminGradesManagement.DeleteGrade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import AdminGradesManagement.AdminGradesManagementPage;
import AdminGradesManagement.GradesDatabase;

public class DeleteGradeEvents implements ActionListener {
    private DeleteGradePage deleteGradePage;
    private AdminGradesManagementPage adminGradesManagementPage;

    public DeleteGradeEvents(DeleteGradePage deleteGradePage, AdminGradesManagementPage adminGradesManagementPage) {
        this.deleteGradePage = deleteGradePage;
        this.adminGradesManagementPage = adminGradesManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            deleteGradePage.dispose();
        } else if (buttonText.equals("Delete")) {
            String studentID = deleteGradePage.getStudentIDField().getText().trim();
            String courseID = deleteGradePage.getCourseIDField().getText().trim();

            if (studentID.isEmpty() || courseID.isEmpty()) {
                JOptionPane.showMessageDialog(deleteGradePage, "Please enter student ID and course ID.");
                return;
            }

            int intStudentID;
            int intCourseID;
            
            try {
                intStudentID = Integer.parseInt(studentID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(deleteGradePage, "Invalid student ID format.");
                return;
            }

            try {
                intCourseID = Integer.parseInt(courseID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(deleteGradePage, "Invalid course ID format.");
                return;
            }

            GradesDatabase gradesDatabase = new GradesDatabase();
            boolean isDeleted = gradesDatabase.deleteCourse(intStudentID, intCourseID);

            if (isDeleted) {
                JOptionPane.showMessageDialog(deleteGradePage, "Grade deleted successfully.");
                deleteGradePage.dispose();
                adminGradesManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(deleteGradePage, "Grade deletion failed.");
            }
        }
    }
}
