package AdminStudentsManagement.AddStudent;

import javax.swing.*;
import java.awt.*;

public class AddStudentPage extends JFrame {
    private JButton addButton, cancelButton;
    private JLabel titleLabel, studentIDLabel, nameLabel, emailLabel;
    private JTextField studentIDField, nameField, emailField;

    public AddStudentPage() {
        setTitle("Add Student");
        setLayout(new GridBagLayout());
        setAddStudentPagePanel();
        setSize(800,450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAddStudentPagePanel() {
        AddStudentEvents addStudentEvents = new AddStudentEvents(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Add Student");
        titleLabel.setFont(titleFont);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(titleLabel, gbc);

        studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(studentIDLabel, gbc);

        studentIDField = new JTextField(10);
        studentIDField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(studentIDField, gbc);

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(nameLabel, gbc);

        nameField = new JTextField(10);
        nameField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(nameField, gbc);

        emailLabel = new JLabel("Email:");
        emailLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(emailLabel, gbc);

        emailField = new JTextField(10);
        emailField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(emailField, gbc);

        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.addActionListener(addStudentEvents);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(addButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(addStudentEvents);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(cancelButton, gbc);
    }
}
