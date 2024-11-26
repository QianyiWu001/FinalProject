package StudentAttendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DatabaseUtilities.ConnectDB;

public class StudentAttendanceDB {
    public Object[][] getAttendanceByStudentId(int studentId) {
        String query = "SELECT a.student_id, a.course_id, a.date, a.status, c.course_name FROM attendance a JOIN courses c ON a.course_id = c.course_id WHERE a.student_id = ?";
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                statement.setInt(1, studentId);
                ResultSet r = statement.executeQuery();
                r.last();
                int numRows = r.getRow();
                r.beforeFirst();
                Object[][] data = new Object[numRows][5];
                int i = 0;
                while (r.next()) {
                    data[i][0] = r.getInt("student_id");
                    data[i][1] = r.getInt("course_id");
                    data[i][2] = r.getString("course_name");
                    data[i][3] = r.getDate("date");
                    data[i][4] = r.getString("status");
                    i++;
                }
                return data;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }
}
