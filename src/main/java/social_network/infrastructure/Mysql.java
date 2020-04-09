package social_network.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
    private static Connection connection;

    public static Connection connection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/social_network", "root",
                        "buspace210"
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }
}
