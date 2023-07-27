package org.bntu.accounting.bntuaccountingsystem.builder;

import org.bntu.accounting.bntuaccountingsystem.models.Salary;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import javax.persistence.*;

public class SalaryBuilder {

    private Teacher teacher;

    private double salaryPerRate;

    private double salaryByLoad;

    private double totalSalary;

    private double expAllowance;

    private double contractAllowance;

    private double qualificationAllowance;

    private double youngSpecialistAllowance;

    private double sixPercent;

    private double additionalAllowance;

    public SalaryBuilder setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public SalaryBuilder setSalaryPerRate(double salaryPerRate) {
        this.salaryPerRate = salaryPerRate;
        return this;
    }

    public SalaryBuilder setSalaryByLoad(double salaryByLoad) {
        this.salaryByLoad = salaryByLoad;
        return this;
    }

    public SalaryBuilder setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
        return this;
    }

    public SalaryBuilder setExpAllowance(double expAllowance) {
        this.expAllowance = expAllowance;
        return this;
    }

    public SalaryBuilder setContractAllowance(double contractAllowance) {
        this.contractAllowance = contractAllowance;
        return this;
    }

    public SalaryBuilder setQualificationAllowance(double qualificationAllowance) {
        this.qualificationAllowance = qualificationAllowance;
        return this;
    }

    public SalaryBuilder setYoungSpecialistAllowance(double youngSpecialistAllowance) {
        this.youngSpecialistAllowance = youngSpecialistAllowance;
        return this;
    }

    public SalaryBuilder setSixPercent(double sixPercent) {
        this.sixPercent = sixPercent;
        return this;
    }

    public SalaryBuilder setAdditionalAllowance(double additionalAllowance) {
        this.additionalAllowance = additionalAllowance;
        return this;
    }
    public Salary build(){
        return new Salary(teacher,salaryPerRate,salaryByLoad,totalSalary,expAllowance,contractAllowance,qualificationAllowance,
                youngSpecialistAllowance,sixPercent,additionalAllowance);
    }
}
