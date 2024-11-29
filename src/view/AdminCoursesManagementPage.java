package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.CourseController;
import entity.Course;

import java.awt.*;
import java.util.List;

public class AdminCoursesManagementPage extends JFrame {
    private JButton backButton, exitButton, addCourseButton, deleteCourseButton, updateCourseButton, searchCourseButton, refreshButton;
    private JTextField searchCourseField;
    private JTable coursesTable;
    private JScrollPane coursesTableScrollPane;
    private CourseController courseController;

    public AdminCoursesManagementPage() {
        courseController = new CourseController(); // 初始化控制器
        setTitle("Admin Courses Management Page");
        setLayout(new BorderLayout());
        setAdminCoursesManagementPagePanel();
        setSize(800, 550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unused")
    private void setAdminCoursesManagementPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));

        addCourseButton = new JButton("Add Course");
        addCourseButton.setFont(functionFont);
        addCourseButton.setPreferredSize(functionDimension);
        addCourseButton.addActionListener(e -> handleAddCourse());

        deleteCourseButton = new JButton("Delete Course");
        deleteCourseButton.setFont(functionFont);
        deleteCourseButton.setPreferredSize(functionDimension);
        deleteCourseButton.addActionListener(e -> handleDeleteCourse());

        updateCourseButton = new JButton("Update Course");
        updateCourseButton.setFont(functionFont);
        updateCourseButton.setPreferredSize(functionDimension);
        updateCourseButton.addActionListener(e -> handleUpdateCourse());

        searchCourseButton = new JButton("Search Course");
        searchCourseButton.setFont(functionFont);
        searchCourseButton.setPreferredSize(functionDimension);
        searchCourseButton.addActionListener(e -> handleSearchCourse());

        searchCourseField = new JTextField(15);
        searchCourseField.setFont(functionFont);
        searchCourseField.setPreferredSize(functionDimension);

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(functionFont);
        refreshButton.setPreferredSize(functionDimension);
        refreshButton.addActionListener(e -> refreshTable());

        topPanel.add(addCourseButton);
        topPanel.add(deleteCourseButton);
        topPanel.add(updateCourseButton);
        topPanel.add(searchCourseField);
        topPanel.add(searchCourseButton);
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

        DefaultTableModel model = new DefaultTableModel(new Object[0][0], new String[]{"Course ID", "Course Name", "Description", "Credits"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // 所有列可编辑
            }
        };

        coursesTable = new JTable(model);
        coursesTable.getTableHeader().setFont(tableFont);
        coursesTable.setFont(tableFont);
        coursesTable.setRowHeight(30);

        coursesTableScrollPane = new JScrollPane(coursesTable);
        coursesTable.setFillsViewportHeight(true);

        add(coursesTableScrollPane, BorderLayout.CENTER);

        refreshTable();
    }

    void refreshTable() {
        List<Course> courses = courseController.getAllCourses();
        DefaultTableModel model = (DefaultTableModel) coursesTable.getModel();
        model.setRowCount(0); // 清空表格数据
        for (Course course : courses) {
            model.addRow(new Object[]{
                course.getCourseId(),
                course.getcourse_name(),
                course.getDescription(),
                course.getCredits()
            });
        }
    }

    private void handleAddCourse() {
        new AddCoursePage(this, courseController);
    }

    private void handleDeleteCourse() {
        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to delete.");
            return;
        }
        int courseId = (int) coursesTable.getValueAt(selectedRow, 0);
        if (courseController.deleteCourse(courseId)) {
            JOptionPane.showMessageDialog(this, "Course deleted successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete course.");
        }
    }

    private void handleUpdateCourse() {
        if (coursesTable.isEditing()) {
            coursesTable.getCellEditor().stopCellEditing(); // 提交用户编辑的数据
        }

        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to update.");
            return;
        }

        int courseId = Integer.parseInt(coursesTable.getValueAt(selectedRow, 0).toString());
        String courseName = coursesTable.getValueAt(selectedRow, 1).toString();
        String description = coursesTable.getValueAt(selectedRow, 2).toString();
        int credits = Integer.parseInt(coursesTable.getValueAt(selectedRow, 3).toString());

        Course updatedCourse = new Course();
        updatedCourse.setCourseId(courseId);
        updatedCourse.setcourse_name(courseName);
        updatedCourse.setDescription(description);
        updatedCourse.setCredits(credits);

        if (courseController.updateCourse(updatedCourse)) {
            JOptionPane.showMessageDialog(this, "Course updated successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update course.");
        }
    }

    private void handleSearchCourse() {
        String query = searchCourseField.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search query.");
            return;
        }
        List<Course> courses = courseController.searchCourses(query);
        if (courses.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No course found.");
        } else {
            updateTableData(courses);
        }
    }

    private void updateTableData(List<Course> courses) {
        DefaultTableModel model = (DefaultTableModel) coursesTable.getModel();
        model.setRowCount(0);
        for (Course course : courses) {
            model.addRow(new Object[]{
                course.getCourseId(),
                course.getcourse_name(),
                course.getDescription(),
                course.getCredits()
            });
        }
    }

    private void handleBack() {
        new AdminLoginPage(); // 返回管理员主页
        dispose();
    }
}