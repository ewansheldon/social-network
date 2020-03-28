package social_network.posts;

import social_network.date.TimeDifference;

import java.io.PrintStream;
import java.util.Collections;
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
        for (int i = posts.size() - 1; i >= 0; i--) {
            Post post = posts.get(i);
            output.println(
                    post.getContent() + " (" + timeDiff.format(post.getDate()) + ")"
            );
        }
    }

    public void wall(List<Post> posts) {
        Comparator<Post> compareByDate = Comparator.comparing(Post::getDate);
        posts.sort(compareByDate.reversed());
        for (Post post : posts) {
            System.out.println(post.getContent());
            output.println(
                    post.getUsername() + " - " + post.getContent() + " (" + timeDiff.format(post.getDate()) + ")"
            );
        }
    }
}
