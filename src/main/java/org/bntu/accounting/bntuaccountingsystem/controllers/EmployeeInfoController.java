package org.bntu.accounting.bntuaccountingsystem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.bntu.accounting.bntuaccountingsystem.excel.readers.TeacherReader;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.io.File;
import java.util.List;

public class EmployeeInfoController {

    @FXML
    private VBox mainBox;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button backButton;
    @FXML
    private Button importButton;

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
    private void initialize() {
        mainBox.setStyle("-fx-background-color: white");
        backButton.setStyle("-fx-background-color: transparent");
        backButton.setCursor(Cursor.HAND);
        backButton.setOnAction(actionEvent -> {
            System.out.println("Back to Main Window");
        });
        importButton.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выберите документ для импорта");
            Stage stage = (Stage) importButton.getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);// Ограничение расширений файлов
            if (selectedFile != null) {
                if (selectedFile.getName().endsWith(".xlsx")) {
                    // Обработка выбранного файла
                    System.out.println("Выбран файл: " + selectedFile.getAbsolutePath());
                } else {
                    System.out.println("Выберите файл с расширением .xlsx");
                }
            }
        });

        fullNameColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("fio"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("post"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("subject"));
        qualificationColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("qualification"));
        workExperienceColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("workExperience"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Teacher,Double>("category"));
        youngSpecialistColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("youngSpecialist"));
        TeacherReader reader = new TeacherReader();
        List<Teacher> teachers = reader.readSheet();
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
        teacherList.addAll(teachers);

        tableView.setItems(teacherList);
    }

}
