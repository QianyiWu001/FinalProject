package AdminAttendanceManagement.DeleteAttendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import AdminAttendanceManagement.AdminAttendanceManagementPage;
import AdminAttendanceManagement.AttendanceDatabase;

public class DeleteAttendanceEvents implements ActionListener {
    private DeleteAttendancePage deleteAttendancePage;
    private AdminAttendanceManagementPage adminAttendanceManagementPage;

    public DeleteAttendanceEvents(DeleteAttendancePage deleteAttendancePage, AdminAttendanceManagementPage adminAttendanceManagementPage) {
        this.deleteAttendancePage = deleteAttendancePage;
        this.adminAttendanceManagementPage = adminAttendanceManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            deleteAttendancePage.dispose();
        } else if (buttonText.equals("Delete")) {
            String studentID = deleteAttendancePage.getStudentIDField().getText().trim();
            String courseID = deleteAttendancePage.getCourseIDField().getText().trim();
            String date = deleteAttendancePage.getDateField().getText().trim();

            if (studentID.isEmpty() || courseID.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(deleteAttendancePage, "Please enter student ID and course ID to delete attendance.");
                return;
            }

            int intStudentID;
            int intCourseID;
            java.sql.Date sqlDate;

            try {
                intStudentID = Integer.parseInt(studentID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(deleteAttendancePage, "Invalid student ID format.");
                return;
            }

            try {
                intCourseID = Integer.parseInt(courseID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(deleteAttendancePage, "Invalid course ID format.");
                return;
            }

            try {
                sqlDate = java.sql.Date.valueOf(date);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(deleteAttendancePage, "Invalid date format.");
                return;
            }

            AttendanceDatabase attendanceDatabase = new AttendanceDatabase();
            boolean isDeleted = attendanceDatabase.deleteAttendance(intStudentID, intCourseID, sqlDate);

            if (isDeleted) {
                JOptionPane.showMessageDialog(deleteAttendancePage, "Attendance for student " + studentID + " in course " + courseID + " has been deleted.");
                deleteAttendancePage.dispose();
                adminAttendanceManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(deleteAttendancePage, "Failed to delete attendance for student " + studentID + " in course " + courseID + ".");
            }
        }
    }
}
