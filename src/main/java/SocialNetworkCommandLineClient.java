import java.io.PrintStream;

public class SocialNetworkCommandLineClient {
    private CommandLineInputParser parser;
    private SocialNetwork socialNetwork;

    public SocialNetworkCommandLineClient(PrintStream output, CommandLineInputParser parser, SocialNetwork socialNetwork) {
        this.parser = parser;
        this.socialNetwork = socialNetwork;
    }

    public void executeCommand(String input) {
        ParsedCommand parsedCommand = parser.getCommandComponents(input);
        socialNetwork.run(parsedCommand.username, parsedCommand.command, parsedCommand.argument);
    }
}
