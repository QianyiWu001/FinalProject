package AdminAttendanceManagement.DeleteAttendance;

import java.awt.*;

import javax.swing.*;

import AdminAttendanceManagement.AdminAttendanceManagementPage;

public class DeleteAttendancePage extends JFrame {
    private JButton deleteButton, cancelButton;
    private JLabel titleLabel, studentIDLabel, courseIDLabel, dateLabel;
    private JTextField studentIDField, courseIDField, dateField;
    private AdminAttendanceManagementPage adminAttendanceManagementPage;

    public DeleteAttendancePage(AdminAttendanceManagementPage adminAttendanceManagementPage) {
        this.adminAttendanceManagementPage = adminAttendanceManagementPage;
        setTitle("Delete Attendance");
        setLayout(new GridBagLayout());
        setDeleteAttendancePagePanel();
        setSize(800,450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setDeleteAttendancePagePanel() {
        DeleteAttendanceEvents deleteAttendanceEvents = new DeleteAttendanceEvents(this, adminAttendanceManagementPage);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Delete Attendance Record");
        titleLabel.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(titleLabel, gbc);

        studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(studentIDLabel, gbc);

        studentIDField = new JTextField(10);
        studentIDField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(studentIDField, gbc);

        courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(courseIDLabel, gbc);

        courseIDField = new JTextField(10);
        courseIDField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(courseIDField, gbc);

        dateLabel = new JLabel("Date(YYYY-MM-DD):");
        dateLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(dateLabel, gbc);

        dateField = new JTextField(10);
        dateField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(dateField, gbc);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(buttonFont);
        deleteButton.addActionListener(deleteAttendanceEvents);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 0);
        add(deleteButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(deleteAttendanceEvents);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 0);
        add(cancelButton, gbc);
    }

    public JTextField getStudentIDField() {
        return studentIDField;
    }

    public JTextField getCourseIDField() {
        return courseIDField;
    }

    public JTextField getDateField() {
        return dateField;
    }
}
