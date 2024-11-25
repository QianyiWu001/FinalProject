package AdminAttendanceManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import AdminAttendanceManagement.AddAttendance.AddAttendancePage;
import AdminAttendanceManagement.DeleteAttendance.DeleteAttendancePage;
import AdminJFrame.AdminLoginPage;

public class AdminAttendanceManagementEvents implements ActionListener {
    private AdminAttendanceManagementPage adminAttendanceManagementPage;

    public AdminAttendanceManagementEvents(AdminAttendanceManagementPage adminAttendanceManagementPage) {
        this.adminAttendanceManagementPage = adminAttendanceManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Back")) {
            new AdminLoginPage();
            adminAttendanceManagementPage.dispose();
        } else if (buttonText.equals("Refresh")) {
            adminAttendanceManagementPage.updateTable();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        } else if (buttonText.equals("Add Attendance")) {
            new AddAttendancePage(adminAttendanceManagementPage);
        } else if (buttonText.equals("Delete Attendance")) {
            new DeleteAttendancePage(adminAttendanceManagementPage);
        } else if (buttonText.equals("Update Attendance")) {
            DefaultTableModel model = (DefaultTableModel) adminAttendanceManagementPage.getAttendanceTable().getModel();
            AttendanceDatabase attendanceDatabase = new AttendanceDatabase();
            boolean isUpdated = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                int studentID = Integer.parseInt(model.getValueAt(i, 0).toString());
                int courseID = Integer.parseInt(model.getValueAt(i, 1).toString());
                String date = model.getValueAt(i, 2).toString();    
                java.sql.Date sqlDate = java.sql.Date.valueOf(date);
                String status = (String) model.getValueAt(i, 3);

                if (attendanceDatabase.updateAttendance(studentID, courseID, sqlDate, status)) {
                    isUpdated = true;
                }
            }

            if (isUpdated) {
                JOptionPane.showMessageDialog(adminAttendanceManagementPage, "Attendance updated successfully.");
                adminAttendanceManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(adminAttendanceManagementPage, "No attendance to update.");
            }
        } else if (buttonText.equals("Search Attendance")) {
            String studentID = adminAttendanceManagementPage.getSearchStudentIDField().getText().trim();
            String courseID = adminAttendanceManagementPage.getSearchCourseIDField().getText().trim();
            String date = adminAttendanceManagementPage.getSearchDateField().getText().trim();

            if (studentID.isEmpty() && courseID.isEmpty() && date.isEmpty()) {
                JOptionPane.showMessageDialog(adminAttendanceManagementPage, "Please enter at least one field to search.");
            }

            int intStudentID = 0;
            int intCourseID = 0;
            java.sql.Date sqlDate = null;

            if (!studentID.isEmpty()) {
                try {
                    intStudentID = Integer.parseInt(studentID);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(adminAttendanceManagementPage, "Invalid student ID.");
                    return;
                }
            }

            if (!courseID.isEmpty()) {
                try {
                    intCourseID = Integer.parseInt(courseID);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(adminAttendanceManagementPage, "Invalid course ID.");
                    return;
                }
            }

            if (!date.isEmpty()) {
                try {
                    sqlDate = java.sql.Date.valueOf(date);
                } catch (IllegalArgumentException nfe) {
                    JOptionPane.showMessageDialog(adminAttendanceManagementPage, "Invalid date format. Please use yyyy-MM-dd.");
                    return;
                }
            }
            AttendanceDatabase attendanceDatabase = new AttendanceDatabase();
            Object[][] data = attendanceDatabase.searchAttendance(intStudentID, intCourseID, sqlDate);

            if (data.length == 0) {
                JOptionPane.showMessageDialog(adminAttendanceManagementPage, "No attendance found.");
                return;
            } else {
                adminAttendanceManagementPage.updateSearchedData(data);
            }

        }
    }

}
