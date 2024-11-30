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
      // 获取所有账单
      public List<Bill> getAllBills() {
        return billDAO.getAllBills();
    }

    // 根据查询条件搜索账单
    public List<Bill> searchBills(String query) {
        return billDAO.searchBills(query);
    }

    // 更新账单
    public boolean updateBill(Bill bill) {
        return billDAO.updateBill(bill);
    }
}
