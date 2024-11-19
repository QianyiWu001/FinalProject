package StudentProfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import StudentJFrame.StudentLoginPage;

public class StudentProfileEvents implements ActionListener {
    private StudentProfilePage studentProfilePage;

    public StudentProfileEvents(StudentProfilePage studentProfilePage) {
        this.studentProfilePage = studentProfilePage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Back")) {
            new StudentLoginPage();
            studentProfilePage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        }
    }
}
