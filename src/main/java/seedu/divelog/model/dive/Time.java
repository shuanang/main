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

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Time)) {
            return false;
        }
        Time time = (Time) obj;
        return value.equals(time.getTimeString());
    }
}
