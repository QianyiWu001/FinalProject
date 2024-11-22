package AdminCoursesManagement;

import javax.swing.*;
import java.awt.*;

public class AdminCoursesManagementPage extends JFrame {
    private JButton backButton, exitButton, addCourseButton, deleteCourseButton, updateCourseButton, searchCourseButton;
    private JTable coursesTable;
    private JScrollPane coursesTableScrollPane;

    public AdminCoursesManagementPage() {
        setTitle("Admin Courses Management Page");
        setLayout(new BorderLayout());
        setAdminCoursesManagementPagePanel();
        setSize(800,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAdminCoursesManagementPagePanel() {
        AdminCoursesManagementEvents adminCoursesManagementEvents = new AdminCoursesManagementEvents(this);
    
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
        addCourseButton = new JButton("Add Course");
        addCourseButton.setFont(buttonFont);
        addCourseButton.setPreferredSize(new Dimension(190, 30));
        addCourseButton.addActionListener(adminCoursesManagementEvents);
        
        deleteCourseButton = new JButton("Delete Course");
        deleteCourseButton.setFont(buttonFont);
        deleteCourseButton.setPreferredSize(new Dimension(190, 30));
        deleteCourseButton.addActionListener(adminCoursesManagementEvents);
        
        updateCourseButton = new JButton("Update Course");
        updateCourseButton.setFont(buttonFont);
        updateCourseButton.setPreferredSize(new Dimension(190, 30));
        updateCourseButton.addActionListener(adminCoursesManagementEvents);
        
        searchCourseButton = new JButton("Search Course");
        searchCourseButton.setFont(buttonFont);
        searchCourseButton.setPreferredSize(new Dimension(190, 30));
        searchCourseButton.addActionListener(adminCoursesManagementEvents); 

        topPanel.add(addCourseButton);
        topPanel.add(deleteCourseButton);
        topPanel.add(updateCourseButton);
        topPanel.add(searchCourseButton);

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
        
        
        

        
        String[] columnNames = {"Course ID", "CourseName", "Credits", "Description"};
        //这里要改成数据库
        Object[][] data = getTextData();

        coursesTable = new JTable(data, columnNames);
        coursesTable.getTableHeader().setFont(tableFont);
        coursesTable.setFont(tableFont);
        coursesTable.setRowHeight(30);
        coursesTableScrollPane = new JScrollPane(coursesTable);
        coursesTable.setFillsViewportHeight(true);

        add(coursesTableScrollPane, BorderLayout.CENTER);
    }

    private Object[][] getTextData() {
        return new Object[][] {
            {"11111", "CS01", "2", "aaaaaaaaaaaa"},
            {"22222", "CS02", "4", "aaaaaaaaaaaaaaaaaaaaaaaa"}
        };
    }
}
