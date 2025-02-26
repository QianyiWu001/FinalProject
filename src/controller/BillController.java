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


    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }


    public List<Bill> searchBills(String query) {
        return billService.searchBills(query);
    }


    public boolean addBill(Bill bill) {
        return billService.addBill(bill);
    }


    public boolean deleteBill(int billId) {
        return billService.deleteBill(billId);
    }

    public boolean updateBill(Bill bill) {
        return billService.updateBill(bill);
    }

}