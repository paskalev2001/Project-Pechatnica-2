package org.informatics.domain;

import java.io.Serializable;

public abstract class Manager extends Employee implements Serializable {
    private double bonusPercentage;
    private double incomeThreshold;

    public Manager(String id, double baseSalary, double bonusPercentage, double incomeThreshold) {
        super(id, baseSalary);
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
    public String toString() {
        return "Manager{" +
                "id='" + getId() + '\'' +
                ", baseSalary=" + getBaseSalary() +
                ", bonusPercentage=" + bonusPercentage +
                ", incomeThreshold=" + incomeThreshold +
                '}';
    }
}
