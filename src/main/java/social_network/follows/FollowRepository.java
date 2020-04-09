package social_network.follows;

import java.util.List;

public interface FollowRepository {
    void save(String user, String followee);

    List<String> getFollowsByUser(String user);
}
