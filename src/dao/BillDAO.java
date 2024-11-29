package dao;

import entity.Bill;
import entity.Bill.PaidStatus;
import DatabaseUtilities.ConnectDB;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public List<Bill> getBillsByStudentId(int studentId) {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT b.bill_id, b.bill_amount, b.due_date, b.paid_status " +
                       "FROM bills b WHERE b.student_id = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setbill_id(rs.getInt("bill_id"));
                bill.setbill_amount(rs.getDouble("bill_amount"));
    
                // 处理 due_date 为 null 的情况
                Date dueDate = rs.getDate("due_date");
                if (dueDate != null) {
                    bill.setdue_date(dueDate.toLocalDate());
                }
    
                // 转换 paid_status 为大写以匹配枚举
                String paidStatus = rs.getString("paid_status").toUpperCase();
                bill.setPaidStatus(Bill.PaidStatus.valueOf(paidStatus));
    
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
    public boolean addBill(Bill bill) {
        String query = "INSERT INTO bills (student_id, bill_amount, due_date, paid_status) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, bill.getStudent().getUserId());
            pstmt.setDouble(2, bill.getbill_amount());
            pstmt.setDate(3, Date.valueOf(bill.getdue_date()));
            pstmt.setString(4, bill.getPaidStatus().name());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBill(int billId) {
        String query = "DELETE FROM bills WHERE bill_id = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, billId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}