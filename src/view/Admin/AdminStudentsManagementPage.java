package view.Admin;

import controller.StudentController;
import entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.util.List;

public class AdminStudentsManagementPage extends JFrame {
    private JButton backButton, exitButton, addStudentButton, deleteStudentButton, updateStudentButton, searchStudentButton, refreshButton;
    private JTextField searchStudentField;
    private JTable studentsTable;
    private JScrollPane studentsTableScrollPane;
    private StudentController studentController;

    public AdminStudentsManagementPage() {
        studentController = new StudentController(); // 初始化控制器
        setTitle("Admin Students Management Page");
        setLayout(new BorderLayout());
        setAdminStudentsManagementPagePanel();
        setSize(800, 550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setAdminStudentsManagementPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));

        addStudentButton = new JButton("Add Student");
        addStudentButton.setFont(functionFont);
        addStudentButton.setPreferredSize(functionDimension);
        addStudentButton.addActionListener(e -> handleAddStudent());

        deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.setFont(functionFont);
        deleteStudentButton.setPreferredSize(functionDimension);
        deleteStudentButton.addActionListener(e -> handleDeleteStudent());

        updateStudentButton = new JButton("Update Student");
        updateStudentButton.setFont(functionFont);
        updateStudentButton.setPreferredSize(functionDimension);
        updateStudentButton.addActionListener(e -> handleUpdateStudent());

        searchStudentButton = new JButton("Search Student");
        searchStudentButton.setFont(functionFont);
        searchStudentButton.setPreferredSize(functionDimension);
        searchStudentButton.addActionListener(e -> handleSearchStudent());

        searchStudentField = new JTextField(15);
        searchStudentField.setFont(functionFont);
        searchStudentField.setPreferredSize(functionDimension);

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(functionFont);
        refreshButton.setPreferredSize(functionDimension);
        refreshButton.addActionListener(e -> refreshTable());

        topPanel.add(addStudentButton);
        topPanel.add(deleteStudentButton);
        topPanel.add(Box.createRigidArea(functionDimension));
        topPanel.add(updateStudentButton);
        topPanel.add(Box.createRigidArea(functionDimension));
        topPanel.add(searchStudentField);
        topPanel.add(searchStudentButton);
        topPanel.add(Box.createRigidArea(functionDimension));
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

        String[] columnNames = {"Student ID", "Name", "Email", "Phone", "Address"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // 所有列可编辑
                return true;
            }
        };
        studentsTable = new JTable(model);
        studentsTable.getTableHeader().setFont(tableFont);
        studentsTable.setFont(tableFont);
        studentsTable.setRowHeight(30);
        studentsTableScrollPane = new JScrollPane(studentsTable);
        studentsTable.setFillsViewportHeight(true);
        
        add(studentsTableScrollPane, BorderLayout.CENTER);
        
        refreshTable();
    }

    void refreshTable() {
        List<Student> students = studentController.getAllStudents();
        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
        model.setRowCount(0); // 清空现有数据
        for (Student student : students) {
            model.addRow(new Object[]{
                student.getUserId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getAddress()
            });
        }
    }

    private void handleAddStudent() {   
        new AddStudentPage(this, studentController);
    }

    private void handleDeleteStudent() {
        int selectedRow = studentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            return;
        }
        int studentId = (int) studentsTable.getValueAt(selectedRow, 0);
        if (studentController.deleteStudent(studentId)) {
            JOptionPane.showMessageDialog(this, "Student deleted successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete student.");
        }
    }
    private void handleUpdateStudent() {
        if (studentsTable.isEditing()) {
            studentsTable.getCellEditor().stopCellEditing(); // 提交用户编辑的数据
        }
    
        int selectedRow = studentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to update.");
            return;
        }
    
        try {
            // 从表格中获取更新后的数据
            int studentId = Integer.parseInt(studentsTable.getValueAt(selectedRow, 0).toString());
            String name = studentsTable.getValueAt(selectedRow, 1).toString();
            String email = studentsTable.getValueAt(selectedRow, 2).toString();
            String phone = studentsTable.getValueAt(selectedRow, 3).toString();
            String address = studentsTable.getValueAt(selectedRow, 4).toString();
    
            // 获取现有 Student 对象（假设通过 studentId 查询）
            Student student = studentController.getStudentById(studentId);
            if (student != null) {
                student.setName(name);
                student.setEmail(email);
                student.setPhone(phone);
                student.setAddress(address);
    
                // 更新数据库
                if (studentController.updateStudent(student)) {
                    JOptionPane.showMessageDialog(this, "Student updated successfully.");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update student.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid data format. Please check your input.");
        }
    }
    private void handleSearchStudent() {
        String query = searchStudentField.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search query.");
            return;
        }
        List<Student> students = studentController.searchStudents(query);
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No student found.");
        } else {
            updateTableData(students);
        }
    }

    private void updateTableData(List<Student> students) {
        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
        model.setRowCount(0);
        for (Student student : students) {
            model.addRow(new Object[]{
                student.getUserId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getAddress()
            });
        }
    }

    private void handleBack() {
        new AdminLoginPage(); // 返回管理员主页
        dispose();
    }
}