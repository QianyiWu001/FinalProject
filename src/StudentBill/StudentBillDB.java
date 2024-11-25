package StudentBill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DatabaseUtilities.ConnectDB;

public class StudentBillDB {
    public String[] getBillById(int studentId) {
        String query = "SELECT student_id, bill_amount, paid_status, due_date FROM bills WHERE student_id = ?";

        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, studentId);

                ResultSet r = statement.executeQuery();
                if (r.next()) {
                    String[] bill = new String[4];
                    bill[0] = r.getString("student_id");
                    bill[1] = r.getString("bill_amount");
                    bill[2] = r.getString("paid_status");
                    bill[3] = r.getString("due_date");
                    return bill;
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }
}
