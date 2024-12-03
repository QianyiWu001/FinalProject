package view;

import controller.LoginController;
import entity.User;
import DatabaseUtilities.Session;
import view.Admin.AdminLoginPage;
import view.Student.StudentLoginPage;

import javax.swing.*;
import java.awt.*;


public class BasicLoginPage extends JFrame {
    private JLabel pageLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton;

    public BasicLoginPage() {
        setTitle("Student Information Management System");
        setLayout(new GridBagLayout());
        setLoginPageJPanel();
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setLoginPageJPanel() {
        LoginController loginController = new LoginController();

        Font pageLabelFont = new Font("Arial", Font.PLAIN, 23);
        Font othersFont = new Font("Arial", Font.PLAIN, 18);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        pageLabel = new JLabel("Student Information Management System");
        pageLabel.setFont(pageLabelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 60, 0);
        add(pageLabel, gbc);

        // Username label and text field
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
        add(usernameField, gbc);

        // Password label and text field
        passwordLabel = new JLabel("Enter password:");
        passwordLabel.setFont(othersFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(12);
        passwordField.setFont(othersFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setFont(pageLabelFont);
        loginButton.addActionListener(e -> handleLogin(loginController));
        loginButton.setPreferredSize(new Dimension(110, 35));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(loginButton, gbc);

        // Calcel button
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(pageLabelFont);
        cancelButton.addActionListener(e -> System.exit(0));
        cancelButton.setPreferredSize(new Dimension(110, 35));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(cancelButton, gbc);
    }

    private void handleLogin(LoginController loginController) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.");
            return;
        }

        // Call the controller's login logic.
        User user = loginController.login(username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");

            // Pass user information to the Session, then open the corresponding page based on the user's role.
            if ("ROLE_ADMIN".equals(user.getRole())) {
                new AdminLoginPage();
            } else {
                System.out.println("login student id "+user.getUserId());
                Session.setStudentId(user.getUserId());
             
System.out.println("Session studentId set to: " + Session.getStudentId());
                new StudentLoginPage();
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid login. Please try again.");
        }
    }
}