package view.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.GradesController;
import entity.Grade;

import java.awt.*;
import java.util.List;

import javax.swing.table.JTableHeader;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AdminGradesManagementPage extends JFrame {
    private JButton backButton, exitButton, addGradeButton, deleteGradeButton, updateGradeButton, searchGradeButton,
            refreshButton;
    private JTextField searchGradeField;
    private JTable gradesTable;
    private JScrollPane gradesTableScrollPane;
    private GradesController gradesController;

    public AdminGradesManagementPage() {
        gradesController = new GradesController(); 
        setTitle("Admin Grades Management Page");
        setLayout(new BorderLayout());
        setAdminGradesManagementPagePanel();
        setSize(900, 550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unused")
    private void setAdminGradesManagementPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));

        addGradeButton = new JButton("Add Grade");
        addGradeButton.setFont(functionFont);
        addGradeButton.setPreferredSize(functionDimension);
        addGradeButton.addActionListener(e -> handleAddGrade());

        deleteGradeButton = new JButton("Delete Grade");
        deleteGradeButton.setFont(functionFont);
        deleteGradeButton.setPreferredSize(functionDimension);
        deleteGradeButton.addActionListener(e -> handleDeleteGrade());

        updateGradeButton = new JButton("Update Grade");
        updateGradeButton.setFont(functionFont);
        updateGradeButton.setPreferredSize(functionDimension);
        updateGradeButton.addActionListener(e -> handleUpdateGrade());

        searchGradeButton = new JButton("Search Grade");
        searchGradeButton.setFont(functionFont);
        searchGradeButton.setPreferredSize(functionDimension);
        searchGradeButton.addActionListener(e -> handleSearchGrade());

        searchGradeField = new JTextField(15);
        searchGradeField.setFont(functionFont);
        searchGradeField.setPreferredSize(functionDimension);

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(functionFont);
        refreshButton.setPreferredSize(functionDimension);
        refreshButton.addActionListener(e -> refreshTable());

        topPanel.add(addGradeButton);
        topPanel.add(deleteGradeButton);
        topPanel.add(updateGradeButton);
        topPanel.add(searchGradeField);
        topPanel.add(searchGradeButton);
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

        DefaultTableModel model = new DefaultTableModel(
                new Object[0][0],
                new String[] { "Enrollment ID", "Student ID", "Course ID", "Grade" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // only able to edit grades
            }
        };

        gradesTable = new JTable(model);
        gradesTable.getTableHeader().setFont(tableFont);
        gradesTable.setFont(tableFont);
        gradesTable.setRowHeight(30);

        gradesTableScrollPane = new JScrollPane(gradesTable);
        gradesTable.setFillsViewportHeight(true);
        // hide enrollment id 
        gradesTable.getColumnModel().getColumn(0).setMinWidth(0);
        gradesTable.getColumnModel().getColumn(0).setMaxWidth(0);
        gradesTable.getColumnModel().getColumn(0).setPreferredWidth(0);

        add(gradesTableScrollPane, BorderLayout.CENTER);

    // add sorting 
    JTableHeader header = gradesTable.getTableHeader();
    header.setReorderingAllowed(false); //unable to click and drop

    boolean[] sortStates = new boolean[4]; // Decending or ascending

    header.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int viewColumn = gradesTable.columnAtPoint(e.getPoint()); // viewcloumn index
            int modelColumn = gradesTable.convertColumnIndexToModel(viewColumn); // turns into modelcolumn index
            int columnCount = gradesTable.getModel().getColumnCount(); // get index

            // makesure valid index
            if (modelColumn >= 0 && modelColumn < columnCount) {
                List<Grade> grades = gradesController.getAllGrades();

                // sorting
                grades.sort((g1, g2) -> {
                    if (modelColumn == 0) { // enrollment id
                        return sortStates[modelColumn]
                                ? Integer.compare(g2.getEnrollmentId(), g1.getEnrollmentId())
                                : Integer.compare(g1.getEnrollmentId(), g2.getEnrollmentId());
                    } else if (modelColumn == 1) { //student id 
                        return sortStates[modelColumn]
                                ? Integer.compare(g2.getStudentId(), g1.getStudentId())
                                : Integer.compare(g1.getStudentId(), g2.getStudentId());
                    } else if (modelColumn == 2) { // course id
                        return sortStates[modelColumn]
                                ? Integer.compare(g2.getCourseId(), g1.getCourseId())
                                : Integer.compare(g1.getCourseId(), g2.getCourseId());
                    } else if (modelColumn == 3) { // grades
                        return sortStates[modelColumn]
                                ? Integer.compare(g2.getGrade(), g1.getGrade())
                                : Integer.compare(g1.getGrade(), g2.getGrade());
                    }
                    return 0; 
                });

                
                sortStates[modelColumn] = !sortStates[modelColumn];

               
                updateTableData(grades);

                // sorting logo
                for (int i = 0; i < columnCount; i++) {
                    String columnName = gradesTable.getColumnName(i).replaceAll(" ▲| ▼", "");
                    if (i == modelColumn) {
                        columnName += sortStates[modelColumn] ? " ▼" : " ▲";
                    }
                    gradesTable.getColumnModel().getColumn(i).setHeaderValue(columnName);
                }
                header.repaint(); // refresh sorting logo
            }
        }
    });

    gradesTableScrollPane = new JScrollPane(gradesTable);
    gradesTableScrollPane.setPreferredSize(new Dimension(750, 300));
    add(gradesTableScrollPane, BorderLayout.CENTER);

    refreshTable(); 
    }

    void refreshTable() {
        List<Grade> grades = gradesController.getAllGrades();
        DefaultTableModel model = (DefaultTableModel) gradesTable.getModel();
        model.setRowCount(0); // clean database
        for (Grade grade : grades) {
            model.addRow(new Object[] {
                    grade.getEnrollmentId(),
                    grade.getStudentId(),
                    grade.getCourseId(),
                    grade.getGrade()
            });
        }
    }

    private void handleAddGrade() {
        new AddGradePage(this, gradesController);
    }

    private void handleDeleteGrade() {
        int selectedRow = gradesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a grade to delete.");
            return;
        }
        int enrollmentId = Integer.parseInt(gradesTable.getValueAt(selectedRow, 0).toString());
        if (gradesController.deleteGrade(enrollmentId)) {
            JOptionPane.showMessageDialog(this, "Grade deleted successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete grade.");
        }
    }

    private void handleUpdateGrade() {
        if (gradesTable.isEditing()) {
            gradesTable.getCellEditor().stopCellEditing(); // submit edited data
        }

        int selectedRow = gradesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a grade to update.");
            return;
        }

        int enrollmentId = Integer.parseInt(gradesTable.getValueAt(selectedRow, 0).toString());
        int gradeValue = Integer.parseInt(gradesTable.getValueAt(selectedRow, 3).toString());
        Grade updatedGrade = new Grade(enrollmentId, gradeValue);
        if (gradesController.updateGrade(updatedGrade)) {
            JOptionPane.showMessageDialog(this, "Grade updated successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update grade.");
        }
    }

    private void handleSearchGrade() {
        String query = searchGradeField.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search query.");
            return;
        }
        List<Grade> grades = gradesController.searchGrades(query);
        if (grades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No grade found.");
        } else {
            updateTableData(grades);
        }
    }

    private void updateTableData(List<Grade> grades) {
        DefaultTableModel model = (DefaultTableModel) gradesTable.getModel();
        model.setRowCount(0); 
    
        for (Grade grade : grades) {
            Object[] row = {
                grade.getEnrollmentId(),
                grade.getStudentId(),
                grade.getCourseId(),
                grade.getGrade()
            };
            model.addRow(row);
        }
    }
    private void handleBack() {
        new AdminLoginPage(); 
        dispose();
    }
}