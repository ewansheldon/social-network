import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AcceptanceTest {
    private CommandLineInputParser parser;
    private SocialNetwork socialNetwork;
    private CommandFactory commandFactory;
    private SocialNetworkCommandLineClient client;
    private PostRepository postRepository;
    private PostFormatter postFormatter;

    @Mock private PrintStream output;

    @BeforeEach
    void setUp() {
        parser = new CommandLineInputParser();
        postRepository = new PostRepository();
        TimeDifference timeDiff = new TimeDifference();
        postFormatter = new PostFormatter(output, timeDiff);
        commandFactory = new CommandFactory(postRepository, postFormatter);
        socialNetwork = new SocialNetwork(commandFactory);
        client = new SocialNetworkCommandLineClient(parser, socialNetwork);
    }

    @Test
    void should_display_a_users_posts_after_posting() {
        client.executeCommand("Alice -> I love the weather today");
        client.executeCommand("Alice");

        verify(output).println("I love the weather today (5 minutes ago)");
    }
}
