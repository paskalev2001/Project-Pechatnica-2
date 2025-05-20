package org.informatics.service;

import org.informatics.domain.Publication;

public class PublicationService {

    public double calculateTotalIncome(Publication publication) {
        return publication.getNumberOfCopies() * publication.getPricePerCopy();
    }

}
