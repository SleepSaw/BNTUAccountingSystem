package org.bntu.accounting.bntuaccountingsystem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.bntu.accounting.bntuaccountingsystem.util.JsonFileReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BaseController {
    private final String filePath = "src\\main\\resources\\files\\options.json";
    private final JsonFileReader reader = new JsonFileReader();
    protected void initComboBox(ComboBox<String> comboBox, String jsonKey, String defaultValue){
        try {
            JSONObject json = reader.readJsonFile(filePath);
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
        }
    }
}
