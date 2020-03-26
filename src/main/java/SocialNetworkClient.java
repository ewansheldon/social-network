public class SocialNetworkClient {
    private CommandFactory commandFactory;

    public SocialNetworkClient(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void run(String input) {
        Command command = commandFactory.createCommand(input);
        command.execute();
    }
}
