package social_network.posts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import social_network.date.DateTime;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JDBCPostRepositoryShould {
    public static final String USER = "Alice";

    private PostRepository postRepository;

    @Mock private DateTime dateTime;

    @BeforeEach
    void setUp() {
        postRepository = new JDBCPostRepository(dateTime);
    }

    @Test
    void return_no_posts_if_user_has_no_posts() {
        List<Post> posts = postRepository.getByUsername(USER);
        assertEquals(0, posts.size());
    }
}