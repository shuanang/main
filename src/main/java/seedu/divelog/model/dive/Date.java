package seedu.divelog.model.dive;

/**
 * This is the date class:
 * TODO: Implement parsing of date etc.
 */

public class Date {
    private final String value;

    public Date(String date) {
        this.value = date;
    }

    public String getDateString() {
        return value;
    }
}

