package dao;

import entity.Bill;
import entity.Bill.PaidStatus;
import DatabaseUtilities.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    // 获取所有账单
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT bill_id, student_id, bill_amount, due_date, paid_status FROM bills";
    
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillID(rs.getInt("bill_id"));
                bill.setStudentId(rs.getInt("student_id"));
                bill.setBillAmount(rs.getDouble("bill_amount"));
    
                // Null check for due_date
                Date dueDate = rs.getDate("due_date");
                if (dueDate != null) {
                    bill.setDueDate(dueDate.toLocalDate());
                } else {
                    bill.setDueDate(null); // 或设置默认值
                }
    
                // 处理 paid_status 映射
                String paidStatus = rs.getString("paid_status").toUpperCase(); // 转换为大写
                try {
                    bill.setPaidStatus(Bill.PaidStatus.valueOf(paidStatus));
                } catch (IllegalArgumentException e) {
                    System.out.println("Warning: Invalid paid_status '" + paidStatus + "' for bill_id " + rs.getInt("bill_id"));
                    bill.setPaidStatus(null); // 或设置默认值
                }
    
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return bills;
    }
    // 根据学生 ID 获取账单
    public List<Bill> getBillsByStudentId(int studentId) {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT bill_id, bill_amount, due_date, paid_status FROM bills WHERE student_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillID(rs.getInt("bill_id"));
                bill.setStudentId(studentId); // 设置 studentId
                bill.setBillAmount(rs.getDouble("bill_amount"));
                bill.setDueDate(rs.getDate("due_date").toLocalDate());
                bill.setPaidStatus(PaidStatus.valueOf(rs.getString("paid_status")));
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }
    public List<Bill> searchBills(String query) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT bill_id, student_id, bill_amount, due_date, paid_status " +
                     "FROM bills " +
                     "WHERE CONCAT(bill_id, student_id, bill_amount, due_date, paid_status) LIKE ?";
    
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillID(rs.getInt("bill_id"));
                bill.setStudentId(rs.getInt("student_id"));
                bill.setBillAmount(rs.getDouble("bill_amount"));
                bill.setDueDate(rs.getDate("due_date").toLocalDate());
                bill.setPaidStatus(Bill.PaidStatus.valueOf(rs.getString("paid_status")));
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return bills;
    }
    // 添加账单
    public boolean addBill(Bill bill) {
        String query = "INSERT INTO bills (bill_id, student_id, bill_amount, due_date, paid_status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bill.getBillID());
            stmt.setInt(2, bill.getStudentId()); // 使用 studentId
            stmt.setDouble(3, bill.getBillAmount());
            stmt.setDate(4, Date.valueOf(bill.getDueDate()));
            stmt.setString(5, bill.getPaidStatus().name());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 删除账单
    public boolean deleteBill(int billId) {
        String query = "DELETE FROM bills WHERE bill_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, billId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 更新账单
    public boolean updateBill(Bill bill) {
        String query = "UPDATE bills SET student_id = ?, bill_amount = ?, due_date = ?, paid_status = ? WHERE bill_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bill.getStudentId()); // 使用 studentId
            stmt.setDouble(2, bill.getBillAmount());
            stmt.setDate(3, Date.valueOf(bill.getDueDate()));
            stmt.setString(4, bill.getPaidStatus().name());
            stmt.setInt(5, bill.getBillID());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}