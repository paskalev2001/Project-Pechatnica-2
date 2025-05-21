package org.informatics.domain;
import java.util.HashMap;
import java.util.Map;

public class PrintingMachine {
    private final String id;
    private final boolean isColorCapable;
    private final int maxPaperCapacity;
    private final int pagesPerMinute;
    private int currentPaperLoaded;
    private final Map<Edition, Integer> printedEditions; // къде е мястото на броя копия

    public PrintingMachine(String id, boolean isColorCapable, int maxPaperCapacity, int pagesPerMinute) {
        this.id = id;
        this.isColorCapable = isColorCapable;
        this.maxPaperCapacity = maxPaperCapacity;
        this.pagesPerMinute = pagesPerMinute;
        this.currentPaperLoaded = 0;
        this.printedEditions = new HashMap<>();
    }


    public int getTotalPagesPrinted() {
        return printedEditions.entrySet()
                .stream()
                .mapToInt(e -> e.getKey().getCopiesPrinted())
                .sum();
    }

    public void setCurrentPaperLoaded (int papers) {
        this.currentPaperLoaded = papers;
    }

    public int getCurrentPaperLoaded() {
        return currentPaperLoaded;
    }

    public String getId() {
        return id;
    }

    public boolean isColorCapable() {
        return isColorCapable;
    }

    public int getMaxPaperCapacity() {
        return maxPaperCapacity;
    }

    public int getPagesPerMinute() {
        return pagesPerMinute;
    }

    public Map<Edition, Integer> getPrintedEditions() {
        return printedEditions;
    }

    @Override
    public String toString() {
        return "PrintingMachine{" +
                "id='" + id + '\'' +
                ", colorCapable=" + isColorCapable +
                ", maxCapacity=" + maxPaperCapacity +
                ", loaded=" + currentPaperLoaded +
                ", pages/min=" + pagesPerMinute +
                '}';
    }
}

