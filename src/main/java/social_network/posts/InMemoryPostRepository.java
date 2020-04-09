package social_network.posts;

import social_network.date.DateTime;

import java.util.*;

public class InMemoryPostRepository implements PostRepository {
    private HashMap<String, List<Post>> posts;
    private DateTime dateTime;

    public InMemoryPostRepository(DateTime dateTime) {
        this.dateTime = dateTime;
        this.posts = new HashMap<>();
    }

    @Override
    public void save(String username, String content) {
        Post post = new Post(username, content, dateTime.now());
        List<Post> userPosts = getOrCreateUserPosts(username);
        userPosts.add(post);
        posts.put(username, userPosts);
    }

    private List<Post> getOrCreateUserPosts(String username) {
        return posts.getOrDefault(username,
                new ArrayList<>()
        );
    }

    @Override
    public List<Post> getByUsername(String username) {
        List<Post> userPosts = posts.getOrDefault(username,
                Collections.emptyList()
        );
        sortChronologically(userPosts);
        return userPosts;
    }

    @Override
    public List<Post> getByUsers(List<String> users) {
        ArrayList<Post> followPosts = new ArrayList<>();
        for (String user : users) {
            followPosts.addAll(posts.get(user));
        }
        sortChronologically(followPosts);
        return followPosts;
    }

    private void sortChronologically(List<Post> followPosts) {
        Comparator<Post> compareByDate = Comparator.comparing(Post::getDate);
        followPosts.sort(compareByDate.reversed());
    }
}
