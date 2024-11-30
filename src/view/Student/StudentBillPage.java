package view.Student;

import controller.BillController;
import entity.Bill;
import DatabaseUtilities.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentBillPage extends JFrame {
    private JTable billsTable;
    private JButton backButton, refreshButton, addBillButton, deleteBillButton;
    private BillController billController;

    public StudentBillPage() {
        billController = new BillController();
        setTitle("Student Bills");
        setLayout(new BorderLayout());
        setSize(800, 550);
        setBillPagePanel();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setBillPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        // Table
        String[] columnNames = {"Bill ID", "Amount", "Due Date", "Status"};
        billsTable = new JTable(new DefaultTableModel(new Object[0][0], columnNames));
        billsTable.getTableHeader().setFont(tableFont);
        billsTable.setFont(tableFont);
        billsTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(billsTable);
        billsTable.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        backButton = createButton("Back", buttonFont);
        backButton.addActionListener(e -> handleBack());
        refreshButton = createButton("Refresh", buttonFont);
        refreshButton.addActionListener(e -> refreshTable());
        // addBillButton = createButton("Add Bill", buttonFont);
        // addBillButton.addActionListener(e -> handleAddBill());
        // deleteBillButton = createButton("Delete Bill", buttonFont);
        // deleteBillButton.addActionListener(e -> handleDeleteBill());

        buttonPanel.add(backButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(addBillButton);
        buttonPanel.add(deleteBillButton);

        add(buttonPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    private JButton createButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    private void refreshTable() {
        int studentId = Session.getStudentId();
        List<Bill> bills = billController.getBillsByStudentId(studentId);
        updateTableData(bills);
    }

    private void updateTableData(List<Bill> bills) {
        DefaultTableModel model = (DefaultTableModel) billsTable.getModel();
        model.setRowCount(0);
        for (Bill bill : bills) {
            model.addRow(new Object[]{
                bill.getBillID(),
                bill.getBillAmount(),
                bill.getDueDate(),
                bill.getPaidStatus().name()
            });
        }
    }

    private void handleAddBill() {
        JOptionPane.showMessageDialog(this, "Add Bill functionality is not implemented yet.");
    }

    private void handleDeleteBill() {
        int selectedRow = billsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a bill to delete.");
            return;
        }
        int billId = (int) billsTable.getValueAt(selectedRow, 0);
        if (billController.deleteBill(billId)) {
            JOptionPane.showMessageDialog(this, "Bill deleted successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete bill.");
        }
    }

    private void handleBack() {
        new StudentLoginPage();
        dispose();
    }
}