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
        setLayout(new GridBagLayout());
        setStudentCoursesListPanel();
        setSize(1500,900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setStudentCoursesListPanel() {
        StudentCoursesListEvents studentCoursesListEvents = new StudentCoursesListEvents(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        //Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(studentCoursesListEvents);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(backButton, gbc);

        titleLabel = new JLabel("Course Registration information");
        titleLabel.setFont(titleFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(titleLabel, gbc);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(studentCoursesListEvents);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(exitButton, gbc);

        String[] columnNames = {"Course ID", "CourseName", "Credits", "Description"};
        //这里要改成数据库
        Object[][] data = getTextData();

        coursesTable = new JTable(data, columnNames);
        coursesTableScrollPane = new JScrollPane(coursesTable);
        coursesTable.setFillsViewportHeight(true);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 50);
        gbc.fill = GridBagConstraints.BOTH;
        add(coursesTableScrollPane, gbc);
    }

    private Object[][] getTextData() {
        return new Object[][] {
            {"11111", "CS01", "2", "aaaaaaaaaaaa"},
            {"22222", "CS02", "4", "aaaaaaaaaaaaaaaaaaaaaaaa"}
        };
    }
}
