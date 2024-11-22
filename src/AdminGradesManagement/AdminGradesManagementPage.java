package AdminGradesManagement;

import javax.swing.*;
import java.awt.*;

public class AdminGradesManagementPage extends JFrame {
    private JButton backButton, exitButton, addButton, clearButton;
    private JLabel titleLabel, courseIDLabel, studentIDLabel, gradeLabel, levelLabel;
    private JTextField courseIDField, studentIDField, gradeField;
    private JComboBox<String> levelComboBox;

    public AdminGradesManagementPage() {
        setTitle("Admin Grades Management Page");
        setLayout(new BorderLayout());
        setAdminGradesManagementPagePanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAdminGradesManagementPagePanel() {
        AdminGradesManagementEvents adminGradesManagementEvents = new AdminGradesManagementEvents(this);

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 50));
        
        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.setPreferredSize(new Dimension(110, 30));
        addButton.addActionListener(adminGradesManagementEvents);

        clearButton = new JButton("Clear");
        clearButton.setFont(buttonFont);
        clearButton.setPreferredSize(new Dimension(110, 30));
        clearButton.addActionListener(adminGradesManagementEvents);

        topPanel.add(addButton);
        topPanel.add(clearButton);
        add(topPanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 50));

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(adminGradesManagementEvents);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(adminGradesManagementEvents);

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);


        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        titleLabel = new JLabel("Add Grades");
        titleLabel.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(titleLabel, gbc);

        courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        centerPanel.add(courseIDLabel, gbc);

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

        gradeLabel = new JLabel("Grade:");
        gradeLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        centerPanel.add(gradeLabel, gbc);

        gradeField = new JTextField(10);
        gradeField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        centerPanel.add(gradeField, gbc);

        levelLabel = new JLabel("Level:");
        levelLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        centerPanel.add(levelLabel, gbc);

        levelComboBox = new JComboBox<String>();
        levelComboBox.addItem("-");
        levelComboBox.addItem("A");
        levelComboBox.addItem("B");
        levelComboBox.addItem("C");
        levelComboBox.addItem("D");
        levelComboBox.addItem("F");
        levelComboBox.setPreferredSize(new Dimension(173, 28));
        levelComboBox.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        centerPanel.add(levelComboBox, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }
    
    public JTextField getCourseIDField() {
        return courseIDField;
    }

    public JTextField getStudentIDField() {
        return studentIDField;
    }

    public JTextField getGradeField() {
        return gradeField;
    }

    public JComboBox<String> getLevelComboBox() {
        return levelComboBox;
    }

}
