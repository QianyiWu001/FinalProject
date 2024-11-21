package BasicLogin;

import javax.swing.*;
import java.awt.*;

public class BasicLoginPage extends JFrame {
    private JLabel pageLabel, usernameLabel, passwordLabel, usernamePlaceholder, passwordPlaceholder, usernameErrorField, passwordErrorField, userTypeLabel, userTypePlaceholder, userTypeErrorField, userLoginErrorField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    private JButton loginButton, cancelButton;

    public BasicLoginPage() {
        setTitle("Student Information Management System");
        setLayout(new GridBagLayout());
        setLoginPageJpanel();
        setSize(1000, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setLoginPageJpanel() {
        BasicLoginEvents basicLoginEvents = new BasicLoginEvents(this); //1
        
        Font pageLabelFont = new Font("Arial", Font.PLAIN, 23);
        Font othersFont = new Font("Arial", Font.PLAIN, 18);
        Font hintFont = new Font("Arial", Font.PLAIN, 14);

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
        gbc.insets = new Insets(0, 0, 0, 20);
        add(usernameLabel, gbc);

        usernamePlaceholder = new JLabel(" ");
        usernamePlaceholder.setFont(hintFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 20);
        add(usernamePlaceholder, gbc);

        usernameField = new JTextField(12);
        usernameField.setFont(othersFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(usernameField, gbc);

        
        usernameErrorField = new JLabel(" ");
        usernameErrorField.setFont(hintFont);
        usernameErrorField.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 0);
        add(usernameErrorField, gbc);


        passwordLabel = new JLabel("Enter password:");
        passwordLabel.setFont(othersFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 20);
        add(passwordLabel, gbc);

        passwordPlaceholder = new JLabel(" ");
        passwordPlaceholder.setFont(hintFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 20);
        add(passwordPlaceholder, gbc);

        passwordField = new JPasswordField(12);
        passwordField.setFont(othersFont);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(passwordField, gbc);

        passwordErrorField = new JLabel(" ");
        passwordErrorField.setFont(hintFont);
        passwordErrorField.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 0);
        add(passwordErrorField, gbc);

        userTypeLabel = new JLabel("Select user type:");
        userTypeLabel.setFont(othersFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 20);
        add(userTypeLabel, gbc);

        userTypePlaceholder = new JLabel(" ");
        userTypePlaceholder.setFont(hintFont);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 20);
        add(userTypePlaceholder, gbc);

        userTypeComboBox = new JComboBox<String>();
        userTypeComboBox.addItem("-");
        userTypeComboBox.addItem("Admin");
        userTypeComboBox.addItem("Student");
        userTypeComboBox.setPreferredSize(new Dimension(182, 28));
        userTypeComboBox.setFont(othersFont);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(userTypeComboBox, gbc);

        userTypeErrorField = new JLabel(" ");
        userTypeErrorField.setFont(hintFont);
        userTypeErrorField.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 40, 0);
        add(userTypeErrorField, gbc);

        loginButton = new JButton("Login");
        loginButton.setFont(pageLabelFont);
        loginButton.addActionListener(basicLoginEvents);  //2
        loginButton.setPreferredSize(new Dimension(110, 35));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(loginButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(pageLabelFont);
        cancelButton.addActionListener(basicLoginEvents);  //2
        cancelButton.setPreferredSize(new Dimension(110, 35));
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(cancelButton, gbc);

        userLoginErrorField = new JLabel(" ");
        userLoginErrorField.setFont(hintFont);
        userLoginErrorField.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        add(userLoginErrorField, gbc);
    }
    
    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JLabel getUsernameErrorField() {
        return usernameErrorField;
    }

    public JLabel getPasswordErrorField() {
        return passwordErrorField;
    }

    public JComboBox<String> getUserTypeComboBox() {
        return userTypeComboBox;
    }

    public JLabel getUserTypeErrorField() {
        return userTypeErrorField;
    }

    public JLabel getUserLoginErrorField() {
        return userLoginErrorField;
    }
}