package AdminStudentsManagement.DeleteStudent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import AdminStudentsManagement.AdminStudentsManagementPage;
import AdminStudentsManagement.StudentsDatabase;

public class DeleteStudentEvents implements ActionListener {
    private DeleteStudentPage deleteStudentPage;
    private AdminStudentsManagementPage adminStudentsManagementPage;

    public DeleteStudentEvents(DeleteStudentPage deleteStudentPage, AdminStudentsManagementPage adminStudentsManagementPage) {
        this.deleteStudentPage = deleteStudentPage;
        this.adminStudentsManagementPage = adminStudentsManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            deleteStudentPage.dispose();
        } else if (buttonText.equals("Delete")) {
            String studentID = deleteStudentPage.getStudentIDField().getText().trim();
            if (studentID.isEmpty()) {
                JOptionPane.showMessageDialog(deleteStudentPage, "Please enter the student ID to delete.");
                return;
            }
            int intStudentID; 

            try {
                intStudentID = Integer.parseInt(studentID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(deleteStudentPage, "Invalid student ID format.");
                return;
            }

            StudentsDatabase studentsDatabase = new StudentsDatabase();
            boolean isDeleted = studentsDatabase.deleteStudent(intStudentID);

            if (isDeleted) {
                JOptionPane.showMessageDialog(deleteStudentPage, "Student " + studentID + " has been deleted.");
                deleteStudentPage.dispose();
                adminStudentsManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(deleteStudentPage, "Failed to delete student " + studentID);
            }
        }
    }
}
