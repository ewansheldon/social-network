package social_network.posts;

import social_network.date.TimeDifference;

import java.io.PrintStream;
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
}
