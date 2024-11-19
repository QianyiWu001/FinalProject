package AdminStudentsManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class AdminStudentsManagementPage extends JFrame {
    private JButton backButton, exitButton, addStudentButton, deleteStudentButton, updateStudentButton, searchStudentButton;
    private JLabel titleLabel;
    private JTable studentsTable;
    private JScrollPane stduentTableScrollPane;

    public AdminStudentsManagementPage() {
        setTitle("Admin Students Management Page");
        setLayout(new GridBagLayout());
        setAdminStudentsManagementPagePanel();
        setSize(1500,900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAdminStudentsManagementPagePanel() {
        AdminStudentsManagementEvents adminStudentsManagementEvents = new AdminStudentsManagementEvents(this);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(110, 30));
        backButton.addActionListener(adminStudentsManagementEvents);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(50, 50, 30, 0);
        add(backButton, gbc);

        titleLabel = new JLabel("Manage Students");
        titleLabel.setFont(titleFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(titleLabel, gbc);

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(110, 30));
        exitButton.addActionListener(adminStudentsManagementEvents);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(50, 0, 30, 50);
        add(exitButton, gbc);

        String[] columnNames = {"Student ID", "Name", "Email", "Phone"};
        //这里要改成数据库
        Object[][] data = getTextData();
        

        studentsTable = new JTable(new DefaultTableModel(data, columnNames));
        stduentTableScrollPane = new JScrollPane(studentsTable);
        studentsTable.setFillsViewportHeight(true);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 50);
        gbc.fill = GridBagConstraints.BOTH;
        add(stduentTableScrollPane, gbc);

        addStudentButton = new JButton("Add Student");
        addStudentButton.setFont(buttonFont);
        addStudentButton.setPreferredSize(new Dimension(190, 30));
        addStudentButton.addActionListener(adminStudentsManagementEvents);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 50, 0, 20);
        add(addStudentButton, gbc);

        deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.setFont(buttonFont);
        deleteStudentButton.setPreferredSize(new Dimension(190, 30));
        deleteStudentButton.addActionListener(adminStudentsManagementEvents);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 0, 20);
        add(deleteStudentButton, gbc);

        updateStudentButton = new JButton("Update Student");
        updateStudentButton.setFont(buttonFont);
        updateStudentButton.setPreferredSize(new Dimension(190, 30));
        updateStudentButton.addActionListener(adminStudentsManagementEvents);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 0, 20);
        add(updateStudentButton, gbc);

        searchStudentButton = new JButton("Search Student");
        searchStudentButton.setFont(buttonFont);
        searchStudentButton.setPreferredSize(new Dimension(190, 30));
        searchStudentButton.addActionListener(adminStudentsManagementEvents);
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 0, 20);
        add(searchStudentButton, gbc);
    }
    //这里要改成数据库
    private Object[][] getTextData() {
        return new Object[][] {
            {"11111", "Alex", "@gmail.com", "1234567890"},
            {"22222", "Brian", "@yahoo.com", "9876543210"}
        };
    }
}
