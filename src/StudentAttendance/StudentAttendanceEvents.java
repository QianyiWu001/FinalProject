package StudentAttendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import StudentJFrame.StudentLoginPage;

public class StudentAttendanceEvents implements ActionListener {
    private StudentAttendancePage studentAttendancePage;

    public StudentAttendanceEvents(StudentAttendancePage studentAttendancePage) {
        this.studentAttendancePage = studentAttendancePage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Back")) {
            new StudentLoginPage();
            studentAttendancePage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        }
    }
}