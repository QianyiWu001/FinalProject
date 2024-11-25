package StudentProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DatabaseUtilities.ConnectDB;

public class StudentProfileDB {
    public String[] searchStudentById(int studentID) {
        String query = "SELECT student_id, name, email, phone, address FROM students WHERE student_id = ?";

        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setInt(1, studentID);

                ResultSet r = stmt.executeQuery();
                if (r.next()) {
                    String[] studentProfile = {r.getString("student_id"), r.getString("name"), r.getString("email"), r.getString("phone"), r.getString("address")};
                    return studentProfile;
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }
}
