package view.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.StudentDAO;
import entity.Student;

public class StudentProfilePage extends JFrame implements ActionListener {
    private JButton backButton, exitButton;
    private JLabel titleLabel, studentIDLabel, nameLabel, emailLabel, phoneLabel, addressLabel;
    private JTextField studentIDTextField, nameTextField, emailTextField, phoneTextField, addressTextField;

    public StudentProfilePage(int studentID) {
        setTitle("Student Profile Page");
        setLayout(new GridBagLayout());
        setSize(800, 550);
        // Get student profile from DAO.
        Student student = fetchStudentProfile(studentID); 
        if (student == null) {
            JOptionPane.showMessageDialog(this, "Student profile not found.");
            dispose();
            return;
        }
        setStudentProfilePagePanel(student);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Student fetchStudentProfile(int studentID) {
        StudentDAO studentDAO = new StudentDAO();
        return studentDAO.getStudentById(studentID);
    }

    private void setStudentProfilePagePanel(Student student) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        backButton = createButton("Back", buttonFont, gbc, 0, 0);
        titleLabel = createLabel("Student Profile", titleFont, gbc, 1, 1, 2);

        exitButton = createButton("Exit", buttonFont, gbc, 3, 0);

        studentIDLabel = createLabel("Student ID:", font, gbc, 1, 2);
        studentIDTextField = createTextField(String.valueOf(student.getUserId()), font, gbc, 2, 2);

        nameLabel = createLabel("Name:", font, gbc, 1, 3);
        nameTextField = createTextField(student.getName(), font, gbc, 2, 3);

        emailLabel = createLabel("Email:", font, gbc, 1, 4);
        emailTextField = createTextField(student.getEmail(), font, gbc, 2, 4);

        phoneLabel = createLabel("Phone:", font, gbc, 1, 5);
        phoneTextField = createTextField(student.getPhone(), font, gbc, 2, 5);

        addressLabel = createLabel("Address:", font, gbc, 1, 6);
        addressTextField = createTextField(student.getAddress(), font, gbc, 2, 6);
    }

    private JButton createButton(String text, Font font, GridBagConstraints gbc, int gridx, int gridy) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setPreferredSize(new Dimension(110, 30));
        button.addActionListener(this);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(button, gbc);
        return button;
    }

    private JLabel createLabel(String text, Font font, GridBagConstraints gbc, int gridx, int gridy) {
        return createLabel(text, font, gbc, gridx, gridy, 1);
    }

    private JLabel createLabel(String text, Font font, GridBagConstraints gbc, int gridx, int gridy, int gridwidth) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(label, gbc);
        return label;
    }

    private JTextField createTextField(String text, Font font, GridBagConstraints gbc, int gridx, int gridy) {
        JTextField textField = new JTextField(15);
        textField.setFont(font);
        textField.setText(text);
        textField.setEditable(false);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(textField, gbc);
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source == backButton) {
            handleBack();
        } else if (source == exitButton) {
            handleExit();
        }
    }

    private void handleBack() {
        new StudentLoginPage();
        dispose();
    }

    private void handleExit() {
        System.exit(0);
    }
}