package org.informatics.service;

import org.informatics.domain.*;

public class PrintingHouseService {
    public void addMachine(PrintingMachine machine) {
        machines.add(machine);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addEdition(Edition edition) {
        editions.add(edition);
    }

    public void printEdition(Edition edition) {
        for (PrintingMachine machine : machines) {
            try {
                machine.printEdition(edition);
                return; // успешно отпечатано
            } catch (Exception ignored) {
                // Продължава към следващата машина
            }
        }
        throw new IllegalStateException("No suitable machine available to print edition: " + edition.getPublication().getTitle());
    }

    public double calculateSalaries() {
        double total = 0.0;
        double income = calculateTotalIncome();

        for (Employee emp : employees) {
            double salary = emp.getBaseSalary();
            if (emp instanceof Manager && income > managerBonusThreshold) {
                salary += salary * (managerBonusPercent / 100.0);
            }
            total += salary;
        }

        return total;
    }

    public double calculateTotalIncome() {
        double total = 0.0;
        for (Edition edition : editions) {
            int copies = edition.getCopiesPrinted();
            double pricePerCopy = edition.getPublication().getPricePerCopy();

            if (copies > discountThresholdCopies) {
                pricePerCopy *= (1 - discountPercent / 100.0);
            }

            total += pricePerCopy * copies;
        }
        return total;
    }

    public double calculatePaperExpenses() {
        double total = 0.0;
        for (Edition edition : editions) {
            PaperType paperType = edition.getPublication().getPaperType();
            PageSize size = edition.getPublication().getPageSize();

            double basePrice = paperType.getBasePriceForA5();
            double multiplier = size.getPriceMultiplier();
            int pages = edition.getPublication().getNumberOfCopies(); // ако 1 брой = 1 стр.
            int totalPages = edition.getTotalPagesPrinted();

            total += basePrice * multiplier * totalPages;
        }
        return total;
    }

    public double calculateProfit() {
        return calculateTotalIncome() - calculateSalaries() - calculatePaperExpenses();
    }

    public void generateReport() {
        System.out.println("==== REPORT FOR PRINTING HOUSE: " + name + " ====");
        System.out.println("Editions printed:");
        for (Edition edition : editions) {
            System.out.printf(" - %s: %d copies printed, income: %.2f%n",
                    edition.getPublication().getTitle(),
                    edition.getCopiesPrinted(),
                    edition.getPublication().getPricePerCopy() * edition.getCopiesPrinted());
        }

        double totalIncome = calculateTotalIncome();
        double paperCosts = calculatePaperExpenses();
        double salaryCosts = calculateSalaries();
        double profit = totalIncome - paperCosts - salaryCosts;

        System.out.printf("%nTotal income: %.2f%n", totalIncome);
        System.out.printf("Paper expenses: %.2f%n", paperCosts);
        System.out.printf("Salaries: %.2f%n", salaryCosts);
        System.out.printf("Net profit: %.2f%n", profit);

        System.out.println("\nEmployees:");
        for (Employee e : employees) {
            System.out.printf(" - %s, salary: %.2f%n", e.getName(), e.getBaseSalary());
        }

        System.out.println("\nPrinting Machines:");
        for (PrintingMachine m : machines) {
            System.out.printf(" - Machine: %s, pages printed: %d, loaded paper: %d%n",
                    m.getId(),
                    m.getTotalPagesPrinted(),
                    m.getCurrentPaperLoaded());
        }

        System.out.println("==========================================\n");
    }
}
