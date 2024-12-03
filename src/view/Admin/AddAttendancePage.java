package view.Admin;

import javax.swing.*;
import java.awt.*;
import controller.AttendanceController;
import entity.Attendance;

public class AddAttendancePage extends JFrame {
    private JButton addButton, cancelButton;
    private JLabel titleLabel, studentIdLabel, courseIdLabel, dateLabel, statusLabel;
    private JTextField studentIdField, courseIdField, dateField, statusField;
    private AttendanceController attendanceController;

    private AdminAttendanceManagementPage adminAttendanceManagementPage;


    public AddAttendancePage(AdminAttendanceManagementPage adminAttendanceManagementPage, AttendanceController attendanceController) {
        this.adminAttendanceManagementPage = adminAttendanceManagementPage; 
        this.attendanceController = attendanceController; 
        setTitle("Add Attendance");
        setLayout(new GridBagLayout());
        setAddAttendancePagePanel();
        setSize(800, 450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void setAddAttendancePagePanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Add Attendance");
        titleLabel.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(titleLabel, gbc);

        studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(studentIdLabel, gbc);

        studentIdField = new JTextField(10);
        studentIdField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(studentIdField, gbc);

        courseIdLabel = new JLabel("Course ID:");
        courseIdLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(courseIdLabel, gbc);

        courseIdField = new JTextField(10);
        courseIdField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(courseIdField, gbc);

        dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(dateLabel, gbc);

        dateField = new JTextField(10);
        dateField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(dateField, gbc);

        statusLabel = new JLabel("Status:");
        statusLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(statusLabel, gbc);

        statusField = new JTextField(10);
        statusField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(statusField, gbc);

        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.addActionListener(e -> handleAddAttendance());
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

    private void handleAddAttendance() {
        String studentIdInput = studentIdField.getText().trim();
        String courseIdInput = courseIdField.getText().trim();
        String dateInput = dateField.getText().trim();
        String status = statusField.getText().trim();

        if (studentIdInput.isEmpty() || courseIdInput.isEmpty() || dateInput.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.");
            return;
        }

        int studentId, courseId;
        try {
            studentId = Integer.parseInt(studentIdInput);
            courseId = Integer.parseInt(courseIdInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Student ID and Course ID must be numbers.");
            return;
        }

        java.sql.Date date;
        try {
            date = java.sql.Date.valueOf(dateInput);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        
        int enrollmentId = attendanceController.getEnrollmentId(studentId, courseId);
        if (enrollmentId == -1) {
            JOptionPane.showMessageDialog(this, "Invalid Student ID and Course ID combination.");
            return;
        }

        Attendance attendance = new Attendance(enrollmentId, date, status, studentId, courseId);

        if (attendanceController.addAttendance(attendance)) {
            JOptionPane.showMessageDialog(this, "Attendance record added successfully.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add attendance record.");
        }
    }
}