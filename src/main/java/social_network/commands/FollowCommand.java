package social_network.commands;

public class FollowCommand implements Command {
    private final FollowRepository followRepository;
    private final String username;
    private final String followee;

    public FollowCommand(FollowRepository followRepository, String username, String followee) {
        this.followRepository = followRepository;
        this.username = username;
        this.followee = followee;
    }

    public void execute() {
        followRepository.save(username, followee);
    }
}
