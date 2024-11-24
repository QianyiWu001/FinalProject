package AdminGradesManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class AdminGradesManagementPage extends JFrame {
    private JButton backButton, exitButton, addGradeButton, deleteGradeButton, updateGradeButton, searchGradeButton;
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

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 50));
        
        addGradeButton = new JButton("Add Grade");
        addGradeButton.setFont(buttonFont);
        addGradeButton.setPreferredSize(new Dimension(190, 30));
        addGradeButton.addActionListener(adminGradesManagementEvents);
        
        deleteGradeButton = new JButton("Delete Grade");
        deleteGradeButton.setFont(buttonFont);
        deleteGradeButton.setPreferredSize(new Dimension(190, 30));
        deleteGradeButton.addActionListener(adminGradesManagementEvents);
        
        updateGradeButton = new JButton("Update Grade");
        updateGradeButton.setFont(buttonFont);
        updateGradeButton.setPreferredSize(new Dimension(190, 30));
        updateGradeButton.addActionListener(adminGradesManagementEvents);
        
        searchGradeButton = new JButton("Search Grade");
        searchGradeButton.setFont(buttonFont);
        searchGradeButton.setPreferredSize(new Dimension(190, 30));
        searchGradeButton.addActionListener(adminGradesManagementEvents); 

        topPanel.add(addGradeButton);
        topPanel.add(deleteGradeButton);
        topPanel.add(updateGradeButton);
        topPanel.add(searchGradeButton);

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
        Object[][] newData = new GradesDatabase().getGradesData(); 
        DefaultTableModel model = (DefaultTableModel) gradesTable.getModel();
        model.setDataVector(newData, new String[] {"Student ID", "Course ID", "Grade"});
    }
}
