package social_network.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/social_network","root","buspace210");
    }
}
