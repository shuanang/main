package seedu.divelog.model.dive;

/**
 * This is the timeZone class: assumes UTC +8 default
 * @@author Cjunx
 */
public class TimeZone {
    private int timeZoneDifference;

    public TimeZone(String timezone) {
        try {
            this.timeZoneDifference = Integer.parseInt(timezone);
        } catch (NumberFormatException ex) {
            System.err.println("Illegal Timezone input");
        }
    }

    public int getTimeZone() {
        return timeZoneDifference;
    }
    public String getTimeZoneString() {
        return "" + timeZoneDifference;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimeZone)) {
            return false;
        }
        return ((TimeZone) obj).timeZoneDifference == timeZoneDifference;
    }
}
