package seedu.divelog.model.dive;

/**
 * This class represents a single dive session
 */
public class DiveSession {
    private final Date dateStart;
    private final Time start;
    private final Time safetyStop;
    private final Date dateEnd;
    private final Time end;
    private final PressureGroup pressureGroupAtBeginning;
    private final PressureGroup pressureGroupAtEnd;
    private final Location location;
    private final DepthProfile depthProfile;

    public DiveSession(Date dateStart, Time start, Time safetyStop, Date dateEnd, Time end, PressureGroup pressureGroupAtBeginning,
                       PressureGroup pressureGroupAtEnd, Location location, DepthProfile depthProfile) {
        this.dateStart = dateStart;
        this.start = start;
        this.safetyStop = safetyStop;
        this.dateEnd = dateEnd;
        this.end = end;
        this.pressureGroupAtBeginning = pressureGroupAtBeginning;
        this.pressureGroupAtEnd = pressureGroupAtEnd;
        this.location = location;
        this.depthProfile = depthProfile;
    }

    public Date getDateStart(){
        return dateStart;
    }

    public Date getDateEnd(){
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
}
