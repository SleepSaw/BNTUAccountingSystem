package org.bntu.accounting.bntuaccountingsystem.services;

import org.bntu.accounting.bntuaccountingsystem.util.CommonData;
import org.bntu.accounting.bntuaccountingsystem.models.Load;
import org.bntu.accounting.bntuaccountingsystem.models.Salary;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

import java.util.List;

public class SalaryService {
    private Salary salary;
    private Teacher teacher;

    private final double baseRate;
    private final CommonData commonData;

    public SalaryService() {
        salary = new Salary();
        teacher = new Teacher();
        commonData = new CommonData();
        baseRate = commonData.getBaseRate();
    }
    // Оклад с учётом разряда (Минимальный)
    public Double findSalaryByEmployeesCategory(int category) {
        return baseRate * commonData.getTariffByCategory(category);

    }
    // Оклад с учётом нагрузки (Фактический)
    public Double findSalaryByEmployeesLoad(Load load) {
        return salary.getSalaryPerRate() * load.getTotalLoad() / 20;
    }

    // Размер надбавки за стаж работы (в у.е.) Зависит от БАЗОВОЙ СТАВКИ
    public Double findExperienceAllowance(String experience) {
        return baseRate * teacher.getLoad().getTotalLoad()/20 * commonData.getAllowanceByExperience(experience);
    }
    // Размер надбавки за работу в отрасли (в у.е.) Зависит от ОКЛАДА С УЧ. НАГРУЗКИ
    public Double findIndustryWorkAllowance(double salary){
        return salary * commonData.getIndustryAllowance();
    }
    // Размер надбавки за специфику работы (в у.е.) Зависит от ОКЛАДА С УЧ. НАГРУЗКИ
    public Double findAllowanceByQualification(String qualification){
        return salary.getSalaryByLoad() * commonData.getAllowanceByQualification(qualification);
    }
    // Размер надбавки молодого специалиста (в у.е.) Зависит от ОКЛАДА С УЧ. НАГРУЗКИ
    public Double findSYAllowance( String specialistType){
        return salary.getSalaryByLoad() * commonData.getYSAllowances(specialistType);
    }
    public Salary findCommonSalaryOfTeacher(Teacher selectedTeacher){
        salary = new Salary();
        this.teacher = selectedTeacher;
        salary.setTeacher(teacher);
        salary.setSalaryPerRate(findSalaryByEmployeesCategory(teacher.getCategory()));
        salary.setSalaryByLoad(findSalaryByEmployeesLoad(teacher.getLoad()));
        salary.setExpAllowance(findExperienceAllowance(teacher.getExp()));
        salary.setQualificationAllowance(findAllowanceByQualification(teacher.getQualification()));
        salary.setContractAllowance(salary.getSalaryByLoad()*0.3);
        salary.setYoungSpecialistAllowance(findSYAllowance(teacher.getYoungSpecialist()));
        salary.setSixPercent(salary.getSalaryByLoad()*0.06);
        double totalSalary = salary.getSalaryByLoad() + salary.getExpAllowance() + salary.getQualificationAllowance()+
                salary.getContractAllowance() + salary.getYoungSpecialistAllowance() + salary.getSixPercent();
        salary.setTotalSalary(totalSalary);
        return salary;
    }
    public Double getTotalSalaryOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getSalary().getTotalSalary();
        }
        return roundValue(result);
    }
    public Double getSalaryPerRateOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getSalary().getSalaryPerRate();
        }
        return roundValue(result);
    }
    public Double getSalaryByLoadOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getSalary().getSalaryByLoad();
        }
        return roundValue(result);
    }
    public Double getExpAllowanceOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getSalary().getExpAllowance();
        }
        return roundValue(result);
    }
    public Double getContractAllowanceOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getSalary().getContractAllowance();
        }
        return roundValue(result);
    }
    public Double getQualAllowanceOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getSalary().getQualificationAllowance();
        }
        return roundValue(result);
    }
    public Double getYoungSpecAllowanceOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getSalary().getYoungSpecialistAllowance();
        }
        return roundValue(result);
    }
    public Double getSixPercentAllowanceOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getSalary().getSixPercent();
        }
        return roundValue(result);
    }
    public Double getAdditionalAllowanceOfAllTeachers(List<Teacher> teachers){
        double result =0;
        for (Teacher teacher: teachers) {
            result += teacher.getSalary().getAdditionalAllowance();
        }
        return roundValue(result);
    }
    private double roundValue(double value){
        double result = Math.round(value * 100);
        result = result/100;
        return result;
    }
}
