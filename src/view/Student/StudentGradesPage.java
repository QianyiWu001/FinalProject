package view.Student;


import dao.GradesDAO;
import entity.Grade;
import DatabaseUtilities.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentGradesPage extends JFrame {
    private JButton backButton, refreshButton;
    private JTable gradesTable;
    private GradesDAO gradesDAO;

    public StudentGradesPage() {
        gradesDAO = new GradesDAO();
        setTitle("Student Grades Page");
        setLayout(new BorderLayout());
        setSize(800, 550);
        setGradesPagePanel();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setGradesPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        // Table
        String[] columnNames = {"Enrollment ID", "Course ID", "Grade"};
        gradesTable = new JTable(new DefaultTableModel(new Object[0][0], columnNames));
        gradesTable.getTableHeader().setFont(tableFont);
        gradesTable.setFont(tableFont);
        gradesTable.setRowHeight(30);

        JScrollPane tableScrollPane = new JScrollPane(gradesTable);
        gradesTable.setFillsViewportHeight(true);
        add(tableScrollPane, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 10));
        backButton = createButton("Back", buttonFont);
        backButton.addActionListener(e -> handleBack());
        refreshButton = createButton("Refresh", buttonFont);
        refreshButton.addActionListener(e -> refreshTable());

        bottomPanel.add(backButton);
        bottomPanel.add(refreshButton);

        add(bottomPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    private JButton createButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setPreferredSize(new Dimension(130, 30));
        return button;
    }

    private void refreshTable() {
        int studentId = Session.getStudentId(); // 从 Session 中获取当前登录学生的 ID
        List<Grade> grades = gradesDAO.getGradesByStudentId(studentId);
        updateTableData(grades);
    }

    private void updateTableData(List<Grade> grades) {
        DefaultTableModel model = (DefaultTableModel) gradesTable.getModel();
        model.setRowCount(0);
        for (Grade grade : grades) {
            model.addRow(new Object[]{
                grade.getEnrollmentId(),
                grade.getCourseId(),
                grade.getGrade()
            });
        }
    }

    private void handleBack() {
        new StudentLoginPage();
        dispose();
    }
}