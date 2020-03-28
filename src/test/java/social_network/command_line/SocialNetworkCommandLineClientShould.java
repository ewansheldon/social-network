package social_network.command_line;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.commands.ParsedCommand;
import social_network.SocialNetwork;

import java.io.PrintStream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SocialNetworkCommandLineClientShould {
    @Mock private PrintStream output;
    @Mock private SocialNetwork socialNetwork;
    @Mock private CommandLineInputParser parser;

    @Test
    void invoke_application_with_username_post_command_and_message() {
        SocialNetworkCommandLineClient client = new SocialNetworkCommandLineClient(parser, socialNetwork);
        ParsedCommand parsedCommand = new ParsedCommand("Alice", "post", "I love the weather today");
        String input = "Alice -> I love the weather today";
        given(parser.getCommandComponents(input)).willReturn(parsedCommand);

        client.executeCommand(input);

        verify(socialNetwork).run("Alice", "post", "I love the weather today");
    }
}