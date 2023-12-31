package org.bntu.accounting.bntuaccountingsystem.models;


public class Salary1 {
    private final double baseRate = 228;
    private double salaryPerRate;
    private double salaryWithLoad;
    private double totalSalary;

    public Salary1() {
    }
    public double getSalaryPerRate() {
        return salaryPerRate;
    }

    public void setSalaryPerRate(double salaryPerRate) {
        this.salaryPerRate = salaryPerRate;
    }

    public double getSalaryWithLoad() {
        return salaryWithLoad;
    }

    public void setSalaryWithLoad(double salaryWithLoad) {
        this.salaryWithLoad = salaryWithLoad;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public double getBaseRate() {
        return baseRate;
    }


    @Override
    public String toString() {
        return "Зарплата: " + salaryPerRate + salaryWithLoad + totalSalary;
    }
}