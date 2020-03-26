public class PostCommand implements Command {
    private final UserRepository userRepository;
    private final String username;
    private final String post;

    public PostCommand(UserRepository userRepository, String username, String post) {
        this.userRepository = userRepository;
        this.username = username;
        this.post = post;
    }

    public void execute() {
        User user = userRepository.findOrCreateByUsername(username);
        user.savePost(post);
    }
}
