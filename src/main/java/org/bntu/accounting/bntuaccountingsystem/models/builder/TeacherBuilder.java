package org.bntu.accounting.bntuaccountingsystem.models.builder;

import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

public class TeacherBuilder {
    private String name;
    private String qualification;
    private String post;

    private String exp;
    private Integer category;
    private String youngSpecialist;

    private String subject;

    public TeacherBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TeacherBuilder setPost(String post) {
        this.post = post;
        return this;
    }

    public TeacherBuilder setWorkExperience(String exp) {
        this.exp = exp;
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
        return new Teacher(name ,post,subject,qualification,exp,category,youngSpecialist);
    }
}
