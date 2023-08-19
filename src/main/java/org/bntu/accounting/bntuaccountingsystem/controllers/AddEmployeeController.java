package org.bntu.accounting.bntuaccountingsystem.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.bntu.accounting.bntuaccountingsystem.models.builder.TeacherBuilder;
import org.bntu.accounting.bntuaccountingsystem.dao.TeacherDAO;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController extends BaseController implements Initializable  {
    private TeacherDAO teacherDAO;
    @FXML
    private TextField fioTextField;
    @FXML
    private Button addButton;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherDAO = new TeacherDAO();
        addButton.setOnAction(actionEvent -> {
            TeacherBuilder builder = new TeacherBuilder();
            Teacher teacher = builder.setName(fioTextField.getText()).setPost(postComboBox.getValue()).
                    setSubject(subjectComboBox.getValue()).setQualification(qualificationComboBox.getValue()).
                    setWorkExperience(expComboBox.getValue()).setCategory(Integer.parseInt(tariffComboBox.getValue())).
                    setYoungSpecialist(specComboBox.getValue()).build();
            teacherDAO.saveTeacher(teacher);
            System.out.println("Teacher is saved");
        });
        initComboBox(postComboBox,"posts","Учитель");
        initComboBox(subjectComboBox,"subjects","Математика");
        initComboBox(qualificationComboBox,"qualifications","в.к.к.");
        initComboBox(specComboBox,"young_specialist","Нет");
        initComboBox(tariffComboBox,"tariffs","7");
        initComboBox(expComboBox,"experiences","До 5 лет");

    }
}
