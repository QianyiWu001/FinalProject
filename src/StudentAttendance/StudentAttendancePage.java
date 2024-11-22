package StudentAttendance;

import javax.swing.*;
import java.awt.*;
public class StudentAttendancePage extends JFrame {
    private JButton backButton, exitButton;
    private JLabel titleLabel;
    private JTable attendanceTable;
    private JScrollPane attendanceTableScrollPane;
    
    public StudentAttendancePage() {
        setTitle("Student Attendance Page");
        setLayout(new BorderLayout());
        setStudentAttendancePagePanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setStudentAttendancePagePanel() {
        StudentAttendanceEvents studentAttendanceEvents = new StudentAttendanceEvents(this);

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        titleLabel = new JLabel("Courses Attendance");
        titleLabel.setFont(titleFont);
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);
        

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 10));

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(studentAttendanceEvents);
        
        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(studentAttendanceEvents);

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        String[] columnNames = {"Course ID", "Course Name", "Attendance Percentage"};
        //这里要改成数据库
        Object[][] data = getTextData();

        attendanceTable = new JTable(data, columnNames);
        attendanceTable.getTableHeader().setFont(tableFont);
        attendanceTable.setFont(tableFont);
        attendanceTable.setRowHeight(30);
        attendanceTableScrollPane = new JScrollPane(attendanceTable);
        attendanceTable.setFillsViewportHeight(true);

        add(attendanceTableScrollPane, BorderLayout.CENTER);
    }
    // 试试
    private Object[][] getTextData() {
        return new Object[][] {
            {"CS101", "Introduction to Computer Science", "90%"},
            {"CS102", "Data Structures and Algorithms", "85%"}
        };
    }
}
