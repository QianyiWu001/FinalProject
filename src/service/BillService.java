package service;

import dao.BillDAO;
import entity.Bill;

import java.util.List;

public class BillService {
    private BillDAO billDAO;

    public BillService() {
        this.billDAO = new BillDAO();
    }

    public List<Bill> getBillsByStudentId(int studentId) {
        return billDAO.getBillsByStudentId(studentId);
    }

    public boolean addBill(Bill bill) {
        return billDAO.addBill(bill);
    }

    public boolean deleteBill(int billId) {
        return billDAO.deleteBill(billId);
    }
}