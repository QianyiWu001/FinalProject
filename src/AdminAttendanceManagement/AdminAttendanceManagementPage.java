package AdminAttendanceManagement;

import javax.swing.*;
import java.awt.*;

public class AdminAttendanceManagementPage extends JFrame {
    private JButton backButton, exitButton, addButton, clearButton;
    private JLabel titleLabel, courseIDlabel, studentIDLabel, dateLabel, statusLabel;
    private JTextField courseIDField, studentIDField, dateField;
    private JComboBox<String> statusComboBox;

    public AdminAttendanceManagementPage() {
        setTitle("Admin Attendance Management Page");
        setLayout(new GridBagLayout());
        setAdminAttendanceManagementPagePanel();
        setSize(1000,600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAdminAttendanceManagementPagePanel() {
        AdminAttendanceManagementEvents adminAttendanceManagementEvents = new AdminAttendanceManagementEvents(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
    
        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(adminAttendanceManagementEvents);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(backButton, gbc);

        titleLabel = new JLabel("Add Attendance");
        titleLabel.setFont(titleFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(titleLabel, gbc);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(adminAttendanceManagementEvents);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(exitButton, gbc);

        courseIDlabel = new JLabel("Course ID:");
        courseIDlabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(courseIDlabel, gbc);

        courseIDField = new JTextField(10);
        courseIDField.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 20, 0);
        add(courseIDField, gbc);

        studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(studentIDLabel, gbc);

        studentIDField = new JTextField(10);
        studentIDField.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 20, 0);
        add(studentIDField, gbc);

        dateLabel = new JLabel("Date:");
        dateLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(dateLabel, gbc);

        dateField = new JTextField(10);
        dateField.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 20, 0);
        add(dateField, gbc);

        statusLabel = new JLabel("Status:");
        statusLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(statusLabel, gbc);

        statusComboBox = new JComboBox<String>();
        statusComboBox.addItem("-");
        statusComboBox.addItem("Present");
        statusComboBox.addItem("Absent");
        statusComboBox.setPreferredSize(new Dimension(173, 28));
        statusComboBox.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 20, 0);
        add(statusComboBox, gbc);

        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.setPreferredSize(new Dimension(110, 30));
        addButton.addActionListener(adminAttendanceManagementEvents);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 50, 30, 0);
        add(addButton, gbc);

        clearButton = new JButton("Clear");
        clearButton.setFont(buttonFont);
        clearButton.setPreferredSize(new Dimension(110, 30));
        clearButton.addActionListener(adminAttendanceManagementEvents);
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 50, 30, 0);
        add(clearButton, gbc);
    }

    public JTextField getCourseIDField() {
        return courseIDField;
    }

    public JTextField getStudentIDField() {
        return studentIDField;
    }

    public JTextField getDateField() {
        return dateField;
    }

    public JComboBox<String> getStatusComboBox() {
        return statusComboBox;
    }
}
