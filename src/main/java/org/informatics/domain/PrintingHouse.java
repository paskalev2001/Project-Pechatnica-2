package org.informatics.domain;

import java.util.ArrayList;
import java.util.List;

public class PrintingHouse {
    private final String name;
    private final List<PrintingMachine> machines; // -> set
    private final List<Employee> employees; // служителите не могат да се дублират . минава на Set
    private final List<Edition> editions;
    private final double managerBonusThreshold;
    private final double managerBonusPercent;
    private final int discountThresholdCopies;
    private final double discountPercent;

    public PrintingHouse(String name, double managerBonusThreshold, double managerBonusPercent,
                         int discountThresholdCopies, double discountPercent) {
        this.name = name;
        this.machines = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.editions = new ArrayList<>();
        this.managerBonusThreshold = managerBonusThreshold;
        this.managerBonusPercent = managerBonusPercent;
        this.discountThresholdCopies = discountThresholdCopies;
        this.discountPercent = discountPercent;
    }

    public String getName() {
        return name;
    }

    public List<PrintingMachine> getMachines() {
        return machines;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Edition> getEditions() {
        return editions;
    }

    @Override
    public String toString() {
        return "PrintingHouse{" +
                "name='" + name + '\'' +
                ", machines=" + machines.size() +
                ", employees=" + employees.size() +
                ", editions=" + editions.size() +
                '}';
    }
}