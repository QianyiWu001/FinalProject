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
        setLayout(new GridBagLayout());
        setAdminGradesManagementPagePanel();
        setSize(1000,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAdminGradesManagementPagePanel() {
        AdminGradesManagementEvents adminGradesManagementEvents = new AdminGradesManagementEvents(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
    
        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(adminGradesManagementEvents);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(backButton, gbc);

        titleLabel = new JLabel("Add Grades");
        titleLabel.setFont(titleFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(titleLabel, gbc);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(adminGradesManagementEvents);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(exitButton, gbc);

        courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(courseIDLabel, gbc);

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

        gradeLabel = new JLabel("Grade:");
        gradeLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(gradeLabel, gbc);

        gradeField = new JTextField(10);
        gradeField.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 20, 0);
        add(gradeField, gbc);

        levelLabel = new JLabel("Level:");
        levelLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(levelLabel, gbc);

        levelComboBox = new JComboBox<String>();
        levelComboBox.addItem("-");
        levelComboBox.addItem("A");
        levelComboBox.addItem("B");
        levelComboBox.addItem("C");
        levelComboBox.addItem("D");
        levelComboBox.addItem("F");
        levelComboBox.setPreferredSize(new Dimension(173, 28));
        levelComboBox.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 20, 0);
        add(levelComboBox, gbc);

        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.setPreferredSize(new Dimension(110, 30));
        addButton.addActionListener(adminGradesManagementEvents);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 50, 30, 0);
        add(addButton, gbc);

        clearButton = new JButton("Clear");
        clearButton.setFont(buttonFont);
        clearButton.setPreferredSize(new Dimension(110, 30));
        clearButton.addActionListener(adminGradesManagementEvents);
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

    public JTextField getGradeField() {
        return gradeField;
    }

    public JComboBox<String> getLevelComboBox() {
        return levelComboBox;
    }

}
