package org.bntu.accounting.bntuaccountingsystem.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.bntu.accounting.bntuaccountingsystem.dao.LoadDAO;
import org.bntu.accounting.bntuaccountingsystem.dao.TeacherDAO;
import org.bntu.accounting.bntuaccountingsystem.excel.LoadFileCreator;
import org.bntu.accounting.bntuaccountingsystem.excel.SalaryFileCreator;
import org.bntu.accounting.bntuaccountingsystem.models.CommonData;
import org.bntu.accounting.bntuaccountingsystem.models.Load;
import org.bntu.accounting.bntuaccountingsystem.models.Salary;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;
import org.bntu.accounting.bntuaccountingsystem.services.LoadService;
import org.bntu.accounting.bntuaccountingsystem.services.SalaryService;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SalaryController  implements Initializable {
    private CommonData commonData;
    private TeacherDAO teacherDAO;
    private LoadDAO loadDAO;
    private SalaryService salaryService;
    private ObservableList<Teacher> teacherList;
    @FXML
    private TableView<Teacher> table;

    @FXML
    private TableColumn<Teacher, String> additionalColumn;

    @FXML
    private TableColumn<Teacher, String> allowancesColumn;

    @FXML
    private TableColumn<Teacher, String> categoryColumn;

    @FXML
    private TableColumn<Teacher, String> coefColumn;

    @FXML
    private TableColumn<Teacher, String> contractColumn;

    @FXML
    private TableColumn<Teacher, String> contractPercColumn;

    @FXML
    private TableColumn<Teacher, String> contractRubColumn;

    @FXML
    private TableColumn<Teacher, String> expColumn;

    @FXML
    private TableColumn<Teacher, String> expPercColumn;

    @FXML
    private TableColumn<Teacher, String> expRubColumn;

    @FXML
    private TableColumn<Teacher, Integer> indexColumn;

    @FXML
    private TableColumn<Teacher, String> loadColumn;

    @FXML
    private TableColumn<Teacher, String> nameColumn;

    @FXML
    private TableColumn<Teacher, ?> qualificationAllowanceColumn;

    @FXML
    private TableColumn<Teacher, String> qualificationAllowancePercColumn;

    @FXML
    private TableColumn<Teacher, String> qualificationAllowanceRubColumn;

    @FXML
    private TableColumn<Teacher, ?> salaryColumn;

    @FXML
    private TableColumn<Teacher, String> sixPercentColumn;

    @FXML
    private TableColumn<Teacher, String> specificationPercColumn;

    @FXML
    private TableColumn<Teacher, String> specificationRubColumn;

    @FXML
    private TableColumn<Teacher, String> totalSalaryColumn;

    @FXML
    private TableColumn<Teacher, String> withLoadSalaryColumn;

    @FXML
    private TableColumn<Teacher, String> withoutLoadSalaryColumn;

    @FXML
    private TableColumn<Teacher, ?> youngSpecialistColumn;

    @FXML
    private TableColumn<Teacher, String> youngSpecialistPercColumn;

    @FXML
    private TableColumn<Teacher, String> youngSpecialistRubColumn;
    @FXML
    private TableColumn<Teacher, String> AdditionalAllowancePercColumn;
    @FXML
    private TableColumn<Teacher, String> AdditionalAllowanceRubColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherDAO = new TeacherDAO();
        loadDAO = new LoadDAO();
        salaryService = new SalaryService();
        commonData = new CommonData();

        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        loadColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getLoad().getTotalLoad())));
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getCategory())));
        coefColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(commonData.getTariffByCategory(data.getValue().getCategory()))));
        withoutLoadSalaryColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getSalary().getSalaryPerRate())));
        withLoadSalaryColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getSalary().getSalaryByLoad())));
        expPercColumn.setCellValueFactory(data -> new SimpleStringProperty(showExpPerc(data.getValue())));
        expRubColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getSalary().getExpAllowance())));
        contractPercColumn.setCellValueFactory(data -> new SimpleStringProperty("30%"));
        contractRubColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getSalary().getContractAllowance())));
        qualificationAllowancePercColumn.setCellValueFactory(data -> new SimpleStringProperty((commonData.getAllowanceByQualification(data.getValue().getQualification())*100)+"%"));
        qualificationAllowanceRubColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getSalary().getQualificationAllowance())));
        youngSpecialistPercColumn.setCellValueFactory(data -> new SimpleStringProperty((commonData.getYSAllowances(data.getValue().getYoungSpecialist())*100)+"%"));
        youngSpecialistRubColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getSalary().getYoungSpecialistAllowance())));
        sixPercentColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getSalary().getSixPercent())));
        AdditionalAllowancePercColumn.setCellValueFactory(data -> new SimpleStringProperty("-"));
        AdditionalAllowanceRubColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getSalary().getAdditionalAllowance())));
        totalSalaryColumn.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getSalary().getTotalSalary())));
        addTeachersToTable();
        setRowsIndexes();
    }
    @FXML
    void saveButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            SalaryFileCreator salaryFileCreator = new SalaryFileCreator();
            salaryFileCreator.createFile(file.getPath(),teacherList);
        }
    }
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
    protected void addTeachersToTable() {
        teacherList = FXCollections.observableArrayList();
        List<Teacher> teachersFromDB = teacherDAO.findAllTeachers();
        teachersFromDB
                .forEach(teacher -> {
                    teacher.setSalary(salaryService.findCommonSalaryOfTeacher(teacher));
                });
        teacherList.addAll(teachersFromDB);
        table.setItems(teacherList);
    }
    private String showExpPerc(Teacher teacher){
        try {
           return commonData.getAllowanceByExperience(teacher.getExp())*100+"%";
        }
        catch (NullPointerException e){
            return null;
        }
    }
    private String showQualPerc(Teacher teacher){
        try {
            return commonData.getAllowanceByQualification(teacher.getQualification())*100+"%";
        }
        catch (NullPointerException e){
            return null;
        }
    }
}
