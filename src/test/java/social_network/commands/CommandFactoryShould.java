package social_network.commands;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import social_network.posts.PostFormatter;
import social_network.posts.PostRepository;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryShould {
    @Mock private PostRepository postRepository;
    @Mock private PostFormatter postFormatter;
    @Mock private FollowRepository followRepository;

    @Test
    void return_a_post_command_when_type_post() {
        CommandFactory commandFactory = new CommandFactory(postRepository, followRepository, postFormatter);
        Command command = commandFactory.create(
                "Alice", "post", "I love the weather today"
        );

        assertTrue(command instanceof PostCommand);
    }

    @Test
    void return_a_read_command_when_type_read() {
        CommandFactory commandFactory = new CommandFactory(postRepository, followRepository, postFormatter);
        Command command = commandFactory.create(
                "Alice", "read", null
        );

        assertTrue(command instanceof ReadCommand);
    }

    @Test
    void return_a_follow_command_when_type_follow() {
        CommandFactory commandFactory = new CommandFactory(postRepository, followRepository, postFormatter);
        Command command = commandFactory.create(
                "Alice", "follow", "Bob"
        );

        assertTrue(command instanceof FollowCommand);
    }
}