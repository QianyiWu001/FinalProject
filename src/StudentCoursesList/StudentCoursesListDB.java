package StudentCoursesList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DatabaseUtilities.ConnectDB;

public class StudentCoursesListDB {
    public Object[][] getCoursesByStudentId(int studentId) {
        String query = "SELECT e.student_id, e.course_id, c.course_name, c.credits FROM enrollments e JOIN courses c ON e.course_id = c.course_id WHERE e.student_id = ?";
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                statement.setInt(1, studentId);
                ResultSet r = statement.executeQuery();
                r.last();
                int numRows = r.getRow();
                r.beforeFirst();
                Object[][] data = new Object[numRows][4];
                int i = 0;
                while (r.next()) {
                    data[i][0] = r.getInt("student_id");
                    data[i][1] = r.getInt("course_id");
                    data[i][2] = r.getString("course_name");
                    data[i][3] = r.getInt("credits");
                    i++;
                }
                return data;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }
}
