package org.informatics.domain;

import java.io.Serializable;

public class Manager extends Employee implements Serializable {
    private double bonusPercentage;
    private double incomeThreshold;

    public Manager(String id, String name, double baseSalary, String department, double bonusPercentage, double incomeThreshold) {
        super(id, name, baseSalary, department);
        if (bonusPercentage < 0) throw new IllegalArgumentException("bonusPercentage cannot be negative");
        if (incomeThreshold < 0) throw new IllegalArgumentException("incomeThreshold cannot be negative");
        this.bonusPercentage = bonusPercentage;
        this.incomeThreshold = incomeThreshold;
    }

    public double getBonusPercentage() { return bonusPercentage; }
    public double getIncomeThreshold() { return incomeThreshold; }

    @Override
    public double calculateSalary(double totalIncome) {
        if (totalIncome >= incomeThreshold) {
            return getBaseSalary() + (bonusPercentage / 100.0) * getBaseSalary();
        }
        return getBaseSalary();
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", baseSalary=" + getBaseSalary() +
                ", department='" + getDepartment() + '\'' +
                ", bonusPercentage=" + bonusPercentage +
                ", incomeThreshold=" + incomeThreshold +
                '}';
    }
}