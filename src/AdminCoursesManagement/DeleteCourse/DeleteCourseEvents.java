package AdminCoursesManagement.DeleteCourse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DeleteCourseEvents implements ActionListener {
    private DeleteCoursePage deleteCoursePage;

    public DeleteCourseEvents(DeleteCoursePage deleteCoursePage) {
        this.deleteCoursePage = deleteCoursePage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            deleteCoursePage.dispose();
        }
    }
}
