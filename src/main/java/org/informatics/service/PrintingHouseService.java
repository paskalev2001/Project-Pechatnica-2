package org.informatics.service;

import org.informatics.domain.*;

import java.util.*;

public class PrintingHouseService {

    private final PrintingHouse printingHouse;

    private final Map<PaperType, Double> basePaperPrices;

    private final double sizeIncreasePercent;

    public PrintingHouseService(PrintingHouse printingHouse,
                                Map<PaperType, Double> basePaperPrices,
                                double sizeIncreasePercent) {
        this.printingHouse = printingHouse;
        this.basePaperPrices = basePaperPrices;
        this.sizeIncreasePercent = sizeIncreasePercent;
    }

    public double getPaperPrice(PaperType type, PageSize size) {
        double basePrice = basePaperPrices.getOrDefault(type, 0.0);
        int sizeDifference = size.ordinal();
        return basePrice * Math.pow(1 + sizeIncreasePercent / 100.0, sizeDifference);
    }

    public void loadPaperToMachine(String machineId, int quantity) {
        PrintingMachine machine = findMachineById(machineId);
        machine.setCurrentPaperLoaded(quantity);
    }

    private final PrintingMachineService printingMachineService = new PrintingMachineService();

    public void printEdition(String machineId, Publication publication, boolean isColor, int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("Броят копия трябва да е положителен.");
        }

        PrintingMachine machine = findMachineById(machineId);

        if (isColor && !machine.isColorCapable()) {
            throw new IllegalArgumentException("Машината не поддържа цветен печат.");
        }

        String editionId = UUID.randomUUID().toString();
        Edition edition = new Edition(editionId, publication, copies, isColor);

        printingMachineService.printEdition(machine, edition);
    }

    public double calculateSalaryExpenses() {
        double total = 0.0;
        for (Employee e : printingHouse.getEmployees()) {
            double base = e.getBaseSalary();
            if (e instanceof Manager) {
                double income = calculateTotalIncome();
                if (income >= printingHouse.getManagerBonusThreshold()) {
                    base += base * printingHouse.getManagerBonusPercent() / 100.0;
                }
            }
            total += base;
        }
        return total;
    }

    public double calculatePaperExpenses() {
        double total = 0.0;

        for (PrintingMachine m : printingHouse.getMachines()) {
            for (Map.Entry<Edition, Integer> entry : m.getPrintedEditions().entrySet()) {
                Edition edition = entry.getKey();
                int copies = edition.getCopiesPrinted();
                Publication pub = edition.getPublication();

                PaperType type = pub.getPaperType();
                PageSize size = pub.getPageSize();
                int pagesPerCopy = pub.getPageCount();

                int totalSheetsUsed = pagesPerCopy * copies;
                double sheetPrice = getPaperPrice(type, size);

                total += totalSheetsUsed * sheetPrice;
            }
        }

        return total;
    }

    public double calculateTotalIncome() {
        double total = 0.0;
        for (Edition e : printingHouse.getEditions()) {
            int copies = e.getCopiesPrinted();
            double pricePerCopy = e.getPublication().getPricePerCopy();

            if (copies > printingHouse.getDiscountThresholdCopies()) {
                pricePerCopy *= 1 - printingHouse.getDiscountPercent() / 100.0;
            }

            total += pricePerCopy * copies;
        }
        return total;
    }

    public int getTotalPrintedPages(String machineId) {
        PrintingMachine machine = findMachineById(machineId);
        return machine.getTotalPagesPrinted();
    }

    private PrintingMachine findMachineById(String id) {
        return printingHouse.getMachines().stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Няма такава машина: " + id));
    }
}
