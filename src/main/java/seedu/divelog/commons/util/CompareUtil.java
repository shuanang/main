package seedu.divelog.commons.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import seedu.divelog.model.dive.OurDate;
import seedu.divelog.model.dive.Time;
import seedu.divelog.model.dive.TimeZone;

/**
 * A class with methods to compare Date and Time, with respect to Timezone
 * @@author Cjunx
 */
public class CompareUtil {
    /**
     * Tells time difference between 2 timings in MINUTES (long)
     */
    public long checkTimeDifference(String startTime, String endTime, String startDate, String endDate)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("DDMMYYYYHHMM");
        String startTimeDate = startDate.concat(startTime);
        String endTimeDate = endDate.concat(endTime);

        Date time1 = format.parse(startTimeDate);
        Date time2 = format.parse(endTimeDate);

        long difference = (time2.getTime() - time1.getTime());
        return (difference / 1000);
    }

    /**
     * Converts date and time into LOCAL time
     * returns first 8 digits of Date in DDMMYYYY, next 4 digits in HHMM
     */
    public Long convertTimeToLocal(Time time, OurDate date, TimeZone timezone) throws Exception {
        String timeNowString = date.getOurDateString().concat(time.getTimeString());

        SimpleDateFormat inputFormat = new SimpleDateFormat("DDMMYYYYHHMM");
        Date oldTime = inputFormat.parse(timeNowString);
        Date newtime = new Date(oldTime.getTime() + TimeUnit.HOURS.toMillis(timezone.getTimeZone()));

        String newDateTime = new SimpleDateFormat("DDMMYYYYHHMM").format(newtime);
        long newDateTimeLong = Integer.parseInt(newDateTime);

        return newDateTimeLong;
    }

    public Date getCurrentDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("DDMMYYYYHHMM");
        Date date = new Date();
        return date;
    }

    public long getCurrentDateTimeLong(){
        Date currentDateTime = getCurrentDateTime();
        String currentDateTimeString = new SimpleDateFormat("DDMMYYYYHHMM").format(currentDateTime);
        long currentDateTimeLong = Integer.parseInt(currentDateTimeString);

        return currentDateTimeLong;
    }

    public long readTimeFromLong(long DDMMYYYYHHMM){
        long time;
        time = DDMMYYYYHHMM%10000;
        return time;
    }

    public long readDateFromLong(long DDMMYYYYHHMM){
        long date;
        date = DDMMYYYYHHMM/10000;
        return date;
    }
}
