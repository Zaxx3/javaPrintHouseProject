package org.example.models;

import org.example.exceptions.InvalidPageSizeException;
import org.example.exceptions.InvalidPaperTypeException;

import java.util.Arrays;
import java.util.List;

public class Issue{
    private String type;
    private final String title;
    private final boolean isColored;
    private final int amount;
    private final int pages;
    protected String pageSize;
    protected String paperType;

    private final List<String> pageSizeTable = Arrays.asList("A1", "A2", "A3", "A4", "A5");
    private final List<String> paperTypeTable = Arrays.asList("normal", "glossy", "newspaper");

    public Issue(String type, String title, int amount, int pages,String pageSize, String paperType, boolean isColored){
        this.type = type;
        this.title = title;
        this.amount = amount;
        this.pageSize = pageSize;
        this.paperType = paperType;
        this.pages = pages;
        this.isColored = isColored;
        setPageSize(pageSize);
        setPaperType(paperType);


    }
    public String getPageSize(){
        return pageSize;
    }
    public String getPaperType(){
        return paperType;
    }

    private void setPageSize(String pageSize){
        if(pageSizeTable.contains(pageSize)){
            this.pageSize = pageSize;
        }else{
            throw new InvalidPageSizeException("Invalid Page Size: "+pageSize);
        }
    }

    private void setPaperType(String paperType){
        if(paperTypeTable.contains(paperType)){
            this.paperType = paperType;
        }else{
            throw new InvalidPaperTypeException("Invalid Paper Type: " + paperType);
        }
    }

    public String getTitle(){
        return this.title;
    }
    public int getAmount(){
        return amount;
    }
    public int getPages(){
        return pages;
    }
    public boolean getIsColored(){
        return isColored;
    }
    public List<String> getPageSizeTable(){
        return pageSizeTable;
    }
    public List<String> getPaperTypeTable(){
        return paperTypeTable;
    }

}
