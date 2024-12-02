package view.Admin; // 根据项目结构修改为实际的包名

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Collections;

import controller.StudentController; // 控制器类
import entity.Student; // 学生实体类



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
        setSize(900, 550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unused")
   private void setAdminStudentsManagementPagePanel() {
    Font tableFont = new Font("Arial", Font.PLAIN, 16);
    Font functionFont = new Font("Arial", Font.PLAIN, 13);
    Dimension functionDimension = new Dimension(130, 30);
 Font buttonFont = new Font("Arial", Font.PLAIN, 18);
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

    searchStudentField = new JTextField(15);
    searchStudentField.setFont(functionFont);
    searchStudentField.setPreferredSize(functionDimension);

    searchStudentButton = new JButton("Search Student");
    searchStudentButton.setFont(functionFont);
    searchStudentButton.setPreferredSize(functionDimension);
    searchStudentButton.addActionListener(e -> handleSearchStudent());

    refreshButton = new JButton("Refresh");
    refreshButton.setFont(functionFont);
    refreshButton.setPreferredSize(functionDimension);
    refreshButton.addActionListener(e -> refreshTable());

    topPanel.add(addStudentButton);
    topPanel.add(deleteStudentButton);
    topPanel.add(updateStudentButton);
    topPanel.add(searchStudentField);
    topPanel.add(searchStudentButton);
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


    // 初始化表格模型
    DefaultTableModel model = new DefaultTableModel(
            new Object[0][0],
            new String[]{"Student ID", "Name", "Email", "Phone"}
    );

    studentsTable = new JTable(model);
    studentsTable.getTableHeader().setFont(tableFont);
    studentsTable.setFont(tableFont);
    studentsTable.setRowHeight(30);

    // 添加表头排序功能和符号
    JTableHeader header = studentsTable.getTableHeader();
    header.setReorderingAllowed(false); // 禁止拖动列

    boolean[] sortStates = new boolean[4]; // 排序状态：false = 升序, true = 降序

    header.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int viewColumn = studentsTable.columnAtPoint(e.getPoint()); // 视图列索引
            int modelColumn = studentsTable.convertColumnIndexToModel(viewColumn); // 转换为模型列索引
            int columnCount = studentsTable.getModel().getColumnCount(); // 获取模型列数

            // 确保模型列索引有效
            if (modelColumn >= 0 && modelColumn < columnCount) {
                List<Student> students = studentController.getAllStudents();

                // 根据列号排序
                students.sort((s1, s2) -> {
                    if (modelColumn == 0) { // 按 Student ID 排序
                        return sortStates[modelColumn]
                                ? Integer.compare(s2.getUserId(), s1.getUserId())
                                : Integer.compare(s1.getUserId(), s2.getUserId());
                    } else if (modelColumn == 1) { // 按 Name 排序
                        return sortStates[modelColumn]
                                ? s2.getName().compareTo(s1.getName())
                                : s1.getName().compareTo(s2.getName());
                    } else if (modelColumn == 2) { // 按 Email 排序
                        return sortStates[modelColumn]
                                ? s2.getEmail().compareTo(s1.getEmail())
                                : s1.getEmail().compareTo(s2.getEmail());
                    } else if (modelColumn == 3) { // 按 Phone 排序
                        return sortStates[modelColumn]
                                ? s2.getPhone().compareTo(s1.getPhone())
                                : s1.getPhone().compareTo(s2.getPhone());
                    }
                    return 0; // 默认不变
                });

                // 切换当前列的排序状态
                sortStates[modelColumn] = !sortStates[modelColumn];

                // 更新表格数据
                updateTableData(students);

                // 更新表头符号
                for (int i = 0; i < columnCount; i++) {
                    String columnName = studentsTable.getColumnName(i).replaceAll(" ▲| ▼", "");
                    if (i == modelColumn) {
                        columnName += sortStates[modelColumn] ? " ▼" : " ▲";
                    }
                    studentsTable.getColumnModel().getColumn(i).setHeaderValue(columnName);
                }
                header.repaint(); // 刷新表头
            }
        }
    });

    studentsTableScrollPane = new JScrollPane(studentsTable);
    studentsTableScrollPane.setPreferredSize(new Dimension(750, 300));
    add(studentsTableScrollPane, BorderLayout.CENTER);

    refreshTable(); // 初始化表格数据
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
        model.setRowCount(0); // 清空表格数据
    
        for (Student student : students) {
            Object[] row = {
                student.getUserId(),
                student.getName(),
                student.getEmail(),
                student.getPhone()
            };
            model.addRow(row);
        }
    }
    private void handleBack() {
        new AdminLoginPage(); // 返回管理员主页
        dispose();
    }
}