package social_network.posts;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Post {
    private String content;
    private LocalDateTime date;

    public Post(String content, LocalDateTime date) {
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
