package social_network.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FollowCommandShould {
    @Mock private FollowRepository followRepository;

    @Test
    void save_user_followee_in_follow_repository() {
        String user = "Alice";
        String followee = "Bob";
        FollowCommand followCommand = new FollowCommand(followRepository, user, followee);

        followCommand.execute();

        verify(followRepository).save(user, followee);
    }
}