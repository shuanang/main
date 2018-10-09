package seedu.divelog.commons.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.divelog.model.dive.Time;

/**
 * A class with methods to compare Date and Time
 */
public class CompareUtil {

    /**
     * Tells time difference between 2 timings in MINUTES (long)
     */
    public long checkTimeDifference(Time start, Time end) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("HHMM");
        Date firstDate = format.parse(start.getTimeString());
        Date secondDate = format.parse(end.getTimeString());
        long difference = (secondDate.getTime() - firstDate.getTime());
        return (difference / 1000);
    }
}
