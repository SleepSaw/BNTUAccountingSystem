package org.bntu.accounting.bntuaccountingsystem.file.excel.creators;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileReader {
    public static JSONObject readJsonFile(String jsonFilePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            return new JSONObject(content);
        } catch (IOException e) {
            System.err.println("Error in reading JSON: " + e.getMessage());
        }
        return null;
    }
    public static String getValueFromJson(String key, JSONObject jsonData){
        try {
            return jsonData.getString(key);
        } catch (JSONException e) {
            System.err.println("Error: Impossible read data from JSON.");
            return null;
        }
    }
}
