package AdminCoursesManagement.AddCourse;

import javax.swing.*;

import AdminCoursesManagement.AdminCoursesManagementPage;

import java.awt.*;

public class AddCoursePage extends JFrame {
    private JButton addButton, cancelButton;
    private JLabel titleLabel, courseIDLabel, courseNameLabel, creditLabel, descriptionLabel;
    private JTextField courseIDField, courseNameField, creditField, descriptionField;
    private AdminCoursesManagementPage adminCoursesManagementPage;

    public AddCoursePage(AdminCoursesManagementPage adminCoursesManagementPage) {
        this.adminCoursesManagementPage = adminCoursesManagementPage;
        setTitle("Add Course");
        setLayout(new GridBagLayout());
        setAddCoursePagePanel();
        setSize(800,450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAddCoursePagePanel() {
        AddCourseEvents addCourseEvents = new AddCourseEvents(this, adminCoursesManagementPage);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Add Course");
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
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(courseIDField, gbc);


        courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(courseNameLabel, gbc);

        courseNameField = new JTextField(10);
        courseNameField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(courseNameField, gbc);

        creditLabel = new JLabel("Credit:");
        creditLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(creditLabel, gbc);

        creditField = new JTextField(10);
        creditField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(creditField, gbc);

        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(descriptionLabel, gbc);

        descriptionField = new JTextField(10);
        descriptionField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(descriptionField, gbc);

        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.addActionListener(addCourseEvents);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 10, 0);
        add(addButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(addCourseEvents);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 10, 0);
        add(cancelButton, gbc);
    }

    public JTextField getCourseIDField() {
        return courseIDField;
    }

    public JTextField getCourseNameField() {
        return courseNameField;
    }

    public JTextField getCreditField() {
        return creditField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }
}
