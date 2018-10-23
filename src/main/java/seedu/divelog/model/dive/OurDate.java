package seedu.divelog.model.dive;

/**
 * This is the date class:
 * TODO: Implement parsing of date etc.
 * @@author Cjunx
 */

public class OurDate {
    private final String value;

    public OurDate(String date) {
        this.value = date;
    }

    public String getOurDateString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OurDate)) {
            return false;
        }
        return ((OurDate) obj).getOurDateString().equals(value);
    }
}


