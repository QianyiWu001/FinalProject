package StudentGrades;

import javax.swing.*;

import DatabaseUtilities.Session;

import java.awt.*;

public class StudentGradesPage extends JFrame {
    private JButton backButton, exitButton;
    private JLabel titleLabel;
    private JTable gradesTable;
    private JScrollPane gradesTableScrollPane;

    public StudentGradesPage() {
        setTitle("Student Grades Page");
        setLayout(new BorderLayout());
        setStudentGradesPagePanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setStudentGradesPagePanel() {
        StudentGradesEvents studentGradesEvents = new StudentGradesEvents(this);

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        titleLabel = new JLabel("Course Grades");
        titleLabel.setFont(titleFont);
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 10));

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(studentGradesEvents);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(studentGradesEvents);

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        String[] columnNames = {"Student ID", "Course ID", "Course Name", "Grade"};
        Object[][] data = getGrades();

        gradesTable = new JTable(data, columnNames);
        gradesTable.getTableHeader().setFont(tableFont);
        gradesTable.setFont(tableFont);
        gradesTable.setRowHeight(30);
        gradesTableScrollPane = new JScrollPane(gradesTable);
        gradesTable.setFillsViewportHeight(true);

        add(gradesTableScrollPane, BorderLayout.CENTER);
    }

    private Object[][] getGrades() {
        int studentID = Session.getStudentID();
        StudentGradesDB studentGradesDB = new StudentGradesDB();
        return studentGradesDB.getGradesByStudentId(studentID);
    }
}
