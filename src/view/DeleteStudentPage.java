package view;

import controller.StudentController;

import javax.swing.*;
import java.awt.*;

public class DeleteStudentPage extends JFrame {
    private JTextField studentIDField;
    private StudentController studentController;
    private AdminStudentsManagementPage adminStudentsManagementPage;

    public DeleteStudentPage(AdminStudentsManagementPage adminStudentsManagementPage, StudentController studentController) {
        this.adminStudentsManagementPage = adminStudentsManagementPage;
        this.studentController = studentController;
        setTitle("Delete Student");
        setLayout(new GridBagLayout());
        initializeUI();
        setSize(800, 450);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initializeUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;

        add(createLabel("Student ID:"), gbc);
        studentIDField = createTextField();
        add(studentIDField, gbc);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteStudent());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, gbc);
    }

    private JLabel createLabel(String text) {
        return new JLabel(text);
    }

    private JTextField createTextField() {
        return new JTextField(20);
    }

    private void deleteStudent() {
        int studentId;
        try {
            studentId = Integer.parseInt(studentIDField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid student ID.");
            return;
        }

        if (studentController.deleteStudent(studentId)) {
            JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            adminStudentsManagementPage.refreshTable();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete student.");
        }
    }
}