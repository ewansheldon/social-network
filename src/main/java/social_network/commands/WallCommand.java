package social_network.commands;

import social_network.posts.Post;
import social_network.posts.PostFormatter;
import social_network.posts.PostRepository;

import java.util.List;

public class WallCommand implements Command {
    private final FollowRepository followRepository;
    private final PostRepository postRepository;
    private final String username;
    private PostFormatter formatter;

    public WallCommand(String username, PostRepository postRepository, FollowRepository followRepository, PostFormatter formatter) {
        this.followRepository = followRepository;
        this.postRepository = postRepository;
        this.username = username;
        this.formatter = formatter;
    }

    public void execute() {
        List<String> followees = followRepository.getFollowsByUser(username);
        List<Post> posts = postRepository.getByUsers(followees);
        formatter.wall(posts);
    }
}
