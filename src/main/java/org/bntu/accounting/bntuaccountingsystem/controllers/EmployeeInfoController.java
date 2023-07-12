package org.bntu.accounting.bntuaccountingsystem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bntu.accounting.bntuaccountingsystem.excel.readers.TeacherReader;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeInfoController implements Initializable {

    @FXML
    private VBox mainBox;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Teacher, String> positionColumn;

    @FXML
    private TableColumn<Teacher, String> fullNameColumn;

    @FXML
    private TableView<Teacher> tableView;

    @FXML
    private TableColumn<Teacher, String> workExperienceColumn;

    @FXML
    private TableColumn<Teacher, String> qualificationColumn;

    @FXML
    private TableColumn<Teacher, String> youngSpecialistColumn;

    @FXML
    private TableColumn<Teacher, String> subjectColumn;

    @FXML
    private TableColumn<Teacher, Double> categoryColumn;

    @FXML
    protected void handleButtonAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(EmployeeInfoController.class.getResource("/gui/add_employee.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(loader.load()));
        newStage.setTitle("Новое окно");
        newStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainBox.setStyle("-fx-background-color: white");
        backButton.setStyle("-fx-background-color: transparent");
        backButton.setCursor(Cursor.HAND);
        backButton.setOnAction(actionEvent -> {
            try {
                updateTable();
                System.out.println("UPDATE TABLE");
            }
            catch (Exception e){
                System.out.println("Bad news");
            }
        });

        fullNameColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("fio"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("post"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("subject"));
        qualificationColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("qualification"));
        workExperienceColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("workExperience"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Teacher,Double>("category"));
        youngSpecialistColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("youngSpecialist"));
        updateTable();
//        TeacherReader reader = new TeacherReader();
//        List<Teacher> teachers = reader.readSheet();
//        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
//        teacherList.addAll(teachers);
//
//        tableView.setItems(teacherList);
    }
    private void updateTable(){
        TeacherReader reader = new TeacherReader();
        List<Teacher> teachers = reader.readSheet();
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
        teacherList.addAll(teachers);
        tableView.setItems(teacherList);
    }
}
