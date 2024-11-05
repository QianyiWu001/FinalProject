package AdminJFrame;

import javax.swing.*;

import BasicLogin.BasicLoginEvents;

import java.awt.*;

public class AdminLoginPage extends JFrame {
    private JButton addStudentBtn, updateStudentBtn, deleteStudentBtn, listStudentBtn, addCourseBtn, deleteCoursesBtn, addAttendanceBtn, addFinalGradeBtn;

    public AdminLoginPage() {
        setTitle("Admin Main Page");
        setLayout(new GridBagLayout());
        setAdminPageJPanel();
        setSize(1000,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setAdminPageJPanel() {
        AdminLoginEvents adminLoginEvents = new AdminLoginEvents(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font font = new Font("Arial", Font.PLAIN, 16);

        addStudentBtn = new JButton("Add Student");
        addStudentBtn.setFont(font);
        addStudentBtn.setPreferredSize(new Dimension(160, 50));
        addStudentBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 150);
        add(addStudentBtn, gbc);

        updateStudentBtn = new JButton("Update Student");
        updateStudentBtn.setFont(font);
        updateStudentBtn.setPreferredSize(new Dimension(160, 50));
        updateStudentBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(updateStudentBtn, gbc);

        deleteStudentBtn = new JButton("Delete Student");
        deleteStudentBtn.setFont(font);
        deleteStudentBtn.setPreferredSize(new Dimension(160, 50));
        deleteStudentBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(deleteStudentBtn, gbc);

        listStudentBtn = new JButton("List Student");
        listStudentBtn.setFont(font);
        listStudentBtn.setPreferredSize(new Dimension(160, 50));
        listStudentBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(listStudentBtn, gbc);

        addCourseBtn = new JButton("Add Course");
        addCourseBtn.setFont(font);
        addCourseBtn.setPreferredSize(new Dimension(160, 50));
        addCourseBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(addCourseBtn, gbc);

        deleteCoursesBtn = new JButton("Delete Course");
        deleteCoursesBtn.setFont(font);
        deleteCoursesBtn.setPreferredSize(new Dimension(160, 50));
        deleteCoursesBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(deleteCoursesBtn, gbc);

        addAttendanceBtn = new JButton("Add Attendance");
        addAttendanceBtn.setFont(font);
        addAttendanceBtn.setPreferredSize(new Dimension(160, 50));
        addAttendanceBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(addAttendanceBtn, gbc);

        addFinalGradeBtn = new JButton("Add Final Grade");
        addFinalGradeBtn.setFont(font);
        addFinalGradeBtn.setPreferredSize(new Dimension(160, 50));
        addFinalGradeBtn.addActionListener(adminLoginEvents);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(addFinalGradeBtn, gbc);



    }
}
