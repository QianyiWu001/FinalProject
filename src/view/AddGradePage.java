package view;

import controller.GradesController;
import entity.Grades;

import javax.swing.*;
import java.awt.*;

public class AddGradePage extends JFrame {
    private final JTextField courseIDField;
    private final JTextField studentIDField;
    private final JTextField gradeField;

    public AddGradePage(AdminGradesManagementPage adminGradesManagementPage, GradesController gradesController) {
        setTitle("Add Grade");
        setLayout(new GridBagLayout());
        courseIDField = new JTextField(10);
        studentIDField = new JTextField(10);
        gradeField = new JTextField(10);
        initialize(adminGradesManagementPage, gradesController);
        setSize(800, 450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Avoid closing the entire app
    }

    @SuppressWarnings("unused")
    private void initialize(AdminGradesManagementPage adminGradesManagementPage, GradesController gradesController) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title Label
        JLabel titleLabel = new JLabel("Add Grade");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Student ID Label and Field
        addLabelAndField("Student ID:", studentIDField, gbc, 1);

        // Course ID Label and Field
        addLabelAndField("Course ID:", courseIDField, gbc, 2);

        // Grade Label and Field
        addLabelAndField("Grade:", gradeField, gbc, 3);

        // Buttons
        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addButton.addActionListener(e -> handleAddGrade(adminGradesManagementPage, gradesController));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
        cancelButton.addActionListener(e -> dispose());

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(addButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(cancelButton, gbc);
    }

    private void addLabelAndField(String labelText, JTextField field, GridBagConstraints gbc, int gridY) {
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        add(label, gbc);

        gbc.gridx = 1;
        add(field, gbc);
    }

    private void handleAddGrade(AdminGradesManagementPage adminGradesManagementPage, GradesController gradesController) {
        String studentID = studentIDField.getText().trim();
        String courseID = courseIDField.getText().trim();
        String grade = gradeField.getText().trim();

        if (studentID.isEmpty() || courseID.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter valid information for all fields.");
            return;
        }

        int intStudentID, intCourseID, intGradeID;

        try {
            intStudentID = Integer.parseInt(studentID);
            intCourseID = Integer.parseInt(courseID);
            intGradeID = Integer.parseInt(grade);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.");
            return;
        }

        // Create a new Grade object and add it using the GradesController
        Grade newGrade = new Grade(intStudentID, intCourseID, intGradeID);
        boolean isAdded = gradesController.addGrade(newGrade);

        if (isAdded) {
            JOptionPane.showMessageDialog(this, "Grade added successfully.");
            adminGradesManagementPage.refreshTable();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add grade.");
        }
    }

    public String getCourseID() {
        return courseIDField.getText().trim();
    }

    public String getStudentID() {
        return studentIDField.getText().trim();
    }

    public String getGrade() {
        return gradeField.getText().trim();
    }
}