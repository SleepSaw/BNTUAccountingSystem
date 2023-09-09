package application.controllers;

import application.excel.LoadFileCreator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import application.dao.LoadDAO;
import application.dao.TeacherDAO;
import application.models.Load;
import application.models.Teacher;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoadWindowController implements Initializable {
    private ObservableList<Teacher> teacherList;
    private TeacherDAO teacherDAO;
    private LoadDAO loadDAO;
    @FXML
    private Button saveButton;
    @FXML
    private TableColumn<Teacher, String> academicLoadColumn;

    @FXML
    private TableColumn<Teacher, String> addLoadColumn;

    @FXML
    private TableColumn<Teacher, Integer> indexColumn;

    @FXML
    private TableColumn<Teacher, String> nameColumn;

    @FXML
    private TableColumn<Teacher, String> orgLoadColumn;

    @FXML
    private TableColumn<Teacher, String> postColumn;

    @FXML
    private TableColumn<Teacher, String> subjectColumn;

    @FXML
    private TableColumn<Teacher, String> totalLoadColumn;

    @FXML
    private TableView<Teacher> loadTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherDAO = new TeacherDAO();
        loadDAO = new LoadDAO();
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        postColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPost()));
        subjectColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSubject()));

        academicLoadColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getLoad().getAcademicLoad())));
        addLoadColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getLoad().getAddLoad())));
        orgLoadColumn.setCellValueFactory (data -> new SimpleStringProperty(Double.toString(data.getValue().getLoad().getOrgLoad())));
        totalLoadColumn.setCellValueFactory (data -> new SimpleStringProperty(Double.toString(data.getValue().getLoad().getTotalLoad())));
        setRowsIndexes();
        addTeachersToTable();
        provideEditableForLoad();
    }
    private void provideEditableForLoad(){
        // Установка фабрики ячеек для редактирования
        academicLoadColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addLoadColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        orgLoadColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Установка обработчика события окончания редактирования ячейки
        academicLoadColumn.setOnEditCommit(event -> {
            Teacher teacher = event.getRowValue();
            Load load = teacher.getLoad();
            load.setAcademicLoad(Double.parseDouble(event.getNewValue()));
            load.setTotalLoad(updateTotalLoad(teacher.getLoad()));
            loadDAO.saveLoad(load);
            loadTable.refresh();
            System.out.println(teacher.getLoad().getTotalLoad());
        });
        // Установка обработчика события окончания редактирования ячейки
        addLoadColumn.setOnEditCommit(event -> {
            Teacher teacher = event.getRowValue();
            Load load = teacher.getLoad();
            load.setAddLoad(Double.parseDouble(event.getNewValue()));
            load.setTotalLoad(updateTotalLoad(teacher.getLoad()));
            loadTable.refresh();
            loadDAO.saveLoad(load);
            System.out.println(teacher.getLoad().getTotalLoad());
        });
        // Установка обработчика события окончания редактирования ячейки
        orgLoadColumn.setOnEditCommit(event -> {
            Teacher teacher = event.getRowValue();
            Load load = teacher.getLoad();
            load.setOrgLoad(Double.parseDouble(event.getNewValue()));
            load.setTotalLoad(updateTotalLoad(teacher.getLoad()));
            loadTable.refresh();
            loadDAO.saveLoad(load);
            System.out.println(teacher.getLoad().getTotalLoad());

        });
        // Установка таблицы редактируемой
        loadTable.setEditable(true);
    }
    protected void addTeachersToTable() {
        teacherList = FXCollections.observableArrayList();
            List<Teacher> teachersFromDB = teacherDAO.findAllTeachers();
            teacherList.addAll(teachersFromDB);
            loadTable.setItems(teacherList);

    }
    private double updateTotalLoad(Load load){
        return load.getAcademicLoad() + load.getAddLoad() + load.getOrgLoad();
    }
    // инициализация колонки с номерами строк
    protected void setRowsIndexes(){
        indexColumn.setCellFactory(new Callback<>() {
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
    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            LoadFileCreator loadFileCreator = new LoadFileCreator();
            List<Teacher> teachers = teacherDAO.findAllTeachers();
            loadFileCreator.createFile(file.getPath(),teachers);
        }
    }
}
