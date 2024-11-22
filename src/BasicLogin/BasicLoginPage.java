package BasicLogin;

import javax.swing.*;
import java.awt.*;

public class BasicLoginPage extends JFrame {
    private JLabel pageLabel, usernameLabel, passwordLabel, userTypeLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    private JButton loginButton, cancelButton;

    public BasicLoginPage() {
        setTitle("Student Information Management System");
        setLayout(new GridBagLayout());
        setLoginPageJpanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setLoginPageJpanel() {
        BasicLoginEvents basicLoginEvents = new BasicLoginEvents(this); //1
        
        Font pageLabelFont = new Font("Arial", Font.PLAIN, 23);
        Font othersFont = new Font("Arial", Font.PLAIN, 18);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        pageLabel = new JLabel("Student Information Management System");
        pageLabel.setFont(pageLabelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 60, 0);
        add(pageLabel, gbc);

        usernameLabel = new JLabel("Enter username:");
        usernameLabel.setFont(othersFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 20);
        add(usernameLabel, gbc);

        usernameField = new JTextField(12);
        usernameField.setFont(othersFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 0);
        add(usernameField, gbc);

        passwordLabel = new JLabel("Enter password:");
        passwordLabel.setFont(othersFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 20);
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(12);
        passwordField.setFont(othersFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 0);
        add(passwordField, gbc);

        userTypeLabel = new JLabel("Select user type:");
        userTypeLabel.setFont(othersFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 20);
        add(userTypeLabel, gbc);

        userTypeComboBox = new JComboBox<String>();
        userTypeComboBox.addItem("-");
        userTypeComboBox.addItem("Admin");
        userTypeComboBox.addItem("Student");
        userTypeComboBox.setPreferredSize(new Dimension(182, 28));
        userTypeComboBox.setFont(othersFont);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 0);
        add(userTypeComboBox, gbc);

        loginButton = new JButton("Login");
        loginButton.setFont(pageLabelFont);
        loginButton.addActionListener(basicLoginEvents);  //2
        loginButton.setPreferredSize(new Dimension(110, 35));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(loginButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(pageLabelFont);
        cancelButton.addActionListener(basicLoginEvents);  //2
        cancelButton.setPreferredSize(new Dimension(110, 35));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(cancelButton, gbc);
    }
    
    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JComboBox<String> getUserTypeComboBox() {
        return userTypeComboBox;
    }
}