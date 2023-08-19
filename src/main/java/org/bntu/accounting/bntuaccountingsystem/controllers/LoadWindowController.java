package org.bntu.accounting.bntuaccountingsystem.controllers;

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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bntu.accounting.bntuaccountingsystem.dao.TeacherDAO;
import org.bntu.accounting.bntuaccountingsystem.excel.ExcelFileHeaderCreator;
import org.bntu.accounting.bntuaccountingsystem.excel.ExcelLoadTableCreator;
import org.bntu.accounting.bntuaccountingsystem.excel.JsonFileReader;
import org.bntu.accounting.bntuaccountingsystem.excel.TeacherLoadWriter;
import org.bntu.accounting.bntuaccountingsystem.models.Load;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoadWindowController implements Initializable {
    private ObservableList<Teacher> teacherList;
    private TeacherDAO teacherDAO;

    private final ExcelFileHeaderCreator fileHeaderCreator = new ExcelFileHeaderCreator();

    private final ExcelLoadTableCreator loadTableCreator = new ExcelLoadTableCreator();
    private final JsonFileReader fileReader = new JsonFileReader();
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
            teacher.getLoad().setAcademicLoad(Double.parseDouble(event.getNewValue()));
            teacher.getLoad().setTotalLoad(updateTotalLoad(teacher.getLoad()));
            loadTable.refresh();
            System.out.println(teacher.getLoad().getTotalLoad());
        });
        // Установка обработчика события окончания редактирования ячейки
        addLoadColumn.setOnEditCommit(event -> {
            Teacher teacher = event.getRowValue();
            teacher.getLoad().setAddLoad(Double.parseDouble(event.getNewValue()));
            teacher.getLoad().setTotalLoad(updateTotalLoad(teacher.getLoad()));
            loadTable.refresh();
            System.out.println(teacher.getLoad().getTotalLoad());
        });
        // Установка обработчика события окончания редактирования ячейки
        orgLoadColumn.setOnEditCommit(event -> {

        });
        // Установка таблицы редактируемой
        loadTable.setEditable(true);
    }
    protected void addTeachersToTable() {
        teacherList = FXCollections.observableArrayList();
            List<Teacher> teachersFromDB = teacherDAO.findAllTeachers();
            teachersFromDB.stream()
                    .filter(teacher -> Objects.isNull(teacher.getLoad()))
                    .forEach(teacher -> teacher.setLoad(new Load(0,0,0)));
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
        String jsonLoadTableFilePath = "C:\\Users\\danii\\IdeaProjects\\BNTUAccountingSystem\\src\\main\\resources\\files\\load_table.json";
        String jsonHeaderFilePath = "C:\\Users\\danii\\IdeaProjects\\BNTUAccountingSystem\\src\\main\\resources\\files\\excel_header.json";
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Лист");
                JSONObject jsonHeader = fileReader.readJsonFile(jsonHeaderFilePath);
                JSONObject jsonLoadTable = fileReader.readJsonFile(jsonLoadTableFilePath);
                fileHeaderCreator.writeDataToExcel(file.getPath(),jsonHeader,workbook);
                loadTableCreator.createLoadTableColumns(file.getPath(),jsonLoadTable,workbook);
                List<Teacher> teachers = teacherDAO.findAllTeachers();
                TeacherLoadWriter teacherLoadWriter = new TeacherLoadWriter();
                teacherLoadWriter.writeAllTeachers(14,teacherList,workbook);
                try (FileOutputStream outputStream = new FileOutputStream(file.getPath())) {
                    workbook.write(outputStream);
                }
            }

            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @FXML
    void academicLoadColumnAction(ActionEvent event) {

    }

    @FXML
    void addLoadColumnAction(ActionEvent event) {

    }

    @FXML
    void orgLoadColumnActon(ActionEvent event) {

    }
    private void setEdit(ActionEvent event){


    }
}
