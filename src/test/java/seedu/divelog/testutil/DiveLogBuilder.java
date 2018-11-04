package seedu.divelog.testutil;

import seedu.divelog.model.DiveLog;
import seedu.divelog.model.dive.DiveSession;

/**
 * A utility class to help with building DiveLog objects.
 * Example usage: <br>
 *     {@code DiveLog ab = new DiveLogBuilder().withDiveSession("John", "Doe").build();}
 */
public class DiveLogBuilder {

    private DiveLog diveLog;

    public DiveLogBuilder() {
        diveLog = new DiveLog();
    }

    public DiveLogBuilder(DiveLog diveLog) {
        this.diveLog = diveLog;
    }

    /**
     * Adds a new {@code DiveSession} to the {@code DiveLog} that we are building.
     * @param dive
     */
    public DiveLogBuilder withDive(DiveSession dive) {
        diveLog.addDive(dive);
        return this;
    }

    public DiveLog build() {
        return diveLog;
    }
}
