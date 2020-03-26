import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostCommandShould {

    @Mock
    private User user;

    @Mock
    private UserRepository userRepository;

    @Test
    void receive_user_object_by_username_and_ask_user_to_save_post() {
        String post = "I love the weather today";
        String username = "Alice";
        PostCommand postCommand = new PostCommand(userRepository, username, post);
        given(userRepository.findOrCreateByUsername(username)).willReturn(user);

        postCommand.execute();

        verify(user).savePost(post);
    }
}