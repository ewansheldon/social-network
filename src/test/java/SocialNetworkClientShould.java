import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SocialNetworkClientShould {
    public static final String ANY_COMMAND_INPUT = "Alice";
    @Mock private Command command;
    @Mock private CommandFactory commandFactory;

    @Test
    void get_a_command_from_command_factory_and_run_execute_on_command() {
        SocialNetworkClient socialNetworkClient = new SocialNetworkClient(commandFactory);
        given(commandFactory.createCommand(ANY_COMMAND_INPUT)).willReturn(command);

        socialNetworkClient.run(ANY_COMMAND_INPUT);

        verify(command).execute();
    }
}