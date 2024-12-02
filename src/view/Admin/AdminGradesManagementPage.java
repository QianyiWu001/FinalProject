package view.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.GradesController;
import entity.Grade;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import controller.GradesController;
import entity.Grade;

public class AdminGradesManagementPage extends JFrame {
    private JButton backButton, exitButton, addGradeButton, deleteGradeButton, updateGradeButton, searchGradeButton,
            refreshButton;
    private JTextField searchGradeField;
    private JTable gradesTable;
    private JScrollPane gradesTableScrollPane;
    private GradesController gradesController;

    public AdminGradesManagementPage() {
        gradesController = new GradesController(); // 初始化控制器
        setTitle("Admin Grades Management Page");
        setLayout(new BorderLayout());
        setAdminGradesManagementPagePanel();
        setSize(900, 550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unused")
    private void setAdminGradesManagementPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));

        addGradeButton = new JButton("Add Grade");
        addGradeButton.setFont(functionFont);
        addGradeButton.setPreferredSize(functionDimension);
        addGradeButton.addActionListener(e -> handleAddGrade());

        deleteGradeButton = new JButton("Delete Grade");
        deleteGradeButton.setFont(functionFont);
        deleteGradeButton.setPreferredSize(functionDimension);
        deleteGradeButton.addActionListener(e -> handleDeleteGrade());

        updateGradeButton = new JButton("Update Grade");
        updateGradeButton.setFont(functionFont);
        updateGradeButton.setPreferredSize(functionDimension);
        updateGradeButton.addActionListener(e -> handleUpdateGrade());

        searchGradeButton = new JButton("Search Grade");
        searchGradeButton.setFont(functionFont);
        searchGradeButton.setPreferredSize(functionDimension);
        searchGradeButton.addActionListener(e -> handleSearchGrade());

        searchGradeField = new JTextField(15);
        searchGradeField.setFont(functionFont);
        searchGradeField.setPreferredSize(functionDimension);

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(functionFont);
        refreshButton.setPreferredSize(functionDimension);
        refreshButton.addActionListener(e -> refreshTable());

        topPanel.add(addGradeButton);
        topPanel.add(deleteGradeButton);
        topPanel.add(updateGradeButton);
        topPanel.add(searchGradeField);
        topPanel.add(searchGradeButton);
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

        DefaultTableModel model = new DefaultTableModel(
                new Object[0][0],
                new String[] { "Enrollment ID", "Student ID", "Course ID", "Grade" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // 仅允许编辑 Grade 列
            }
        };

        gradesTable = new JTable(model);
        gradesTable.getTableHeader().setFont(tableFont);
        gradesTable.setFont(tableFont);
        gradesTable.setRowHeight(30);

        gradesTableScrollPane = new JScrollPane(gradesTable);
        gradesTable.setFillsViewportHeight(true);
        // 隐藏 enrollmentId 列
        gradesTable.getColumnModel().getColumn(0).setMinWidth(0);
        gradesTable.getColumnModel().getColumn(0).setMaxWidth(0);
        gradesTable.getColumnModel().getColumn(0).setPreferredWidth(0);

        add(gradesTableScrollPane, BorderLayout.CENTER);

    // 添加表头排序功能和符号
    JTableHeader header = gradesTable.getTableHeader();
    header.setReorderingAllowed(false); // 禁止拖动列

    boolean[] sortStates = new boolean[4]; // 排序状态：false = 升序, true = 降序

    header.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int viewColumn = gradesTable.columnAtPoint(e.getPoint()); // 视图列索引
            int modelColumn = gradesTable.convertColumnIndexToModel(viewColumn); // 转换为模型列索引
            int columnCount = gradesTable.getModel().getColumnCount(); // 获取模型列数

            // 确保模型列索引有效
            if (modelColumn >= 0 && modelColumn < columnCount) {
                List<Grade> grades = gradesController.getAllGrades();

                // 根据列号排序
                grades.sort((g1, g2) -> {
                    if (modelColumn == 0) { // 按 Enrollment ID 排序
                        return sortStates[modelColumn]
                                ? Integer.compare(g2.getEnrollmentId(), g1.getEnrollmentId())
                                : Integer.compare(g1.getEnrollmentId(), g2.getEnrollmentId());
                    } else if (modelColumn == 1) { // 按 Student ID 排序
                        return sortStates[modelColumn]
                                ? Integer.compare(g2.getStudentId(), g1.getStudentId())
                                : Integer.compare(g1.getStudentId(), g2.getStudentId());
                    } else if (modelColumn == 2) { // 按 Course ID 排序
                        return sortStates[modelColumn]
                                ? Integer.compare(g2.getCourseId(), g1.getCourseId())
                                : Integer.compare(g1.getCourseId(), g2.getCourseId());
                    } else if (modelColumn == 3) { // 按 Grade 排序
                        return sortStates[modelColumn]
                                ? Integer.compare(g2.getGrade(), g1.getGrade())
                                : Integer.compare(g1.getGrade(), g2.getGrade());
                    }
                    return 0; // 默认不变
                });

                // 切换当前列的排序状态
                sortStates[modelColumn] = !sortStates[modelColumn];

                // 更新表格数据
                updateTableData(grades);

                // 更新表头符号
                for (int i = 0; i < columnCount; i++) {
                    String columnName = gradesTable.getColumnName(i).replaceAll(" ▲| ▼", "");
                    if (i == modelColumn) {
                        columnName += sortStates[modelColumn] ? " ▼" : " ▲";
                    }
                    gradesTable.getColumnModel().getColumn(i).setHeaderValue(columnName);
                }
                header.repaint(); // 刷新表头
            }
        }
    });

    gradesTableScrollPane = new JScrollPane(gradesTable);
    gradesTableScrollPane.setPreferredSize(new Dimension(750, 300));
    add(gradesTableScrollPane, BorderLayout.CENTER);

    refreshTable(); // 初始化表格数据
    }

    void refreshTable() {
        List<Grade> grades = gradesController.getAllGrades();
        DefaultTableModel model = (DefaultTableModel) gradesTable.getModel();
        model.setRowCount(0); // 清空表格数据
        for (Grade grade : grades) {
            model.addRow(new Object[] {
                    grade.getEnrollmentId(),
                    grade.getStudentId(),
                    grade.getCourseId(),
                    grade.getGrade()
            });
        }
    }

    private void handleAddGrade() {
        new AddGradePage(this, gradesController);
    }

    private void handleDeleteGrade() {
        int selectedRow = gradesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a grade to delete.");
            return;
        }
        int enrollmentId = Integer.parseInt(gradesTable.getValueAt(selectedRow, 0).toString());
        if (gradesController.deleteGrade(enrollmentId)) {
            JOptionPane.showMessageDialog(this, "Grade deleted successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete grade.");
        }
    }

    private void handleUpdateGrade() {
        if (gradesTable.isEditing()) {
            gradesTable.getCellEditor().stopCellEditing(); // 提交用户编辑的数据
        }

        int selectedRow = gradesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a grade to update.");
            return;
        }

        int enrollmentId = Integer.parseInt(gradesTable.getValueAt(selectedRow, 0).toString());
        int gradeValue = Integer.parseInt(gradesTable.getValueAt(selectedRow, 3).toString());
        Grade updatedGrade = new Grade(enrollmentId, gradeValue);
        if (gradesController.updateGrade(updatedGrade)) {
            JOptionPane.showMessageDialog(this, "Grade updated successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update grade.");
        }
    }

    private void handleSearchGrade() {
        String query = searchGradeField.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search query.");
            return;
        }
        List<Grade> grades = gradesController.searchGrades(query);
        if (grades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No grade found.");
        } else {
            updateTableData(grades);
        }
    }

    private void updateTableData(List<Grade> grades) {
        DefaultTableModel model = (DefaultTableModel) gradesTable.getModel();
        model.setRowCount(0); // 清空表格数据
    
        for (Grade grade : grades) {
            Object[] row = {
                grade.getEnrollmentId(),
                grade.getStudentId(),
                grade.getCourseId(),
                grade.getGrade()
            };
            model.addRow(row);
        }
    }
    private void handleBack() {
        new AdminLoginPage(); // 返回管理员主页
        dispose();
    }
}