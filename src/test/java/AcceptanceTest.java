import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AcceptanceTest {
    @Mock
    private Console console;
    private UserRepository userRepository;
    private InputParser inputParser;
    private CommandFactory commandFactory;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        inputParser = new InputParser();
        commandFactory = new CommandFactory(userRepository, inputParser);
    }

    @Test
    void returns_users_post_after_creating_a_post() {
        SocialNetworkClient socialNetworkClient = new SocialNetworkClient(commandFactory);
        socialNetworkClient.run("Alice -> I love the weather today");
        socialNetworkClient.run("Alice");

        verify(console).printLine("I love the weather today (5 minutes ago)");
    }
}
