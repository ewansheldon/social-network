import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineInputParser {
    private Map<String, String> commands;

    public CommandLineInputParser() {
        commands = Map.of(
                "->", "post"
        );
    }

    public ParsedCommand getCommandComponents(String input) {
        Pattern pattern = Pattern.compile("(\\w+)\\s(->)\\s(.*)");
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        String command = commands.get(matcher.group(2));

        return new ParsedCommand(matcher.group(1), command, matcher.group(3));
    }
}