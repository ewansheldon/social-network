import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostCommandShould {
    @Mock private PostRepository postRepository;

    @Test
    void call_post_repository_to_save_post() {
        String username = "Alice";
        String post = "I love the weather today";
        PostCommand command = new PostCommand(postRepository, username, post);

        command.execute();

        verify(postRepository).save(username, post);
    }
}