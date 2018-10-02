/**
 * This is the date class:
 * TODO: Implement parsing of date etc.
 */

package seedu.divelog.model.dive;

public class Date {
    private final String value;

    public Date(String date) {
        this.value = date;
    }

    public String getDateString() {
        return value;
    }
}

