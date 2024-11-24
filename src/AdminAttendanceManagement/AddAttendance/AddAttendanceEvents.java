package AdminAttendanceManagement.AddAttendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import AdminAttendanceManagement.AdminAttendanceManagementPage;
import AdminAttendanceManagement.AttendanceDatabase;

public class AddAttendanceEvents implements ActionListener {
    private AddAttendancePage addAttendancePage;
    private AdminAttendanceManagementPage adminAttendanceManagementPage;
    
    public AddAttendanceEvents(AddAttendancePage addAttendancePage, AdminAttendanceManagementPage adminAttendanceManagementPage) {
        this.addAttendancePage = addAttendancePage;
        this.adminAttendanceManagementPage = adminAttendanceManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            addAttendancePage.dispose();
        } else if (buttonText.equals("Add")) {
            String studentID = addAttendancePage.getStudentIDField().getText();
            String courseID = addAttendancePage.getCourseIDField().getText();
            String date = addAttendancePage.getDateField().getText();
            String status = addAttendancePage.getStatusComboBox().getSelectedItem().toString();

            if (studentID.equals("") || courseID.equals("") || date.equals("") || status.equals("-")) {
                JOptionPane.showMessageDialog(addAttendancePage, "Please enter all fields.");
                return;
            }

            int intStudentID;
            int intCourseID;
            java.sql.Date sqlDate;

            try {
                intStudentID = Integer.parseInt(studentID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(addAttendancePage, "Please enter a NUMBER for Student ID.");
                return;
            }

            try {
                intCourseID = Integer.parseInt(courseID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(addAttendancePage, "Please enter a NUMBER for Course ID.");
                return;
            }

            try {
                sqlDate = java.sql.Date.valueOf(date);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(addAttendancePage, "Please enter a valid date in the format yyyy-MM-dd.");
                return;
            }

            AttendanceDatabase attendanceDatabase = new AttendanceDatabase();
            boolean isAdded = attendanceDatabase.addAttendance(intStudentID, intCourseID, sqlDate, status);

            if (isAdded) {
                JOptionPane.showMessageDialog(addAttendancePage, "Attendance added successfully.");
                addAttendancePage.dispose();
                adminAttendanceManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(addAttendancePage, "Failed to add attendance.");
            }
        }
    }
}
