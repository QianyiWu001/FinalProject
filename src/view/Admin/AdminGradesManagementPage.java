package view.Admin;

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

    private void setAdminGradesManagementPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
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
        topPanel.add(updateGradeButton);
        topPanel.add(searchGradeField);
        topPanel.add(searchGradeButton);
        topPanel.add(refreshButton);

        add(topPanel, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(
            new Object[0][0],
            new String[]{"Enrollment ID", "Student ID", "Course ID", "Grade"}
        ) {
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

        add(gradesTableScrollPane, BorderLayout.CENTER);

        refreshTable();
    }

    void refreshTable() {
        List<Grade> grades = gradesController.getAllGrades();
        DefaultTableModel model = (DefaultTableModel) gradesTable.getModel();
        model.setRowCount(0); // 清空表格数据
        for (Grade grade : grades) {
            model.addRow(new Object[]{
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
        model.setRowCount(0);
        for (Grade grade : grades) {
            model.addRow(new Object[]{
                grade.getEnrollmentId(),
                grade.getStudentId(),
                grade.getCourseId(),
                grade.getGrade()
            });
        }
    }
}