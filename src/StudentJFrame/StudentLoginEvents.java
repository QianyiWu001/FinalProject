package StudentJFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import DatabaseUtilities.Session;
import StudentAttendance.StudentAttendancePage;
import StudentBill.StudentBillDB;
import StudentBill.StudentBillPage;
import StudentCoursesList.StudentCoursesListPage;
import StudentGrades.StudentGradesPage;
import StudentProfile.StudentProfileDB;
import StudentProfile.StudentProfilePage;
import view.BasicLoginPage;

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
            StudentProfileDB DB = new StudentProfileDB();
            String[] studentProfile = DB.searchStudentById(studentID);

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
            int studentID = Session.getStudentID();
            StudentBillDB DB = new StudentBillDB();
            String[] studentBill = DB.getBillById(studentID);

            if (studentBill == null) {
                JOptionPane.showMessageDialog(studentLoginPage, "Cannot find student bill information.");
            } else {
                new StudentBillPage(studentBill);
                studentLoginPage.dispose();
            }
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
