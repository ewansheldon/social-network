package social_network.posts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.date.DateTime;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PostRepositoryShould {

    public static final String USER = "Alice";
    public static final String ANOTHER_USER = "Bob";
    public static final String ANOTHER_DIFFERENT_USER = "Jonathan";
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

    @Test
    void get_all_posts_for_all_users_given() {
        postRepository.save(USER, "I love the weather today");
        postRepository.save(USER, "I hope it is as sunny tomorrow !");
        postRepository.save(ANOTHER_USER, "I must remember to bring my sunglasses out with me");
        postRepository.save(ANOTHER_DIFFERENT_USER, ":)");

        List<Post> posts = postRepository.getByUsers(List.of(USER, ANOTHER_USER));

        assertEquals(3, posts.size());
    }
}