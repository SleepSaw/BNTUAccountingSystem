package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button exitButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button optionsButton;

    @FXML
    private Button salaryButton;

    @FXML
    void exitButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loadButtonAction(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(EmployeeInfoController.class.getResource("/gui/load.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Педагогическая нагрузка");
            stage.show();
    }

    @FXML
    void optionsButtonAction(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(EmployeeInfoController.class.getResource("/gui/employee-info.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Список сотрудников");
            stage.show();
    }

    @FXML
    void salaryButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EmployeeInfoController.class.getResource("/gui/salary.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Оклады сотрудников");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
