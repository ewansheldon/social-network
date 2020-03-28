import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AcceptanceTest {
    @Mock private PrintStream output;
    private CommandLineInputParser parser;
    private SocialNetwork socialNetwork;
    private CommandFactory commandFactory;
    private SocialNetworkCommandLineClient client;
    private PostRepository postRepository;
    private PostFormatter postFormatter;

    @BeforeEach
    void setUp() {
        parser = new CommandLineInputParser();
        postRepository = new PostRepository();
        postFormatter = new PostFormatter();
        commandFactory = new CommandFactory(postRepository, postFormatter);
        socialNetwork = new SocialNetwork(commandFactory);
        client = new SocialNetworkCommandLineClient(output, parser, socialNetwork);
    }

    @Test
    void should_display_a_users_posts_after_posting() {
        client.executeCommand("Alice -> I love the weather today");
        client.executeCommand("Alice");

        verify(output).println("I love the weather today (5 minutes ago)");
    }
}
