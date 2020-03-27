public class SocialNetwork {
    private CommandFactory commandFactory;

    public SocialNetwork(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void run(String username, String commandType, String argument) {
        Command command = commandFactory.create(username, commandType, argument);

        command.execute();
    }
}
