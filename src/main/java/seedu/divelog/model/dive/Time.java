package seedu.divelog.model.dive;

/**
 * This is the time class:
 * TODO: Implement parsing of time, locales etc.
 */
public class Time {
    private final String value;

    public Time (String time) {
        this.value = time;
    }

    public String getTimeString() {
        return value;
    }
}
