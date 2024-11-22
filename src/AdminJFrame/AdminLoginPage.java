package AdminJFrame;

import javax.swing.*;

import BasicLogin.BasicLoginEvents;

import java.awt.*;

public class AdminLoginPage extends JFrame {
    private JLabel titleLabel;
    private JButton courseBtn, studentBtn, attendanceBtn, gradeBtn, backButton, logoutButton;;

    public AdminLoginPage() {
        setTitle("Admin Main Page");
        setLayout(new GridBagLayout());
        setAdminPageJPanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setAdminPageJPanel() {
        AdminLoginEvents adminLoginEvents = new AdminLoginEvents(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 18);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Welcome to Admin Homepage");
        titleLabel.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 30, 0);
        add(titleLabel, gbc);

        courseBtn = new JButton("Courses Management");
        courseBtn.setFont(font);
        courseBtn.setPreferredSize(new Dimension(250, 40));
        courseBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(courseBtn, gbc);
        
        studentBtn = new JButton("Students Management");
        studentBtn.setFont(font);
        studentBtn.setPreferredSize(new Dimension(250, 40));
        studentBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(studentBtn, gbc);

        attendanceBtn = new JButton("Attendance Management");
        attendanceBtn.setFont(font);
        attendanceBtn.setPreferredSize(new Dimension(250, 40));
        attendanceBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(attendanceBtn, gbc);

        gradeBtn = new JButton("Grades Management");
        gradeBtn.setFont(font);
        gradeBtn.setPreferredSize(new Dimension(250, 40));
        gradeBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(gradeBtn, gbc);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(adminLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(backButton, gbc);

        logoutButton = new JButton("Exit");
        logoutButton.setFont(buttonFont);
        logoutButton.setPreferredSize(new Dimension(110, 30));
        logoutButton.addActionListener(adminLoginEvents);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 50, 10, 0); 
        add(logoutButton, gbc);
    }
}
