package view.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DatabaseUtilities.ConnectDB;
import DatabaseUtilities.Session;



import controller.BillController;
import entity.Bill;
import view.BasicLoginPage;
import java.util.List;


public class StudentLoginPage extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JButton viewProfileButton, viewCoursesButton, viewBillButton, viewAttendanceButton, viewGradeButton, backButton, logoutButton;

    public StudentLoginPage() {
        setTitle("Student Main Page");
        setLayout(new GridBagLayout());
        setSize(800, 550);
        setStudentPageJPanel();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setStudentPageJPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Font titleFont = new Font("Arial", Font.PLAIN, 22);
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Welcome to Student Homepage");
        titleLabel.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 30, 0);
        add(titleLabel, gbc);

        viewProfileButton = createButton("View Profile", font, gbc, 1);
        viewCoursesButton = createButton("View Courses", font, gbc, 2);
        viewBillButton = createButton("View Bill", font, gbc, 3);
        viewAttendanceButton = createButton("View Attendance", font, gbc, 4);
        viewGradeButton = createButton("View Grades", font, gbc, 5);

        backButton = createButton("Back", buttonFont, gbc, 6, 0, 10, 10);
        
    }

    private JButton createButton(String text, Font font, GridBagConstraints gbc, int gridy) {
        return createButton(text, font, gbc, gridy, 0, 0, 0);
    }

    private JButton createButton(String text, Font font, GridBagConstraints gbc, int gridy, int gridx, int insetLeft, int insetRight) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setPreferredSize(new Dimension(200, 40));
        button.addActionListener(this);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, insetLeft, 20, insetRight);
        add(button, gbc);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        switch (buttonText) {
            case "View Profile":
                handleViewProfile();
                break;
            case "View Courses":
                new StudentCoursesListPage();
                dispose();
                break;
            case "View Bill":
                handleViewBill();
                break;
            case "View Attendance":
                new StudentAttendancePage();
                dispose();
                break;
            case "View Grades":
                new StudentGradesPage();
                dispose();
                break;
            case "Back":
                Session.clear();
                new BasicLoginPage();
                dispose();
                break;
          
        }
    }

    private void handleViewProfile() {
        int studentID = Session.getStudentId();
        System.out.println("Session studentId in StudentLoginPage: " + Session.getStudentId());
        String[] studentProfile = fetchStudentProfile(studentID);
        System.out.println("studentid"+studentID);

        if (studentProfile == null) {
            JOptionPane.showMessageDialog(this, "Cannot find student profile.");
        } else {
            new StudentProfilePage(studentID);
            dispose();
        }
    }

    private String[] fetchStudentProfile(int studentID) {
        String query = "SELECT student_id, name, email, phone, address FROM students WHERE student_id = ?";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new String[]{
                    rs.getString("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address")
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void handleViewBill() {
    int studentID = Session.getStudentId(); // 从 Session 获取当前学生 ID
    BillController billController = new BillController(); // 初始化 BillController

    List<Bill> studentBills = billController.getBillsByStudentId(studentID); // 获取学生账单信息

    if (studentBills == null || studentBills.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Cannot find student bill information.");
    } else {
        new StudentBillPage(); // 打开账单页面
        dispose(); // 关闭当前窗口
    }
}
}