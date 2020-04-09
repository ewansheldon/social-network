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
        throw new UnsupportedOperationException();
    }

    public List<Post> getByUsername(String username) {
        List<Post> posts = new ArrayList<>();
        Connection conn = Mysql.connection();
        try {
            PreparedStatement statement = conn.prepareStatement(
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
        throw new UnsupportedOperationException();
    }
}
