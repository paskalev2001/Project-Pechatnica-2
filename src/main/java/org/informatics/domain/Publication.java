package org.informatics.domain;

public class Publication {
    private String id;
    private String title;
    private PageSize pageSize;
    private PaperType paperType;
    private double pricePerCopy;
    private int pageCount;

    public Publication(String id, String title, int numberOfCopies, PageSize pageSize, PaperType paperType,
                       boolean isColor, double pricePerCopy, int pageCount) {
        if (numberOfCopies < 0) {
            throw new IllegalArgumentException("Number of copies cannot be negative");
        }
        if (pricePerCopy < 0) {
            throw new IllegalArgumentException("Price per copy cannot be negative");
        }
        if (pageCount < 0) {
            throw new IllegalArgumentException("Page count cannot be negative");
        }
        this.id = id;
        this.title = title;
        this.pageSize = pageSize;
        this.paperType = paperType;
        this.pricePerCopy = pricePerCopy;
        this.pageCount = pageCount;
    }

    public String geId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public int getPageCount() {
        return pageCount;
    }

    public PageSize getPageSize() {
        return pageSize;
    }

    public PaperType getPaperType() {
        return paperType;
    }


    public double getPricePerCopy() {
        return pricePerCopy;
    }

    public void setPricePerCopy(double pricePerCopy) {
        if (pricePerCopy < 0) {
            throw new IllegalArgumentException("Price per copy cannot be negative");
        }
        this.pricePerCopy = pricePerCopy;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", pageSize=" + pageSize +
                ", paperType=" + paperType +
                ", pricePerCopy=" + pricePerCopy +
                '}';
    }
}