package DatabaseUtilities;
public class Session {
    private static int studentID;

    public static int getStudentID() {
        return studentID;
    }

    public static void setStudentID(int studentID) {
        Session.studentID = studentID;
    }

    // clear the studentID when exit.
    public static void clear() {
        studentID = 0;
    }
}
