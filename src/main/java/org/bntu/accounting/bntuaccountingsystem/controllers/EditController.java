package org.bntu.accounting.bntuaccountingsystem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.bntu.accounting.bntuaccountingsystem.models.Load;
import org.bntu.accounting.bntuaccountingsystem.models.Salary;
import org.bntu.accounting.bntuaccountingsystem.models.builder.TeacherBuilder;
import org.bntu.accounting.bntuaccountingsystem.dao.TeacherDAO;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController extends BaseController implements Initializable {
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
        initComboBox(postComboBox,"posts",teacher.getPost());
        initComboBox(subjectComboBox,"subjects", teacher.getSubject());
        initComboBox(qualificationComboBox,"qualifications", teacher.getQualification());
        initComboBox(specComboBox,"young_specialist", teacher.getYoungSpecialist());
        initComboBox(tariffComboBox,"tariffs",teacher.getCategory().toString());
        initComboBox(expComboBox,"experiences", teacher.getExp());
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
