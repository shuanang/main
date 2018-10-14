package seedu.divelog.testutil;

import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.Location;
import seedu.divelog.model.dive.OurDate;
import seedu.divelog.model.dive.PressureGroup;
import seedu.divelog.model.dive.Time;
import seedu.divelog.model.dive.TimeZone;
import seedu.divelog.model.util.SampleDataUtil;

/**
 * @author arjo129
 * A utility class to help with building Person objects.
 */
public class DiveSessionBuilder {

    public static final String DEFAULT_START = "0700";
    public static final String DEFAULT_END = "0800";
    public static final String DEFAULT_SAFETY_STOP = "0745";
    public static final String DEFAULT_PG_START = "A";
    public static final String DEFAULT_PG_END = "F";
    public static final String DEFAULT_LOCATION = "Bali";
    public static final String DEFAULT_DATE_START = "01012019";
    public static final String DEFAULT_DATE_END = "01012019";
    public static final String DEFAULT_TIMEZONE = "-8";
    public static final float DEFAULT_DEPTH = 5;

    private Time start;
    private Time safetyStop;
    private Time end;
    private PressureGroup pressureGroupAtBeginning;
    private PressureGroup pressureGroupAtEnd;
    private Location location;
    private DepthProfile depthProfile;
    private OurDate dateEnd;
    private OurDate dateStart;
    private final TimeZone timezone;
    public DiveSessionBuilder() {
        start = new Time(DEFAULT_START);
        end = new Time(DEFAULT_END);
        safetyStop = new Time(DEFAULT_SAFETY_STOP);
        pressureGroupAtBeginning = new PressureGroup(DEFAULT_PG_START);
        pressureGroupAtEnd = new PressureGroup(DEFAULT_PG_END);
        location = new Location(DEFAULT_LOCATION);
        depthProfile = new DepthProfile(DEFAULT_DEPTH);
        dateEnd = new OurDate(DEFAULT_DATE_START);
        dateStart = new OurDate(DEFAULT_DATE_END);
        timezone = new TimeZone(DEFAULT_TIMEZONE);
    }

    /**
     * Initializes the DiveSessionBuilder with the data of {@code diveToCopy}.
     */
    public DiveSessionBuilder(DiveSession diveToCopy) {
        start = diveToCopy.getStart();
        end = diveToCopy.getEnd();
        safetyStop = diveToCopy.getSafetyStop();
        pressureGroupAtEnd = diveToCopy.getPressureGroupAtEnd();
        pressureGroupAtBeginning = diveToCopy.getPressureGroupAtBeginning();
        location = diveToCopy.getLocation();
        depthProfile = diveToCopy.getDepthProfile();
        dateEnd = diveToCopy.getDateEnd();
        dateStart = diveToCopy.getDateStart();
        timezone = diveToCopy.getTimeZone();
    }
    /**
     * Sets the start time of the {@code DiveSession} that we are building.
     */
    public DiveSessionBuilder withStart(String time) {
        this.start = new Time(time);
        return this;
    }

    /**
     * Sets the end time of the {@code DiveSession} that we are building.
     */
    public DiveSessionBuilder withEnd(String time) {
        this.end = new Time(time);
        return this;
    }

    /**
     * Sets the safety stop time of the {@code DiveSession} that we are building.
     */
    public DiveSessionBuilder withSafetyStop(String time) {
        this.safetyStop = new Time(time);
        return this;
    }

    /**
     * Sets the starting pressure group of the {@code DiveSession} that we are building.
     */
    public DiveSessionBuilder withPressureGroupAtBeginning(String pressureGroup) {
        this.pressureGroupAtBeginning = new PressureGroup(pressureGroup);
        return this;
    }

    /**
     * Sets the ending pressure group of the {@code DiveSession} that we are building.
     */
    public DiveSessionBuilder withPressureGroupAtEnd(String pressureGroup) {
        this.pressureGroupAtEnd = new PressureGroup(pressureGroup);
        return this;
    }

    /**
     * Sets the location of the {@code DiveSession} that we are building.
     */
    public DiveSessionBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the depth of the {@code DiveSession} that we are building.
     */
    public DiveSessionBuilder withDepth(float depth) {
        this.depthProfile = new DepthProfile(depth);
        return this;
    }

    /**
     * Builds the DiveSession object
     * @return DiveSession
     */
    public DiveSession build() {
        return new DiveSession(dateStart, start, safetyStop, dateEnd, end,
                pressureGroupAtBeginning, pressureGroupAtEnd, location, depthProfile,
                timezone);
    }

}
