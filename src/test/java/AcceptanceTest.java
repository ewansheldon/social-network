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

    @BeforeEach
    void setUp() {
        parser = new CommandLineInputParser();
        socialNetwork = new SocialNetwork();
    }

    @Test
    void should_display_a_users_posts_after_posting() {
        SocialNetworkCommandLineClient client = new SocialNetworkCommandLineClient(output, parser, socialNetwork);

        client.executeCommand("Alice -> I love the weather today");
        client.executeCommand("Alice");

        verify(output).println("I love the weather today (5 minutes ago)");
    }
}
