package social_network;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.command_line.CommandLineInputParser;
import social_network.command_line.SocialNetworkCommandLineClient;
import social_network.commands.CommandFactory;
import social_network.date.DateTime;
import social_network.date.TimeDifference;
import social_network.posts.PostFormatter;
import social_network.posts.PostRepository;

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

    @Test
    void should_display_posts_of_user_and_follows_when_wall() {
        LocalDateTime tenMinsAgo = LocalDateTime.now().minus(10, ChronoUnit.MINUTES);
        LocalDateTime eightMinsAgo = LocalDateTime.now().minus(8, ChronoUnit.MINUTES);
        LocalDateTime fiveMinsAgo = LocalDateTime.now().minus(5, ChronoUnit.MINUTES);
        LocalDateTime twoMinsAgo = LocalDateTime.now().minus(2, ChronoUnit.MINUTES);
        LocalDateTime oneMinsAgo = LocalDateTime.now().minus(1, ChronoUnit.MINUTES);

        given(dateTime.now()).willReturn(
                tenMinsAgo,
                eightMinsAgo,
                fiveMinsAgo,
                twoMinsAgo,
                oneMinsAgo
        );

        client.executeCommand("Alice -> I love the weather today");
        client.executeCommand("Bob -> Damn! We lost!");
        client.executeCommand("Bob -> Good game though.");
        client.executeCommand("Jonathan -> Hi Alice! :)");
        client.executeCommand("Alice follows Bob");
        client.executeCommand("Alice follows Jonathan");
        client.executeCommand("Alice -> Hi Jonathan! :)");
        client.executeCommand("Alice wall");

        InOrder inOrder = inOrder(output);
        inOrder.verify(output).println("Alice - Hi Jonathan! :) (1 minute ago)");
        inOrder.verify(output).println("Jonathan - Hi Alice! :) (2 minutes ago)");
        inOrder.verify(output).println("Bob - Good game though. (5 minutes ago)");
        inOrder.verify(output).println("Bob -Damn! We lost! (8 minutes ago)");
        inOrder.verify(output).println("Alice - I love the weather today (10 minutes ago)");
    }
}
