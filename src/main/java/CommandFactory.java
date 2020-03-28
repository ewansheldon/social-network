public class CommandFactory {
    private PostRepository postRepository;
    private PostFormatter postFormatter;

    public CommandFactory(PostRepository postRepository, PostFormatter postFormatter) {
        this.postRepository = postRepository;
        this.postFormatter = postFormatter;
    }

    public Command create(String username, String command, String argument) {
        if (command.equals("post")) {
            return new PostCommand(postRepository, username, argument);
        }

        return new ReadCommand(postRepository, postFormatter, username);
    }
}
