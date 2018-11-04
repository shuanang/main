package seedu.divelog.model.dive;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import seedu.divelog.commons.core.LogsCenter;
import seedu.divelog.commons.util.CompareUtil;
import seedu.divelog.logic.pressuregroup.PressureGroupLogic;
import seedu.divelog.logic.pressuregroup.exceptions.LimitExceededException;
import seedu.divelog.model.dive.exceptions.InvalidTimeException;

/**
 * This class represents a single dive session
 */
public class DiveSession implements Comparable {
    private final OurDate dateStart;
    private final Time start;
    private final Time safetyStop;
    private final OurDate dateEnd;
    private final Time end;
    private final Location location;
    private final DepthProfile depthProfile;
    private final TimeZone timezone;
    private Date dateTime;
    //These are recomputed later
    private PressureGroup pressureGroupAtBeginning;
    private PressureGroup pressureGroupAtEnd;


    public DiveSession(
            OurDate dateStart, Time start, Time safetyStop,
            OurDate dateEnd, Time end,
            PressureGroup pressureGroupAtBeginning,
            PressureGroup pressureGroupAtEnd, Location location,
            DepthProfile depthProfile, TimeZone timezone) {
        this.dateStart = dateStart;
        this.start = start;
        this.safetyStop = safetyStop;
        this.dateEnd = dateEnd;
        this.end = end;
        this.pressureGroupAtBeginning = pressureGroupAtBeginning;
        this.pressureGroupAtEnd = pressureGroupAtEnd;
        this.location = location;
        this.depthProfile = depthProfile;
        this.timezone = timezone;
        String dateTimeString = this.getDateStart().getOurDateString().concat(this.getStart().getTimeString());
        try {
            this.dateTime = new SimpleDateFormat("ddMMyyyyHHmm").parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public OurDate getDateStart() {
        return dateStart;
    }

    public OurDate getDateEnd() {
        return dateEnd;
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }

    public PressureGroup getPressureGroupAtBeginning() {
        return pressureGroupAtBeginning;
    }

    public PressureGroup getPressureGroupAtEnd() {
        return pressureGroupAtEnd;
    }

    public void setPressureGroupAtBeginning(PressureGroup pressureGroup) {
        pressureGroupAtBeginning = pressureGroup;
    }

    public Time getSafetyStop() {
        return safetyStop;
    }

    public DepthProfile getDepthProfile() {
        return depthProfile;
    }

    public Location getLocation() {
        return location;
    }

    public TimeZone getTimeZone() {
        return timezone;
    }

    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Gets the dive start time as a java Date object
     * @return The date as the system's locale percieves it.
     */
    public Date getDiveLocalDate() throws Exception {
        String date = getDateStart().getOurDateString();
        String time = getStart().getTimeString();
        int timeZone = getTimeZone().getTimeZone();
        return CompareUtil.getLocalDate(time, date, timeZone);
    }


    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof DiveSession)) {
            return false;
        }

        DiveSession other = (DiveSession) obj;

        return other.getLocation().equals(getLocation())
                && other.getDateStart().equals(getDateStart())
                && other.getDateEnd().equals(getDateEnd())
                && other.getStart().equals(getStart())
                && other.getSafetyStop().equals(getSafetyStop())
                && other.getPressureGroupAtBeginning().equals(getPressureGroupAtBeginning())
                && other.getPressureGroupAtEnd().equals(getPressureGroupAtEnd())
                && other.getEnd().equals(getEnd())
                && other.getDepthProfile().equals(getDepthProfile())
                && other.getTimeZone().equals(getTimeZone());
    }

    /**
     * Recomputes the end pressure group given Non-Repeated Dive
     * @throws LimitExceededException when dives exceed threshold
     * @throws InvalidTimeException when time given is in invalid format. This should never happen.
     */
    public void computePressureGroupNonRepeated() throws LimitExceededException, InvalidTimeException {
        try {
            float actualBottomTime = CompareUtil.checkTimeDifference(start.getTimeString(), end.getTimeString(),
                dateStart.getOurDateString(), dateEnd.getOurDateString());
            pressureGroupAtEnd = PressureGroupLogic.computePressureGroupFirstDive(depthProfile, actualBottomTime);
        } catch (LimitExceededException l) {
            throw l;
        } catch (Exception e) {
            //Shouldn't ever be reached
            Logger log = LogsCenter.getLogger(DiveSession.class);
            log.severe("Something went wrong with the time format");
            throw new InvalidTimeException();
        }
    }

    /**
     * Recomputes the end pressure group given Repeated Dive
     * @throws LimitExceededException when it exceeds the thresholds
     * @throws InvalidTimeException when time given is in invalid format. This should never happen.
     */
    public void computePressureGroupComputeRepeated() throws LimitExceededException, InvalidTimeException {
        try {
            float actualBottomTime = CompareUtil.checkTimeDifference(start.getTimeString(), end.getTimeString(),
                    dateStart.getOurDateString(), dateEnd.getOurDateString());
            pressureGroupAtEnd = PressureGroupLogic.computePressureGroup(depthProfile, actualBottomTime,
                    pressureGroupAtBeginning);
        } catch (LimitExceededException l) {
            throw l;
        } catch (Exception e) {
            //Shouldn't ever be reached
            Logger log = LogsCenter.getLogger(DiveSession.class);
            log.severe("Something went wrong with the time format");
            throw new InvalidTimeException();
        }
    }

    /**
     * Measures the time between different DiveSessions.
     * @return time in minutes between dive sessions
     * @throws InvalidTimeException When time format is incorrect. Should never reach this stage.
     */
    public float getTimeBetweenDiveSession(DiveSession other) throws InvalidTimeException {
        try {
            return CompareUtil.checkTimeDifference(end.getTimeString(), other.start.getTimeString(),
                    dateEnd.getOurDateString(), other.dateStart.getOurDateString());
        } catch (Exception e) {
            Logger log = LogsCenter.getLogger(DiveSession.class);
            log.severe("Something went wrong with the time format");
            throw new InvalidTimeException();
        }
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dive Location: " + getLocation().getLocationName() + "\n");
        stringBuilder.append("\tStart: " + getDateStart().getOurDateString() + " " + getStart().getTimeString() + "\n");
        stringBuilder.append("\tEnd: " + getDateEnd().getOurDateString() + " " + getEnd().getTimeString() + "\n");
        stringBuilder.append("\tPressureGroup: " + getPressureGroupAtBeginning().getPressureGroup() + "->"
                + getPressureGroupAtEnd().getPressureGroup() + "\n");
        stringBuilder.append("\tDepth: " + getDepthProfile().getDepth() + "\n");
        stringBuilder.append("\tTime Zone:" + getTimeZone().getTimeZoneString());
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Object o) {

        if (!(o instanceof DiveSession)) {
            throw new ClassCastException();
        }

        DiveSession other = (DiveSession) o;
        try {
            Date otherDate = other.getDiveLocalDate();
            Date myDate = getDiveLocalDate();
            return myDate.compareTo(otherDate);
        } catch (Exception e) {
            throw new ClassCastException();
        }
    }
}
