package social_network.commands;

import social_network.posts.PostRepository;

public class PostCommand implements Command {
    private final PostRepository postRepository;
    private final String message;
    private final String username;

    public PostCommand(PostRepository postRepository, String username, String message) {
        this.postRepository = postRepository;
        this.message = message;
        this.username = username;
    }

    public void execute() {
        postRepository.save(username, message);
    }
}
