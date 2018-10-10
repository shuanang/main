package seedu.divelog.commons.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.divelog.model.dive.Time;
import seedu.divelog.model.dive.OurDate;

/**
 * A class with methods to compare Date and Time
 */
public class CompareUtil {

    /**
     * Tells time difference between 2 timings in MINUTES (long)
     */
    public long checkTimeDifference(Time startTime, Time endTime, OurDate startDate, OurDate endDate)
            throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("DDMMYYYYHHMM");
        String startTimeDate = (startDate.getOurDateString() + startTime.getTimeString());
        String endTimeDate = (endDate.getOurDateString() + startTime.getTimeString());

        Date time1 = format.parse(startTimeDate);
        Date time2 = format.parse(endTimeDate);

        long difference = (time2.getTime() - time1.getTime());
        return (difference / 1000);
    }
}
