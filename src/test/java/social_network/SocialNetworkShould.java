package social_network;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.commands.Command;
import social_network.commands.CommandFactory;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SocialNetworkShould {
    @Mock private Command command;
    @Mock private CommandFactory commandFactory;

    @Test
    void call_execute_on_a_post_command_received_from_command_factory() {
        SocialNetwork socialNetwork = new SocialNetwork(commandFactory);
        String username = "Alice";
        String command = "post";
        String argument = "I love the weather today";
        given(commandFactory.create(username, command, argument)).willReturn(this.command);

        socialNetwork.run(username, command, argument);

        verify(this.command).execute();
    }
}