package seedu.divelog.model.dive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.Test;

//@@author shuanang
public class PressureGroupTest {

    @Test
    public void pressureGroup_repeatDive() throws JSONException {
        PressureGroup pg = new PressureGroup("d", 14, 50);
        assertEquals(pg.getNewPressureGroup(), "T");
    }
}
