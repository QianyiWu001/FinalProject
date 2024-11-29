package view.Student;

import controller.EnrollmentController;
import entity.Course;
import DatabaseUtilities.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentCoursesListPage extends JFrame {
    private JTable coursesTable;
    private JButton backButton, refreshButton;
    private EnrollmentController enrollmentController;

    public StudentCoursesListPage() {
        enrollmentController = new EnrollmentController();
        setTitle("Student Courses List");
        setLayout(new BorderLayout());
        setSize(800, 550);
        setCoursesPagePanel();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setCoursesPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        // 表格
        String[] columnNames = {"Course ID", "Course Name", "Description", "Credits"};
        coursesTable = new JTable(new DefaultTableModel(new Object[0][0], columnNames));
        coursesTable.getTableHeader().setFont(tableFont);
        coursesTable.setFont(tableFont);
        coursesTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(coursesTable);
        coursesTable.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);

        // 底部按钮
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
        int studentId = Session.getStudentId(); // 从 Session 获取当前学生 ID
        List<Course> courses = enrollmentController.getCoursesByStudentId(studentId);
        updateTableData(courses);
    }

    private void updateTableData(List<Course> courses) {
        DefaultTableModel model = (DefaultTableModel) coursesTable.getModel();
        model.setRowCount(0);
        for (Course course : courses) {
            model.addRow(new Object[]{
                course.getCourseId(),
                course.getCourseName(),
                course.getDescription(),
                course.getCredits()
            });
        }
    }

    private void handleBack() {
        new StudentLoginPage();
        dispose();
    }
}