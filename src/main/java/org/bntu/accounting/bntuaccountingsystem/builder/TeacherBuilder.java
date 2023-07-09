package org.bntu.accounting.bntuaccountingsystem.builder;

import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

public class TeacherBuilder {
    // Фамилия - Имя - Отчество
    private String fio;
    // Должность
    private String post;
    // Стаж работы
    private String workExperience;
    // Квалификация
    private String qualification;
    // Тарифный разряд
    private int category;
    // Предмет, который преподаёт работник
    private String subject;
    // Молодой специалист
    private String youngSpecialist;

    public TeacherBuilder setFio(String fio) {
        this.fio = fio;
        return this;
    }

    public TeacherBuilder setPost(String post) {
        this.post = post;
        return this;
    }

    public TeacherBuilder setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
        return this;
    }

    public TeacherBuilder setQualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public TeacherBuilder setCategory(int category) {
        this.category = category;
        return this;
    }

    public TeacherBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public TeacherBuilder setYoungSpecialist(String youngSpecialist) {
        this.youngSpecialist = youngSpecialist;
        return this;
    }
    public Teacher build(){
        return new Teacher(fio,post,subject,qualification,workExperience,category,youngSpecialist);
    }
}
