package seedu.divelog.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import seedu.divelog.logic.pressuregroup.exceptions.LimitExceededException;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.DiveSessionList;
import seedu.divelog.model.dive.exceptions.InvalidTimeException;
import seedu.divelog.testutil.DiveSessionBuilder;

public class DiveSessionListTest {
    @Test
    public void recalculatePressureGroup_test() throws LimitExceededException, InvalidTimeException {
        //Test dives with incorrect pressure groups
        DiveSession diveSession1 = new DiveSessionBuilder()
                .withStart("1000")
                .withStartDate("01011996")
                .withEnd("1030")
                .withEndDate("01011996").build();
        DiveSession diveSession2 = new DiveSessionBuilder()
                .withStart("1100")
                .withStartDate("01011996")
                .withEnd("1200")
                .withEndDate("01011996").build();
        DiveSession diveSession3 = new DiveSessionBuilder()
                .withStart("1100")
                .withStartDate("01021996")
                .withEnd("1200")
                .withEndDate("01021996").build();

        //Populate
        DiveSessionList diveSessions = new DiveSessionList();
        diveSessions.add(diveSession1);
        diveSessions.add(diveSession2);
        diveSessions.add(diveSession3);
        diveSessions.recalculatePressureGroup();

        //Corrected dives
        DiveSession idealDive1 = new DiveSessionBuilder()
                .withStart("1000")
                .withStartDate("01011996")
                .withEnd("1030")
                .withPressureGroupAtEnd("D")
                .withEndDate("01011996").build();
        DiveSession idealDive2 = new DiveSessionBuilder()
                .withStart("1100")
                .withStartDate("01011996")
                .withPressureGroupAtBeginning("C")
                .withPressureGroupAtEnd("P")
                .withEnd("1200")
                .withEndDate("01011996").build();
        DiveSession idealDive3 = new DiveSessionBuilder()
                .withStart("1100")
                .withStartDate("01021996")
                .withEnd("1200")
                .withEndDate("01021996").build();

        DiveSessionList expected = new DiveSessionList();
        expected.add(idealDive1);
        expected.add(idealDive2);
        expected.add(idealDive3);
        assertEquals(expected, diveSessions);

        DiveSessionList emptyDiveList;

    }
}
