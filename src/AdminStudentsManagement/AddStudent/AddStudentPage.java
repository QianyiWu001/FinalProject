package AdminStudentsManagement.AddStudent;

import javax.swing.*;

import AdminStudentsManagement.AdminStudentsManagementPage;

import java.awt.*;

public class AddStudentPage extends JFrame {
    private JButton addButton, cancelButton;
    private JLabel titleLabel, studentIDLabel, nameLabel, emailLabel, phoneLabel, addressLabel  ;
    private JTextField studentIDField, nameField, emailField, phoneField, addressField;
    private AdminStudentsManagementPage adminStudentsManagementPage;

    public AddStudentPage(AdminStudentsManagementPage adminStudentsManagementPage) {
        this.adminStudentsManagementPage = adminStudentsManagementPage;
        setTitle("Add Student");
        setLayout(new GridBagLayout());
        setAddStudentPagePanel();
        setSize(800,450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAddStudentPagePanel() {
        AddStudentEvents addStudentEvents = new AddStudentEvents(this, adminStudentsManagementPage);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Add Student");
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
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(studentIDField, gbc);

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
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
        gbc.insets = new Insets(0, 0, 10, 10);
        add(emailLabel, gbc);

        emailField = new JTextField(10);
        emailField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(emailField, gbc);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(phoneLabel, gbc);

        phoneField = new JTextField(10);
        phoneField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(phoneField, gbc);

        addressLabel = new JLabel("Address:");
        addressLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(addressLabel, gbc);

        addressField = new JTextField(10);
        addressField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(addressField, gbc);

        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.addActionListener(addStudentEvents);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(addButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(addStudentEvents);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(cancelButton, gbc);
    }
    public JTextField getStudentIDField() {
        return studentIDField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JTextField getAddressField() {
        return addressField;
    }
}
