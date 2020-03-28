import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeDifference {
    public static final int MINUTE = 60;
    public static final int HOUR = 60 * 60;
    public static final int DAY = 60 * 60 * 24;

    public String format(LocalDateTime date) {
        long secondsDiff = date.until(LocalDateTime.now(), ChronoUnit.SECONDS);

        if (secondsDiff >= DAY) {
            long days = secondsDiff / DAY;
            return days + " day" + pluralise(days) + " ago";
        }

        if (secondsDiff >= HOUR) {
            long hours = secondsDiff / HOUR;
            return hours + " hour" + pluralise(hours) + " ago";
        }

        if (secondsDiff >= MINUTE) {
            long minutes = secondsDiff / MINUTE;
            return minutes + " minute" + pluralise(minutes) + " ago";
        }

        return secondsDiff + " second" + pluralise(secondsDiff) + " ago";
    }

    private String pluralise(long amount) {
        return amount == 1 ? "" : "s";
    }
}
