package seedu.divelog.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.divelog.model.DiveLog;
import seedu.divelog.model.dive.DiveSession;

/**
 * A utility class containing a list of {@code DiveSession} objects to be used in tests.
 */
public class TypicalDiveSessions {

    public static final DiveSession DIVE_AT_BALI = new DiveSessionBuilder().build();
    public static final DiveSession DIVE_AT_TIOMAN = new DiveSessionBuilder()
            .withLocation("Tioman")
            .withStart("1000")
            .withEnd("1030")
            .build();
    public static final DiveSession DIVE_AT_NIGHT = new DiveSessionBuilder()
            .withLocation("Tioman")
            .withStart("2100")
            .withSafetyStop("2145")
            .withEnd("2200")
            .build();

    public static final String KEYWORD_MATCHING_TIOMAN = "tioman";

    private TypicalDiveSessions() {} // prevents instantiation

    /**
     * Returns an {@code DiveLog} with all the typical dive sessions.
     */
    public static DiveLog getTypicalDiveLog() {
        DiveLog ab = new DiveLog();
        for (DiveSession dive : getTypicalDives()) {
            ab.addDive(dive);
        }
        return ab;
    }

    public static List<DiveSession> getTypicalDives() {
        return new ArrayList<>(Arrays.asList(DIVE_AT_BALI, DIVE_AT_TIOMAN));
    }
}
