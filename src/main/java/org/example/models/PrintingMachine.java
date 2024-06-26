package org.example.models;

import java.util.HashMap;
import java.util.Map;

public class PrintingMachine {
    private int maxPaperCapacity;
    private final boolean colorPrint;
    private int pagerPerMin;
    private int loadedPaper=0;
    private int pagesPrinted=0;
    private Map<String, Integer> printedIssues = new HashMap<>();

    public PrintingMachine(int maxPaperCapacity, boolean colorPrint, int pagerPerMin){
        this.maxPaperCapacity = maxPaperCapacity;
        this.colorPrint = colorPrint;
        this.pagerPerMin = pagerPerMin;
    }

    public void loadPaper(int paper){
        this.loadedPaper = paper;
    }

    public int getLoadedPaper(){
        return this.loadedPaper;
    }
    public void addIssue(String issueTitle, int copies, int pages){
       printedIssues.put(issueTitle, copies);
       this.pagesPrinted += pages;
       this.loadedPaper -=pages;
    }

    public boolean isColorPrint(){
        return this.colorPrint;
    }

    public Map<String, Integer> getPrintedIssues(){
        return printedIssues;
    }

    public int getPagesPrinted(){
        return this.pagesPrinted;
    }


}
