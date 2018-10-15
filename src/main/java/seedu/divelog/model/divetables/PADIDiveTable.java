package seedu.divelog.model.divetables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import seedu.divelog.MainApp;
import seedu.divelog.commons.core.LogsCenter;
import seedu.divelog.commons.util.DiveTableUtil;
import seedu.divelog.model.dive.DepthProfile;
import seedu.divelog.model.dive.PressureGroup;

/**
 * This class loads dive tables
 */
public class PADIDiveTable {

    private static final Logger logger = LogsCenter.getLogger(PADIDiveTable.class);
    private static PADIDiveTable diveTable = new PADIDiveTable();

    private final DiveTableUtil surfaceTable;
    private final DiveTableUtil depthToPressureGroup;
    private final DiveTableUtil diveTableUtil;

    private PADIDiveTable() {
        this.surfaceTable = new DiveTableUtil("divetables/surface_table.json");
        this.depthToPressureGroup = new DiveTableUtil("divetables/Dive_table_1.json");
        this.diveTableUtil = new DiveTableUtil("divetables/Dive_table_2.json");
        logger.info("Successfully loaded dive tables");
    }

    public static PADIDiveTable getInstance() {
        return diveTable;
    }


    /**
     * Looks up surface interval table
     * @param pressureGroup1 - Pressure group along x axis
     * @param pressureGroup2 - Pressure goup along y axis
     * @return returns a JSON Array with the surface intervals
     */
    public JSONArray getSurfaceTable(PressureGroup pressureGroup1, PressureGroup pressureGroup2) {
        try {
            logger.info("Attempting to read json");
            JSONObject table = surfaceTable.readJSONFileFromResources();
            logger.info("attempting to lookup data");
            JSONObject column = table.getJSONObject(pressureGroup1.getPressureGroup());
            return column.getJSONArray(pressureGroup2.getPressureGroup());
        } catch (JSONException json) {
            logger.severe("Malformatted json");
        } catch (IOException e) {
            logger.severe("Failed to read dive tables due to an IOException\n\t"+e.getMessage());
        }
        return null;
    }

    /**
     * Looks up depth to PG
     * @param depth - The depth
     * @param duration - minutes;
     */
    public PressureGroup depthToPG(DepthProfile depth, int duration) {
        try {
            logger.info("Attempting to read json");
            JSONObject table = depthToPressureGroup.readJSONFileFromResources();
            logger.info("attempting to lookup data");
            String key = findClosestKey(table, depth.getDepth());
            JSONObject column = table.getJSONObject(key);
            key = findClosestKey(column, duration);
            String pressureGroup = column.getString(key);
            return new PressureGroup(pressureGroup);
        } catch (IOException io) {
            logger.severe("Failed to read dive tables due to an IOException\n\t"+io.getMessage());
        } catch (JSONException json) {
            logger.severe("Failed to parse JSON");
        }

        return null;
    }

    /**
     * Reads the depth and pressure group and returns an array containing the minimum time
     * @param depth - depth dove to
     * @param pressureGroup - pressure group at time
     * @return an array containing
     */
    public JSONArray depthToTimes(DepthProfile depth, PressureGroup pressureGroup) {
        try {
            JSONObject table = diveTableUtil.readJSONFileFromResources();
            String key = findClosestKey(table, depth.getDepth());
            JSONObject column = table.getJSONObject(key);
            return column.getJSONArray(pressureGroup.getPressureGroup());
        } catch (IOException io) {
            logger.severe("Failed to read dive tables due to an IOException\n\t"+io.getMessage());
        } catch (JSONException json) {
            logger.severe("Failed to parse JSON");
        }
        return null;

    }

    /**
     * Looks for the nearest key
     * @param object - JSON Object
     * @param key - The key that is nearest to that value.
     */
    public static String findClosestKey(JSONObject object, float key) {
        Iterator<String> keys = object.keys();
        ArrayList<Integer> integerKeys = new ArrayList<Integer>();
        while (keys.hasNext()) {
            String curr = keys.next();
            integerKeys.add(Integer.parseInt(curr));
        }
        Collections.sort(integerKeys);
        int i = 0;
        while (key > integerKeys.get(i)) {
            i++;
        }
        return integerKeys.get(i).toString();
    }
}
