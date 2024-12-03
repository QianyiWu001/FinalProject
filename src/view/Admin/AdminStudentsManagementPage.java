package view.Admin; 

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


import controller.StudentController; 
import entity.Student; 



public class AdminStudentsManagementPage extends JFrame {
    private JButton backButton, exitButton, addStudentButton, deleteStudentButton, updateStudentButton, searchStudentButton, refreshButton;
    private JTextField searchStudentField;
    private JTable studentsTable;
    private JScrollPane studentsTableScrollPane;
    private StudentController studentController;

    public AdminStudentsManagementPage() {
        studentController = new StudentController(); 
        setTitle("Admin Students Management Page");
        setLayout(new BorderLayout());
        setAdminStudentsManagementPagePanel();
        setSize(900, 550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unused")
   private void setAdminStudentsManagementPagePanel() {
    Font tableFont = new Font("Arial", Font.PLAIN, 16);
    Font functionFont = new Font("Arial", Font.PLAIN, 13);
    Dimension functionDimension = new Dimension(130, 30);
 Font buttonFont = new Font("Arial", Font.PLAIN, 18);
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));

    addStudentButton = new JButton("Add Student");
    addStudentButton.setFont(functionFont);
    addStudentButton.setPreferredSize(functionDimension);
    addStudentButton.addActionListener(e -> handleAddStudent());

    deleteStudentButton = new JButton("Delete Student");
    deleteStudentButton.setFont(functionFont);
    deleteStudentButton.setPreferredSize(functionDimension);
    deleteStudentButton.addActionListener(e -> handleDeleteStudent());

    updateStudentButton = new JButton("Update Student");
    updateStudentButton.setFont(functionFont);
    updateStudentButton.setPreferredSize(functionDimension);
    updateStudentButton.addActionListener(e -> handleUpdateStudent());

    searchStudentField = new JTextField(15);
    searchStudentField.setFont(functionFont);
    searchStudentField.setPreferredSize(functionDimension);

    searchStudentButton = new JButton("Search Student");
    searchStudentButton.setFont(functionFont);
    searchStudentButton.setPreferredSize(functionDimension);
    searchStudentButton.addActionListener(e -> handleSearchStudent());

    refreshButton = new JButton("Refresh");
    refreshButton.setFont(functionFont);
    refreshButton.setPreferredSize(functionDimension);
    refreshButton.addActionListener(e -> refreshTable());

    topPanel.add(addStudentButton);
    topPanel.add(deleteStudentButton);
    topPanel.add(updateStudentButton);
    topPanel.add(searchStudentField);
    topPanel.add(searchStudentButton);
    topPanel.add(refreshButton);

    add(topPanel, BorderLayout.NORTH);
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 10));

    backButton = new JButton("Back");
    backButton.setFont(buttonFont);
    backButton.setPreferredSize(new Dimension(110, 30));
    backButton.addActionListener(e -> handleBack());

    exitButton = new JButton("Exit");
    exitButton.setFont(buttonFont);
    exitButton.setPreferredSize(new Dimension(110, 30));
    exitButton.addActionListener(e -> System.exit(0));

    buttonPanel.add(backButton);
    buttonPanel.add(exitButton);

    add(buttonPanel, BorderLayout.SOUTH);


    // 初始化表格模型
    DefaultTableModel model = new DefaultTableModel(
            new Object[0][0],
            new String[]{"Student ID", "Name", "Email", "Phone"}
    );

    studentsTable = new JTable(model);
    studentsTable.getTableHeader().setFont(tableFont);
    studentsTable.setFont(tableFont);
    studentsTable.setRowHeight(30);

    // table sorting
    JTableHeader header = studentsTable.getTableHeader();
    header.setReorderingAllowed(false); 

    boolean[] sortStates = new boolean[4]; // sorting status

    header.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int viewColumn = studentsTable.columnAtPoint(e.getPoint()); 
            int modelColumn = studentsTable.convertColumnIndexToModel(viewColumn); 
            int columnCount = studentsTable.getModel().getColumnCount(); 

       //sorting by lists
            if (modelColumn >= 0 && modelColumn < columnCount) {
                List<Student> students = studentController.getAllStudents();

              
                students.sort((s1, s2) -> {
                    if (modelColumn == 0) { //studentid 
                        return sortStates[modelColumn]
                                ? Integer.compare(s2.getUserId(), s1.getUserId())
                                : Integer.compare(s1.getUserId(), s2.getUserId());
                    } else if (modelColumn == 1) { // name
                        return sortStates[modelColumn]
                                ? s2.getName().compareTo(s1.getName())
                                : s1.getName().compareTo(s2.getName());
                    } else if (modelColumn == 2) { // email
                        return sortStates[modelColumn]
                                ? s2.getEmail().compareTo(s1.getEmail())
                                : s1.getEmail().compareTo(s2.getEmail());
                    } else if (modelColumn == 3) { // phone
                        return sortStates[modelColumn]
                                ? s2.getPhone().compareTo(s1.getPhone())
                                : s1.getPhone().compareTo(s2.getPhone());
                    }
                    return 0; // 
                });

          
                sortStates[modelColumn] = !sortStates[modelColumn];

                updateTableData(students);

                // logo
                for (int i = 0; i < columnCount; i++) {
                    String columnName = studentsTable.getColumnName(i).replaceAll(" ▲| ▼", "");
                    if (i == modelColumn) {
                        columnName += sortStates[modelColumn] ? " ▼" : " ▲";
                    }
                    studentsTable.getColumnModel().getColumn(i).setHeaderValue(columnName);
                }
                header.repaint(); // refresh table
            }
        }
    });

    studentsTableScrollPane = new JScrollPane(studentsTable);
    studentsTableScrollPane.setPreferredSize(new Dimension(750, 300));
    add(studentsTableScrollPane, BorderLayout.CENTER);

    refreshTable(); 
}

    void refreshTable() {
        List<Student> students = studentController.getAllStudents();
        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
        model.setRowCount(0); // clean table
        for (Student student : students) {
            model.addRow(new Object[]{
                student.getUserId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getAddress()
            });
        }
    }
//add student
    private void handleAddStudent() {   
        new AddStudentPage(this, studentController);
    }
//delete
    private void handleDeleteStudent() {
        int selectedRow = studentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            return;
        }
        int studentId = (int) studentsTable.getValueAt(selectedRow, 0);
        if (studentController.deleteStudent(studentId)) {
            JOptionPane.showMessageDialog(this, "Student deleted successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete student.");
        }
    }
    private void handleUpdateStudent() {
        if (studentsTable.isEditing()) {
            studentsTable.getCellEditor().stopCellEditing(); // submit edited data
        }
    
        int selectedRow = studentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to update.");
            return;
        }
    
        try {
            // get updated data
            int studentId = Integer.parseInt(studentsTable.getValueAt(selectedRow, 0).toString());
            String name = studentsTable.getValueAt(selectedRow, 1).toString();
            String email = studentsTable.getValueAt(selectedRow, 2).toString();
            String phone = studentsTable.getValueAt(selectedRow, 3).toString();
            String address = studentsTable.getValueAt(selectedRow, 4).toString();
    
            // get updated student
            Student student = studentController.getStudentById(studentId);
            if (student != null) {
                student.setName(name);
                student.setEmail(email);
                student.setPhone(phone);
                student.setAddress(address);
    
                // update database
                if (studentController.updateStudent(student)) {
                    JOptionPane.showMessageDialog(this, "Student updated successfully.");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update student.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid data format. Please check your input.");
        }
    }
    private void handleSearchStudent() {
        String query = searchStudentField.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search query.");
            return;
        }
        List<Student> students = studentController.searchStudents(query);
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No student found.");
        } else {
            updateTableData(students);
        }
    }
    private void updateTableData(List<Student> students) {
        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
        model.setRowCount(0); 
    
        for (Student student : students) {
            Object[] row = {
                student.getUserId(),
                student.getName(),
                student.getEmail(),
                student.getPhone()
            };
            model.addRow(row);
        }
    }
    private void handleBack() {
        new AdminLoginPage(); 
        dispose();
    }
}