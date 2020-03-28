package social_network.command_line;

import social_network.commands.ParsedCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineInputParser {
    private Map<String, String> commands;

    public CommandLineInputParser() {
        initialiseCommandNameInterpreter();
    }

    private void initialiseCommandNameInterpreter() {
        commands = new HashMap<>();
        commands.put(null, "read");
        commands.put("->", "post");
        commands.put("follows", "follow");
    }

    public ParsedCommand getCommandComponents(String input) {
        Pattern pattern = Pattern.compile("(\\w+)\\s?(->|follows)?\\s?(.+)?");
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        String command = commands.get(matcher.group(2));

        return new ParsedCommand(matcher.group(1), command, matcher.group(3));
    }

}
