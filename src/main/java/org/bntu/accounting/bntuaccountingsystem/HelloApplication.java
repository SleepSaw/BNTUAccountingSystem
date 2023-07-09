package org.bntu.accounting.bntuaccountingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bntu.accounting.bntuaccountingsystem.excel.readers.LoadReader;
import org.bntu.accounting.bntuaccountingsystem.excel.readers.TeacherReader;
import org.bntu.accounting.bntuaccountingsystem.models.Load;
import org.bntu.accounting.bntuaccountingsystem.models.Salary;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.bntu.accounting.bntuaccountingsystem.services.SalaryService;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employee-info.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Страница с верхним меню");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}