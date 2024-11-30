package view.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.BillController;
import entity.Bill;

public class AddBillPage extends JFrame {
    private JButton addButton, cancelButton;
    private JLabel billIDLabel, studentIDLabel, amountLabel, dueDateLabel, statusLabel;
    private JTextField billIDField, studentIDField, amountField, dueDateField, statusField;
    private AdminBillManagementPage adminBillManagementPage;
    private BillController billController;

    public AddBillPage(AdminBillManagementPage adminBillManagementPage, BillController billController) {
        this.adminBillManagementPage = adminBillManagementPage;
        this.billController = billController;

        setTitle("Add Bill");
        setLayout(null);
        setSize(400, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        billIDLabel = new JLabel("Bill ID:");
        billIDLabel.setFont(labelFont);
        billIDLabel.setBounds(50, 50, 100, 30);
        billIDField = new JTextField();
        billIDField.setBounds(160, 50, 180, 30);

        studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(labelFont);
        studentIDLabel.setBounds(50, 100, 100, 30);
        studentIDField = new JTextField();
        studentIDField.setBounds(160, 100, 180, 30);

        amountLabel = new JLabel("Amount:");
        amountLabel.setFont(labelFont);
        amountLabel.setBounds(50, 150, 100, 30);
        amountField = new JTextField();
        amountField.setBounds(160,

 150, 180, 30);

        dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setFont(labelFont);
        dueDateLabel.setBounds(50, 200, 100, 30);
        dueDateField = new JTextField();
        dueDateField.setBounds(160, 200, 180, 30);

        statusLabel = new JLabel("Status:");
        statusLabel.setFont(labelFont);
        statusLabel.setBounds(50, 250, 100, 30);
        statusField = new JTextField();
        statusField.setBounds(160, 250, 180, 30);

        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.setBounds(80, 300, 100, 30);
        addButton.addActionListener(e -> handleAddBill());

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.setBounds(200, 300, 100, 30);
        cancelButton.addActionListener(e -> dispose());

        add(billIDLabel);
        add(billIDField);
        add(studentIDLabel);
        add(studentIDField);
        add(amountLabel);
        add(amountField);
        add(dueDateLabel);
        add(dueDateField);
        add(statusLabel);
        add(statusField);
        add(addButton);
        add(cancelButton);
    }

    private void handleAddBill() {
        try {
            int billID = Integer.parseInt(billIDField.getText().trim());
            int studentID = Integer.parseInt(studentIDField.getText().trim());
            double amount = Double.parseDouble(amountField.getText().trim());
            String dueDate = dueDateField.getText().trim();
            String status = statusField.getText().trim();

            Bill bill = new Bill();
            bill.setBillID(billID);
            bill.setBillAmount(amount);
            bill.setDueDate(java.time.LocalDate.parse(dueDate));
            bill.setPaidStatus(Bill.PaidStatus.valueOf(status));

            boolean success = billController.addBill(bill);

            if (success) {
                JOptionPane.showMessageDialog(this, "Bill added successfully.");
                adminBillManagementPage.refreshTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add bill.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check your data.");
        }
    }
}