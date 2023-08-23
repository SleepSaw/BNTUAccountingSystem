package org.bntu.accounting.bntuaccountingsystem.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileReader {
    public JSONObject readJsonFile(String jsonFilePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            return new JSONObject(content);
        } catch (IOException e) {
            System.err.println("Error in reading JSON: " + e.getMessage());
        }
        return null;
    }
    public Object getValueFromJson(String key, JSONObject jsonData){
        try {
            return jsonData.getString(key);
        } catch (JSONException e) {
            System.err.println("Error: Impossible read data from JSON.");
            return null;
        }
    }

}
