package view.Student;

import controller.BillController;
import entity.Bill;
import entity.Bill.PaidStatus;
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

    @SuppressWarnings("unused")
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

        buttonPanel.add(backButton);
        buttonPanel.add(refreshButton);


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
    model.setRowCount(0); // 清空表格数据

    if (bills.size() == 1 && bills.get(0).getPaidStatus() == PaidStatus.NOBill) {
        // 如果是 No Bill 状态
        model.addRow(new Object[]{"N/A", "N/A", "N/A", "No Bill"});
    } else {
        for (Bill bill : bills) {
            Object[] row = {
                bill.getStudentId(),
               
                bill.getBillAmount(),
                bill.getDueDate() != null ? bill.getDueDate() : "N/A",
                bill.getPaidStatus().toString() // 显示枚举的名称
            };
            model.addRow(row);
        }
    }
}

    private void handleBack() {
        new StudentLoginPage();
        dispose();
    }
}