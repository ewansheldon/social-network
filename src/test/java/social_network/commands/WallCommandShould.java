package social_network.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.posts.Post;
import social_network.posts.PostFormatter;
import social_network.posts.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WallCommandShould {
    @Mock private FollowRepository followRepository;
    @Mock private PostRepository postRepository;
    @Mock private PostFormatter formatter;

    @Test
    void get_follows_get_posts_for_each_and_send_to_formatter() {
        String user = "Alice";
        List<String> followees = List.of("Bob", "Jonathan", "Alice");
        List<Post> posts = List.of(
                new Post(user, "I love the weather today", LocalDateTime.now()),
                new Post("Jonathan", "Me too!", LocalDateTime.now()),
                new Post("Bob", "Yes I love it when it is sunny and cold", LocalDateTime.now())
        );
        WallCommand wallCommand = new WallCommand(user, postRepository, followRepository, formatter);
        given(followRepository.getFollowsByUser(user)).willReturn(followees);
        given(postRepository.getByUsers(followees)).willReturn(posts);
        wallCommand.execute();

        verify(formatter).wall(posts);
    }
}