package StudentJFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import DatabaseUtilities.Session;

import BasicLogin.BasicLoginPage;
import StudentAttendance.StudentAttendancePage;
import StudentBill.StudentBillPage;
import StudentCoursesList.StudentCoursesListPage;
import StudentGrades.StudentGradesPage;
import StudentProfile.StudentDB;
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
            int studentID = Session.getStudentID();
            StudentDB studentDB = new StudentDB();
            String[] studentProfile = studentDB.searchStudentById(studentID);

            if (studentProfile == null) {
                JOptionPane.showMessageDialog(studentLoginPage, "Cannot find student profile.");
            } else {
                new StudentProfilePage(studentProfile);
                studentLoginPage.dispose();
            }
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
            Session.clear();
            new BasicLoginPage();
            studentLoginPage.dispose();
        } else if (buttonText.equals("Exit")) {
            Session.clear();
            System.exit(0);
        }
    }
}
