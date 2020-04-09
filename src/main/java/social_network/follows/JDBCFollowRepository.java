package social_network.follows;

import java.util.List;

public class JDBCFollowRepository implements FollowRepository {
    public void save(String user, String followee) {
        throw new UnsupportedOperationException();
    }

    public List<String> getFollowsByUser(String user) {
        throw new UnsupportedOperationException();
    }
}
