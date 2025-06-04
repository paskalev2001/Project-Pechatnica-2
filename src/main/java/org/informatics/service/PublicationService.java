package org.informatics.service;

import org.informatics.domain.Publication;
import org.informatics.domain.PageSize;
import org.informatics.domain.PaperType;

import java.util.*;

public class PublicationService {
    private Map<String, Publication> publications = new HashMap<>();

    public void addPublication(Publication publication) {
        if (publications.containsKey(publication.geId())) {
            throw new IllegalArgumentException("Publication with this ID already exists");
        }
        publications.put(publication.geId(), publication);
    }

    public Publication getPublicationById(String id) {
        if (!publications.containsKey(id)) {
            throw new NoSuchElementException("Publication not found");
        }
        return publications.get(id);
    }

    public List<Publication> getAllPublications() {
        return new ArrayList<>(publications.values());
    }

    public void updatePublication(String id, Publication updated) {
        if (!publications.containsKey(id)) {
            throw new NoSuchElementException("Publication not found");
        }
        publications.put(id, updated);
    }

    public void removePublication(String id) {
        if (!publications.containsKey(id)) {
            throw new NoSuchElementException("Publication not found");
        }
        publications.remove(id);
    }


    public double calculatePricePerCopy(PaperType paperType, PageSize pageSize, int pageCount) {
        if (pageCount <= 0) {
            throw new IllegalArgumentException("Page count must be positive");
        }
        double basePrice = paperType.getBasePriceForA5(); // за една страница А5
        double sizeMultiplier = pageSize.getPriceMultiplier();
        return basePrice * sizeMultiplier * pageCount;
    }

    public void recalculatePricePerCopy(String publicationId) {
        Publication pub = getPublicationById(publicationId);
        double newPrice = calculatePricePerCopy(pub.getPaperType(), pub.getPageSize(), pub.getPageCount());
        pub.setPricePerCopy(newPrice);
    }
}
