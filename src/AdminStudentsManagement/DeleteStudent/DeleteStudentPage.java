package AdminStudentsManagement.DeleteStudent;

import javax.swing.*;
import java.awt.*;

public class DeleteStudentPage extends JFrame {
    private JButton deleteButton, cancelButton;
    private JLabel titleLabel, studentIDLabel;
    private JTextField studentIDField;

    public DeleteStudentPage() {
        setTitle("Detele Student");
        setLayout(new GridBagLayout());
        setDeleteStudentPagePanel();
        setSize(800,450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setDeleteStudentPagePanel() {
        DeleteStudentEvents deleteStudentEvents = new DeleteStudentEvents(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Delete Student");
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

        deleteButton = new JButton("Delete");
        deleteButton.setFont(buttonFont);
        deleteButton.addActionListener(deleteStudentEvents);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(deleteButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(deleteStudentEvents);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(cancelButton, gbc);
    }
}
