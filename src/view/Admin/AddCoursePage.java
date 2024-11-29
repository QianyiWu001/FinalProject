package view.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.CourseController;
import entity.Course;

public class AddCoursePage extends JFrame {
    private JButton addButton, cancelButton;
    private JLabel titleLabel, courseIDLabel, courseNameLabel, creditLabel, descriptionLabel;
    private JTextField courseIDField, courseNameField, creditField, descriptionField;
    private AdminCoursesManagementPage adminCoursesManagementPage;
    private CourseController courseController;
    // Constructor accepting AdminCoursesManagementPage and CourseController
    public AddCoursePage(AdminCoursesManagementPage adminCoursesManagementPage, CourseController courseController) {
        this.adminCoursesManagementPage = adminCoursesManagementPage;
        this.courseController = courseController; // Use the passed CourseController
        setTitle("Add Course");
        setLayout(new GridBagLayout());
        setAddCoursePagePanel();
        setSize(800, 450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public AddCoursePage(AdminCoursesManagementPage adminCoursesManagementPage) {
        this.adminCoursesManagementPage = adminCoursesManagementPage;
        this.courseController = new CourseController(); // 初始化控制器
        setTitle("Add Course");
        setLayout(new GridBagLayout());
        setAddCoursePagePanel();
        setSize(800, 450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void setAddCoursePagePanel() {
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
        add(courseIDField, gbc);

        courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(courseNameLabel, gbc);

        courseNameField = new JTextField(10);
        courseNameField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(courseNameField, gbc);

        creditLabel = new JLabel("Credits:");
        creditLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(creditLabel, gbc);

        creditField = new JTextField(10);
        creditField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(creditField, gbc);

        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(descriptionLabel, gbc);

        descriptionField = new JTextField(10);
        descriptionField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(descriptionField, gbc);

        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddCourse();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(20, 0, 10, 0);
        add(addButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(e -> dispose());
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(cancelButton, gbc);
    }

    private void handleAddCourse() {
        String courseID = courseIDField.getText().trim();
        String courseName = courseNameField.getText().trim();
        String credits = creditField.getText().trim();
        String description = descriptionField.getText().trim();

        if (courseID.isEmpty() || courseName.isEmpty() || credits.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.");
            return;
        }

        int intCourseID, intCredits;
        try {
            intCourseID = Integer.parseInt(courseID);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Course ID must be a number.");
            return;
        }

        try {
            intCredits = Integer.parseInt(credits);
            if (intCredits < 0) {
                JOptionPane.showMessageDialog(this, "Credits cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Credits must be a number.");
            return;
        }

        Course course = new Course();
        course.setCourseId(intCourseID);
        course.setCourseName(courseName);
        course.setCredits(intCredits);
        course.setDescription(description);

        if (courseController.addCourse(course)) {
            JOptionPane.showMessageDialog(this, "Course added successfully.");
            adminCoursesManagementPage.refreshTable();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add course.");
        }
    }
}