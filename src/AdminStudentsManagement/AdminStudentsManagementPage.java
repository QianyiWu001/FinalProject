package AdminStudentsManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class AdminStudentsManagementPage extends JFrame {
    private JButton backButton, exitButton, addStudentButton, deleteStudentButton, updateStudentButton, searchStudentButton, refreshButton;
    private JTextField searchStudentField;
    private JTable studentsTable;
    private JScrollPane studentsTableScrollPane;

    public AdminStudentsManagementPage() {
        setTitle("Admin Students Management Page");
        setLayout(new BorderLayout());
        setAdminStudentsManagementPagePanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAdminStudentsManagementPagePanel() {
        AdminStudentsManagementEvents adminStudentsManagementEvents = new AdminStudentsManagementEvents(this);

        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));

        addStudentButton = new JButton("Add Student");
        addStudentButton.setFont(functionFont);
        addStudentButton.setPreferredSize(functionDimension);
        addStudentButton.addActionListener(adminStudentsManagementEvents);
        

        deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.setFont(functionFont);
        deleteStudentButton.setPreferredSize(functionDimension);
        deleteStudentButton.addActionListener(adminStudentsManagementEvents);
        

        updateStudentButton = new JButton("Update Student");
        updateStudentButton.setFont(functionFont);
        updateStudentButton.setPreferredSize(functionDimension);
        updateStudentButton.addActionListener(adminStudentsManagementEvents);
        
        searchStudentButton = new JButton("Search Student");
        searchStudentButton.setFont(functionFont);
        searchStudentButton.setPreferredSize(functionDimension);
        searchStudentButton.addActionListener(adminStudentsManagementEvents);

        searchStudentField = new JTextField(10);
        searchStudentField.setFont(functionFont);
        searchStudentField.addActionListener(adminStudentsManagementEvents);

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(functionFont);
        refreshButton.setPreferredSize(functionDimension);
        refreshButton.addActionListener(adminStudentsManagementEvents);
        
        
        topPanel.add(addStudentButton);
        topPanel.add(deleteStudentButton);
        topPanel.add(updateStudentButton);
        topPanel.add(searchStudentButton);
        topPanel.add(searchStudentField);
        topPanel.add(refreshButton);
        
        add(topPanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 10));

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(adminStudentsManagementEvents);


        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(adminStudentsManagementEvents);

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);
        
        String[] columnNames = {"Student ID", "Name", "Email", "Phone", "Address"};
        // Retrieve data from the "students" database and display it.
        StudentsDatabase studentsDatabase = new StudentsDatabase();
        Object[][] data = studentsDatabase.getStudentsData();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        studentsTable = new JTable(model);
        studentsTable.getTableHeader().setFont(tableFont);
        studentsTable.setFont(tableFont);
        studentsTable.setRowHeight(30);
        studentsTableScrollPane = new JScrollPane(studentsTable);
        studentsTable.setFillsViewportHeight(true);

        add(studentsTableScrollPane, BorderLayout.CENTER);
    }

    public void updateTable() {
        // Retrieve all data from the "students" database.
        Object[][] newData = new StudentsDatabase().getStudentsData();
        // Update.
        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
        model.setDataVector(newData, new String[] {"Student ID", "Name", "Email", "Phone", "Address"});
    }

    public JTextField getSearchStudentField() {
        return searchStudentField;
    }

    public void updateSearchedData(Object[][] data) {
        // Retrieve data we need.
        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
        model.setDataVector(data, new String[] {"Student ID", "Name", "Email", "Phone", "Address"});
    }

    public JTable getStudentsTable() {
        return studentsTable;
    }

}
