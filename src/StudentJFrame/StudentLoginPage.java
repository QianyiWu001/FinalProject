package StudentJFrame;

import javax.swing.*;

import DatabaseUtilities.Session;

import java.awt.*;

public class StudentLoginPage extends JFrame {
    private JLabel titleLabel;
    private JButton viewProfileButton, viewCoursesButton, viewBillButton, viewAttendenceButton, viewGradeButton, backButton, logoutButton;

    public StudentLoginPage() {
        setTitle("Student Main Page");
        setLayout(new GridBagLayout());
        setSize(800,550);
        setStudentPageJPanel();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setStudentPageJPanel() {
        StudentLoginEvents studentLoginEvents = new StudentLoginEvents(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Welcome to Student Homepage");
        titleLabel.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 30, 0);
        add(titleLabel, gbc);

        viewProfileButton = new JButton("View Profile");
        viewProfileButton.setFont(font);
        viewProfileButton.setPreferredSize(new Dimension(200, 40));
        viewProfileButton.addActionListener(studentLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(viewProfileButton, gbc);

        viewCoursesButton = new JButton("View Courses");
        viewCoursesButton.setFont(font);
        viewCoursesButton.setPreferredSize(new Dimension(200, 40));
        viewCoursesButton.addActionListener(studentLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(viewCoursesButton, gbc);

        viewBillButton = new JButton("View Bill");
        viewBillButton.setFont(font);
        viewBillButton.setPreferredSize(new Dimension(200, 40));
        viewBillButton.addActionListener(studentLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(viewBillButton, gbc);

        viewAttendenceButton = new JButton("View Attendance");
        viewAttendenceButton.setFont(font);
        viewAttendenceButton.setPreferredSize(new Dimension(200, 40));
        viewAttendenceButton.addActionListener(studentLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(viewAttendenceButton, gbc);

        viewGradeButton = new JButton("View Grades");
        viewGradeButton.setFont(font);
        viewGradeButton.setPreferredSize(new Dimension(200, 40));
        viewGradeButton.addActionListener(studentLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(viewGradeButton, gbc);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(studentLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(backButton, gbc);

        logoutButton = new JButton("Exit");
        logoutButton.setFont(buttonFont);
        logoutButton.setPreferredSize(new Dimension(110, 30));
        logoutButton.addActionListener(studentLoginEvents);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 50, 10, 0);
        add(logoutButton, gbc);
    }
}
