package org.informatics.service;
import org.informatics.domain.PrintingMachine;
import org.informatics.domain.Edition;

public class PrintingMachineService {
    public int loadPaper(PrintingMachine pm, int sheets) {
        if (sheets <= 0) {
            throw new IllegalArgumentException("Cannot load zero or negative sheets.");
        }
        if (pm.getCurrentPaperLoaded() + sheets > pm.getMaxPaperCapacity()) {
            throw new IllegalArgumentException("Exceeds machine's paper capacity.");
        }
        int newLoaded = pm.getCurrentPaperLoaded() + sheets;
        pm.setCurrentPaperLoaded(newLoaded);
        return newLoaded;
    }

    public void printEdition(PrintingMachine pm, Edition edition) {
        if (edition.isColorPrint() && !pm.isColorCapable()) {
            throw new UnsupportedOperationException("This machine cannot print in color.");
        }

        int requiredSheets = edition.getCopiesPrinted() * edition.getPublication().getPageCount();

        if (requiredSheets > pm.getCurrentPaperLoaded()) {
            throw new IllegalStateException("Not enough paper loaded to print this edition.");
        }

        pm.setCurrentPaperLoaded(pm.getCurrentPaperLoaded() - requiredSheets);

        pm.getPrintedEditions().merge(edition, edition.getCopiesPrinted(), Integer::sum);
        System.out.printf("Machine %s printed %d copies of '%s'%n", pm.getId(), edition.getCopiesPrinted(), edition.getPublication().getTitle());
    }
}
