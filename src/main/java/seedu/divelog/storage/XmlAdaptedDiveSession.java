package seedu.divelog.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.divelog.commons.exceptions.IllegalValueException;
import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.Location;
import seedu.divelog.model.dive.PressureGroup;
import seedu.divelog.model.dive.Time;

/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedDiveSession {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "DiveSession's %s field is missing!";
    @XmlElement(required = true)
    private String startTime;
    @XmlElement(required = true)
    private String safetyStop;
    @XmlElement(required = true)
    private String endTime;
    @XmlElement(required = true)
    private String pressureGroupAtBeginning;
    @XmlElement(required = true)
    private String pressureGroupAtEnd;
    @XmlElement(required = true)
    private String location;
    @XmlElement(required = true)
    private float depthProfile;
    /**
     * Constructs an XmlAdaptedDiveSession.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedDiveSession() {}

    /**
     * Constructs an {@code XmlAdaptedDiveSession} with the given person details.
     */
    public XmlAdaptedDiveSession(String startTime, String safetyStop, String endTime, String pressureGroupAtBeginning,
                                 String pressureGroupAtEnd, String location, float depthProfile) {
        this.startTime = startTime;
        this.safetyStop = safetyStop;
        this.endTime = endTime;
        this.pressureGroupAtBeginning = pressureGroupAtBeginning;
        this.pressureGroupAtEnd = pressureGroupAtEnd;
        this.location = location;
        this.depthProfile = depthProfile;
    }

    /**
     * Converts a given DiveSession into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedDiveSession
     */
    public XmlAdaptedDiveSession(DiveSession source) {
        this.startTime = source.getStart().getTimeString();
        this.safetyStop = source.getSafetyStop().getTimeString();
        this.endTime = source.getEnd().getTimeString();
        this.location = source.getLocation().getLocationName();
        this.pressureGroupAtBeginning = source.getPressureGroupAtBeginning().getPressureGroup();
        this.pressureGroupAtEnd = source.getPressureGroupAtEnd().getPressureGroup();
        this.depthProfile = source.getDepthProfile().getDepth();
    }

    /**
     * Converts this jaxb-friendly adapted dive session object into the model's DiveSession object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public DiveSession toModelType() {
        return new DiveSession(new Time(startTime), new Time(safetyStop), new  Time(endTime),
                new PressureGroup(pressureGroupAtBeginning), new PressureGroup(pressureGroupAtEnd),
                new Location(location), new DepthProfile(depthProfile));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedDiveSession)) {
            return false;
        }

        XmlAdaptedDiveSession x = (XmlAdaptedDiveSession)other;
        return startTime.equals(x.startTime)
                && endTime.equals(x.endTime)
                && safetyStop.equals(x.safetyStop)
                && location.equals(x.location)
                && pressureGroupAtEnd.equals(x.pressureGroupAtEnd)
                && pressureGroupAtBeginning.equals(x.pressureGroupAtBeginning)
                && depthProfile == x.depthProfile;
    }
}
