package AdminStudentsManagement.DeleteStudent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DeleteStudentEvents implements ActionListener {
    private DeleteStudentPage deleteStudentPage;

    public DeleteStudentEvents(DeleteStudentPage deleteStudentPage) {
        this.deleteStudentPage = deleteStudentPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            deleteStudentPage.dispose();
        }
    }
}
