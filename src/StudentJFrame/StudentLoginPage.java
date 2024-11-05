package StudentJFrame;

import javax.swing.*;
import java.awt.*;

import javax.swing.JButton;

public class StudentLoginPage extends JFrame {
    private JButton viewProfileButton, viewCoursesButton, viewBillButton, viewAttendenceButton, viewGradeButton;

    public StudentLoginPage() {
        setTitle("Student Main Page");
        setLayout(new GridBagLayout());
        setSize(1000, 600);
        setStudentPageJPanel();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setStudentPageJPanel() {
        StudentLoginEvents studentLoginEvents = new StudentLoginEvents(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font font = new Font("Arial", Font.PLAIN, 21);

        viewProfileButton = new JButton("View Profile");
        viewProfileButton.setFont(font);
        viewProfileButton.setPreferredSize(new Dimension(200, 50));
        viewProfileButton.addActionListener(studentLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 60, 10);
        add(viewProfileButton, gbc);

        viewCoursesButton = new JButton("View Courses");
        viewCoursesButton.setFont(font);
        viewCoursesButton.setPreferredSize(new Dimension(200, 50));
        viewCoursesButton.addActionListener(studentLoginEvents);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 60, 10);
        add(viewCoursesButton, gbc);

        viewBillButton = new JButton("View Bill");
        viewBillButton.setFont(font);
        viewBillButton.setPreferredSize(new Dimension(200, 50));
        viewBillButton.addActionListener(studentLoginEvents);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 60, 10);
        add(viewBillButton, gbc);

        viewAttendenceButton = new JButton("View Attendance");
        viewAttendenceButton.setFont(font);
        viewAttendenceButton.setPreferredSize(new Dimension(200, 50));
        viewAttendenceButton.addActionListener(studentLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 0, 10);
        add(viewAttendenceButton, gbc);

        viewGradeButton = new JButton("View Grades");
        viewGradeButton.setFont(font);
        viewGradeButton.setPreferredSize(new Dimension(200, 50));
        viewGradeButton.addActionListener(studentLoginEvents);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 0, 10);
        add(viewGradeButton, gbc);
    }

}
