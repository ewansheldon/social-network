package social_network.posts;

import social_network.date.TimeDifference;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;

public class PostFormatter {
    private PrintStream output;
    private TimeDifference timeDiff;

    public PostFormatter(PrintStream output, TimeDifference time) {
        this.output = output;
        this.timeDiff = time;
    }

    public void read(List<Post> posts) {
        sortPostsByDate(posts);
        for (Post post : posts) {
            output.println(
                    formatted(post)
            );
        }
    }

    public void wall(List<Post> posts) {
        sortPostsByDate(posts);
        for (Post post : posts) {
            output.println(
                    post.getUsername() + " - " + formatted(post)
            );
        }
    }

    private void sortPostsByDate(List<Post> posts) {
        Comparator<Post> compareByDate = Comparator.comparing(Post::getDate);
        posts.sort(compareByDate.reversed());
    }

    private String formatted(Post post) {
        return post.getContent() + " (" + timeDiff.format(post.getDate()) + ")";
    }
}
