package org.informatics.domain;

import java.io.Serializable;

public class Operator extends Employee  implements Serializable {

    public Operator(String name, double baseSalary, String department) {
        super(name, baseSalary, department);
    }

    @Override
    public double calculateSalary(double totalIncome) {
        return getBaseSalary();
    }

    @Override
    public String toString() {
        return "Operator{" + "name='" + getName() + "', baseSalary=" + getBaseSalary() + "}";
    }
}
