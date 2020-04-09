package social_network.follows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import social_network.infrastructure.Mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JDBCFollowRepositoryShould {

    private FollowRepository followRepository;

    @BeforeEach
    void setUp() {
        followRepository = new JDBCFollowRepository();
    }

    @Test
    void return_users_self_by_default_for_follows_by_user() {
        List<String> followees = followRepository.getFollowsByUser("Alice");
        assertEquals(1, followees.size());
        assertEquals("Alice", followees.get(0));
    }

    @Test
    void save_followee_to_user_key() {
        followRepository.save("Alice", "Bob");
        List<String> followees = followRepository.getFollowsByUser("Alice");

        assertEquals(2, followees.size());
        assertEquals("Bob", followees.get(1));
    }

    @Test
    void save_followee_to_same_user_if_user_exists() {
        FollowRepository followRepository = new InMemoryFollowRepository();
        followRepository.save("Alice", "Bob");
        followRepository.save("Alice", "Jonathan");
        List<String> followees = followRepository.getFollowsByUser("Alice");

        assertEquals(3, followees.size());
        assertEquals("Bob", followees.get(1));
        assertEquals("Jonathan", followees.get(2));
    }

    @Test
    void save_followee_to_different_user_if_different_user() {
        FollowRepository followRepository = new InMemoryFollowRepository();
        followRepository.save("Alice", "Bob");
        followRepository.save("Bob", "Jonathan");
        List<String> followees = followRepository.getFollowsByUser("Alice");

        assertEquals(2, followees.size());
        assertEquals("Bob", followees.get(1));
    }


    @AfterEach
    void tearDown() throws SQLException {
        Connection conn = Mysql.connection();
        Statement statement = conn.createStatement();
        statement.executeUpdate("TRUNCATE follows;");
    }
}