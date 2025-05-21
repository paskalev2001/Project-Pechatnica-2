package org.informatics.domain;

public class Edition {
    private String id;
    private Publication publication;
    private int copiesPrinted;
    private boolean isColorPrint;

    public Edition( String id, Publication publication, int copiesPrinted, boolean isColorPrint) {
        if (copiesPrinted <= 0) {
            throw new IllegalArgumentException("Number of printed copies must be positive.");
        }

        this.id = id;
        this.publication = publication;
        this.copiesPrinted = copiesPrinted;
        this.isColorPrint = isColorPrint;
    }
    public String getId() { return id;}

    public Publication getPublication() {
        return publication;
    }

    public int getCopiesPrinted() {
        return copiesPrinted;
    }

    public boolean isColorPrint() {
        return isColorPrint;
    }

    @Override
    public String toString() {
        return String.format("Edition of '%s' - printed %d copies, %s print",
                publication.getTitle(), copiesPrinted, isColorPrint ? "Color" : "Black & White");
    }
}
