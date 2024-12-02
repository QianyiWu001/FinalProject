package view.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import controller.AttendanceController;
import entity.Attendance;


public class AdminAttendanceManagementPage extends JFrame {
    private JButton backButton, exitButton, addAttendanceButton, deleteAttendanceButton, updateAttendanceButton, searchAttendanceButton, refreshButton;
    private JTextField searchField;
    private JTable attendanceTable;
    private JScrollPane attendanceTableScrollPane;
    private AttendanceController attendanceController;
    private List<Attendance> attendances;
    private int activeColumn = -1;

    // 排序逻辑
    boolean[] sortStates = new boolean[5]; // 记录每列的排序状态

    public AdminAttendanceManagementPage() {
        attendanceController = new AttendanceController(); // 初始化控制器
        setTitle("Admin Attendance Management Page");
        setLayout(new BorderLayout());
        setAdminAttendanceManagementPagePanel();
        setSize(900, 550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unused")
    private void setAdminAttendanceManagementPagePanel() {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Font functionFont = new Font("Arial", Font.PLAIN, 13);
        Dimension functionDimension = new Dimension(130, 30);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));

        addAttendanceButton = new JButton("Add Attendance");
        addAttendanceButton.setFont(functionFont);
        addAttendanceButton.setPreferredSize(functionDimension);
        addAttendanceButton.addActionListener(e -> handleAddAttendance());

        deleteAttendanceButton = new JButton("Delete Attendance");
        deleteAttendanceButton.setFont(functionFont);
        deleteAttendanceButton.setPreferredSize(functionDimension);
        deleteAttendanceButton.addActionListener(e -> handleDeleteAttendance());

        updateAttendanceButton = new JButton("Update Attendance");
        updateAttendanceButton.setFont(functionFont);
        updateAttendanceButton.setPreferredSize(functionDimension);
        updateAttendanceButton.addActionListener(e -> handleUpdateAttendance());

        searchField = new JTextField(15);
        searchField.setFont(functionFont);
        searchField.setPreferredSize(functionDimension);

        searchAttendanceButton = new JButton("Search Attendance");
        searchAttendanceButton.setFont(functionFont);
        searchAttendanceButton.setPreferredSize(functionDimension);
        searchAttendanceButton.addActionListener(e -> handleSearchAttendance());

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(functionFont);
        refreshButton.setPreferredSize(functionDimension);
        refreshButton.addActionListener(e -> refreshTable());

        topPanel.add(addAttendanceButton);
        topPanel.add(deleteAttendanceButton);
        topPanel.add(updateAttendanceButton);
        topPanel.add(searchField);
        topPanel.add(searchAttendanceButton);
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

    // 表格初始化，包含 enrollmentId，但隐藏该列
    DefaultTableModel model = new DefaultTableModel(
        new Object[0][0],
        new String[]{"Enrollment ID", "Student ID", "Course ID", "Date", "Status"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 3 || column == 4; // 仅允许编辑 Date 和 Status 列
        }
    };

    attendanceTable = new JTable(model);
    attendanceTable.getTableHeader().setFont(tableFont);
    attendanceTable.setFont(tableFont);
    attendanceTable.setRowHeight(30);

    // 隐藏 enrollmentId 列
    attendanceTable.getColumnModel().getColumn(0).setMinWidth(0);
    attendanceTable.getColumnModel().getColumn(0).setMaxWidth(0);
    attendanceTable.getColumnModel().getColumn(0).setPreferredWidth(0);

    attendanceTableScrollPane = new JScrollPane(attendanceTable);
    attendanceTable.setFillsViewportHeight(true);
    add(attendanceTableScrollPane, BorderLayout.CENTER);
    // 添加表头排序功能和符号
    JTableHeader header = attendanceTable.getTableHeader();
    header.setReorderingAllowed(false); // 禁止拖动列

    // 记录每列的排序状态
    boolean[] sortStates = new boolean[5]; // false = 升序, true = 降序

    header.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int column = attendanceTable.columnAtPoint(e.getPoint());
            if (column >= 0) { // 仅当点击有效列时执行
                List<Attendance> attendances = attendanceController.getAllAttendance();

                // 根据列号排序
                attendances.sort((a1, a2) -> {
                    if (column == 0) { // 按 Enrollment ID 排序
                        return sortStates[column]
                                ? Integer.compare(a2.getEnrollmentId(), a1.getEnrollmentId())
                                : Integer.compare(a1.getEnrollmentId(), a2.getEnrollmentId());
                    } else if (column == 1) { // 按 Student ID 排序
                        return sortStates[column]
                                ? Integer.compare(a2.getStudentId(), a1.getStudentId())
                                : Integer.compare(a1.getStudentId(), a2.getStudentId());
                    } else if (column == 2) { // 按 Course ID 排序
                        return sortStates[column]
                                ? Integer.compare(a2.getCourseId(), a1.getCourseId())
                                : Integer.compare(a1.getCourseId(), a2.getCourseId());
                    } else if (column == 3) { // 按 Date 排序
                        return sortStates[column]
                                ? a2.getDate().compareTo(a1.getDate())
                                : a1.getDate().compareTo(a2.getDate());
                    } else if (column == 4) { // 按 Status 排序
                        return sortStates[column]
                                ? a2.getStatus().compareTo(a1.getStatus())
                                : a1.getStatus().compareTo(a2.getStatus());
                    }
                    return 0; // 默认不变
                });

                // 切换当前列的排序状态
                sortStates[column] = !sortStates[column];

                // 更新表格数据
                updateTableData(attendances);

                // 更新表头符号
                for (int i = 0; i < attendanceTable.getColumnCount(); i++) {
                    String columnName = attendanceTable.getColumnName(i).replaceAll(" ▲| ▼", "");
                    if (i == column) {
                        columnName += sortStates[column] ? " ▼" : " ▲";
                    }
                    attendanceTable.getColumnModel().getColumn(i).setHeaderValue(columnName);
                }
                header.repaint(); // 刷新表头
            }
        }
    });

    attendanceTableScrollPane = new JScrollPane(attendanceTable);
    attendanceTable.setFillsViewportHeight(true);

    add(attendanceTableScrollPane, BorderLayout.CENTER);

    refreshTable();
}
private void updateTableData(List<Attendance> attendances) {
    DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
    model.setRowCount(0); // 清空表格数据
    for (Attendance attendance : attendances) {
        Object[] row = {
            attendance.getEnrollmentId(),
            attendance.getStudentId(),
            attendance.getCourseId(),
            attendance.getDate(),
            attendance.getStatus()
        };
        model.addRow(row);
    }
}


    private void handleAddAttendance() {
        new AddAttendancePage(this, attendanceController);
    }

    private void handleDeleteAttendance() {
        int selectedRow = attendanceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an attendance record to delete.");
            return;
        }
        int enrollmentId = Integer.parseInt(attendanceTable.getValueAt(selectedRow, 0).toString());
        String date = attendanceTable.getValueAt(selectedRow, 1).toString();

        if (attendanceController.deleteAttendance(enrollmentId, date)) {
            JOptionPane.showMessageDialog(this, "Attendance record deleted successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete attendance record.");
        }
    }
    private void handleUpdateAttendance() {
        if (attendanceTable.isEditing()) {
            attendanceTable.getCellEditor().stopCellEditing(); // 提交用户编辑的数据
        }
    
        int selectedRow = attendanceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an attendance record to update.");
            return;
        }
    
        int enrollmentId = Integer.parseInt(attendanceTable.getValueAt(selectedRow, 0).toString());
        String dateString = attendanceTable.getValueAt(selectedRow, 3).toString(); // 获取 Date 列
        String status = attendanceTable.getValueAt(selectedRow, 4).toString(); // 获取 Status 列

        dateString = formatDate(dateString);
    
        if (dateString == null) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please ensure the date is in YYYY-MM-DD format.");
            return;
        }
    
        Attendance updatedAttendance = new Attendance();
        updatedAttendance.setEnrollmentId(enrollmentId);
        updatedAttendance.setDate(java.sql.Date.valueOf(dateString)); // 转换为 SQL 日期
        updatedAttendance.setStatus(status);
    
        if (attendanceController.updateAttendance(updatedAttendance)) {
            JOptionPane.showMessageDialog(this, "Attendance status updated successfully.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update attendance status.");
        }
    }                   
    
    // 格式化日期为 YYYY-MM-DD，如果格式无效返回 null
    private String formatDate(String dateString) {
        try {
            if (dateString.contains(" ")) { // 如果包含时间部分
                dateString = dateString.split(" ")[0]; // 提取日期部分
            }
            java.sql.Date.valueOf(dateString); // 检查格式
            return dateString;
        } catch (IllegalArgumentException e) {
            return null; // 格式无效
        }
    }


    void refreshTable() {
        List<Attendance> attendanceList = attendanceController.getAllAttendance();
        DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
        model.setRowCount(0); // 清空表格数据
    
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Attendance attendance : attendanceList) {
            model.addRow(new Object[]{
                attendance.getEnrollmentId(),
                attendance.getStudentId(),  // 添加 Student ID
                attendance.getCourseId(),  // 添加 Course ID
                dateFormat.format(attendance.getDate()),
                attendance.getStatus()
            });
        }
    }
    private void handleSearchAttendance() {
        String searchText = searchField.getText().trim(); // Get the search text from the input field
    
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter text to search.");
            return;
        }
    
        // Call the controller to search attendance records
        List<Attendance> attendanceList = attendanceController.searchAttendance(searchText);
    
        if (attendanceList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No matching attendance records found.");
        } else {
            // Populate the table with the search results
            DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
            model.setRowCount(0); // Clear the table before adding new rows
    
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Attendance attendance : attendanceList) {
                String formattedDate = dateFormat.format(attendance.getDate());
                model.addRow(new Object[]{
                    attendance.getEnrollmentId(),
                    formattedDate,
                    attendance.getStatus()
                });
            }
        }
    }
    private void handleBack() {
        new AdminLoginPage(); // 返回管理员主页
        dispose();
    }
}