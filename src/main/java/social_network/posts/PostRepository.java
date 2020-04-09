package social_network.posts;

import java.util.List;

public interface PostRepository {
    void save(String username, String content);

    List<Post> getByUsername(String username);

    List<Post> getByUsers(List<String> users);
}
