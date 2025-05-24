package org.informatics.domain;

import java.io.Serializable;

public class Manager extends Employee implements Serializable {
    private double bonusPercentage;
    private double incomeThreshold;

    public Manager(String id, double baseSalary, String department, double bonusPercentage, double incomeThreshold) {
        super(id, baseSalary, department);
        this.bonusPercentage = bonusPercentage;
        this.incomeThreshold = incomeThreshold;
    }

    public double getIncomeThreshold() {
        return incomeThreshold;
    }

    public double getBonusPercentage() {
        return bonusPercentage;
    }

    @Override
    public double calculateSalary(double totalIncome) {
        return 0;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + getId() + '\'' +
                ", baseSalary=" + getBaseSalary() +
                ", bonusPercentage=" + bonusPercentage +
                ", incomeThreshold=" + incomeThreshold +
                '}';
    }
}
