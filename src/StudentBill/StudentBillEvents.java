package StudentBill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import StudentJFrame.StudentLoginPage;

public class StudentBillEvents implements ActionListener {
    private StudentBillPage studentBillPage;

    public StudentBillEvents(StudentBillPage studentBillPage) {
        this.studentBillPage = studentBillPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Back")) {
            new StudentLoginPage();
            studentBillPage.dispose();
        } else if (buttonText.equals("Exit")) {
            System.exit(0);
        }
    }
}
