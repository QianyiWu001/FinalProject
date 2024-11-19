package StudentGrades;

import javax.swing.*;
import java.awt.*;

public class StudentGradesPage extends JFrame {
    private JButton backButton, exitButton;
    private JLabel titleLabel;
    private JTable gradesTable;
    private JScrollPane gradesTableScrollPane;

    public StudentGradesPage() {
        setTitle("Student Grades Page");
        setLayout(new GridBagLayout());
        setStudentGradesPagePanel();
        setSize(1500,900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setStudentGradesPagePanel() {
        StudentGradesEvents studentGradesEvents = new StudentGradesEvents(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(studentGradesEvents);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(backButton, gbc);

        titleLabel = new JLabel("Course Grades");
        titleLabel.setFont(titleFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(titleLabel, gbc);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(studentGradesEvents);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(exitButton, gbc);

        String[] columnNames = {"Course ID", "Course Name", "Grade"};
        //这里要改成数据库
        Object[][] data = getTextData();

        gradesTable = new JTable(data, columnNames);
        gradesTableScrollPane = new JScrollPane(gradesTable);
        gradesTable.setFillsViewportHeight(true);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 50);
        gbc.fill = GridBagConstraints.BOTH;
        add(gradesTableScrollPane, gbc);
    }

    private Object[][] getTextData() {
        return new Object[][]{
            {"CS101", "Introduction to Computer Science", "90.0"},
            {"CS102", "Data Structures and Algorithms", "60.0"}
        };
    }
}
