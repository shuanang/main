package seedu.divelog.model.divetables;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import seedu.divelog.commons.util.DiveTableUtil;
import seedu.divelog.model.dive.PressureGroup;

public class PADIDiveTableTest {

    @Test
    public void getSurfaceTable_canRead() throws JSONException {
        PADIDiveTable padiDiveTable = PADIDiveTable.getInstance();
        JSONArray arr = padiDiveTable.getSurfaceTable(new PressureGroup("A"), new PressureGroup("A"));
        assertEquals(arr.get(0).toString(),"00:00");
        assertEquals(arr.get(1).toString(),"03:00");
    }
}
