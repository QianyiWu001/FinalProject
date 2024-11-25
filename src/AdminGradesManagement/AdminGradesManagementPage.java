package AdminGradesManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class AdminGradesManagementPage extends JFrame {
    private JButton backButton, exitButton, addGradeButton, deleteGradeButton, updateGradeButton, searchGradeButton, refreshButton;
    private JTextField searchGradeByStudentIDField, searchGradeByCourseIDField;
    private JTable gradesTable;
    private JScrollPane gradesTableScrollPane;

    public AdminGradesManagementPage() {
        setTitle("Admin Grades Management Page");
        setLayout(new BorderLayout());
        setAdminGradesManagementPagePanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAdminGradesManagementPagePanel() {
        AdminGradesManagementEvents adminGradesManagementEvents = new AdminGradesManagementEvents(this);

        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 50));
        
        addGradeButton = new JButton("Add Grade");
        addGradeButton.setFont(functionFont);
        addGradeButton.setPreferredSize(functionDimension);
        addGradeButton.addActionListener(adminGradesManagementEvents);
        
        deleteGradeButton = new JButton("Delete Grade");
        deleteGradeButton.setFont(functionFont);
        deleteGradeButton.setPreferredSize(functionDimension);
        deleteGradeButton.addActionListener(adminGradesManagementEvents);
        
        updateGradeButton = new JButton("Update Grade");
        updateGradeButton.setFont(functionFont);
        updateGradeButton.setPreferredSize(functionDimension);
        updateGradeButton.addActionListener(adminGradesManagementEvents);
        
        searchGradeButton = new JButton("Search Grade");
        searchGradeButton.setFont(functionFont);
        searchGradeButton.setPreferredSize(functionDimension);
        searchGradeButton.addActionListener(adminGradesManagementEvents); 

        searchGradeByStudentIDField = new JTextField(10);
        searchGradeByStudentIDField.setFont(functionFont);
        searchGradeByStudentIDField.addActionListener(adminGradesManagementEvents);

        searchGradeByCourseIDField = new JTextField(10);
        searchGradeByCourseIDField.setFont(functionFont);
        searchGradeByCourseIDField.addActionListener(adminGradesManagementEvents);

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(functionFont);
        refreshButton.setPreferredSize(functionDimension);
        refreshButton.addActionListener(adminGradesManagementEvents);

        topPanel.add(addGradeButton);
        topPanel.add(deleteGradeButton);
        topPanel.add(updateGradeButton);
        topPanel.add(searchGradeButton);
        topPanel.add(searchGradeByStudentIDField);
        topPanel.add(searchGradeByCourseIDField);
        topPanel.add(refreshButton);

        add(topPanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 50));

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(adminGradesManagementEvents);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(adminGradesManagementEvents);

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        String[] columnNames = {"Student ID", "Course ID", "Grade"};
        // Retrieve data from the "grades" database and display it.
        GradesDatabase gradesDatabase = new GradesDatabase();
        Object[][] data = gradesDatabase.getGradesData();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        gradesTable = new JTable(model);
        gradesTable.getTableHeader().setFont(tableFont);
        gradesTable.setFont(tableFont);
        gradesTable.setRowHeight(30);
        gradesTableScrollPane = new JScrollPane(gradesTable);
        gradesTable.setFillsViewportHeight(true);

        add(gradesTableScrollPane, BorderLayout.CENTER);
    }
    public void updateTable() {
        // Retrieve all data from the "grades" database.
        Object[][] newData = new GradesDatabase().getGradesData(); 
        // Update the table with the new data.
        DefaultTableModel model = (DefaultTableModel) gradesTable.getModel();
        model.setDataVector(newData, new String[] {"Student ID", "Course ID", "Grade"});
    }

    public JTextField getSearchGradeByStudentIDField() {
        return searchGradeByStudentIDField;
    }

    public JTextField getSearchGradeByCourseIDField() {
        return searchGradeByCourseIDField;
    }

    public void updateSearchedData(Object[][] data) {
        // Retrieve data we need.
        DefaultTableModel model = (DefaultTableModel) gradesTable.getModel(); 
        model.setDataVector(data, new String[] {"Student ID", "Course ID", "Grade"});
    }

    public JTable getGradesTable() {
        return gradesTable;
    }
}
