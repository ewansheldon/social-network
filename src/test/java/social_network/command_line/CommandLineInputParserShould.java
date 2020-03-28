package social_network.command_line;

import org.junit.jupiter.api.Test;
import social_network.commands.ParsedCommand;

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

    @Test
    void create_follow_command_components_from_follow_input_string() {
        CommandLineInputParser parser = new CommandLineInputParser();
        ParsedCommand parsedCommand = parser.getCommandComponents("Alice follows Bob");

        assertEquals("Alice", parsedCommand.username);
        assertEquals("follow", parsedCommand.command);
        assertEquals("Bob", parsedCommand.argument);
    }

}