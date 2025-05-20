package org.informatics;

import org.informatics.domain.PageSize;
import org.informatics.domain.PaperType;

public class Publication {
    private String title;
    private int numberOfCopies; // Отпада
    private PageSize pageSize;
    private PaperType paperType;
    private boolean isColor; // Отпада
    private double pricePerCopy; // Да мине към big decimal и да мине към edition

    public Publication(String title, int numberOfCopies, PageSize pageSize, PaperType paperType, boolean isColor, double pricePerCopy) {
        this.title = title;
        this.numberOfCopies = numberOfCopies;
        this.pageSize = pageSize;
        this.paperType = paperType;
        this.isColor = isColor;
        this.pricePerCopy = pricePerCopy;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public PageSize getPageSize() {
        return pageSize;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public boolean isColor() {
        return isColor;
    }

    public double getPricePerCopy() {
        return pricePerCopy;
    }

    public void setPricePerCopy(double pricePerCopy) {
        this.pricePerCopy = pricePerCopy;
    }

    public double calculateTotalIncome() {
        return numberOfCopies * pricePerCopy;
    } //това не трябва да е в модела на данните

    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", pageSize=" + pageSize +
                ", paperType=" + paperType +
                ", isColor=" + isColor +
                ", pricePerCopy=" + pricePerCopy +
                '}';
    }
}