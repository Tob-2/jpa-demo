package sit.int224.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private final static String URL = "jdbc:mysql://localhost:3306/sample";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "mysql@sit";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
