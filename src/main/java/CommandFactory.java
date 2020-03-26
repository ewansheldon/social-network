public class CommandFactory {
    private UserRepository userRepository;
    private InputParser inputParser;

    public CommandFactory(UserRepository userRepository, InputParser inputParser) {
        this.userRepository = userRepository;
        this.inputParser = inputParser;
    }

    public Command createCommand(String input) {
        ParsedInput parsedInput = inputParser.parse(input);

        return new PostCommand(userRepository, parsedInput.username, parsedInput.argument);
    }
}
