package org.bntu.accounting.bntuaccountingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bntu.accounting.bntuaccountingsystem.controllers.AddEmployeeController;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/employee-info.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Страница с верхним меню");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}