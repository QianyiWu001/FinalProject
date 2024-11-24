package AdminCoursesManagement.DeleteCourse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import AdminCoursesManagement.AdminCoursesManagementPage;
import AdminCoursesManagement.CoursesDatabase;

public class DeleteCourseEvents implements ActionListener {
    private DeleteCoursePage deleteCoursePage;
    private AdminCoursesManagementPage adminCoursesManagementPage;

    public DeleteCourseEvents(DeleteCoursePage deleteCoursePage, AdminCoursesManagementPage adminCoursesManagementPage) {
        this.deleteCoursePage = deleteCoursePage;
        this.adminCoursesManagementPage = adminCoursesManagementPage;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("Cancel")) {
            deleteCoursePage.dispose();
        } else if (buttonText.equals("Delete")) {
            String courseID = deleteCoursePage.getCourseIDField().getText().trim();
            if (courseID.isEmpty()) {
                JOptionPane.showMessageDialog(deleteCoursePage, "Please enter the course ID to delete.");
                return;
            }
            int intCourseID;

            try {
                intCourseID = Integer.parseInt(courseID);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(deleteCoursePage, "Invalid course ID format.");
                return;
            }

            CoursesDatabase coursesDatabase = new CoursesDatabase();
            boolean isDeleted = coursesDatabase.deleteCourse(intCourseID);

            if (isDeleted) {
                JOptionPane.showMessageDialog(deleteCoursePage, "Course " + courseID + " has been deleted.");
                deleteCoursePage.dispose();
                adminCoursesManagementPage.updateTable();
            } else {
                JOptionPane.showMessageDialog(deleteCoursePage, "Failed to delete course " + courseID);
            }
        }
    }
}
