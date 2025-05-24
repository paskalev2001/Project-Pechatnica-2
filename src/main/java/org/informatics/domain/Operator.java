package org.informatics.domain;

import java.io.Serializable;

public class Operator extends Employee implements Serializable {
    public Operator(String id, String name, double baseSalary, String department) {
        super(id, name, baseSalary, department);
    }

    @Override
    public double calculateSalary(double totalIncome) {
        return getBaseSalary();
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", baseSalary=" + getBaseSalary() +
                ", department='" + getDepartment() + '\'' +
                '}';
    }
}
