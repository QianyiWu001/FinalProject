package StudentGrades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import StudentJFrame.StudentLoginPage;

public class StudentGradesEvents implements ActionListener {
    private StudentGradesPage studentGradesPage;

    public StudentGradesEvents(StudentGradesPage studentGradesPage) {
        this.studentGradesPage = studentGradesPage;
    }

    
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Back")) {
            new StudentLoginPage();
            studentGradesPage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        }
    }
}