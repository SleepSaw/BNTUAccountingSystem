package application.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "salary")
public class Salary implements Serializable {
    @Id
    @OneToOne()
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;
    @Column(name = "salary_rate")
    private double salaryPerRate;
    @Column(name = "salary_load")
    private double salaryByLoad;
    @Column(name = "salary_total")
    private double totalSalary;
    @Column(name = "exp_allowance")
    private double expAllowance;
    @Column(name = "contract_allowance")
    private double contractAllowance;
    @Column(name = "qualification_allowance")
    private double qualificationAllowance;
    @Column(name = "young_specialist_allowance")
    private double youngSpecialistAllowance;
    @Column(name = "six_percent_allowance")
    private double sixPercent;
    @Column(name = "additional_allowance")
    private double additionalAllowance;

    public Salary() {
    }

    public Salary(Teacher teacher, double salaryPerRate, double salaryByLoad, double totalSalary,
                  double expAllowance, double contractAllowance, double qualificationAllowance,
                  double youngSpecialistAllowance, double sixPercent, double additionalAllowance) {
        this.teacher = teacher;
        this.salaryPerRate = salaryPerRate;
        this.salaryByLoad = salaryByLoad;
        this.totalSalary = totalSalary;
        this.expAllowance = expAllowance;
        this.contractAllowance = contractAllowance;
        this.qualificationAllowance = qualificationAllowance;
        this.youngSpecialistAllowance = youngSpecialistAllowance;
        this.sixPercent = sixPercent;
        this.additionalAllowance = additionalAllowance;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.setSalary(this);
    }

    public double getSalaryPerRate() {
        return roundValue(salaryPerRate) ;
    }

    public void setSalaryPerRate(double salaryPerRate) {
        this.salaryPerRate = salaryPerRate;
    }

    public double getSalaryByLoad() {
        return roundValue(salaryByLoad);
    }

    public void setSalaryByLoad(double salaryByLoad) {
        this.salaryByLoad = salaryByLoad;
    }

    public double getTotalSalary() {
        return roundValue(totalSalary);
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public double getExpAllowance() {
        return roundValue(expAllowance);
    }

    public void setExpAllowance(double expAllowance) {
        this.expAllowance = expAllowance;
    }

    public double getContractAllowance() {
        return roundValue(contractAllowance);
    }

    public void setContractAllowance(double contractAllowance) {
        this.contractAllowance = contractAllowance;
    }

    public double getQualificationAllowance() {
        return roundValue(qualificationAllowance);
    }

    public void setQualificationAllowance(double qualificationAllowance) {
        this.qualificationAllowance = qualificationAllowance;
    }

    public double getYoungSpecialistAllowance() {
        return roundValue(youngSpecialistAllowance);
    }

    public void setYoungSpecialistAllowance(double youngSpecialistAllowance) {
        this.youngSpecialistAllowance = youngSpecialistAllowance;
    }

    public double getSixPercent() {
        return roundValue(sixPercent);
    }

    public void setSixPercent(double sixPercent) {
        this.sixPercent = sixPercent;
    }

    public double getAdditionalAllowance() {
        return roundValue(additionalAllowance);
    }

    public void setAdditionalAllowance(double additionalAllowance) {
        this.additionalAllowance = additionalAllowance;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "teacher=" + teacher +
                ", salaryPerRate=" + salaryPerRate +
                ", salaryByLoad=" + salaryByLoad +
                ", totalSalary=" + totalSalary +
                ", expAllowance=" + expAllowance +
                ", contractAllowance=" + contractAllowance +
                ", qualificationAllowance=" + qualificationAllowance +
                ", youngSpecialistAllowance=" + youngSpecialistAllowance +
                ", sixPercent=" + sixPercent +
                ", additionalAllowance=" + additionalAllowance +
                '}';
    }
    private double roundValue(double value){
        double result = Math.round(value * 100);
        result = result/100;
        return result;
    }
}
