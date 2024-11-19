package StudentCoursesList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import StudentJFrame.StudentLoginPage;

public class StudentCoursesListEvents implements ActionListener {
    private StudentCoursesListPage studentCoursesListPage;

    public StudentCoursesListEvents(StudentCoursesListPage studentCoursesListPage) {
        this.studentCoursesListPage = studentCoursesListPage;
    }
   
    public void actionPerformed(ActionEvent e) {
       JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Back")) {
            new StudentLoginPage();
            studentCoursesListPage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        }
    }
}
