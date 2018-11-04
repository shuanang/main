package seedu.divelog.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.divelog.testutil.TypicalDiveSessions.getTypicalDiveLog;

import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DiveLogTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final DiveLog diveLog = new DiveLog();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), diveLog.getDiveList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        diveLog.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyDiveLog_replacesData() {
        DiveLog newData = getTypicalDiveLog();
        diveLog.resetData(newData);
        assertEquals(newData, diveLog);
    }

    @Test
    public void hasDiveSession_nullDiveSession_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        diveLog.hasDive(null);
    }


    @Test
    public void getDiveSessionList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        diveLog.getDiveList().remove(0);
    }

}
