package AdminAttendanceManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class AdminAttendanceManagementPage extends JFrame {
    private JButton backButton, exitButton, addAttendanceButton, deleteAttendanceButton, updateAttendanceButton, searchAttendanceButton, refreshButton;
    private JTextField searchStudentIDField, searchCourseIDField, searchDateField;
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
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 50));
        
        addAttendanceButton = new JButton("Add Attendance");
        addAttendanceButton.setFont(functionFont);
        addAttendanceButton.setPreferredSize(functionDimension);
        addAttendanceButton.addActionListener(adminAttendanceManagementEvents);
        
        deleteAttendanceButton = new JButton("Delete Attendance");
        deleteAttendanceButton.setFont(functionFont);
        deleteAttendanceButton.setPreferredSize(functionDimension);
        deleteAttendanceButton.addActionListener(adminAttendanceManagementEvents);
        
        updateAttendanceButton = new JButton("Update Attendance");
        updateAttendanceButton.setFont(functionFont);
        updateAttendanceButton.setPreferredSize(functionDimension);
        updateAttendanceButton.addActionListener(adminAttendanceManagementEvents);
        
        searchAttendanceButton = new JButton("Search Attendance");
        searchAttendanceButton.setFont(functionFont);
        searchAttendanceButton.setPreferredSize(functionDimension);
        searchAttendanceButton.addActionListener(adminAttendanceManagementEvents); 

        searchStudentIDField = new JTextField(10);
        searchStudentIDField.setFont(functionFont);
        searchStudentIDField.addActionListener(adminAttendanceManagementEvents);

        searchCourseIDField = new JTextField(10);
        searchCourseIDField.setFont(functionFont);
        searchCourseIDField.addActionListener(adminAttendanceManagementEvents);

        searchDateField = new JTextField(10);
        searchDateField.setFont(functionFont);
        searchDateField.addActionListener(adminAttendanceManagementEvents);

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(functionFont);
        refreshButton.setPreferredSize(functionDimension);
        refreshButton.addActionListener(adminAttendanceManagementEvents);

        topPanel.add(addAttendanceButton);
        topPanel.add(deleteAttendanceButton);
        topPanel.add(updateAttendanceButton);
        topPanel.add(searchAttendanceButton);
        topPanel.add(searchStudentIDField);
        topPanel.add(searchCourseIDField);
        topPanel.add(searchDateField);
        topPanel.add(refreshButton);

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
        // Retrieve all data from "Attendance" database.
        Object[][] newData = new AttendanceDatabase().getAttendanceData();
        // Update.
        DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
        model.setDataVector(newData, new String[] {"Student ID", "Course ID", "Date", "Status"});
    }
    public JTextField getSearchStudentIDField() {
        return searchStudentIDField;
    }

    public JTextField getSearchCourseIDField() {
        return searchCourseIDField;
    }

    public JTextField getSearchDateField() {
        return searchDateField;
    }

    public void updateSearchedData(Object[][] data) {
        // Retrieve data we need.
        DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
        model.setDataVector(data, new String[] {"Student ID", "Course ID", "Date", "Status"});
    }

    public JTable getAttendanceTable() {
        return attendanceTable;
    }

}
