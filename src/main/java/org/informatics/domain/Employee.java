package org.informatics.domain;
import java.io.Serializable;

public abstract class Employee implements Serializable {
    private String id;
    private String name;
    private double baseSalary;
    private String department;

    public Employee(String id, String name, double baseSalary, String department) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id cannot be null or blank");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name cannot be null or blank");
        if (department == null || department.isBlank()) throw new IllegalArgumentException("department cannot be null or blank");
        if (baseSalary < 0) throw new IllegalArgumentException("baseSalary cannot be negative");
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
        this.department = department;
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

    public String getDepartment() { return department; }

    public abstract double calculateSalary(double totalIncome);

    @Override
    public String toString() {
        return "Employee{id= '" + id + "'name='" + name + "', baseSalary=" + baseSalary + "', department="+department+"}";

    }
}
