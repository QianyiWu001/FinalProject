package AdminGradesManagement.DeleteGrade;

import javax.swing.*;

import AdminGradesManagement.AdminGradesManagementPage;

import java.awt.*;

public class DeleteGradePage extends JFrame {
    private JButton deleteButton, cancelButton;
    private JLabel titleLabel, studentIDLabel, courseIDLabel;
    private JTextField studentIDField, courseIDField;
    private AdminGradesManagementPage adminGradesManagementPage;

    public DeleteGradePage(AdminGradesManagementPage adminGradesManagementPage) {
        this.adminGradesManagementPage = adminGradesManagementPage;
        setTitle("Delete Grade");
        setLayout(new GridBagLayout());
        setDeleteGradePagePanel();
        setSize(800,450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setDeleteGradePagePanel() {
        DeleteGradeEvents deleteGradeEvents = new DeleteGradeEvents(this, adminGradesManagementPage);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Delete Grade");
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

        deleteButton = new JButton("Delete");
        deleteButton.setFont(buttonFont);
        deleteButton.addActionListener(deleteGradeEvents);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(deleteButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(deleteGradeEvents);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(cancelButton, gbc);
    }

    public JTextField getStudentIDField() {
        return studentIDField;
    }

    public JTextField getCourseIDField() {
        return courseIDField;
    }

}
