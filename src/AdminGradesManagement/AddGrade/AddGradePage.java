package AdminGradesManagement.AddGrade;

import javax.swing.*;

import AdminGradesManagement.AdminGradesManagementPage;

import java.awt.*;

public class AddGradePage extends JFrame {
    private JButton addButton, cancelButton;
    private JLabel titleLabel, courseIDLabel, studentIDLabel, gradeLabel;
    private JTextField courseIDField, studentIDField, gradeField;
    private AdminGradesManagementPage adminGradesManagementPage;

    public AddGradePage(AdminGradesManagementPage adminGradesManagementPage) {
        this.adminGradesManagementPage = adminGradesManagementPage;
        setTitle("Add Grade");
        setLayout(new GridBagLayout());
        setAddGradePagePanel();
        setSize(800,450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAddGradePagePanel() {
        AddGradeEvents addGradeEvents = new AddGradeEvents(this, adminGradesManagementPage);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Add Grade");
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

        gradeLabel = new JLabel("Grade:");
        gradeLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(gradeLabel, gbc);

        gradeField = new JTextField(10);
        gradeField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(gradeField, gbc);

        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.addActionListener(addGradeEvents);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(addButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(addGradeEvents);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(cancelButton, gbc);
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
}
