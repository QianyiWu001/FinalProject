package entity;

import java.time.LocalDate;



public class Bill {

    private int billID;
   private Student student;


    private double billAmount;



    private LocalDate dueDate;

    public enum PaidStatus {
        PAID,
        UNPAID,
        PENDING
    }


    private PaidStatus paidStatus;



    // Getters and Setters
    public int getbill_id() {
        return billID;
    }

    public void setbill_id(int billID) {
        this.billID = billID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getbill_amount() {
        return billAmount;
    }

    public void setbill_amount(double bill_amount) {
        this.billAmount = bill_amount;
    }

    public LocalDate getdue_date() {
        return dueDate;
    }

    public void setdue_date(LocalDate due_date) {
        this.dueDate = due_date;
    }

    public PaidStatus getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(PaidStatus paidStatus) {
        this.paidStatus = paidStatus;
    }
}
