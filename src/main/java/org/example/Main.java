package org.example;


import java.util.*;

class printingHouse{

    private double basePriceNormalPaper;
    private double basePriceGlossyPaper;
    private double basePriceNewspaperPaper;
    private final Map<String, Double> priceTable = new HashMap<>();

    private final List<String> pageSizeTable = Arrays.asList("A1", "A2", "A3", "A4", "A5");
    private final List<String> paperTypeTable = Arrays.asList("normal", "glossy", "newspaper");

    private String type;
    private String title;
    private int amount;
    private String pageSize;
    private String paperType;

    public printingHouse(String type, String title, int amount, String pageSize, String paperType){
        this.type = type;
        this.title = title;
        this.amount = amount;
        setPageSize(pageSize);
        setPaperType(paperType);
    }

    private void setPageSize(String pageSize){
      if(pageSizeTable.contains(pageSize)){
          this.pageSize = pageSize;
      }else{
          throw new Error("Invalid Page Size");
      }
    }

    private void setPaperType(String paperType){
      if(paperTypeTable.contains(paperType)){
          this.paperType = paperType;
      }else{
          throw new Error("Invalid Paper Type");
      }
    }

    public void setPaperPrice(double basePriceNormalPaper, double basePriceGlossyPaper, double basePriceNewspaperPaper) {
        this.basePriceNormalPaper = basePriceNormalPaper;
        this.basePriceGlossyPaper = basePriceGlossyPaper;
        this.basePriceNewspaperPaper = basePriceNewspaperPaper;
        setPriceTable();
    }

    //TODO
    //Correct the pricing
    private void setPriceTable(){
        for (String s : paperTypeTable) {
            for (int j = 0; j < pageSizeTable.size(); j++) {
                String size = pageSizeTable.get(j);
                String sizePaperCombination = size + " " + s;
                double price = switch (s) {
                    case "normal" -> this.basePriceNormalPaper * (j + 1);
                    case "glossy" -> this.basePriceGlossyPaper * (j + 1);
                    case "newspaper" -> this.basePriceNewspaperPaper * (j + 1);
                    default -> 0;
                };
                priceTable.put(sizePaperCombination, price);
            }
        }
    }

    public double getPrice(String pageSize, String paperType){
        String sizePaperCombination = pageSize+" "+paperType;
        if(priceTable.containsKey(sizePaperCombination)){
            return priceTable.get(sizePaperCombination);
        }else{
            throw new Error("Invalid Paper Type and/or Size");
        }


    }




}







public class Main{
    public static void main(String[] args) {
        printingHouse issue1 = new printingHouse("Book", "wee", 4, "A3", "normal");
        issue1.setPaperPrice(1,1.5,0.5);


        System.out.println(issue1.getPrice("A2", "newspapers"));

    }
}