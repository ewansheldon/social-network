package social_network.commands;

import social_network.posts.Post;
import social_network.posts.PostFormatter;
import social_network.posts.PostRepository;

import java.util.List;

public class ReadCommand implements Command {
    private final PostRepository postRepository;
    private PostFormatter postFormatter;
    private final String username;

    public ReadCommand(PostRepository postRepository, PostFormatter postFormatter, String username) {
        this.postRepository = postRepository;
        this.postFormatter = postFormatter;
        this.username = username;
    }

    public void execute() {
        List<Post> posts = postRepository.getByUsername(username);
        postFormatter.read(posts);
    }
}
