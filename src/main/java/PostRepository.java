import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PostRepository {
    private HashMap<String, List<Post>> posts;

    public PostRepository() {
        this.posts = new HashMap<>();
    }

    public void save(String username, String content) {
        Post post = new Post(content);
        List<Post> userPosts = posts.getOrDefault(username,
                new ArrayList<>()
        );
        userPosts.add(post);

        posts.put(username, userPosts);
    }

    public List<Post> getByUsername(String username) {
        return posts.getOrDefault(username,
                Collections.emptyList()
        );
    }
}
