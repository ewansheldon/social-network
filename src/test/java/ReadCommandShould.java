import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReadCommandShould {
    @Mock private PostRepository postRepository;
    @Mock private PostFormatter postFormatter;

    @Test
    void fetch_all_posts_for_user_and_send_to_formatter() {
        String username = "Alice";
        ReadCommand command = new ReadCommand(postRepository, postFormatter, username);
        LocalDateTime date = LocalDateTime.now();
        List<Post> posts = List.of(new Post("I love the weather today", date));
        given(postRepository.getByUsername(username)).willReturn(posts);

        command.execute();

        verify(postFormatter).read(posts);
    }
}