package view.Admin;

import controller.AdminLoginController;

import javax.swing.*;
import java.awt.*;

public class AdminLoginPage extends JFrame {
    private JLabel titleLabel;
    private JButton courseBtn, studentBtn, attendanceBtn, gradeBtn, enrollmentBtn, billButton, backButton, logoutButton;

    public AdminLoginPage() {
        setTitle("Admin Main Page");
        setLayout(new GridBagLayout());
        setAdminPageJPanel(); 
        setSize(800, 650);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unused")
    private void setAdminPageJPanel() {
        // Controller to pass this page
        AdminLoginController adminLoginController = new AdminLoginController(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 18);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        // Title
        titleLabel = new JLabel("Welcome to Admin Homepage");
        titleLabel.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 30, 0);
        add(titleLabel, gbc);

        // Course manage button
        courseBtn = new JButton("Courses Management");
        courseBtn.setFont(font);
        courseBtn.setPreferredSize(new Dimension(250, 40));
        courseBtn.addActionListener(e -> adminLoginController.handleCourseManagement());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(courseBtn, gbc);

        // Student managentment button
        studentBtn = new JButton("Students Management");
        studentBtn.setFont(font);
        studentBtn.setPreferredSize(new Dimension(250, 40));
        studentBtn.addActionListener(e -> adminLoginController.handleStudentManagement());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(studentBtn, gbc);

        // Attendance
        attendanceBtn = new JButton("Attendance Management");
        attendanceBtn.setFont(font);
        attendanceBtn.setPreferredSize(new Dimension(250, 40));
        attendanceBtn.addActionListener(e -> adminLoginController.handleAttendanceManagement());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(attendanceBtn, gbc);

        // Grades
        gradeBtn = new JButton("Grades Management");
        gradeBtn.setFont(font);
        gradeBtn.setPreferredSize(new Dimension(250, 40));
        gradeBtn.addActionListener(e -> adminLoginController.handleGradesManagement());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(gradeBtn, gbc);

        // Enrollment
        enrollmentBtn = new JButton("Enrollment Management");
        enrollmentBtn.setFont(font);
        enrollmentBtn.setPreferredSize(new Dimension(250, 40));
        enrollmentBtn.addActionListener(e -> adminLoginController.handleEnrollmentManagement());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(enrollmentBtn, gbc);
        // Bill
        billButton = new JButton("Bill Management");
        billButton.setFont(font);
        billButton.setPreferredSize(new Dimension(250, 40));
        billButton.addActionListener(e -> adminLoginController.handleBillManagement());
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(billButton, gbc);
        // Return
        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(e -> adminLoginController.handleBack());
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(backButton, gbc);

        // Exit 
        logoutButton = new JButton("Exit");
        logoutButton.setFont(buttonFont);
        logoutButton.setPreferredSize(new Dimension(110, 30));
        logoutButton.addActionListener(e -> adminLoginController.handleExit());
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 50, 10, 0);
        add(logoutButton, gbc);
    }
}