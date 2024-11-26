package AdminCoursesManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class AdminCoursesManagementPage extends JFrame {
    private JButton backButton, exitButton, addCourseButton, deleteCourseButton, updateCourseButton, searchCourseButton, refreshButton;
    private JTextField searchCourseField;
    private JTable coursesTable;
    private JScrollPane coursesTableScrollPane;

    public AdminCoursesManagementPage() {
        setTitle("Admin Courses Management Page");
        setLayout(new BorderLayout());
        setAdminCoursesManagementPagePanel();
        setSize(900,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAdminCoursesManagementPagePanel() {
        AdminCoursesManagementEvents adminCoursesManagementEvents = new AdminCoursesManagementEvents(this);
    
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
        
        addCourseButton = new JButton("Add Course");
        addCourseButton.setFont(functionFont);
        addCourseButton.setPreferredSize(functionDimension);
        addCourseButton.addActionListener(adminCoursesManagementEvents);
        
        deleteCourseButton = new JButton("Delete Course");
        deleteCourseButton.setFont(functionFont);
        deleteCourseButton.setPreferredSize(functionDimension);
        deleteCourseButton.addActionListener(adminCoursesManagementEvents);
        
        updateCourseButton = new JButton("Update Course");
        updateCourseButton.setFont(functionFont);
        updateCourseButton.setPreferredSize(functionDimension);
        updateCourseButton.addActionListener(adminCoursesManagementEvents);
        
        searchCourseField = new JTextField(15);
        searchCourseField.setFont(functionFont);
        searchCourseField.setPreferredSize(functionDimension);
        searchCourseField.addActionListener(adminCoursesManagementEvents);

        searchCourseButton = new JButton("Search Course");
        searchCourseButton.setFont(functionFont);
        searchCourseButton.setPreferredSize(functionDimension);
        searchCourseButton.addActionListener(adminCoursesManagementEvents); 
        
        refreshButton = new JButton("Refresh");
        refreshButton.setFont(functionFont);
        refreshButton.setPreferredSize(functionDimension);
        refreshButton.addActionListener(adminCoursesManagementEvents);

        topPanel.add(addCourseButton);
        topPanel.add(deleteCourseButton);
        topPanel.add(Box.createRigidArea(functionDimension));
        topPanel.add(updateCourseButton);
        topPanel.add(Box.createRigidArea(functionDimension));
        topPanel.add(searchCourseField);
        topPanel.add(searchCourseButton);
        topPanel.add(Box.createRigidArea(functionDimension));
        topPanel.add(refreshButton);

        add(topPanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 10));
        
        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(adminCoursesManagementEvents);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(adminCoursesManagementEvents);
        
        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        String[] columnNames = {"Course ID", "CourseName", "Description", "Credits"};
        // Retrieve data from the "courses" database and display it.
        CoursesDatabase coursesDatabase = new CoursesDatabase();
        Object[][] data = coursesDatabase.getCoursesData();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        coursesTable = new JTable(model);
        coursesTable.getTableHeader().setFont(tableFont);
        coursesTable.setFont(tableFont);
        coursesTable.setRowHeight(30);
        coursesTableScrollPane = new JScrollPane(coursesTable);
        coursesTable.setFillsViewportHeight(true);

        add(coursesTableScrollPane, BorderLayout.CENTER);
    }

    public void updateTable() {
        // Retrieve all data from the "courses" database.
        Object[][] newData = new CoursesDatabase().getCoursesData();
        // Update the table with the new data.
        DefaultTableModel model = (DefaultTableModel) coursesTable.getModel();
        model.setDataVector(newData, new String[] {"Course ID", "CourseName", "Description", "Credits"});
    }

    public JTextField getSearchCourseField() {
        return searchCourseField;
    }

    public void updateSearchedData(Object[][] data) {
        // Retrieve data we need.
        DefaultTableModel model = (DefaultTableModel) coursesTable.getModel();
        model.setDataVector(data, new String[] {"Course ID", "CourseName", "Description", "Credits"});
    }

    public JTable getCoursesTable() {
        return coursesTable;
    }

}
