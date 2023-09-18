package application.controllers;

import application.util.JSONFileLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseController {
    private final String filePath = "options.json";
    private final JSONFileLoader loader = new JSONFileLoader();
    protected void initComboBox(ComboBox<String> comboBox, String jsonKey, String defaultValue){
        try {
            JSONObject json = loader.loadJsonFile(filePath);
            JSONArray jsonArray = (JSONArray) json.get(jsonKey);
            List<String> itemsList = new ArrayList<>();
            for (Object item: jsonArray) {
                itemsList.add((String)item);
            }
            ObservableList<String> items = FXCollections.observableList(itemsList);
            comboBox.setItems(items);
            comboBox.setValue(defaultValue);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
