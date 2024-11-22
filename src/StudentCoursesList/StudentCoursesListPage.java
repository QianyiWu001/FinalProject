package StudentCoursesList;

import javax.swing.*;
import java.awt.*;


public class StudentCoursesListPage extends JFrame {
    private JButton backButton, exitButton;
    private JLabel titleLabel;
    private JTable coursesTable;
    private JScrollPane coursesTableScrollPane;

    public StudentCoursesListPage() {
        setTitle("Student Courses List Page");
        setLayout(new BorderLayout());
        setStudentCoursesListPanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setStudentCoursesListPanel() {
        StudentCoursesListEvents studentCoursesListEvents = new StudentCoursesListEvents(this);

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        titleLabel = new JLabel("Course Registration information");
        titleLabel.setFont(titleFont);
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 10));
        
        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(studentCoursesListEvents);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(studentCoursesListEvents);

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        
        
        String[] columnNames = {"Course ID", "CourseName", "Credits", "Description"};
        //这里要改成数据库
        Object[][] data = getTextData();

        coursesTable = new JTable(data, columnNames);
        coursesTable.getTableHeader().setFont(tableFont);
        coursesTable.setFont(tableFont);
        coursesTable.setRowHeight(30);
        coursesTableScrollPane = new JScrollPane(coursesTable);
        coursesTable.setFillsViewportHeight(true);

        add(coursesTableScrollPane, BorderLayout.CENTER);
    }

    private Object[][] getTextData() {
        return new Object[][] {
            {"11111", "CS01", "2", "aaaaaaaaaaaa"},
            {"22222", "CS02", "4", "aaaaaaaaaaaaaaaaaaaaaaaa"}
        };
    }
}
