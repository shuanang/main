package seedu.divelog.logic.pressuregroup;
//@@author shuanang
import org.json.JSONArray;
import org.json.JSONException;

import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.divetables.PadiDiveTable;

/**
 * Calculates the pressure group given depth and minutes spent at depth for single/repeat dives.
 */
public class PressureGroup {
    private final String pressureGroup;
    private String newPg = " ";
    private int totalBottomTime = 0;

    public PressureGroup(String pressureGroup, int newDepth, int minutesRepeatDive) throws JSONException {
        assert pressureGroup.length() == 1;
        assert Character.isLetter(pressureGroup.charAt(0));
        this.pressureGroup = pressureGroup.toUpperCase();
        PadiDiveTable padiDiveTable = PadiDiveTable.getInstance();
        JSONArray arr = padiDiveTable.depthToTimes(new DepthProfile(newDepth), new seedu.divelog.model.dive.PressureGroup(pressureGroup));
        assert minutesRepeatDive <= arr.getInt(1);
        this.totalBottomTime = arr.getInt(0) + minutesRepeatDive;
        seedu.divelog.model.dive.PressureGroup pg = padiDiveTable.depthToPg(new DepthProfile(newDepth), this.totalBottomTime);
        newPg = pg.getPressureGroup();
    }

    public String getPressureGroup() {
        return pressureGroup;
    }

    public String getNewPressureGroup() {
        return newPg;
    }
}
