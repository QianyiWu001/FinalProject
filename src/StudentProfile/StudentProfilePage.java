package StudentProfile;

import javax.swing.*;
import java.awt.*;

public class StudentProfilePage extends JFrame {
    private JButton backButton, exitButton;
    private JLabel titleLabel, studentIDLabel, nameLabel, emailLabel;
    private JTextField studentIDTextField, nameTextField, emailTextField;

    public StudentProfilePage() {
        setTitle("Student Profile Page");
        setLayout(new GridBagLayout());
        setStudentProfilePagePanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setStudentProfilePagePanel() {
        StudentProfileEvents studentProfileEvents = new StudentProfileEvents(this);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(studentProfileEvents);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(backButton, gbc);

        titleLabel = new JLabel("Student Profile");
        titleLabel.setFont(titleFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(titleLabel, gbc);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(studentProfileEvents);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(exitButton, gbc);

        studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(studentIDLabel, gbc);

        studentIDTextField = new JTextField(15);
        studentIDTextField.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(studentIDTextField, gbc);

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(nameLabel, gbc);

        nameTextField = new JTextField(15);
        nameTextField.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(nameTextField, gbc);

        emailLabel = new JLabel("Email:");
        emailLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(emailLabel, gbc);

        emailTextField = new JTextField(15);
        emailTextField.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(emailTextField, gbc);
    }
}
