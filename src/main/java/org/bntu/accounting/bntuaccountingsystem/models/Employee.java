package org.bntu.accounting.bntuaccountingsystem.models;


public class Employee {
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
    // Зароботная плата
    private Salary salary;

    public Employee() {
    }

    public Employee(String fio, String post, String workExperience, String qualification, int category) {
        this.fio = fio;
        this.post = post;
        this.workExperience = workExperience;
        this.qualification = qualification;
        this.category = category;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return fio + " | " + post + " | " + workExperience + " | " + (int)category + " | " + qualification + salary + " | ";
    }
}
