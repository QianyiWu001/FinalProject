package AdminStudentsManagement.AddStudent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import AdminStudentsManagement.AdminStudentsManagementPage;
import AdminStudentsManagement.StudentsDatabase;

public class AddStudentEvents implements ActionListener {
    private AddStudentPage addStudentPage;
    private AdminStudentsManagementPage adminStudentsManagementPage;

    public AddStudentEvents(AddStudentPage addStudentPage, AdminStudentsManagementPage adminStudentsManagementPage) {
        this.addStudentPage = addStudentPage;
        this.adminStudentsManagementPage = adminStudentsManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            addStudentPage.dispose();
        } else if (buttonText.equals("Add")) {
            String studentID = addStudentPage.getStudentIDField().getText().trim();
            String studentName = addStudentPage.getNameField().getText().trim();
            String studentEmail = addStudentPage.getEmailField().getText().trim();
            String studentPhone = addStudentPage.getPhoneField().getText().trim();
            String studentAddress = addStudentPage.getAddressField().getText().trim();

            if (studentID.isEmpty() || studentName.isEmpty() || studentEmail.isEmpty() || studentPhone.isEmpty() || studentAddress.isEmpty()) {
                JOptionPane.showMessageDialog(addStudentPage, "Please enter valid student information.");
            }

            int intStudentID;
            
            try {
                intStudentID = Integer.parseInt(studentID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(addStudentPage, "Please enter a NUMBER for student ID.");
                return;
            }

            StudentsDatabase studentsDatabase = new StudentsDatabase();
            boolean isAdded = studentsDatabase.addStudent(intStudentID, studentName, studentEmail, studentPhone, studentAddress);

            if (isAdded) {
                JOptionPane.showMessageDialog(addStudentPage, "Student added successfully!");
                addStudentPage.dispose();
                adminStudentsManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(addStudentPage, "Failed to add student.");
            }
        }
    }
}
