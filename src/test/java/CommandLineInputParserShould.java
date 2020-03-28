import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineInputParserShould {
    @Test
    void create_post_command_components_from_post_input_string() {
        CommandLineInputParser parser = new CommandLineInputParser();
        ParsedCommand parsedCommand = parser.getCommandComponents("Alice -> I love the weather today");

        assertEquals("Alice", parsedCommand.username);
        assertEquals("post", parsedCommand.command);
        assertEquals("I love the weather today", parsedCommand.argument);
    }

    @Test
    void create_read_command_components_from_read_input_string() {
        CommandLineInputParser parser = new CommandLineInputParser();
        ParsedCommand parsedCommand = parser.getCommandComponents("Alice");

        assertEquals("Alice", parsedCommand.username);
        assertEquals("read", parsedCommand.command);
        assertNull(parsedCommand.argument);
    }
}