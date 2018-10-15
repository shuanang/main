package seedu.divelog.commons.util;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

/**
 * Loads dive tables from JSON files
 */
public class DiveTableUtil {
    private static ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules()
            .registerModule(new JsonOrgModule());

    private final String filename;

    public DiveTableUtil(String filename) {
        this.filename = filename;
    }

    /**
     * Retrieves a JSONObject file from resources
     * @return JSONObject containing the dive tables
     * @throws IOException if file format is wrong.
     */
    public JSONObject readJSONFileFromResources() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
        JSONObject jsonObject = objectMapper.readValue(file, JSONObject.class);
        return jsonObject;
    }
}
