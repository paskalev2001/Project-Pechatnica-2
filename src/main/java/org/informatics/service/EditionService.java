package org.informatics.service;
import org.informatics.domain.Edition;

public class EditionService {
    public double getCostPerSheet(Edition edition) {
        return edition.getPublication().getPaperType().getBasePriceForA5() * edition.getPublication().getPageSize().getPriceMultiplier();
    }

    public int getTotalPagesPrinted(Edition edition) {
        return edition.getCopiesPrinted();
    }

    public double getTotalPaperCost(Edition edition) {
        return getCostPerSheet(edition) * getTotalPagesPrinted(edition);
    }

}
