package view.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.EnrollmentController;
import entity.Enrollment;

import java.awt.*;
import java.util.List;

public class AdminEnrollmentManagementPage extends JFrame {
    private JButton backButton, exitButton, addEnrollmentButton, deleteEnrollmentButton, updateEnrollmentButton, searchEnrollmentButton, refreshButton;
    private JTextField searchEnrollmentField;
    private JTable enrollmentTable;
    private JScrollPane enrollmentTableScrollPane;
    private EnrollmentController enrollmentController;

    public AdminEnrollmentManagementPage() {
        enrollmentController = new EnrollmentController(); // 初始化控制器
        setTitle("Admin Enrollment Management Page");
        setLayout(new BorderLayout());
        setAdminEnrollmentManagementPagePanel();
        setSize(800, 550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unused")
    private void setAdminEnrollmentManagementPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));

        addEnrollmentButton = new JButton("Add Enrollment");
        addEnrollmentButton.setFont(functionFont);
        addEnrollmentButton.setPreferredSize(functionDimension);
        addEnrollmentButton.addActionListener(e -> handleAddEnrollment());

        deleteEnrollmentButton = new JButton("Delete Enrollment");
        deleteEnrollmentButton.setFont(functionFont);
        deleteEnrollmentButton.setPreferredSize(functionDimension);
        deleteEnrollmentButton.addActionListener(e -> handleDeleteEnrollment());

        updateEnrollmentButton = new JButton("Update Enrollment");
        updateEnrollmentButton.setFont(functionFont);
        updateEnrollmentButton.setPreferredSize(functionDimension);
        updateEnrollmentButton.addActionListener(e -> handleUpdateEnrollment());

        searchEnrollmentButton = new JButton("Search Enrollment");
        searchEnrollmentButton.setFont(functionFont);
        searchEnrollmentButton.setPreferredSize(functionDimension);
        searchEnrollmentButton.addActionListener(e -> handleSearchEnrollment());

        searchEnrollmentField = new JTextField(15);
        searchEnrollmentField.setFont(functionFont);
        searchEnrollmentField.setPreferredSize(functionDimension);

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(functionFont);
        refreshButton.setPreferredSize(functionDimension);
        refreshButton.addActionListener(e -> refreshTable());

        topPanel.add(addEnrollmentButton);
        topPanel.add(deleteEnrollmentButton);
        topPanel.add(updateEnrollmentButton);
        topPanel.add(searchEnrollmentField);
        topPanel.add(searchEnrollmentButton);
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

        DefaultTableModel model = new DefaultTableModel(new Object[0][0], new String[]{"Enrollment ID", "Student ID", "Course ID"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // 所有列可编辑
            }
        };

        enrollmentTable = new JTable(model);
        enrollmentTable.getTableHeader().setFont(tableFont);
        enrollmentTable.setFont(tableFont);
        enrollmentTable.setRowHeight(30);

        enrollmentTableScrollPane = new JScrollPane(enrollmentTable);
        enrollmentTable.setFillsViewportHeight(true);

        add(enrollmentTableScrollPane, BorderLayout.CENTER);

        refreshTable();
    }

    void refreshTable() {
        List<Enrollment> enrollments = enrollmentController.getAllEnrollments();
        DefaultTableModel model = (DefaultTableModel) enrollmentTable.getModel();
        model.setRowCount(0); // 清空表格数据
        for (Enrollment enrollment : enrollments) {
            model.addRow(new Object[]{
                enrollment.getEnrollmentId(),
                enrollment.getStudentId(),
                enrollment.getCourseId()
            });
        }
    }

    private void handleAddEnrollment() {
        new AddEnrollmentPage(this, enrollmentController);
    }

    private void handleDeleteEnrollment() {
        int selectedRow = enrollmentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an enrollment to delete.");
            return;
        }
        int enrollmentId = (int) enrollmentTable.getValueAt(selectedRow, 0);
        if (enrollmentController.deleteEnrollment(enrollmentId)) {
            JOptionPane.showMessageDialog(this, "Enrollment deleted successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete enrollment.");
        }
    }

    private void handleUpdateEnrollment() {
        if (enrollmentTable.isEditing()) {
            enrollmentTable.getCellEditor().stopCellEditing(); // 提交用户编辑的数据
        }

        int selectedRow = enrollmentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an enrollment to update.");
            return;
        }

        int enrollmentId = Integer.parseInt(enrollmentTable.getValueAt(selectedRow, 0).toString());
        int studentId = Integer.parseInt(enrollmentTable.getValueAt(selectedRow, 1).toString());
        int courseId = Integer.parseInt(enrollmentTable.getValueAt(selectedRow, 2).toString());

        Enrollment updatedEnrollment = new Enrollment();
        updatedEnrollment.setEnrollmentId(enrollmentId);
        updatedEnrollment.setStudentId(studentId);
        updatedEnrollment.setCourseId(courseId);

        if (enrollmentController.updateEnrollment(updatedEnrollment)) {
            JOptionPane.showMessageDialog(this, "Enrollment updated successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update enrollment.");
        }
    }

    private void handleSearchEnrollment() {
        String query = searchEnrollmentField.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search query.");
            return;
        }
        List<Enrollment> enrollments = enrollmentController.searchEnrollments(query);
        if (enrollments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No enrollment found.");
        } else {
            updateTableData(enrollments);
        }
    }

    private void updateTableData(List<Enrollment> enrollments) {
        DefaultTableModel model = (DefaultTableModel) enrollmentTable.getModel();
        model.setRowCount(0);
        for (Enrollment enrollment : enrollments) {
            model.addRow(new Object[]{
                enrollment.getEnrollmentId(),
                enrollment.getStudentId(),
                enrollment.getCourseId()
            });
        }
    }

    private void handleBack() {
        new AdminLoginPage(); // 返回管理员主页
        dispose();
    }
}