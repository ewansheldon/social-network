package social_network.posts;

import social_network.date.DateTime;

import java.util.List;

public class JDBCPostRepository implements PostRepository {
    public JDBCPostRepository(DateTime dateTime) {
    }

    public void save(String username, String content) {
        throw new UnsupportedOperationException();
    }

    public List<Post> getByUsername(String username) {
        throw new UnsupportedOperationException();
    }

    public List<Post> getByUsers(List<String> users) {
        throw new UnsupportedOperationException();
    }
}
