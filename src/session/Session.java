package session;

public class Session {
    private static int studentId;

    public static int getStudentId() {
        return studentId;
    }

    public static void setStudentId(int studentId) {
        Session.studentId = studentId;
    }

    public static void clear() {
        studentId = 0;
    }
}