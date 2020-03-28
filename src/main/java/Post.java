import java.util.Calendar;

public class Post {
    private String content;
    private Calendar date;

    public Post(String content, Calendar date) {
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public Calendar getDate() {
        return date;
    }
}
