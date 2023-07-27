package org.bntu.accounting.bntuaccountingsystem.services;

import org.bntu.accounting.bntuaccountingsystem.builder.SalaryBuilder;
import org.bntu.accounting.bntuaccountingsystem.models.CommonData;
import org.bntu.accounting.bntuaccountingsystem.models.Load;
import org.bntu.accounting.bntuaccountingsystem.models.Salary;
import org.bntu.accounting.bntuaccountingsystem.models.Teacher;

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
    public double findSalaryByEmployeesCategory(int category) {
        return baseRate * commonData.getTariffByCategory(category);
    }
    // Оклад с учётом нагрузки (Фактический)
    public double findSalaryByEmployeesLoad(Load load) {
        return salary.getSalaryPerRate() * load.getTotalLoad() / 20;
    }

    // Размер надбавки за стаж работы (в у.е.) Зависит от БАЗОВОЙ СТАВКИ
    public double findExperienceAllowance(String experience) {
        return baseRate * teacher.getLoad().getTotalLoad()/20 * commonData.getAllowanceByExperience(experience);
    }
    // Размер надбавки за работу в отрасли (в у.е.) Зависит от ОКЛАДА С УЧ. НАГРУЗКИ
    public double findIndustryWorkAllowance(double salary){
        return salary * commonData.getIndustryAllowance();
    }
    // Размер надбавки за специфику работы (в у.е.) Зависит от ОКЛАДА С УЧ. НАГРУЗКИ
    public double findAllowanceByQualification(String qualification){
        return salary.getSalaryByLoad() * commonData.getAllowanceByQualification(qualification);
    }
    // Размер надбавки молодого специалиста (в у.е.) Зависит от ОКЛАДА С УЧ. НАГРУЗКИ
    public double findSYAllowance( String specialistType){
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

}
