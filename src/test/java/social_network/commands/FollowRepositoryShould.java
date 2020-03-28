package social_network.commands;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FollowRepositoryShould {
    @Test
    void return_users_self_by_default_for_follows_by_user() {
        FollowRepository followRepository = new FollowRepository();
        List<String> followees = followRepository.getFollowsByUser("Alice");

        assertEquals(1, followees.size());
        assertEquals("Alice", followees.get(0));
    }

    @Test
    void save_followee_to_user_key() {
        FollowRepository followRepository = new FollowRepository();
        followRepository.save("Alice", "Bob");
        List<String> followees = followRepository.getFollowsByUser("Alice");

        assertEquals(2, followees.size());
        assertEquals("Bob", followees.get(1));
    }

    @Test
    void save_followee_to_same_user_if_user_exists() {
        FollowRepository followRepository = new FollowRepository();
        followRepository.save("Alice", "Bob");
        followRepository.save("Alice", "Jonathan");
        List<String> followees = followRepository.getFollowsByUser("Alice");

        assertEquals(3, followees.size());
        assertEquals("Bob", followees.get(1));
        assertEquals("Jonathan", followees.get(2));
    }

    @Test
    void save_followee_to_different_user_if_different_user() {
        FollowRepository followRepository = new FollowRepository();
        followRepository.save("Alice", "Bob");
        followRepository.save("Bob", "Jonathan");
        List<String> followees = followRepository.getFollowsByUser("Alice");

        assertEquals(2, followees.size());
        assertEquals("Bob", followees.get(1));
    }
}