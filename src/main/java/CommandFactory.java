public class CommandFactory {
    private PostRepository postRepository;

    public CommandFactory(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Command create(String username, String command, String argument) {
        if (command.equals("post")) {
            return new PostCommand(postRepository, username, argument);
        }

        return new ReadCommand(postRepository, username);
    }
}
