package view;

import javax.swing.*;
import java.awt.*;

import controller.GradesController;
import entity.Grade;

public class AddGradePage extends JFrame {
    private JTextField enrollmentIdField, gradeField;
    private JButton addButton, cancelButton;
    private GradesController gradesController;
    private AdminGradesManagementPage adminGradesManagementPage;

    public AddGradePage(AdminGradesManagementPage adminGradesManagementPage, GradesController gradesController) {
        this.adminGradesManagementPage = adminGradesManagementPage;
        this.gradesController = gradesController;
        setTitle("Add Grade");
        setLayout(new GridBagLayout());
        setAddGradePagePanel();
        setSize(600, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void setAddGradePagePanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        Font font = new Font("Arial", Font.PLAIN, 20);

        JLabel enrollmentIdLabel = new JLabel("Enrollment ID:");
        enrollmentIdLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(enrollmentIdLabel, gbc);

        enrollmentIdField = new JTextField(10);
        enrollmentIdField.setFont(font);
        gbc.gridx = 1;
        add(enrollmentIdField, gbc);

        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(gradeLabel, gbc);

        gradeField = new JTextField(10);
        gradeField.setFont(font);
        gbc.gridx = 1;
        add(gradeField, gbc);

        addButton = new JButton("Add");
        addButton.setFont(font);
        addButton.addActionListener(e -> handleAddGrade());
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(addButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(font);
        cancelButton.addActionListener(e -> dispose());
        gbc.gridx = 1;
        add(cancelButton, gbc);
    }

    private void handleAddGrade() {
        String enrollmentIdText = enrollmentIdField.getText().trim();
        String gradeText = gradeField.getText().trim();

        if (enrollmentIdText.isEmpty() || gradeText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.");
            return;
        }

        try {
            int enrollmentId = Integer.parseInt(enrollmentIdText);
            int grade = Integer.parseInt(gradeText);

            Grade newGrade = new Grade(enrollmentId, grade);

            if (gradesController.addGrade(newGrade)) {
                JOptionPane.showMessageDialog(this, "Grade added successfully.");
                adminGradesManagementPage.refreshTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add grade.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enrollment ID and Grade must be numbers.");
        }
    }
}