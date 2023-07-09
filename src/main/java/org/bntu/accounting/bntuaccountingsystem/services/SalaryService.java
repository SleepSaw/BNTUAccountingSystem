package org.bntu.accounting.bntuaccountingsystem.services;

import org.bntu.accounting.bntuaccountingsystem.models.CommonData;
import org.bntu.accounting.bntuaccountingsystem.models.Load;

public class SalaryService {

    private final double baseRate;
    private final CommonData commonData;

    public SalaryService() {
        commonData = new CommonData();
        baseRate = commonData.getBaseRate();
    }

    // Оклад с учётом разряда (Минимальный)
    public double findSalaryByEmployeesCategory(int category) {
        return baseRate * commonData.getTariffByCategory(category);
    }

    // Оклад с учётом нагрузки (Фактический)
    public double findSalaryByEmployeesLoad(double salary, Load load) {
        return salary * load.getTotalLoad() / 20;
    }

    // Размер надбавки за стаж работы (в у.е.) Зависит от БАЗОВОЙ СТАВКИ
    public double findExperienceAllowance(String experience) {
        return baseRate * commonData.getAllowanceByExperience(experience);
    }
    // Размер надбавки за работу в отрасли (в у.е.) Зависит от ОКЛАДА С УЧ. НАГРУЗКИ
    public double findIndustryWorkAllowance(double salary){
        return salary * commonData.getIndustryAllowance();
    }
    // Размер надбавки за специфику работы (в у.е.) Зависит от ОКЛАДА С УЧ. НАГРУЗКИ
    public double findAllowanceByQualification(double salary, String qualification){
        return salary * commonData.getAllowanceByQualification(qualification);
    }
    // Размер надбавки молодого специалиста (в у.е.) Зависит от ОКЛАДА С УЧ. НАГРУЗКИ
    public double findSYAllowance(double salary, String specialistType){
        return salary * commonData.getYSAllowances(specialistType);
    }
}
