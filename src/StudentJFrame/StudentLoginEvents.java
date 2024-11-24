package StudentJFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import BasicLogin.BasicLoginPage;
import StudentAttendance.StudentAttendancePage;
import StudentBill.StudentBillPage;
import StudentCoursesList.StudentCoursesListPage;
import StudentGrades.StudentGradesPage;
import StudentProfile.StudentProfilePage;

public class StudentLoginEvents implements ActionListener {
    private StudentLoginPage studentLoginPage;

    public StudentLoginEvents(StudentLoginPage studentLoginPage) {
        this.studentLoginPage = studentLoginPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();
        
        if (buttonText.equals("View Profile")) {
            new StudentProfilePage();
            studentLoginPage.dispose();
        } else if (buttonText.equals("View Courses")) {
            new StudentCoursesListPage();
            studentLoginPage.dispose();
        } else if (buttonText.equals("View Bill")) {
            new StudentBillPage();
            studentLoginPage.dispose();
        } else if (buttonText.equals("View Attendance")) {
            new StudentAttendancePage();
            studentLoginPage.dispose();
        } else if (buttonText.equals("View Grades")) {
            new StudentGradesPage();
            studentLoginPage.dispose();
        } else if (buttonText.equals("Back")) {
            new BasicLoginPage();
            studentLoginPage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        }
    }
}
