package seedu.divelog.logic.pressuregroup;
//@@author shuanang
import org.json.JSONArray;
import org.json.JSONException;

import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.PressureGroup;
import seedu.divelog.model.divetables.PadiDiveTable;

/**
 * Calculates the pressure group given depth and minutes spent at depth for single/repeat dives.
 */
class PressureGroupLogic {
    PressureGroupLogic(){}
    /**
     * Calculates the pressure group given depth and minutes spent at depth for repeat dives.
     */
    static PressureGroup computePressureGroup(DepthProfile depth,
                                              float duration, PressureGroup pg) throws JSONException {
        PadiDiveTable padiDiveTable = PadiDiveTable.getInstance();
        JSONArray arr = padiDiveTable.depthToTimes(depth, pg);
        assert (int) duration <= arr.getInt(1);
        int totalBottomTime;
        totalBottomTime = arr.getInt(0) + (int) duration;
        pg = padiDiveTable.depthToPg(depth, totalBottomTime);
        return pg;
    }

    /**
     * Calculates the pressure group after a given time the user has emerged from the water.
     */
    static PressureGroup computePressureGroupAfterSurfaceInterval(PressureGroup pg, float duration) {
        //WORK IN PROGRESS
        return pg;
    }
}
