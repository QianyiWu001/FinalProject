package DatabaseUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String dbURL = "jdbc:mysql://localhost:3306/StudentManagement";
    private static final String dbUser = "root";
    private static final String dbPassword = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbURL, dbUser, dbPassword);
    }
}
