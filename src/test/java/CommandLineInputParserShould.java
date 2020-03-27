import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineInputParserShould {
    @Test
    void break_input_string_into_command_components() {
        CommandLineInputParser parser = new CommandLineInputParser();
        ParsedCommand parsedCommand = parser.getCommandComponents("Alice -> I love the weather today");

        assertEquals("Alice", parsedCommand.username);
        assertEquals("post", parsedCommand.command);
        assertEquals("I love the weather today", parsedCommand.argument);
    }
}