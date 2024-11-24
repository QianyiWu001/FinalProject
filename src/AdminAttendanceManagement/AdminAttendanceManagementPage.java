package AdminAttendanceManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class AdminAttendanceManagementPage extends JFrame {
    private JButton backButton, exitButton, addAttendanceButton, deleteAttendanceButton, updateAttendanceButton, searchAttendanceButton;
    private JTable attendanceTable;
    private JScrollPane attendanceTableScrollPane;

    public AdminAttendanceManagementPage() {
        setTitle("Admin Attendance Management Page");
        setLayout(new BorderLayout());
        setAdminAttendanceManagementPagePanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAdminAttendanceManagementPagePanel() {
        AdminAttendanceManagementEvents adminAttendanceManagementEvents = new AdminAttendanceManagementEvents(this);

        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 50));
        
        addAttendanceButton = new JButton("Add Attendance");
        addAttendanceButton.setFont(buttonFont);
        addAttendanceButton.setPreferredSize(new Dimension(190, 30));
        addAttendanceButton.addActionListener(adminAttendanceManagementEvents);
        
        deleteAttendanceButton = new JButton("Delete Attendance");
        deleteAttendanceButton.setFont(buttonFont);
        deleteAttendanceButton.setPreferredSize(new Dimension(190, 30));
        deleteAttendanceButton.addActionListener(adminAttendanceManagementEvents);
        
        updateAttendanceButton = new JButton("Update Attendance");
        updateAttendanceButton.setFont(buttonFont);
        updateAttendanceButton.setPreferredSize(new Dimension(190, 30));
        updateAttendanceButton.addActionListener(adminAttendanceManagementEvents);
        
        searchAttendanceButton = new JButton("Search Attendance");
        searchAttendanceButton.setFont(buttonFont);
        searchAttendanceButton.setPreferredSize(new Dimension(190, 30));
        searchAttendanceButton.addActionListener(adminAttendanceManagementEvents); 

        topPanel.add(addAttendanceButton);
        topPanel.add(deleteAttendanceButton);
        topPanel.add(updateAttendanceButton);
        topPanel.add(searchAttendanceButton);

        add(topPanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 50));
        
        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(adminAttendanceManagementEvents);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(adminAttendanceManagementEvents);

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        String[] columnNames = {"Student ID", "Course ID", "Date", "Status"};
        // Retrieve data from "Attendance" database and display it.
        AttendanceDatabase attendanceDatabase = new AttendanceDatabase();
        Object[][] data = attendanceDatabase.getAttendanceData();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        attendanceTable = new JTable(model);
        attendanceTable.getTableHeader().setFont(tableFont);
        attendanceTable.setFont(tableFont);
        attendanceTable.setRowHeight(30);
        attendanceTableScrollPane = new JScrollPane(attendanceTable);
        attendanceTable.setFillsViewportHeight(true);

        add(attendanceTableScrollPane, BorderLayout.CENTER);        
    }

    public void updateTable() {
        Object[][] newData = new AttendanceDatabase().getAttendanceData();
        DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
        model.setDataVector(newData, new String[] {"Student ID", "Course ID", "Date", "Status"});
    }
}
