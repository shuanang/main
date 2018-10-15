package seedu.divelog.commons.util;

import java.io.File;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

public class DiveUtil {
    public static ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules()
            .registerModule(JsonOrgModule);

    private final String filename;

    public DiveUtil(String filename) {
        this.filename = filename;
    }

    /**
     * Retrieves a JSONObject file from resources
     * @return JSONObject
     */
    public JSONObject readJSONFileFromResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        JSONObject jsonObject = objectMapper.readValue(file,JSONObject.class);
        readJSONFileFromResources()
    }
}
