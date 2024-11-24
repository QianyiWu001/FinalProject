package AdminCoursesManagement.DeleteCourse;

import javax.swing.*;

import AdminCoursesManagement.AdminCoursesManagementPage;

import java.awt.*;

public class DeleteCoursePage extends JFrame {
    private JButton deleteButton, cancelButton;
    private JLabel titleLabel, courseIDLabel;
    private JTextField courseIDField;
    private AdminCoursesManagementPage adminCoursesManagementPage;


    public DeleteCoursePage(AdminCoursesManagementPage adminCoursesManagementPage) {
        this.adminCoursesManagementPage = adminCoursesManagementPage;
        setTitle("Delete Course");
        setLayout(new GridBagLayout());
        setDeleteCoursePagePanel();
        setSize(800,450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setDeleteCoursePagePanel() {
        DeleteCourseEvents deleteCourseEvents = new DeleteCourseEvents(this, adminCoursesManagementPage);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Delete Course");
        titleLabel.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(titleLabel, gbc);

        courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(courseIDLabel, gbc);

        courseIDField = new JTextField(10);
        courseIDField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(courseIDField, gbc);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(buttonFont);
        deleteButton.addActionListener(deleteCourseEvents);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(deleteButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(deleteCourseEvents);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(cancelButton, gbc);
    }

    public JTextField getCourseIDField() {
        return courseIDField;
    }
}
