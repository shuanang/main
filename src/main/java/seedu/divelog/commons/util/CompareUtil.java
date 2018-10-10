package seedu.divelog.commons.util;

import seedu.divelog.model.dive.OurDate;
import seedu.divelog.model.dive.Time;
import seedu.divelog.model.dive.TimeZone;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A class with methods to compare Date and Time, with respect to Timezone
 */
public class CompareUtil {

    /**
     * Tells time difference between 2 timings in MINUTES (long)
     */
    public long checkTimeDifference(Time startTime, Time endTime, OurDate startDate, OurDate endDate, TimeZone timeZone)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("DDMMYYYYHHMM");
        String startTimeDate = startDate.getOurDateString().concat(startTime.getTimeString());
        String endTimeDate = endDate.getOurDateString().concat(startTime.getTimeString());


        Date time1 = format.parse(startTimeDate);
        Date time2 = format.parse(endTimeDate);

        long difference = (time2.getTime() - time1.getTime());
        return (difference / 1000);
    }

    /**
     * Converts date and time into LOCAL time
     */
    public Date convertTimeToLocal(Time time, OurDate date, TimeZone timezone) throws Exception {
        String timeNowString = date.getOurDateString().concat(time.getTimeString());

        SimpleDateFormat inputFormat = new SimpleDateFormat("DDMMYYYYHHMM");
        Date oldTime = inputFormat.parse(timeNowString);
        Date newtime = new Date(oldTime.getTime() + TimeUnit.HOURS.toMillis(timezone.getTimeZoneString()));

        return newtime;
    }


}
