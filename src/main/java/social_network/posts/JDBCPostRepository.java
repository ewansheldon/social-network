package social_network.posts;

import social_network.date.DateTime;
import social_network.infrastructure.Mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPostRepository implements PostRepository {
    public JDBCPostRepository(DateTime dateTime) {
    }

    public void save(String username, String content) {
        int userID = getOrCreateUserID(username);

        try {
            PreparedStatement statement = Mysql.connection().prepareStatement(
                    "INSERT INTO posts (user_id, content, created_at) VALUES " +
                            "(?, ?, NOW());");

            statement.setInt(1, userID);
            statement.setString(2, content);
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

    public List<Post> getByUsername(String username) {
        List<Post> posts = new ArrayList<>();
        try {
            PreparedStatement statement = Mysql.connection().prepareStatement(
                    "SELECT * FROM posts INNER JOIN users ON " +
                            "posts.user_id = users.id WHERE users.username = ?;");
            statement.setString(1, username);

            ResultSet postsResults = statement.executeQuery();
            while (postsResults.next()) {
                posts.add(new Post(
                        username,
                        postsResults.getString("content"),
                        postsResults.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public List<Post> getByUsers(List<String> users) {
        List<Post> posts = new ArrayList<>();
        try {
            String usernameParameters = "'" + String.join("','", users) + "'";
            PreparedStatement statement = Mysql.connection().prepareStatement(
                    "SELECT posts.*, users.username FROM posts INNER JOIN users ON users.id = posts.user_id " +
                            "WHERE users.username in (" + usernameParameters + ")");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                posts.add(new Post(
                        results.getString("username"),
                        results.getString("content"),
                        results.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }
}
