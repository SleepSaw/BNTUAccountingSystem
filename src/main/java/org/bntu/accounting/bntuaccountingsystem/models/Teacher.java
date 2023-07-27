package org.bntu.accounting.bntuaccountingsystem.models;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "post")
    private String post;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "exp")
    private String exp;
    @Column(name = "category")
    private Integer category;
    @Column(name = "young_specialist")
    private String youngSpecialist;
    @Column(name = "subject")
    private String subject;
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER,mappedBy = "teacher")
    private Load load;
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER,mappedBy = "teacher")
    private Salary salary;

    public Teacher() {
    }

    public Teacher(String name, String post,String subject, String qualification, String exp, Integer category, String youngSpecialist) {
        this.name = name;
        this.post = post;
        this.qualification = qualification;
        this.exp = exp;
        this.category = category;
        this.youngSpecialist = youngSpecialist;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getYoungSpecialist() {
        return youngSpecialist;
    }

    public void setYoungSpecialist(String youngSpecialist) {
        this.youngSpecialist = youngSpecialist;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Load getLoad() {
        return load;
    }

    public void setLoad(Load load) {
        this.load = load;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", post='" + post + '\'' +
                ", qualification='" + qualification + '\'' +
                ", exp='" + exp + '\'' +
                ", category='" + category + '\'' +
                ", youngSpecialist='" + youngSpecialist + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
