package seedu.divelog.model.dive;

import org.junit.Test;

import seedu.divelog.testutil.DiveSessionBuilder;

public class DiveSessionTest {
    @Test
    public void testCompareTo() {
        DiveSession diveSession1 = new DiveSessionBuilder()
                .withStart("1000")
                .withStartDate("01011996")
                .withEnd("1100")
                .withEndDate("01011996").build();
        DiveSession diveSession2 = new DiveSessionBuilder()
                .withStart("1100")
                .withStartDate("01011996")
                .withEnd("1200")
                .withEndDate("01011996").build();
        DiveSession diveSession3 = new DiveSessionBuilder()
                .withStart("1100")
                .withStartDate("01011998")
                .withEnd("1200").build();

    }
}
