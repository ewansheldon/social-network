package social_network.posts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.date.TimeDifference;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostFormatterShould {
    @Mock private PrintStream output;
    @Mock private TimeDifference timeDiff;

    @Test
    void should_print_the_posts_and_timestamp() {
        PostFormatter formatter = new PostFormatter(output, timeDiff);
        LocalDateTime date = LocalDateTime.now();
        List<Post> posts = List.of(
                new Post("I love the weather today", date)
        );
        given(timeDiff.format(date)).willReturn("5 minutes ago");

        formatter.read(posts);

        verify(output).println("I love the weather today (5 minutes ago)");
    }
}