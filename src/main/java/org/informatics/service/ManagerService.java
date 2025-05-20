package org.informatics.service;
import org.informatics.domain.Manager;

public class ManagerService {

    public double calculateSalary(Manager manager, double totalIncome) {
        double incomeThreshold = manager.getIncomeThreshold();
        double bonusPercentage = manager.getBonusPercentage();
        if (totalIncome > incomeThreshold) {
            return manager.getBaseSalary() + (manager.getBaseSalary() * bonusPercentage / 100);
        } else {
            return manager.getBaseSalary();
        }
    }
}
