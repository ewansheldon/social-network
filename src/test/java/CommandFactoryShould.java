import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryShould {
    @Mock
    private UserRepository userRepository;
    @Mock
    private InputParser inputParser;

    @Test
    void return_post_command_when_post_input_given() {
        CommandFactory commandFactory = new CommandFactory(userRepository, inputParser);
        String postCommandInput = "Alice -> I love the weather today";
        Command command = commandFactory.createCommand(postCommandInput);

        assertEquals(PostCommand.class, command.getClass());
    }
}