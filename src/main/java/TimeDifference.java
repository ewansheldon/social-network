import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeDifference {
    public static final int MINUTE = 60;
    public static final int HOUR = 60 * 60;
    public static final int DAY = 60 * 60 * 24;

    public String format(LocalDateTime date) {
        long secondsDiff = date.until(LocalDateTime.now(), ChronoUnit.SECONDS);

        if (secondsDiff >= DAY) {
            return secondsDiff / DAY + " days ago";
        }

        if (secondsDiff >= HOUR) {
            return secondsDiff / HOUR + " hours ago";
        }

        if (secondsDiff >= MINUTE) {
            return secondsDiff / MINUTE + " minutes ago";
        }

        return secondsDiff + " seconds ago";
    }
}
