package seedu.divelog.logic.pressuregroup;
//@@author shuanang
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;

import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.DiveSession;
import seedu.divelog.model.dive.PressureGroup;
import seedu.divelog.model.dive.exceptions.InvalidTimeException;
import seedu.divelog.model.divetables.PadiDiveTable;

/**
 * Calculates the pressure group given depth and minutes spent at depth for single/repeat dives.
 */
public class PressureGroupLogic {
    PressureGroupLogic(){}
    /**
     * Calculates the pressure group given depth and minutes spent at depth for repeat dives.
     */
    public static PressureGroup computePressureGroup(DepthProfile depth,
                                              float duration, PressureGroup pg) throws JSONException {
        PadiDiveTable padiDiveTable = PadiDiveTable.getInstance();
        JSONArray arr = padiDiveTable.depthToTimes(depth, pg);
        assert (int) duration <= arr.getInt(1);
        int totalBottomTime;
        totalBottomTime = arr.getInt(0) + (int) duration;
        PressureGroup newPg;
        newPg = padiDiveTable.depthToPg(depth, totalBottomTime);
        return newPg;
    }

    /**
     * Calculates the pressure group after a given time the user has emerged from the water.
     */
    public static PressureGroup computePressureGroupAfterSurfaceInterval(PressureGroup pg, float duration) {
        PadiDiveTable padiDiveTable = PadiDiveTable.getInstance();
        final int minSurfaceTimeMinutesToMinPressureGroup = 3 * 60;
        PressureGroup newPg;
        if (duration >= minSurfaceTimeMinutesToMinPressureGroup) {
            return new PressureGroup("A");
        } else {
            newPg = padiDiveTable.fromSurfaceInterval(pg, duration);
        }
        return newPg;
    }

    /**
     * Calculates the time to next pressure group, returns a float with minutes to next pressure group
     */
    public static PressureGroup computeTimetoNextPg(DiveSession lastDive) throws InvalidTimeException, JSONException {
        //fromsurfaceinterval - ending pressure group + difference in minutes -> current pg
        //To add / clarify : current time cannot be smaller/before the endoflastdivetime
        String endOfLastDiveTimeString = lastDive.getEnd().getTimeString();
        String endOfLastDivePg = lastDive.getPressureGroupAtEnd().getPressureGroup();
        String currentTime = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String endOfLastDiveTimeFormatted = "".concat(String.valueOf(endOfLastDiveTimeString.charAt(0)))
                .concat(String.valueOf(endOfLastDiveTimeString.charAt(1))).concat(":")
                .concat(String.valueOf(endOfLastDiveTimeString.charAt(2)))
                .concat(String.valueOf(endOfLastDiveTimeString.charAt(3)));
        PadiDiveTable padiDiveTable = PadiDiveTable.getInstance();
        float currentTimeInt = padiDiveTable.timeToMinutes(currentTime);
        float endOfLastDiveTimeInt = padiDiveTable.timeToMinutes(endOfLastDiveTimeFormatted);
        float surfaceDuration = currentTimeInt - endOfLastDiveTimeInt;
        String currentPg = computePressureGroupAfterSurfaceInterval(new PressureGroup(endOfLastDivePg), surfaceDuration)
                .getPressureGroup();
        //from current pg to next pg time : next pg arr[0] - difference in minutes (surfaceDuration)
        JSONArray arr = padiDiveTable.getSurfaceTable(new PressureGroup(endOfLastDivePg), new PressureGroup(currentPg));
        String nextPg = String.valueOf(((char) (currentPg.charAt(0) - 1)));
        JSONArray arr1 = padiDiveTable.getSurfaceTable(new PressureGroup(endOfLastDivePg), new PressureGroup(nextPg));
        String toNextPgMinimumTime = arr1.get(0).toString();
        float timeToNextPg = padiDiveTable.timeToMinutes(toNextPgMinimumTime) - surfaceDuration;
        System.out.println("Time to next Pg group, " + nextPg + " : " + timeToNextPg + " minutes");
        return null;
    }
}
