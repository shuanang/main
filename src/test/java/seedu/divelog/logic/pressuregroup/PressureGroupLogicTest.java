package seedu.divelog.logic.pressuregroup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.Test;

import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.Location;
import seedu.divelog.model.dive.OurDate;
import seedu.divelog.model.dive.PressureGroup;
import seedu.divelog.model.dive.Time;
import seedu.divelog.model.dive.TimeZone;
import seedu.divelog.model.dive.exceptions.InvalidTimeException;


//@@author shuanang
public class PressureGroupLogicTest {

    @Test
    public void computePressureGroup_test() throws JSONException {

        PressureGroup pressureGroup = PressureGroupLogic.computePressureGroup(new DepthProfile(14),
                50, new PressureGroup("d"));
        assertEquals(pressureGroup.getPressureGroup(), "T");
    }

    @Test
    public void computePressureGroupAfterSurfaceInterval_test() {
        PressureGroup pressureGroup =
                PressureGroupLogic.computePressureGroupAfterSurfaceInterval(new PressureGroup("H"), 27);
        assertEquals(pressureGroup.getPressureGroup(), "E");
    }

    /*@Test
    public void computeTimeToNextPg_test() throws InvalidTimeException, JSONException {
        PressureGroup pressureGroup = PressureGroupLogic.computeTimetoNextPg(new DiveSession(new OurDate("04082018"),
                new Time("0700"),
                new Time("0945"),
                new OurDate("04082018"),
                new Time("1000"),
                new PressureGroup("A"),
                new PressureGroup("R"),
                new Location("Bali"),
                new DepthProfile(10.0f),
                new TimeZone("+5")));
    } */
}
