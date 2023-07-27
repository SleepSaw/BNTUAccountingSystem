package org.bntu.accounting.bntuaccountingsystem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.bntu.accounting.bntuaccountingsystem.builder.TeacherBuilder;
import org.bntu.accounting.bntuaccountingsystem.dao.TeacherDAO;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    private Teacher teacher;
    private TeacherDAO teacherDAO;
    @FXML
    private TextField fioTextField;
    @FXML
    private Button editButton;

    @FXML
    private ComboBox<String> expComboBox;

    @FXML
    private ComboBox<String> postComboBox;

    @FXML
    private ComboBox<String> qualificationComboBox;

    @FXML
    private ComboBox<String> specComboBox;

    @FXML
    private ComboBox<String> subjectComboBox;

    @FXML
    private ComboBox<String> tariffComboBox;

    public EditController(Teacher teacher) {
        this.teacher = teacher;
        System.out.println(teacher + " IN CONTROLLER");
    }

    private void initPostComboBox(){
        ObservableList<String> posts = FXCollections.observableArrayList("Учитель", "Прочее");
        postComboBox.setItems(posts);
        postComboBox.setValue(teacher.getPost());
    }
    private void initSubjectComboBox(){
        ObservableList<String> subjects = FXCollections.observableArrayList("Математика","Русский язык и литература","Фзика");
        subjectComboBox.setItems(subjects);
        subjectComboBox.setValue(teacher.getSubject());
    }
    private void initQualificationComboBox(){
        ObservableList<String> qualifications = FXCollections.observableArrayList("б/к", "1-я к.к.","2-я к.к.",
                "в.к.к.", "уч.-методист");
        qualificationComboBox.setItems(qualifications);
        qualificationComboBox.setValue(teacher.getQualification());
    }
    private void initExpComboBox(){
        ObservableList<String> exps = FXCollections.observableArrayList("До 5 лет", "5-10 лет", "10-15 лет", "св. 15 лет");
        expComboBox.setItems(exps);
        expComboBox.setValue(teacher.getExp());
    }
    private void initSpecComboBox(){
        ObservableList<String> specList = FXCollections.observableArrayList("Нет", "Молодой специалист", "Одарённый");
        specComboBox.setItems(specList);
        specComboBox.setValue(teacher.getYoungSpecialist());
    }
    private void initTariffComboBox(){
        ObservableList<String> tariffs = FXCollections.observableArrayList("7", "8", "9", "10", "11");
        tariffComboBox.setItems(tariffs);
        tariffComboBox.setValue(teacher.getCategory().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherDAO = new TeacherDAO();
        fioTextField.setText(teacher.getName());
        editButton.setOnAction(actionEvent -> {
            TeacherBuilder builder = new TeacherBuilder();
            Teacher updatedTeacher = builder.setName(fioTextField.getText()).setPost(postComboBox.getValue()).
                    setSubject(subjectComboBox.getValue()).setQualification(qualificationComboBox.getValue()).
                    setWorkExperience(expComboBox.getValue()).setCategory(Integer.parseInt(tariffComboBox.getValue())).
                    setYoungSpecialist(specComboBox.getValue()).build();
            updatedTeacher.setId(teacher.getId());
            teacherDAO.updateTeacher(updatedTeacher);
        });
        initPostComboBox();
        initSubjectComboBox();
        initQualificationComboBox();
        initExpComboBox();
        initSpecComboBox();
        initTariffComboBox();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
