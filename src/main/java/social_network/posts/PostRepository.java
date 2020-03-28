package social_network.posts;

import social_network.date.DateTime;

import java.util.*;

public class PostRepository {
    private HashMap<String, List<Post>> posts;
    private DateTime dateTime;

    public PostRepository(DateTime dateTime) {
        this.dateTime = dateTime;
        this.posts = new HashMap<>();
    }

    public void save(String username, String content) {
        Post post = new Post(content, dateTime.now());
        List<Post> userPosts = getOrCreateUserPosts(username);
        userPosts.add(post);
        posts.put(username, userPosts);
    }

    private List<Post> getOrCreateUserPosts(String username) {
        return posts.getOrDefault(username,
                    new ArrayList<>()
            );
    }

    public List<Post> getByUsername(String username) {
        return posts.getOrDefault(username,
                Collections.emptyList()
        );
    }
}
