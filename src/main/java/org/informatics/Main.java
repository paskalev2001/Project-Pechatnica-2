package org.informatics;

import org.informatics.domain.*;
import org.informatics.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //---- Publication Example
        System.out.println("------- Publication Example --------\n");

        PublicationService service = new PublicationService();

        Publication book = new Publication("b1", "Harry Potter and the Prisoner from Azkaban", PageSize.A4, PaperType.GLOSSY, 0, 250);
        Publication newspaper = new Publication("n1", "Daily herald News", PageSize.A3, PaperType.NEWSPAPER, 0, 24);
        Publication poster = new Publication("p1", "100 Kila Event", PageSize.A2, PaperType.NORMAL, 0, 2);

        service.addPublication(book);
        service.addPublication(newspaper);
        service.addPublication(poster);

        service.recalculatePricePerCopy("b1");
        service.recalculatePricePerCopy("n1");
        service.recalculatePricePerCopy("p1");

        for (Publication pub : service.getAllPublications()) {
            System.out.println(pub);
            System.out.println("Price per copy: " + pub.getPricePerCopy());
            System.out.println("---------------");
        }

        // Пример за промяна на цена на ръка (ако искаш да наложиш custom цена)
        book.setPricePerCopy(123.45);
        System.out.println("After setting the price per copy we get: " + book.getPricePerCopy());

        // Пример за премахване на издание
        service.removePublication("n1");
        System.out.println("Publications after we removed the News Paper :");
        for (Publication pub : service.getAllPublications()) {
            System.out.println(pub);
        }

        //------ Edition Func Example --------
        System.out.println("\n------ Edition Func Example --------\n");


        PublicationService publicationService = new PublicationService();
        publicationService.addPublication(book);
        publicationService.recalculatePricePerCopy("b1");

        System.out.println("Information for edition : " + book);
        System.out.println("Price per copy: " + book.getPricePerCopy());

        Edition edition = new Edition("ed1", book, 100, false);

        EditionService editionService = new EditionService();

        System.out.println("\n----- Printing info -----");
        System.out.println(edition);

        double costPerSheet = editionService.getCostPerSheet(edition);
        int totalSheets = editionService.getTotalPagesPrinted(edition);
        double totalPaperCost = editionService.getTotalPaperCost(edition);
        double totalIncome = editionService.calculateTotalIncome(edition);

        System.out.printf("Cost per sheet: %.2f lv.\n", costPerSheet);
        System.out.println("Total sheets: " + totalSheets);
        System.out.printf("Total paper cost: %.2f lv.\n", totalPaperCost);
        System.out.printf("Total Income: %.2f lv.\n", totalIncome);

        //---- Employees Example

        System.out.println("\n------- Employees Example --------\n");

        Employee op1 = new Operator("op1", "Ivan ivanov", 1200, "Production");
        Employee op2 = new Operator("op2", "Petur Petrov", 1400, "Production");
        Employee manager = new Manager("m1", "Jaki Menigera", 2000, "Management", 15, 10000);

        Map<String, Employee> employees = new HashMap<>();
        employees.put(op1.getId(), op1);
        employees.put(op2.getId(), op2);
        employees.put(manager.getId(), manager);

        EmployeeService employeeService = new EmployeeService();

        double totalIncomeEmployees = 12000;

        double totalPayroll = employeeService.getTotalPayroll(employees, totalIncomeEmployees);

        System.out.println("---- List of employees and their income " + totalIncomeEmployees + " ----");
        for (Employee e : employees.values()) {
            System.out.printf("%s: %.2f lv.%n", e.getName(), e.calculateSalary(totalIncomeEmployees));
        }
        System.out.printf("Total Payroll: %.2f lv.%n", totalPayroll);

        String searchId = "op2";
        Employee found = employeeService.findEmployeeById(employees, searchId);
        if (found != null) {
            System.out.println("Found employee: " + found);
        }

        // -------------- Printing Machine Example -------------------
        System.out.println("-------------- Printing Machine Example -------------------");


        PrintingMachine machine = new PrintingMachine("m1", false, 60000, 120); // 60 000 листа макс, 120 стр/мин

        PrintingMachineService pmService = new PrintingMachineService();

        System.out.println("Machine before being loaded:");
        System.out.println(machine);

        try {
            int loaded = pmService.loadPaper(machine, 50000);
            System.out.println("Loaded paper: " + loaded);
        } catch (Exception ex) {
            System.out.println("Error when being loaded: " + ex.getMessage());
        }

        System.out.println("Machine after being loaded:");
        System.out.println(machine);

        try {
            pmService.printEdition(machine, edition);
        } catch (Exception ex) {
            System.out.println("Error while printing: " + ex.getMessage());
        }

        System.out.println("Machine state after the print:");
        System.out.println(machine);
        System.out.println("Total Pages Printed: " + machine.getTotalPagesPrinted());

        Edition colorEdition = new Edition("ed2", book, 10, true);
        try {
            pmService.printEdition(machine, colorEdition);
        } catch (Exception ex) {
            System.out.println("Expected error when doing color print: " + ex.getMessage());
        }

        Edition bigEdition = new Edition("ed3", book, 100000, false);
        try {
            pmService.printEdition(machine, bigEdition);
        } catch (Exception ex) {
            System.out.println("Expected error when paper is not enough: " + ex.getMessage());
        }

        System.out.println("\n--- Edition that were printed on the machimnes ---");
        machine.getPrintedEditions().forEach((ed, count) ->
                System.out.println(ed + " - copies: " + count)
        );

        //---------- Printing House Example ------------
        System.out.println("\n---------- Printing House Example ------------\n");

        Publication publication = new Publication(
                "book4",
                "Java book",
                PageSize.A4,
                PaperType.GLOSSY,
                0,
                200     // 200 страници
        );

        Edition edition2 = new Edition(
                "ed1",
                publication,
                100,
                true
        );

        PrintingMachine machine2 = new PrintingMachine(
                "pm1",
                true,
                30000,
                100
        );

        PrintingMachineService machineService = new PrintingMachineService();

        try {
            int loaded = machineService.loadPaper(machine2, 20000);
            System.out.println("Successfully loaded paper: " + loaded);
        } catch (Exception ex) {
            System.out.println("Error loading paper: " + ex.getMessage());
        }

        try {
            machineService.printEdition(machine2, edition2);
        } catch (Exception ex) {
            System.out.println("Error while printing: " + ex.getMessage());
        }

        System.out.println(machine2);
        System.out.println("Total pages printed: " + machine2.getTotalPagesPrinted());

        System.out.println("\n----- Report for printed edition ----");
        machine2.getPrintedEditions().forEach((ed, count) ->
                System.out.println(ed + " - printed copies: " + count)
        );

        try {
            machineService.loadPaper(machine2, 20000);
        } catch (Exception ex) {
            System.out.println("Очаквана грешка при презареждане: " + ex.getMessage());
        }

        PrintingMachine bwMachine = new PrintingMachine(
                "pm2",
                false,
                10000,
                80
        );
        machineService.loadPaper(bwMachine, 5000);
        try {
            machineService.printEdition(bwMachine, edition);
        } catch (Exception ex) {
            System.out.println("Expected error when printing on BW machine " + ex.getMessage());
        }
    }
}