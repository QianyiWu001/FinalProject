package AdminStudentsManagement.AddStudent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AddStudentEvents implements ActionListener {
    private AddStudentPage addStudentPage;

    public AddStudentEvents(AddStudentPage addStudentPage) {
        this.addStudentPage = addStudentPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            addStudentPage.dispose();
        }
    }
}
