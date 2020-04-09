package social_network.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeDifferenceShould {
    @Test
    void tell_you_if_a_date_is_seconds_ago() {
        TimeDifferenceFormatter timeDiff = new TimeDifferenceFormatter();
        LocalDateTime date = LocalDateTime.now().minus(10, ChronoUnit.SECONDS);

        assertEquals("10 seconds ago", timeDiff.format(date));
    }

    @Test
    void tell_you_if_a_date_is_one_second_ago() {
        TimeDifferenceFormatter timeDiff = new TimeDifferenceFormatter();
        LocalDateTime date = LocalDateTime.now().minus(1, ChronoUnit.SECONDS);

        assertEquals("1 second ago", timeDiff.format(date));
    }

    @Test
    void tell_you_if_a_calendar_date_is_minutes_ago() {
        TimeDifferenceFormatter timeDiff = new TimeDifferenceFormatter();
        LocalDateTime date = LocalDateTime.now().minus(10, ChronoUnit.MINUTES);

        assertEquals("10 minutes ago", timeDiff.format(date));
    }

    @Test
    void tell_you_if_a_date_is_one_minute_ago() {
        TimeDifferenceFormatter timeDiff = new TimeDifferenceFormatter();
        LocalDateTime date = LocalDateTime.now().minus(1, ChronoUnit.MINUTES);

        assertEquals("1 minute ago", timeDiff.format(date));
    }

    @Test
    void tell_you_if_a_calendar_date_is_hours_ago() {
        TimeDifferenceFormatter timeDiff = new TimeDifferenceFormatter();
        LocalDateTime date = LocalDateTime.now().minus(10, ChronoUnit.HOURS);

        assertEquals("10 hours ago", timeDiff.format(date));
    }

    @Test
    void tell_you_if_a_date_is_one_hour_ago() {
        TimeDifferenceFormatter timeDiff = new TimeDifferenceFormatter();
        LocalDateTime date = LocalDateTime.now().minus(1, ChronoUnit.HOURS);

        assertEquals("1 hour ago", timeDiff.format(date));
    }

    @Test
    void tell_you_if_a_calendar_date_is_days_ago() {
        TimeDifferenceFormatter timeDiff = new TimeDifferenceFormatter();
        LocalDateTime date = LocalDateTime.now().minus(10, ChronoUnit.DAYS);

        assertEquals("10 days ago", timeDiff.format(date));
    }

    @Test
    void tell_you_if_a_date_is_one_day_ago() {
        TimeDifferenceFormatter timeDiff = new TimeDifferenceFormatter();
        LocalDateTime date = LocalDateTime.now().minus(1, ChronoUnit.DAYS);

        assertEquals("1 day ago", timeDiff.format(date));
    }
}