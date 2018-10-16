package seedu.divelog.model.dive;

//@@author arjo129

import org.json.JSONArray;
import org.json.JSONException;

import seedu.divelog.model.divetables.PadiDiveTable;

/**
 * Represents a pressure group
 */
public class PressureGroup {

    private static final String PRESSURE_GROUP_VALIDATION_REGEX = "([A-Z||a-z])";
    private final String pressureGroup;
    private String newPG = " ";
    private int totalBottomTime = 0;

    /**
     * Constructs a pressure group object.
     * @param pressureGroup A String that must be 1 alphabetical character in length
     */
    public PressureGroup(String pressureGroup) {
        assert pressureGroup.length() == 1;
        assert Character.isLetter(pressureGroup.charAt(0));
        this.pressureGroup = pressureGroup.toUpperCase();
    }

    //@@author shuanang
    public PressureGroup(String pressureGroup, int newDepth, int minutesRepeatDive) throws JSONException {
        assert pressureGroup.length() == 1;
        assert Character.isLetter(pressureGroup.charAt(0));
        this.pressureGroup = pressureGroup.toUpperCase();
        PadiDiveTable padiDiveTable = PadiDiveTable.getInstance();
        JSONArray arr = padiDiveTable.depthToTimes(new DepthProfile(newDepth), new PressureGroup(pressureGroup));
        assert minutesRepeatDive <= arr.getInt(1);
        this.totalBottomTime = arr.getInt(0) + minutesRepeatDive;
        PressureGroup pg = padiDiveTable.depthToPg(new DepthProfile(newDepth), this.totalBottomTime);
        newPG = pg.getPressureGroup();
    }

    public String getPressureGroup() {
        return pressureGroup;
    }

    public String getNewPressureGroup() {
        return newPG;
    }

    /**
     * Checks if a string is a valid pressure group. A valid pressure group consists only
     * of 1 alphabetical character.
     * @param token
     * @return The validity of the string
     */
    public static boolean isValid(String token) {
        return token.matches(PRESSURE_GROUP_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PressureGroup)) {
            return false;
        }
        PressureGroup pg = (PressureGroup) obj;
        return pg.getPressureGroup().equals(pressureGroup);
    }
}
