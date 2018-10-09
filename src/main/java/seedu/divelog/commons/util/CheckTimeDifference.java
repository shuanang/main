package seedu.divelog.commons.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.divelog.model.dive.Time;

public class CheckTimeDifference {

    /**
     * Tells time difference between 2 timings in MINUTES (long)
     */
    public long CheckTimeDifference(Time start, Time end) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("HHMM");
        Date firstDate = format.parse(start.getTimeString());
        Date secondDate = format.parse(end.getTimeString());
        long difference = (secondDate.getTime() - firstDate.getTime());
        return difference/1000;
    }
    
}
