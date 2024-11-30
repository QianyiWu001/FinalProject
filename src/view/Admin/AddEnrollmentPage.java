package view.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.EnrollmentController;
import entity.Enrollment;

public class AddEnrollmentPage extends JFrame {
    private JButton addButton, cancelButton;
    private JLabel titleLabel, enrollmentIDLabel, studentIDLabel, courseIDLabel;
    private JTextField enrollmentIDField, studentIDField, courseIDField;
    private AdminEnrollmentManagementPage adminEnrollmentManagementPage;
    private EnrollmentController enrollmentController;

    public AddEnrollmentPage(AdminEnrollmentManagementPage adminEnrollmentManagementPage, EnrollmentController enrollmentController) {
        this.adminEnrollmentManagementPage = adminEnrollmentManagementPage;
        this.enrollmentController = enrollmentController;

        setTitle("Add Enrollment");
        setLayout(null);
        setSize(400, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Title
        titleLabel = new JLabel("Add New Enrollment");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(100, 20, 200, 30);

        // Enrollment ID
        enrollmentIDLabel = new JLabel("Enrollment ID:");
        enrollmentIDLabel.setFont(labelFont);
        enrollmentIDLabel.setBounds(50, 70, 100, 30);

        enrollmentIDField = new JTextField();
        enrollmentIDField.setBounds(160, 70, 180, 30);

        // Student ID
        studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(labelFont);
        studentIDLabel.setBounds(50, 120, 100, 30);

        studentIDField = new JTextField();
        studentIDField.setBounds(160, 120, 180, 30);

        // Course ID
        courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setFont(labelFont);
        courseIDLabel.setBounds(50, 170, 100, 30);

        courseIDField = new JTextField();
        courseIDField.setBounds(160, 170, 180, 30);

        // Buttons
        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.setBounds(80, 250, 100, 30);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.setBounds(200, 250, 100, 30);

        // Add components to frame
        add(titleLabel);
        add(enrollmentIDLabel);
        add(enrollmentIDField);
        add(studentIDLabel);
        add(studentIDField);
        add(courseIDLabel);
        add(courseIDField);
        add(addButton);
        add(cancelButton);

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddEnrollment();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the add enrollment window
            }
        });
    }

    private void handleAddEnrollment() {
        try {
            int enrollmentId = Integer.parseInt(enrollmentIDField.getText().trim());
            int studentId = Integer.parseInt(studentIDField.getText().trim());
            int courseId = Integer.parseInt(courseIDField.getText().trim());

            Enrollment newEnrollment = new Enrollment(enrollmentId, studentId, courseId);
            boolean success = enrollmentController.addEnrollment(newEnrollment);

            if (success) {
                JOptionPane.showMessageDialog(this, "Enrollment added successfully.");
                adminEnrollmentManagementPage.refreshTable(); // Refresh the parent table
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add enrollment. Please try again.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }
}