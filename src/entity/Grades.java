package entity;

public class Grades extends Enrollment {

    private int grade;

    // 无参数构造器
    public Grades() {
        super(); // 调用父类 Enrollment 的无参数构造器
    }

    // 全参数构造器
    public Grades(int enrollmentId, int studentId, int courseId, int grade) {
        super(enrollmentId, studentId, courseId); // 调用父类构造器初始化字段
        this.grade = grade; // 初始化子类字段
    }

    // Getters and Setters
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "enrollmentId=" + getEnrollmentId() +
                ", studentId=" + getStudentId() +
                ", courseId=" + getCourseId() +
                ", grade=" + grade +
                '}';
    }
}