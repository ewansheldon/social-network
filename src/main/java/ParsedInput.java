public class ParsedInput {
    public final String username;
    public final String command;
    public final String argument;

    public ParsedInput(String username, String command, String argument) {
        this.username = username;
        this.command = command;
        this.argument = argument;
    }
}
