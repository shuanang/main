package seedu.divelog.model.util;

import seedu.divelog.model.DiveLog;
import seedu.divelog.model.ReadOnlyDiveLog;
import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.Location;
import seedu.divelog.model.dive.OurDate;
import seedu.divelog.model.dive.PressureGroup;
import seedu.divelog.model.dive.Time;
import seedu.divelog.model.dive.TimeZone;


/**
 * Contains utility methods for populating {@code DiveLog} with sample data.
 */
public class SampleDataUtil {
    public static DiveSession[] getSampleDives() {
        return new DiveSession[] {
            new DiveSession(new OurDate("04112018"),
                        new Time("0700"),
                        new Time("0745"),
                        new OurDate("04112018"),
                        new Time("0800"),
                        new PressureGroup("A"),
                        new PressureGroup("T"),
                        new Location("Sentosa"),
                        new DepthProfile(15.0f),
                        new TimeZone("+8")), new DiveSession(new OurDate("05082018"),
                new Time("1100"),
                new Time("1145"),
                new OurDate("05082018"),
                new Time("1200"),
                new PressureGroup("A"),
                new PressureGroup("M"),
                new Location("Bali"),
                new DepthProfile(14.0f),
                new TimeZone("+8")), new DiveSession(new OurDate("05082018"),
                    new Time("1500"),
                    new Time("1545"),
                    new OurDate("05082018"),
                    new Time("1600"),
                    new PressureGroup("A"),
                    new PressureGroup("M"),
                    new Location("Bali"),
                    new DepthProfile(10.0f),
                    new TimeZone("+8")), new DiveSession(new OurDate("08082018"),
                    new Time("1100"),
                    new Time("1145"),
                    new OurDate("08082018"),
                    new Time("1200"),
                    new PressureGroup("A"),
                    new PressureGroup("L"),
                    new Location("Tioman"),
                    new DepthProfile(8.0f),
                    new TimeZone("+8"))
        };
    }

    public static ReadOnlyDiveLog getSampleDiveLog() {
        DiveLog sampleDl = new DiveLog();
        for (DiveSession dive : getSampleDives()) {
            sampleDl.addDive(dive);
        }
        return sampleDl;
    }

}
