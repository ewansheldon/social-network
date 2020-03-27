import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryShould {
    @Test
    void return_a_post_command_when_type_post() {
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.create(
                "Alice", "post", "I love the weather today"
        );

        assertTrue(command instanceof PostCommand);
    }
}