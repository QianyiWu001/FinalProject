package controller;

import entity.Bill;
import service.BillService;

import java.util.List;

public class BillController {
    private BillService billService;

    public BillController() {
        this.billService = new BillService();
    }

    public List<Bill> getBillsByStudentId(int studentId) {
        return billService.getBillsByStudentId(studentId);
    }

    // 获取所有账单
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    // 根据查询条件搜索账单
    public List<Bill> searchBills(String query) {
        return billService.searchBills(query);
    }

    // 添加账单
    public boolean addBill(Bill bill) {
        return billService.addBill(bill);
    }

    // 删除账单
    public boolean deleteBill(int billId) {
        return billService.deleteBill(billId);
    }

    // 更新账单
    public boolean updateBill(Bill bill) {
        return billService.updateBill(bill);
    }

}