package seedu.divelog.model.dive;

/**
 * This class represents a single dive session
 */
public class DiveSession {
    private final Time start;
    private final Time safetyStop;
    private final Time end;
    private final PressureGroup pressureGroupAtBeginning;
    private final PressureGroup pressureGroupAtEnd;
    private final Location location;
    private final DepthProfile depthProfile;

    public DiveSession(Time start, Time safetyStop, Time end, PressureGroup pressureGroupAtBeginning,
                       PressureGroup pressureGroupAtEnd, Location location, DepthProfile depthProfile) {
        this.start = start;
        this.safetyStop = safetyStop;
        this.end = end;
        this.pressureGroupAtBeginning = pressureGroupAtBeginning;
        this.pressureGroupAtEnd = pressureGroupAtEnd;
        this.location = location;
        this.depthProfile = depthProfile;
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

    public Time getSafetyStop() {
        return safetyStop;
    }

    public DepthProfile getDepthProfile() {
        return depthProfile;
    }

    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param dive
     * @return
     */
    public boolean isSameDiveSession(DiveSession dive) {
        return dive == this;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof DiveSession)) {
            return false;
        }
        return super.equals(obj);
    }
}
