package AdminStudentsManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class AdminStudentsManagementPage extends JFrame {
    private JButton backButton, exitButton, addStudentButton, deleteStudentButton, updateStudentButton, searchStudentButton;
    private JTable studentsTable;
    private JScrollPane stduentsTableScrollPane;

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

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));

        addStudentButton = new JButton("Add Student");
        addStudentButton.setFont(buttonFont);
        addStudentButton.setPreferredSize(new Dimension(190, 30));
        addStudentButton.addActionListener(adminStudentsManagementEvents);
        

        deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.setFont(buttonFont);
        deleteStudentButton.setPreferredSize(new Dimension(190, 30));
        deleteStudentButton.addActionListener(adminStudentsManagementEvents);
        

        updateStudentButton = new JButton("Update Student");
        updateStudentButton.setFont(buttonFont);
        updateStudentButton.setPreferredSize(new Dimension(190, 30));
        updateStudentButton.addActionListener(adminStudentsManagementEvents);
        
        searchStudentButton = new JButton("Search Student");
        searchStudentButton.setFont(buttonFont);
        searchStudentButton.setPreferredSize(new Dimension(190, 30));
        searchStudentButton.addActionListener(adminStudentsManagementEvents);
        
        topPanel.add(addStudentButton);
        topPanel.add(deleteStudentButton);
        topPanel.add(updateStudentButton);
        topPanel.add(searchStudentButton);
        
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
        
        String[] columnNames = {"Student ID", "Name", "Email", "Phone"};
        //这里要改成数据库
        Object[][] data = getTextData();
        

        studentsTable = new JTable(new DefaultTableModel(data, columnNames));
        studentsTable.getTableHeader().setFont(tableFont);
        studentsTable.setFont(tableFont);
        studentsTable.setRowHeight(30);
        stduentsTableScrollPane = new JScrollPane(studentsTable);
        studentsTable.setFillsViewportHeight(true);

        add(stduentsTableScrollPane, BorderLayout.CENTER);
    }
    //这里要改成数据库
    private Object[][] getTextData() {
        return new Object[][] {
            {"11111", "Alex", "@gmail.com", "1234567890"},
            {"22222", "Brian", "@yahoo.com", "9876543210"}
        };
    }
}
