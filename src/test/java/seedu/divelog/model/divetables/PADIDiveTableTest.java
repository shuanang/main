package seedu.divelog.model.divetables;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.PressureGroup;

public class PADIDiveTableTest {

    @Test
    public void getSurfaceTable_canRead() throws JSONException {
        PADIDiveTable padiDiveTable = PADIDiveTable.getInstance();
        JSONArray arr = padiDiveTable.getSurfaceTable(new PressureGroup("A"), new PressureGroup("A"));
        assertEquals(arr.get(0).toString(),"00:00");
        assertEquals(arr.get(1).toString(),"03:00");
    }

    @Test
    public void deptToPressuregroup_canRead() throws JSONException {
        PADIDiveTable padiDiveTable = PADIDiveTable.getInstance();
        PressureGroup pg = padiDiveTable.depthToPG(new DepthProfile(10), 122);
        assertEquals(pg.getPressureGroup(),"T");
    }
}
