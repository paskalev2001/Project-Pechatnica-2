package org.informatics.domain;
import java.io.Serializable;

// да се добави ид uuid (in utils)
public abstract class Employee implements Serializable {
    private String id;
    private String name;
    private double baseSalary;

    public Employee(String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public abstract double calculateSalary(double totalIncome);

    @Override
    public String toString() {
        return "Employee{id= '" + id + "'name='" + name + "', baseSalary=" + baseSalary + "}";

    }
}
