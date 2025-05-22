package org.informatics.domain;

import java.util.HashSet;
import java.util.Set;

public class PrintingHouse {
    private final String name;
    private final Set<PrintingMachine> machines;
    private final Set<Employee> employees;
    private final Set<Edition> editions;
    private final double managerBonusThreshold;
    private final double managerBonusPercent;
    private final int discountThresholdCopies;
    private final double discountPercent;

    public PrintingHouse(String name,
                         double managerBonusThreshold,
                         double managerBonusPercent,
                         int discountThresholdCopies,
                         double discountPercent) {
        this.name = name;
        this.managerBonusThreshold = managerBonusThreshold;
        this.managerBonusPercent = managerBonusPercent;
        this.discountThresholdCopies = discountThresholdCopies;
        this.discountPercent = discountPercent;

        this.machines = new HashSet<>();
        this.employees = new HashSet<>();
        this.editions = new HashSet<>();
    }


    public boolean addMachine(PrintingMachine machine) {
        return machines.add(machine);
    }

    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    public boolean addEdition(Edition edition) {
        return editions.add(edition);
    }

    public Set<PrintingMachine> getMachines() {
        return machines;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Set<Edition> getEditions() {
        return editions;
    }

    public String getName() {
        return name;
    }

    public double getManagerBonusThreshold() {
        return managerBonusThreshold;
    }

    public double getManagerBonusPercent() {
        return managerBonusPercent;
    }

    public int getDiscountThresholdCopies() {
        return discountThresholdCopies;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }
}