package AdminStudentsManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import AdminJFrame.AdminLoginPage;
import AdminStudentsManagement.AddStudent.AddStudentPage;
import AdminStudentsManagement.DeleteStudent.DeleteStudentPage;

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
        } else if (buttonText.equals("Refresh")) {
            adminStudentsManagementPage.updateTable();
        }else if (buttonText.equals("Exit")) {
            System.exit(0);
        } else if (buttonText.equals("Add Student")) {
            new AddStudentPage(adminStudentsManagementPage);
        } else if (buttonText.equals("Delete Student")) {
            new DeleteStudentPage(adminStudentsManagementPage);
        } else if (buttonText.equals("Update Student")) {
            DefaultTableModel model = (DefaultTableModel) adminStudentsManagementPage.getStudentsTable().getModel();
            StudentsDatabase studentsDatabase = new StudentsDatabase();
            boolean isUpdated = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                int studentID = Integer.parseInt(model.getValueAt(i, 0).toString());
                String name = (String) model.getValueAt(i, 1);
                String email = (String) model.getValueAt(i, 2);
                String phone = (String) model.getValueAt(i, 3);
                String address = (String) model.getValueAt(i, 4);

                if (studentsDatabase.updateStudent(studentID, name, email, phone, address)) {
                    isUpdated = true;
                }
            }

            if (isUpdated) {
                JOptionPane.showMessageDialog(adminStudentsManagementPage, "Student(s) updated successfully.");
                adminStudentsManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(adminStudentsManagementPage, "No student(s) updated.");
            }
        } else if (buttonText.equals("Search Student")) {
            String query = adminStudentsManagementPage.getSearchStudentField().getText().trim();
            if (query.isEmpty()) {
                JOptionPane.showMessageDialog(adminStudentsManagementPage, "Please enter a student name to search.");
                return;
            }
            StudentsDatabase studentsDatabase = new StudentsDatabase();
            Object[][] data = studentsDatabase.searchStudent(query);
            if (data.length == 0) {
                JOptionPane.showMessageDialog(adminStudentsManagementPage, "No student found.");
                return;
            } else {
                adminStudentsManagementPage.updateSearchedData(data);
            }
        }
    }
}
