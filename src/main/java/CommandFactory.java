public class CommandFactory {
    public Command create(String username, String command, String argument) {
        return new PostCommand(username, argument);
    }
}
