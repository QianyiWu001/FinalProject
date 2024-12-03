package view.Student;

import dao.AttendanceDAO;
import entity.Attendance;
import DatabaseUtilities.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentAttendancePage extends JFrame {
    private JTable attendanceTable;
    private JButton backButton, refreshButton;
    private AttendanceDAO attendanceDAO;

    public StudentAttendancePage() {
        attendanceDAO = new AttendanceDAO();
        setTitle("Student Attendance Page");
        setLayout(new BorderLayout());
        setSize(800, 550);
        setAttendancePagePanel();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setAttendancePagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        // table
        String[] columnNames = {"Enrollment ID", "Date", "Status"};
        attendanceTable = new JTable(new DefaultTableModel(new Object[0][0], columnNames));
        attendanceTable.getTableHeader().setFont(tableFont);
        attendanceTable.setFont(tableFont);
        attendanceTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        attendanceTable.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);

        // button
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
        int studentId = Session.getStudentId(); 
        List<Attendance> attendanceList = attendanceDAO.getAttendanceByStudentId(studentId);
        updateTableData(attendanceList);
    }

    private void updateTableData(List<Attendance> attendanceList) {
        DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
        model.setRowCount(0);
        for (Attendance attendance : attendanceList) {
            model.addRow(new Object[]{
                attendance.getEnrollmentId(),
                attendance.getDate(),
                attendance.getStatus()
            });
        }
    }

    private void handleBack() {
        new StudentLoginPage(); // 返回到学生主页面
        dispose();
    }
}