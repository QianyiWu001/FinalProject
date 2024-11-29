package entity;

import java.util.Date;

public class Attendance {

    private int enrollmentId;
    private Date date;
    private String status;
    private int studentId; // 新增字段
    private int courseId;  // 新增字段

    // 无参数构造器
    public Attendance() {}

    // 全参数构造器
    public Attendance(int enrollmentId, Date date, String status, int studentId, int courseId) {
        this.enrollmentId = enrollmentId;
        this.date = date;
        this.status = status;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // Getters 和 Setters
    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "enrollmentId=" + enrollmentId +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}