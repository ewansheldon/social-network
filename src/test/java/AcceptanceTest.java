import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
public class AcceptanceTest {
    private SocialNetworkCommandLineClient client;

    @Mock private PrintStream output;
    @Mock private DateTime dateTime;

    @BeforeEach
    void setUp() {
        PostRepository postRepository = new PostRepository(dateTime);
        TimeDifference timeDiff = new TimeDifference();
        PostFormatter postFormatter = new PostFormatter(output, timeDiff);
        CommandFactory commandFactory = new CommandFactory(postRepository, postFormatter);
        SocialNetwork socialNetwork = new SocialNetwork(commandFactory);
        CommandLineInputParser parser = new CommandLineInputParser();
        client = new SocialNetworkCommandLineClient(parser, socialNetwork);
    }

    @Test
    void should_display_a_users_posts_after_posting() {
        LocalDateTime fiveMinsAgo = LocalDateTime.now().minus(5, ChronoUnit.MINUTES);
        LocalDateTime twoMinsAgo = LocalDateTime.now().minus(2, ChronoUnit.MINUTES);
        LocalDateTime oneMinsAgo = LocalDateTime.now().minus(1, ChronoUnit.MINUTES);

        given(dateTime.now()).willReturn(
                fiveMinsAgo,
                twoMinsAgo,
                oneMinsAgo
        );

        client.executeCommand("Alice -> I love the weather today");
        client.executeCommand("Bob -> Damn! We lost!");
        client.executeCommand("Bob -> Good game though.");
        client.executeCommand("Alice");
        client.executeCommand("Bob");

        InOrder inOrder = inOrder(output);
        inOrder.verify(output).println("I love the weather today (5 minutes ago)");
        inOrder.verify(output).println("Good game though. (1 minute ago)");
        inOrder.verify(output).println("Damn! We lost! (2 minutes ago)");
    }
}
