package social_network.follows;

import social_network.infrastructure.Mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCFollowRepository implements FollowRepository {
    public void save(String user, String followee) {
        int userID = getOrCreateUserID(user);
        int followeeID = getOrCreateUserID(followee);

        try {
            PreparedStatement statement = Mysql.connection().prepareStatement(
                    "INSERT INTO follows (user_id, followee_id) VALUES (?,?);");
            statement.setInt(1, userID);
            statement.setInt(2, followeeID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getOrCreateUserID(String username) {
        try {
            PreparedStatement statement = Mysql.connection().prepareStatement(
                    "INSERT IGNORE INTO users (username) VALUES (?);");
            statement.setString(1, username);
            statement.execute();

            statement = Mysql.connection().prepareStatement(
                    "SELECT id from users where username = ?;");
            statement.setString(1, username);
            ResultSet userResult = statement.executeQuery();

            userResult.next();
            return userResult.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public List<String> getFollowsByUser(String username) {
        List<String> usernames = new ArrayList<>();
        usernames.add(username);

        try {
            PreparedStatement statement = Mysql.connection().prepareStatement(
                    "SELECT followees.username FROM users AS followees " +
                            "INNER JOIN follows ON follows.followee_id = followees.id " +
                            "INNER JOIN users ON follows.user_id = users.id WHERE users.username = ?");
            statement.setString(1, username);

            ResultSet followResults = statement.executeQuery();
            while (followResults.next()) {
                usernames.add(followResults.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usernames;
    }
}
