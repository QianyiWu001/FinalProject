package view.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;
import controller.BillController;
import entity.Bill;
import entity.Bill.PaidStatus;


public class AdminBillManagementPage extends JFrame {
    private JButton backButton, exitButton, addBillButton, deleteBillButton, updateBillButton, searchBillButton,
            refreshButton;
    private JTextField searchBillField;
    private JTable billsTable;
    private JScrollPane billsTableScrollPane;
    private BillController billController;

    public AdminBillManagementPage() {
        billController = new BillController(); // 初始化控制器
        setTitle("Admin Bill Management Page");
        setLayout(new BorderLayout());
        setAdminBillManagementPagePanel();
        setSize(800, 550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unused")
    private void setAdminBillManagementPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));

        addBillButton = new JButton("Add Bill");
        addBillButton.setFont(functionFont);
        addBillButton.setPreferredSize(functionDimension);
        addBillButton.addActionListener(e -> handleAddBill());

        deleteBillButton = new JButton("Delete Bill");
        deleteBillButton.setFont(functionFont);
        deleteBillButton.setPreferredSize(functionDimension);
        deleteBillButton.addActionListener(e -> handleDeleteBill());

        updateBillButton = new JButton("Update Bill");
        updateBillButton.setFont(functionFont);
        updateBillButton.setPreferredSize(functionDimension);
        updateBillButton.addActionListener(e -> handleUpdateBill());

        searchBillButton = new JButton("Search Bill");
        searchBillButton.setFont(functionFont);
        searchBillButton.setPreferredSize(functionDimension);
        searchBillButton.addActionListener(e -> handleSearchBill());

        searchBillField = new JTextField(15);
        searchBillField.setFont(functionFont);
        searchBillField.setPreferredSize(functionDimension);

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(functionFont);
        refreshButton.setPreferredSize(functionDimension);
        refreshButton.addActionListener(e -> refreshTable());

        topPanel.add(addBillButton);
        topPanel.add(deleteBillButton);
        topPanel.add(updateBillButton);
        topPanel.add(searchBillField);
        topPanel.add(searchBillButton);
        topPanel.add(refreshButton);

        add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 10));

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(e -> handleBack());

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        DefaultTableModel model = new DefaultTableModel(new Object[0][0],
                new String[] { "Bill ID", "Student ID", "Amount", "Due Date", "Status" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // 所有列可编辑
            }
        };

        billsTable = new JTable(model);
        billsTable.getTableHeader().setFont(tableFont);
        billsTable.setFont(tableFont);
        billsTable.setRowHeight(30);


        billsTable.getColumnModel().getColumn(0).setMinWidth(0);
        billsTable.getColumnModel().getColumn(0).setMaxWidth(0);
        billsTable.getColumnModel().getColumn(0).setPreferredWidth(0);


        billsTableScrollPane = new JScrollPane(billsTable);
        billsTable.setFillsViewportHeight(true);
    
        add(billsTableScrollPane, BorderLayout.CENTER);

 // 添加表头排序功能和符号
    JTableHeader header = billsTable.getTableHeader();
    header.setReorderingAllowed(false); // 禁止拖动列

    // 记录每列的排序状态
    boolean[] sortStates = new boolean[4]; // false = 升序, true = 降序

    header.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int viewColumn = billsTable.columnAtPoint(e.getPoint()); // 获取视图列索引
            int modelColumn = billsTable.convertColumnIndexToModel(viewColumn); // 转换为模型列索引
            int columnCount = billsTable.getModel().getColumnCount(); // 获取模型列数
    
            // 确保模型列索引有效
            if (modelColumn >= 0 && modelColumn < columnCount) {
                List<Bill> bills = billController.getAllBills();
    
                // 根据列号排序
                bills.sort((b1, b2) -> {
                    if (modelColumn == 0) { // 按 Bill ID 排序
                        return sortStates[modelColumn]
                                ? Integer.compare(b2.getBillID(), b1.getBillID())
                                : Integer.compare(b1.getBillID(), b2.getBillID());
                    } else if (modelColumn == 1) { // 按 Student ID 排序
                        return sortStates[modelColumn]
                                ? Integer.compare(b2.getStudentId(), b1.getStudentId())
                                : Integer.compare(b1.getStudentId(), b2.getStudentId());
                    } else if (modelColumn == 2) { // 按 Bill Amount 排序
                        return sortStates[modelColumn]
                                ? Double.compare(b2.getBillAmount(), b1.getBillAmount())
                                : Double.compare(b1.getBillAmount(), b2.getBillAmount());
                    } else if (modelColumn == 3) { // 按 Due Date 排序
                        return sortStates[modelColumn]
                                ? b2.getDueDate().compareTo(b1.getDueDate())
                                : b1.getDueDate().compareTo(b2.getDueDate());
                    } else if (modelColumn == 4) { // 按 Paid Status 排序
                        return sortStates[modelColumn]
                                ? b2.getPaidStatus().compareTo(b1.getPaidStatus())
                                : b1.getPaidStatus().compareTo(b2.getPaidStatus());
                    }
                    return 0; // 默认不变
                });
    
                // 切换当前列的排序状态
                sortStates[modelColumn] = !sortStates[modelColumn];
    
                // 更新表格数据
                updateTableData(bills);
    
                // 更新表头符号
                JTableHeader header = billsTable.getTableHeader();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = billsTable.getColumnName(i).replaceAll(" ▲| ▼", "");
                    if (i == modelColumn) {
                        columnName += sortStates[modelColumn] ? " ▼" : " ▲";
                    }
                    billsTable.getColumnModel().getColumn(i).setHeaderValue(columnName);
                }
                header.repaint(); // 刷新表头
            }
        }
    });
    refreshTable();
    }

    void refreshTable() {
        List<Bill> bills = billController.getAllBills();
        DefaultTableModel model = (DefaultTableModel) billsTable.getModel();
        model.setRowCount(0); // 清空表格数据
        for (Bill bill : bills) {
            model.addRow(new Object[] {
                    bill.getBillID(),
                    bill.getStudentId(),
                    bill.getBillAmount(),
                    bill.getDueDate(),
                    bill.getPaidStatus()
            });
        }
    }

    private void handleAddBill() {
        new AddBillPage(this, billController);
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

    private void handleUpdateBill() {
        // 停止表格编辑，提交用户输入的数据
        if (billsTable.isEditing()) {
            billsTable.getCellEditor().stopCellEditing();
        }

        // 检查是否选中了一行
        int selectedRow = billsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a bill to update.");
            return;
        }

        try {
            // 从表格中获取用户输入的数据
            int billId = Integer.parseInt(billsTable.getValueAt(selectedRow, 0).toString());
            double amount = Double.parseDouble(billsTable.getValueAt(selectedRow, 2).toString());
            String dueDate = billsTable.getValueAt(selectedRow, 3).toString();
            String status = billsTable.getValueAt(selectedRow, 4).toString();

            // 创建更新后的 Bill 对象
            Bill updatedBill = new Bill();
            updatedBill.setBillID(billId);
            updatedBill.setBillAmount(amount);
            updatedBill.setDueDate(java.time.LocalDate.parse(dueDate));
            updatedBill.setPaidStatus(Bill.PaidStatus.valueOf(status));

            // 调用控制器更新账单
            if (billController.updateBill(updatedBill)) {
                JOptionPane.showMessageDialog(this, "Bill updated successfully.");
                refreshTable(); // 刷新表格数据
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update bill.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid data. Please check your input.");
        }
    }

    private void handleSearchBill() {
        String query = searchBillField.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search query.");
            return;
        }
        List<Bill> bills = billController.searchBills(query);
        if (bills.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No bills found.");
        } else {
            updateTableData(bills);
        }
    }

    private void updateTableData(List<Bill> bills) {
        DefaultTableModel model = (DefaultTableModel) billsTable.getModel();
        model.setRowCount(0); // 清空表格数据
    
    if (bills.size() == 1 && bills.get(0).getPaidStatus() == PaidStatus.NOBill) {
        // 如果是 No Bill 状态
        model.addRow(new Object[]{"N/A", "N/A", "N/A","N/A", "No Bill"});
    } else{
        for (Bill bill : bills) {
            Object[] row = {
                bill.getBillID(),
                bill.getStudentId(),
                bill.getBillAmount(),
                bill.getDueDate() != null ? bill.getDueDate() : "N/A",
                bill.getPaidStatus()
            };
            model.addRow(row);
        }
    }
        
    }

    private void handleBack() {
        new AdminLoginPage(); // 返回管理员主页
        dispose();
    }
}