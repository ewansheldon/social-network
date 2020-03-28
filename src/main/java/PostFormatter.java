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
        for (Post post : posts) {
            output.println(
                    post.getContent() + " (" + timeDiff.format(post.getDate()) + ")"
            );
        }
    }
}
