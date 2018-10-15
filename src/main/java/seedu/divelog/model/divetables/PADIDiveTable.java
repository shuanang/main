package seedu.divelog.model.divetables;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import seedu.divelog.MainApp;
import seedu.divelog.commons.core.LogsCenter;
import seedu.divelog.commons.util.DiveTableUtil;
import seedu.divelog.model.dive.PressureGroup;

/**
 * This class loads dive tables
 */
public class PADIDiveTable {

    private static final Logger logger = LogsCenter.getLogger(PADIDiveTable.class);
    private static PADIDiveTable diveTable = new PADIDiveTable();

    private static DiveTableUtil surfaceTable = new DiveTableUtil("divetables/surface_table.json");
    private static DiveTableUtil depthToPressureGroup;
    private static DiveTableUtil diveTableUtil;

    private PADIDiveTable() {
        //this.surfaceTable = new DiveTableUtil("divetables/surface_table.json");
        //this.depthToPressureGroup = new DiveTableUtil("divetables/Dive_table_1.json");
        //this.diveTableUtil = new DiveTableUtil("divetables/dive_table_2.json");
        logger.info("Successfully loaded dive tables");
    }

    public static PADIDiveTable getInstance() {
        return diveTable;
    }


    /**
     * Looks up surface interval table
     * @param pressureGroup1 - Pressure group along x axis
     * @param pressureGroup2 - Pressure goup along y axis
     * @return returns a JSON Array with the dive tables
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

}
