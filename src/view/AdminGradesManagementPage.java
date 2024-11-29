package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.GradesController;
import entity.Grade;

import java.awt.*;
import java.util.List;

public class AdminGradesManagementPage extends JFrame {
    private JButton backButton, exitButton, addGradeButton, deleteGradeButton, updateGradeButton, searchGradeButton, refreshButton;
    private JTextField searchGradeField;
    private JTable gradesTable;
    private JScrollPane gradesTableScrollPane;
    private GradesController gradesController;

    public AdminGradesManagementPage() {
        gradesController = new GradesController(); // 初始化控制器
        setTitle("Admin Grades Management Page");
        setLayout(new BorderLayout());
        setAdminGradesManagementPagePanel();
        setSize(800, 550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unused")
    private void setAdminGradesManagementPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);

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
        topPanel.add(Box.createRigidArea(functionDimension));
        topPanel.add(updateGradeButton);
        topPanel.add(Box.createRigidArea(functionDimension));
        topPanel.add(searchGradeField);
        topPanel.add(searchGradeButton);
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

        DefaultTableModel model = new DefaultTableModel(new Object[0][0], new String[]{"Student ID", "Course ID", "Grade"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // 所有列可编辑
            }
        };
    
        // 创建表格并应用模型
        gradesTable = new JTable(model);
        gradesTable.getTableHeader().setFont(tableFont);
        gradesTable.setFont(tableFont);
        gradesTable.setRowHeight(30);
    
        JScrollPane gradesTableScrollPane = new JScrollPane(gradesTable);
        gradesTable.setFillsViewportHeight(true);
    
        add(gradesTableScrollPane, BorderLayout.CENTER);
    
        refreshTable();
    }

    void refreshTable() {
        List<Grade> grades = gradesController.getAllGrades();
        DefaultTableModel model = (DefaultTableModel) gradesTable.getModel();
        model.setRowCount(0); // 清空表格数据
        for (Grade grade : grades) {
            model.addRow(new Object[]{
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
        int studentId = (int) gradesTable.getValueAt(selectedRow, 0);
        int courseId = (int) gradesTable.getValueAt(selectedRow, 1);
        if (gradesController.deleteGrade(studentId, courseId)) {
            JOptionPane.showMessageDialog(this, "Grade deleted successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete grade.");
        }
    }
    // private void handleUpdateGrade() {
    //     int selectedRow = gradesTable.getSelectedRow();
    //     if (selectedRow == -1) {
    //         JOptionPane.showMessageDialog(this, "Please select a grade to update.");
    //         return;
    //     }
        
    //     // 确保正确地解析数据
    //     int studentId = Integer.parseInt(gradesTable.getValueAt(selectedRow, 0).toString());
    //     int courseId = Integer.parseInt(gradesTable.getValueAt(selectedRow, 1).toString());
    //     int gradeValue = Integer.parseInt(gradesTable.getValueAt(selectedRow, 2).toString());
    
    //     Grade updatedGrade = new Grade(studentId, courseId, gradeValue);
    //     if (gradesController.updateGrade(updatedGrade)) {
    //         JOptionPane.showMessageDialog(this, "Grade updated successfully.");
    //         refreshTable();
    //     } else {
    //         JOptionPane.showMessageDialog(this, "Failed to update grade.");
    //     }
    // }
    private void handleUpdateGrade() {
        if (gradesTable.isEditing()) {
            gradesTable.getCellEditor().stopCellEditing(); // 提交用户编辑的数据
        }
    
        int selectedRow = gradesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a grade to update.");
            return;
        }
    
        int studentId = Integer.parseInt(gradesTable.getValueAt(selectedRow, 0).toString());
        int courseId = Integer.parseInt(gradesTable.getValueAt(selectedRow, 1).toString());
        int gradeValue = Integer.parseInt(gradesTable.getValueAt(selectedRow, 2).toString());
        System.out.println("Student ID: " + gradesTable.getValueAt(selectedRow, 0));
        System.out.println("Course ID: " + gradesTable.getValueAt(selectedRow, 1));
        System.out.println("Grade Value: " + gradesTable.getValueAt(selectedRow, 2));
        Grade updatedGrade = new Grade(studentId, courseId, gradeValue);
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
        model.setRowCount(0);
        for (Grade grade : grades) {
            model.addRow(new Object[]{
                grade.getStudentId(),
                grade.getCourseId(),
                grade.getGrade()
            });
        }
    }

    private void handleBack() {
        new AdminLoginPage(); // 返回管理员主页
        dispose();
    }
}