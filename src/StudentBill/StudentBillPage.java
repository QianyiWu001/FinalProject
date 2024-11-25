package StudentBill;

import javax.swing.*;
import java.awt.*;

public class StudentBillPage extends JFrame {
    private JButton backButton, exitButton;
    private JLabel titleLabel, studentIDLabel, currentBalanceLabel, StatusLabel, BalanceDue;
    private JTextField studentIDField, currentBalanceField, StatusField, BalanceDueField;

    public StudentBillPage(String[] studentBill) {
        setTitle("Student Bill Page");
        setLayout(new GridBagLayout());
        setStudentBillPagePanel(studentBill);
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setStudentBillPagePanel(String[] studentBill) {
        StudentBillEvents studentBillEvents = new StudentBillEvents(this);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(studentBillEvents);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(backButton, gbc);

        titleLabel = new JLabel("Student Bill");
        titleLabel.setFont(titleFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(titleLabel, gbc);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(studentBillEvents);
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

        studentIDField = new JTextField(10);
        studentIDField.setFont(font);
        studentIDField.setText(studentBill[0]);
        studentIDField.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(studentIDField, gbc);

        currentBalanceLabel = new JLabel("Current Balance:");
        currentBalanceLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(currentBalanceLabel, gbc);

        currentBalanceField = new JTextField(10);
        currentBalanceField.setFont(font);
        currentBalanceField.setText(studentBill[1]);
        currentBalanceField.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(currentBalanceField, gbc);

        StatusLabel = new JLabel("Paid Status:");
        StatusLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(StatusLabel, gbc);

        StatusField = new JTextField(10);
        StatusField.setFont(font);
        StatusField.setText(studentBill[2]);
        StatusField.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(StatusField, gbc);

        BalanceDue = new JLabel("Due Date:");
        BalanceDue.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 10);
        add(BalanceDue, gbc);

        BalanceDueField = new JTextField(10);
        BalanceDueField.setFont(font);
        BalanceDueField.setText(studentBill[3]);
        BalanceDueField.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(BalanceDueField, gbc);
    }
}
