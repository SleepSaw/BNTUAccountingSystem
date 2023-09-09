package application.services;

import application.models.Teacher;

import java.util.List;

public class LoadService {
    public Double getTotalLoadOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getLoad().getTotalLoad();
        }
        return result;
    }
    public Double getAcademicLoadOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getLoad().getAcademicLoad();
        }
        return result;
    }
    public Double getOrgLoadOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getLoad().getOrgLoad();
        }
        return result;
    }
    public Double getAddLoadOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getLoad().getAddLoad();
        }
        return result;
    }
}
