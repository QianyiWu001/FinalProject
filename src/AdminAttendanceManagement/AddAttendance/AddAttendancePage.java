package AdminAttendanceManagement.AddAttendance;

import java.awt.*;
import javax.swing.*;

import AdminAttendanceManagement.AdminAttendanceManagementPage;

public class AddAttendancePage extends JFrame {
    private JButton addButton, cancelButton;
    private JLabel titleLabel, studentIDLabel, courseIDLabel, dateLabel, statusLabel;
    private JTextField studentIDField, courseIDField, dateField;
    private JComboBox<String> statusComboBox;

    private AdminAttendanceManagementPage adminAttendanceManagementPage;

    public AddAttendancePage(AdminAttendanceManagementPage adminAttendanceManagementPage) {
        this.adminAttendanceManagementPage = adminAttendanceManagementPage;
        setTitle("Add Attendance");
        setLayout(new GridBagLayout());
        setAddAttendancePagePanel();
        setSize(800,450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAddAttendancePagePanel() {
        AddAttendanceEvents addAttendanceEvents = new AddAttendanceEvents(this, adminAttendanceManagementPage);

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

        studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(studentIDLabel, gbc);

        studentIDField = new JTextField(10);
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
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(dateField, gbc);

        statusLabel = new JLabel("Status:");
        statusLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(statusLabel, gbc);

        String[] statusList = {"-", "Present", "Absent", "Late"};
        statusComboBox = new JComboBox<>(statusList);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(statusComboBox, gbc);

        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.addActionListener(addAttendanceEvents);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(addButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(addAttendanceEvents);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 0, 10, 0);        
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

    public JComboBox<String> getStatusComboBox() {
        return statusComboBox;
    }
}
