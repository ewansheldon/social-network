package social_network.posts;

import social_network.date.TimeDifferenceFormatter;

import java.io.PrintStream;
import java.util.List;

public class PostFormatter {
    private PrintStream output;
    private TimeDifferenceFormatter timeDiffFormatter;

    public PostFormatter(PrintStream output, TimeDifferenceFormatter timeDiffFormatter) {
        this.output = output;
        this.timeDiffFormatter = timeDiffFormatter;
    }

    public void read(List<Post> posts) {
        for (Post post : posts) {
            output.println(
                    formatted(post)
            );
        }
    }

    public void wall(List<Post> posts) {
        for (Post post : posts) {
            output.println(
                    post.getUsername() + " - " + formatted(post)
            );
        }
    }

    private String formatted(Post post) {
        return post.getContent() + " (" + timeDiffFormatter.format(post.getDate()) + ")";
    }
}
