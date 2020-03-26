import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputParserShould {
    @Test
    void return_object_with_username_and_post_command() {
        InputParser inputParser = new InputParser();
        ParsedInput parsedInput = inputParser.parse("Alice -> I love the weather today");

        assertEquals("Alice", parsedInput.username);
        assertEquals("->", parsedInput.command);
        assertEquals("I love the weather today", parsedInput.argument);
    }
}