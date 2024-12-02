package entity;
import java.time.LocalDate;

public class Bill {

    private int billID;
    private int studentId; // 使用 studentId 而不是 Student 对象
    private double billAmount;
    private LocalDate dueDate;

    public enum PaidStatus {
        PAID,
        UNPAID,
        PENDING,
        NOBill
    }

    private PaidStatus paidStatus;

    // Getters and Setters
    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public PaidStatus getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(PaidStatus paidStatus) {
        this.paidStatus = paidStatus;
    }

    // Constructor
    public Bill(int billID, int studentId, double billAmount, LocalDate dueDate, PaidStatus paidStatus) {
        this.billID = billID;
        this.studentId = studentId;
        this.billAmount = billAmount;
        this.dueDate = dueDate;
        this.paidStatus = paidStatus;
    }

    // Default Constructor
    public Bill() {
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billID=" + billID +
                ", studentId=" + studentId +
                ", billAmount=" + billAmount +
                ", dueDate=" + dueDate +
                ", paidStatus=" + paidStatus +
                '}';
    }
}