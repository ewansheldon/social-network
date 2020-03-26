import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryShould {
    @Test
    void create_a_user_when_given_username() {
        UserRepository userRepository = new UserRepository();
        User alice = userRepository.findOrCreateByUsername("Alice");

        assertEquals("Alice", alice.getUsername());
    }

    @Test
    void return_same_user_when_already_exists() {
        UserRepository userRepository = new UserRepository();
        User alice = userRepository.findOrCreateByUsername("Alice");
        User aliceQueried = userRepository.findOrCreateByUsername("Alice");

        assertEquals(alice, aliceQueried);
    }
}