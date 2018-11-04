package seedu.divelog.commons.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//@@author Cjunx
/**
 * A class with methods to compare Date and Time, with respect to Timezone
 */
public class CompareUtil {
    /**
     * Tells time difference between 2 timings in MINUTES (long)
     */
    public static long checkTimeDifference(String startTime, String endTime, String startDate, String endDate)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmm");
        String startTimeDate = startDate.concat(startTime);
        String endTimeDate = endDate.concat(endTime);
        Date date1 = format.parse(startTimeDate);
        Date date2 = format.parse(endTimeDate);
        long diff = Math.abs((date1.getTime() - date2.getTime())) / 60000;

        return (diff);
    }

    /**
     * Converts date and time into LOCAL time
     * returns a java date object
     */
    public static Date getLocalDate(String time, String date, int timezone) throws Exception {
        String timeNowString = date.concat(time);
        SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMyyyyHHmm");
        Date oldTime = inputFormat.parse(timeNowString);
        Date newTime = new Date(oldTime.getTime() + TimeUnit.HOURS.toMillis(timezone));
        return newTime;
    }

    /**
     * Converts date and time into LOCAL time
     * returns first 8 digits of Date in DDMMYYYY, next 4 digits in HHMM
     */
    public static Long convertTimeToLocal(String time, String date, int timezone) throws Exception {
        String timeNowString = date.concat(time);
        SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMyyyyHHmm");
        Date oldTime = inputFormat.parse(timeNowString);
        Date newTime = new Date(oldTime.getTime() + TimeUnit.HOURS.toMillis(timezone));

        String newDateTime = new SimpleDateFormat("ddMMyyyyHHmm").format(newTime);
        long newDateTimeLong = Long.parseLong(newDateTime);

        return newDateTimeLong;
    }

    /**
     * gets current date and time in Date
     */
    public static Date getCurrentDateTime() {
        Date date = new Date();
        //        String newDateTime = new SimpleDateFormat("ddMMyyyyHHmm").format(date);
        //        long newDateTimeLong = Long.parseLong(newDateTime);
        return date;
    }

    /**
     * gets current date and time in long
     */
    public static long getCurrentDateTimeLong() {
        Date currentDateTime = getCurrentDateTime();
        String currentDateTimeString = new SimpleDateFormat("ddMMyyyyHHmm").format(currentDateTime);
        long currentDateTimeLong = Long.parseLong(currentDateTimeString);

        return currentDateTimeLong;
    }

    /**
     * Converts DDMMYYYYHHMM into DDMMYYYY
     */
    public static String readTimeFromLong(long date) {
        long time;
        time = date % 10000;
        String timeString = Long.toString(time);
        if (time < 1000) {
            timeString = "0" + timeString;
        }
        return timeString;
    }

    /**
     * Converts DDMMYYYYHHMM into HHMM
     */
    public static String readDateFromLong(long longdate) {
        long date;
        date = longdate / 10000;
        String dateString = Long.toString(date);
        if (date < 10000000) {
            dateString = "0" + dateString;
        }
        return dateString;
    }



}
