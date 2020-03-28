package social_network.commands;

import social_network.posts.PostFormatter;
import social_network.posts.PostRepository;

public class CommandFactory {
    private PostRepository postRepository;
    private PostFormatter postFormatter;
    private FollowRepository followRepository;

    public CommandFactory(PostRepository postRepository, FollowRepository followRepository, PostFormatter postFormatter) {
        this.postRepository = postRepository;
        this.postFormatter = postFormatter;
        this.followRepository = followRepository;
    }

    public Command create(String username, String command, String argument) {
        if (command.equals("wall")) {
            return new WallCommand(username, postRepository, followRepository, postFormatter);
        }

        if (command.equals("follow")) {
            return new FollowCommand(followRepository, username, argument);
        }

        if (command.equals("post")) {
            return new PostCommand(postRepository, username, argument);
        }

        return new ReadCommand(postRepository, postFormatter, username);
    }
}
