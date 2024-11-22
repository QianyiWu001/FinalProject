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
        setLayout(new BorderLayout());
        setAdminAttendanceManagementPagePanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAdminAttendanceManagementPagePanel() {
        AdminAttendanceManagementEvents adminAttendanceManagementEvents = new AdminAttendanceManagementEvents(this);

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 50));
        
        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.setPreferredSize(new Dimension(110, 30));
        addButton.addActionListener(adminAttendanceManagementEvents);

        clearButton = new JButton("Clear");
        clearButton.setFont(buttonFont);
        clearButton.setPreferredSize(new Dimension(110, 30));
        clearButton.addActionListener(adminAttendanceManagementEvents);

        topPanel.add(addButton);
        topPanel.add(clearButton);
        add(topPanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 50));
        
        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(adminAttendanceManagementEvents);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(adminAttendanceManagementEvents);

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);


        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        titleLabel = new JLabel("Add Attendance");
        titleLabel.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(titleLabel, gbc);

        courseIDlabel = new JLabel("Course ID:");
        courseIDlabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        centerPanel.add(courseIDlabel, gbc);

        courseIDField = new JTextField(10);
        courseIDField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        centerPanel.add(courseIDField, gbc);

        studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        centerPanel.add(studentIDLabel, gbc);

        studentIDField = new JTextField(10);
        studentIDField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        centerPanel.add(studentIDField, gbc);

        dateLabel = new JLabel("Date:");
        dateLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        centerPanel.add(dateLabel, gbc);

        dateField = new JTextField(10);
        dateField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        centerPanel.add(dateField, gbc);

        statusLabel = new JLabel("Status:");
        statusLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        centerPanel.add(statusLabel, gbc);

        statusComboBox = new JComboBox<String>();
        statusComboBox.addItem("-");
        statusComboBox.addItem("Present");
        statusComboBox.addItem("Absent");
        statusComboBox.setPreferredSize(new Dimension(173, 28));
        statusComboBox.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        centerPanel.add(statusComboBox, gbc);

        add(centerPanel, BorderLayout.CENTER);        
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
