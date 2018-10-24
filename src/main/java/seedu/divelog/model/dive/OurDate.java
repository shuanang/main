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
    /*
    * Given Date in DDMMYYYY String, returns in long
     */
    public long getOurDateLong() {
        String arr[] = value.split("");
        long dateLong = Long.parseLong(arr[0]) * 100000
                + Long.parseLong(arr[1]) * 10000
                + Long.parseLong(arr[2]) * 1000
                + Long.parseLong(arr[3]) * 100
                + Long.parseLong(arr[4]) * 10
                + Long.parseLong(arr[5]);
        return dateLong;
    }
}


