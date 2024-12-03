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
        List<Bill> bills = billDAO.getBillsByStudentId(studentId);
        if (bills.isEmpty()) {
            Bill noBill = new Bill();
            noBill.setPaidStatus(null);
            bills.add(noBill);
        }
        return bills;
    }
    public boolean addBill(Bill bill) {
        return billDAO.addBill(bill);
    }

    public boolean deleteBill(int billId) {
        return billDAO.deleteBill(billId);
    }

      public List<Bill> getAllBills() {
        return billDAO.getAllBills();
    }


    public List<Bill> searchBills(String query) {
        return billDAO.searchBills(query);
    }


    public boolean updateBill(Bill bill) {
        return billDAO.updateBill(bill);
    }
}
