package org.bntu.accounting.bntuaccountingsystem.models;

public class Teacher extends Employee{
    // Предмет, который преподаёт работник
    private String subject;
    // Нагрузка педагогического работника
    private Load load;
    // Молодой специалист
    private String youngSpecialist;

    public Teacher() {
    }

    public Teacher(String fio, String post, String subject, String qualification, String workExperience, int category, String youngSpecialist) {
        super(fio, post, workExperience, qualification, category);
        this.subject = subject;
        this.youngSpecialist = youngSpecialist;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Load getLoad() {
        return load;
    }

    public void setLoad(Load load) {
        this.load = load;
    }

    public String getYoungSpecialist() {
        return youngSpecialist;
    }

    public void setYoungSpecialist(String youngSpecialist) {
        this.youngSpecialist = youngSpecialist;
    }
    @Override
    public String toString() {
        return super.toString() + " | " + subject + " | " + youngSpecialist + " | " +
                 load + " | ";
    }
}
