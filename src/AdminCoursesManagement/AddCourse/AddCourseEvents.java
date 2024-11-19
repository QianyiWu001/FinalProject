package AdminCoursesManagement.AddCourse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AddCourseEvents implements ActionListener {
    private AddCoursePage addCoursePage;

    public AddCourseEvents(AddCoursePage addCoursePage) {
        this.addCoursePage = addCoursePage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            addCoursePage.dispose();
        }
    }
}
