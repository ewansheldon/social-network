package social_network.posts;

import java.time.LocalDateTime;

public class Post {
    private String content;
    private LocalDateTime date;
    private String username;

    public Post(String username, String content, LocalDateTime date) {
        this.content = content;
        this.date = date;
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }
}
