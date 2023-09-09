package application.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import application.dao.TeacherDAO;
import application.models.Teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeInfoController implements Initializable {
    private TeacherDAO teacherDAO;

    @FXML
    private VBox mainBox;

    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;

    @FXML
    private Button clearButton;
    @FXML
    private MenuItem removeItem;
    @FXML
    private MenuItem editItem;
    @FXML
    private ContextMenu tableContextMenu;

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
    private TableColumn<Teacher, Integer> indexColumn;

    public EmployeeInfoController() {
        teacherDAO = new TeacherDAO();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setRowsIndexes();
        initContextMenu();
        mainBox.setStyle("-fx-background-color: white");

        fullNameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("post"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("subject"));
        qualificationColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("qualification"));
        workExperienceColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("exp"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Double>("category"));
        youngSpecialistColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("youngSpecialist"));
        updateTable();

    }

    protected Teacher getTeacherFromTable(){
        return tableView.getSelectionModel().getSelectedItem();
    }
    // обновление таблицы
    protected void updateTable () {
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
        teacherList.addAll(teacherDAO.findAllTeachers());
        tableView.setItems(teacherList);
    }
    public void initContextMenu(){
        tableView.setRowFactory(new Callback<TableView<Teacher>, TableRow<Teacher>>() {
            @Override
            public TableRow<Teacher> call(TableView<Teacher> tableView) {
                final TableRow<Teacher> row = new TableRow<>();
                row.setOnContextMenuRequested(event -> {
                    if (!row.isEmpty()) {
                        tableContextMenu.show(row, event.getScreenX(), event.getScreenY());
                    }
                });
                return row;
            }
        });
    }

    // инициализация колонки с номерами строк
    protected void setRowsIndexes(){
        indexColumn.setCellFactory(new Callback<TableColumn<Teacher, Integer>, TableCell<Teacher, Integer>>() {
            @Override
            public TableCell<Teacher, Integer> call(TableColumn<Teacher, Integer> param) {
                return new TableCell<Teacher, Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            int rowIndex = getIndex() + 1;
                            setText(String.valueOf(rowIndex));
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }
    // Слушатель для кнопки "Добавить"
    @FXML
    protected void handleButtonAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(EmployeeInfoController.class.getResource("/gui/add_employee.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(loader.load()));
        newStage.setTitle("Новое окно");
        newStage.show();
    }
    @FXML
    void updateButtonAction(ActionEvent event) {
        try {
            updateTable();
        } catch (Exception e) {
            System.out.println("Bad news");
        }
    }
    @FXML
    void editAction(ActionEvent event) throws IOException {
            Teacher selectedTeacher = getTeacherFromTable();
            if (selectedTeacher != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(EmployeeInfoController.class.getResource("/gui/edit_employee.fxml"));
                fxmlLoader.setController(new EditController(selectedTeacher));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Новое окно");
                stage.show();
            }

    }
    @FXML
    void removeAction(ActionEvent event) throws IOException {
        Teacher selectedTeacher = getTeacherFromTable();
        if (selectedTeacher != null) {
            System.out.println(selectedTeacher);
            teacherDAO.removeTeacher(selectedTeacher);
        }
        updateTable();
    }
}
