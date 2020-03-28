import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostRepositoryShould {

    public static final String USER = "Alice";
    private PostRepository postRepository;
    @Mock private DateTime dateTime;

    @BeforeEach
    void setUp() {
        postRepository = new PostRepository(dateTime);
    }

    @Test
    void return_no_posts_if_user_has_no_posts() {
        List<Post> posts = postRepository.getByUsername(USER);
        assertEquals(0, posts.size());
    }

    @Test
    void add_a_post_for_username_after_save_post() {
        postRepository.save(USER, "I love the weather today");
        List<Post> posts = postRepository.getByUsername(USER);

        assertEquals(1, posts.size());
    }

    @Test
    void add_posts_to_same_user_list_when_same_username() {
        postRepository.save(USER, "I love the weather today");
        postRepository.save(USER, "I hope it is as sunny tomorrow !");
        List<Post> posts = postRepository.getByUsername(USER);

        assertEquals(2, posts.size());
    }
}