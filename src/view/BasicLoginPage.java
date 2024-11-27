package view;

import controller.LoginController;
import entity.User;

import javax.swing.*;


import StudentJFrame.StudentLoginPage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicLoginPage extends JFrame {
    private JLabel pageLabel, usernameLabel, passwordLabel, userTypeLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    private JButton loginButton, cancelButton;

    public BasicLoginPage() {
        setTitle("Student Information Management System");
        setLayout(new GridBagLayout());
        setLoginPageJpanel(); // 初始化界面
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

// 移除 userTypeComboBox 和相关代码
public void setLoginPageJpanel() {
    LoginController loginController = new LoginController();

    Font pageLabelFont = new Font("Arial", Font.PLAIN, 23);
    Font othersFont = new Font("Arial", Font.PLAIN, 18);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.CENTER;

    // 标题
    pageLabel = new JLabel("Student Information Management System");
    pageLabel.setFont(pageLabelFont);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(20, 0, 60, 0);
    add(pageLabel, gbc);

    // 用户名标签和文本框
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

    // 密码标签和文本框
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

    // 登录按钮
    loginButton = new JButton("Login");
    loginButton.setFont(pageLabelFont);
    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleLogin(loginController);
        }
    });
    loginButton.setPreferredSize(new Dimension(110, 35));
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    add(loginButton, gbc);

    // 取消按钮
    cancelButton = new JButton("Cancel");
    cancelButton.setFont(pageLabelFont);
    cancelButton.addActionListener(e -> System.exit(0));
    cancelButton.setPreferredSize(new Dimension(110, 35));
    gbc.gridx = 1;
    gbc.gridy = 3;
    add(cancelButton, gbc);
}

// 更新 handleLogin 方法
private void handleLogin(LoginController loginController) {
    String username = usernameField.getText();
    String password = new String(passwordField.getPassword());

    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter username and password.");
        return;
    }

    // 调用控制器登录逻辑
    User user = loginController.login(username, password);
    if (user != null) {
        JOptionPane.showMessageDialog(this, "Login successful!");

        // 根据角色打开相应页面
        if ("ROLE_ADMIN".equals(user.getRole())) {
            new AdminLoginPage();
        } else {
            new StudentLoginPage();
        }
        dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Invalid login. Please try again.");
    }
}

    // 提供公共方法以供测试
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