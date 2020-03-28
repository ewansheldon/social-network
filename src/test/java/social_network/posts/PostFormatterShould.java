package social_network.posts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.date.TimeDifference;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostFormatterShould {
    @Mock private PrintStream output;
    @Mock private TimeDifference timeDiff;

    @Test
    void print_the_posts_and_timestamp() {
        PostFormatter formatter = new PostFormatter(output, timeDiff);
        LocalDateTime date = LocalDateTime.now();
        List<Post> posts = List.of(
                new Post("Alice", "I love the weather today", date)
        );
        given(timeDiff.format(date)).willReturn("5 minutes ago");

        formatter.read(posts);

        verify(output).println("I love the weather today (5 minutes ago)");
    }

    @Test
    void print_the_wall_in_chronological_order_and_with_username() {
        PostFormatter formatter = new PostFormatter(output, timeDiff);
        LocalDateTime earlierDate = LocalDateTime.now().minus(10, ChronoUnit.SECONDS);
        LocalDateTime laterDate = LocalDateTime.now().minus(5, ChronoUnit.SECONDS);
        List<Post> posts = Arrays.asList(
                new Post("Alice", "I love the weather today", earlierDate),
                new Post("Bob", "I also love it", laterDate)
        );
        given(timeDiff.format(earlierDate)).willReturn("10 seconds ago");
        given(timeDiff.format(laterDate)).willReturn("5 seconds ago");

        formatter.wall(posts);

        InOrder inOrder = inOrder(output);
        inOrder.verify(output).println("Bob - I also love it (5 seconds ago)");
        inOrder.verify(output).println("Alice - I love the weather today (10 seconds ago)");
    }
}