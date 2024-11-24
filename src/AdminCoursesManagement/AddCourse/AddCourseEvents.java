package AdminCoursesManagement.AddCourse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import AdminCoursesManagement.AdminCoursesManagementPage;
import AdminCoursesManagement.CoursesDatabase;

public class AddCourseEvents implements ActionListener {
    private AddCoursePage addCoursePage;
    private AdminCoursesManagementPage adminCoursesManagementPage;

    public AddCourseEvents(AddCoursePage addCoursePage, AdminCoursesManagementPage adminCoursesManagementPage) {
        this.addCoursePage = addCoursePage;
        this.adminCoursesManagementPage = adminCoursesManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            addCoursePage.dispose();
        } else if (buttonText.equals("Add")) {
            String courseID = addCoursePage.getCourseIDField().getText().trim();
            String courseName = addCoursePage.getCourseNameField().getText().trim();
            String credit = addCoursePage.getCreditField().getText().trim();
            String description = addCoursePage.getDescriptionField().getText().trim();
            
            if (courseID.isEmpty() || courseName.isEmpty() || credit.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(addCoursePage, "Please enter valid course information.");
                return;
            }

            int intCourseID;
            int intCredit;

            try {
                intCourseID = Integer.parseInt(courseID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(addCoursePage, "Please enter a NUMBER for course ID.");
                return;
            }

            try {
                intCredit = Integer.parseInt(credit);
                if (intCredit < 0) {
                    JOptionPane.showMessageDialog(addCoursePage, "Credit cannot be negative.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addCoursePage, "Please enter a NUMBER for credit.");
                return;
            }
            
            CoursesDatabase coursesDatabase = new CoursesDatabase();
            boolean isAdded = coursesDatabase.addCourse(intCourseID, courseName, intCredit, description);

            if (isAdded) {
                JOptionPane.showMessageDialog(addCoursePage, "Course added successfully.");
                addCoursePage.dispose();
                adminCoursesManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(addCoursePage, "Failed to add course.");
            }
        }
    }
}
