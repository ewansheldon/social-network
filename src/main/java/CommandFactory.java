public class CommandFactory {
    private PostRepository postRepository;

    public CommandFactory(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Command create(String username, String command, String argument) {
        return new PostCommand(postRepository, username, argument);
    }
}
