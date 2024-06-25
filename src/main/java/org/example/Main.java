package org.example;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.util.*;

abstract class Employee{
    protected double baseSalary;
    public Employee(double baseSalary){
        this.baseSalary = baseSalary;
    }
    public double getBaseSalary(){
        return this.baseSalary;
    }

    public abstract double calculateSalary();


}

class Operator extends Employee {

    public Operator(double baseSalary){
        super(baseSalary);
    }

    @Override
    public double calculateSalary(){
        return getBaseSalary();
    }

}

class Manager extends Employee{
    private final double bonusSalary;
    private final double threshold;
    double revenue;
    public Manager(double baseSalary, double bonusSalary, double threshold){
        super(baseSalary);
        this.bonusSalary = bonusSalary;
        this.threshold = threshold;
    }

    public void setRevenue(double revenue){
        this.revenue = revenue;
    }

    //TODO
    //Salary increase with a %
    @Override
    public double calculateSalary(){
        if(revenue>threshold){
            return getBaseSalary()+(revenue*bonusSalary);
        }else{
            return getBaseSalary();
        }
    }


}

class PrintingMachine {
    private int maxPaperCapacity;
    private final boolean colorPrint;
    private int pagerPerMin;
    private int loadedPaper=0;
    private int pagesPrinted=0;
    private final Map<String, Integer> printedIssues = new HashMap<>();

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




class Issue{
    private String type;
    private final String title;
    private final boolean isColored;
    private final int amount;
    private final int pages;
    private String pageSize;
    private String paperType;

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
    protected String getPageSize(){
        return pageSize;
    }
    protected String getPaperType(){
        return paperType;
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

    protected String getTitle(){
        return this.title;
    }
    protected int getAmount(){
        return amount;
    }
    protected int getPages(){
        return pages;
    }
    protected boolean getIsColored(){
        return isColored;
    }
    protected List<String> getPageSizeTable(){
        return pageSizeTable;
    }
    protected List<String> getPaperTypeTable(){
        return paperTypeTable;
    }

}

class PrintingHouse{

    //Primitive Types
    private double basePriceNormalPaper;
    private double basePriceGlossyPaper;
    private double basePriceNewspaperPaper;
    private final int amountPaperBought;
    private final int discountThreshold;
    private final double discount;

    //Spicy Types
    final Map<String, Double> priceTable = new HashMap<>();
    private final List<String> pageSizeTable;
    private final List<String> paperTypeTable;

   //Other Classes
    final List<Operator> operatorList = new ArrayList<>();
    final List<Manager> managerList = new ArrayList<>();
    private final List<Issue> issues = new ArrayList<>();
    final List<PrintingMachine> printingMachinesList = new ArrayList<>();




    public PrintingHouse(int amountPaperBought, Issue issue, int discountThreshold, double discount){
        this.amountPaperBought = amountPaperBought;
        this.discountThreshold = discountThreshold;
        this.discount = discount;
        issues.add(issue);
        pageSizeTable = issue.getPageSizeTable();
        paperTypeTable = issue.getPaperTypeTable();
    }

    public void addPrintingMachine(PrintingMachine machine){
        printingMachinesList.add(machine);
    }

    public void addOperator (Operator op){
        operatorList.add(op);
    }

    public void addManager (Manager mg){
        managerList.add(mg);
    }



    public void setPaperPrice(double basePriceNormalPaper, double basePriceGlossyPaper, double basePriceNewspaperPaper) {
        this.basePriceNormalPaper = basePriceNormalPaper;
        this.basePriceGlossyPaper = basePriceGlossyPaper;
        this.basePriceNewspaperPaper = basePriceNewspaperPaper;
        setPriceTable();
    }


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


    public void startPrint(){
        int printingMachines = printingMachinesList.size();
        for(Issue issue : issues){
            int pagerPerIssue = issue.getPages()*issue.getAmount();
            for(int i=0; i<printingMachines; i++){
                if(pagerPerIssue<=printingMachinesList.get(i).getLoadedPaper()){
                    if(issue.getIsColored()==printingMachinesList.get(i).isColorPrint()){
                        printingMachinesList.get(i).addIssue(issue.getTitle(), issue.getAmount(), pagerPerIssue);

                    }else if(i+1==printingMachines){
                        throw new Error("Invalid print type");
                    }
                }else if(i+1==printingMachines){
                    throw new Error("Not enough paper in machine");
                }


            }
        }
    }

    public double calculateRevenue(){
        double revenue=0;
        double pricePerIssue;
        for(Issue issue : issues){
            String pageType = issue.getPageSize()+" "+issue.getPaperType();
            double pricePerPage = priceTable.get(pageType);
            if(issue.getAmount()<discountThreshold){
                pricePerIssue = pricePerPage*issue.getPages();
            }else{
                pricePerIssue = pricePerPage*issue.getPages();
                double discountedPrice = pricePerIssue-(((pricePerPage*issue.getPages())*discount)/100);
                pricePerIssue = discountedPrice;
            }
            revenue+=pricePerIssue*issue.getAmount();
        }
        return revenue;
    }

    public double calculateExpenses(){
        double opExpenses=0;
        double mgExpenses=0;
        double paperExpenses;

        for (Operator operator : operatorList) {
            opExpenses += operator.getBaseSalary();
        }
        for (Manager manager : managerList){
            mgExpenses += manager.calculateSalary();
        }
        paperExpenses = amountPaperBought*basePriceNormalPaper;

        opExpenses+=mgExpenses+paperExpenses;
        return opExpenses;

    }


}







public class Main{

    public static String[] handleFileReading(File input) throws FileNotFoundException {
        String[] data = new String[(int) input.length()];
        int index = 0;

       String[] rawData;

        Scanner reader = new Scanner(input);
        while(reader.hasNext()){
            rawData = reader.nextLine().split(" ");

            data[index] = rawData[2];
            index++;
        }



       return data;
    }

    public static void main(String[] args) {
        try{
            FileWriter writer = new FileWriter("printHouseOutput.txt");
            File input = new File("input.txt");
            String[] data;
            data = handleFileReading(input);

//        Issue issue = new Issue("Book", "Wee", 10,12 , "A4", "normal", true);
//        PrintingHouse printingHouse = new PrintingHouse(  100000, issue, 10, 5);
//        printingHouse.setPaperPrice(1,1.5,0.5);
//        Operator op1 = new Operator(1200);
//        Manager mg1 = new Manager(2000, 1000, 500);
//        PrintingMachine pm = new PrintingMachine(10000, true, 20);

        String type = data[0];
        String title = data[1];
        int amount = Integer.parseInt(data[2]);
        int pages = Integer.parseInt(data[3]);
        String pageSize = data[4];
        String paperType = data[5];
        boolean isColored = Boolean.parseBoolean(data[6]);
        int paperBought = Integer.parseInt(data[7]);
        int discountThreshold = Integer.parseInt(data[8]);
        int discount = Integer.parseInt(data[9]);
        double basePriceNormalPaper = Double.parseDouble(data[10]);
        double basePriceGlossyPaper = Double.parseDouble(data[11]);
        double basePriceNewspaperPaper = Double.parseDouble(data[12]);
        int salary = Integer.parseInt(data[13]);
        int bonusSalary = Integer.parseInt(data[14]);
        int threshold = Integer.parseInt(data[15]);
        int maxPaperCapacity = Integer.parseInt(data[16]);
        boolean colorPrint = Boolean.parseBoolean(data[17]);
        int pagesPerMin = Integer.parseInt(data[18]);


        Issue issue = new Issue(type, title, amount, pages, pageSize, paperType, isColored);
        PrintingHouse printingHouse = new PrintingHouse(paperBought, issue, discountThreshold, discount);
        printingHouse.setPaperPrice(basePriceNormalPaper, basePriceGlossyPaper, basePriceNewspaperPaper);
        Operator op1 = new Operator(salary);
        Manager mg1 = new Manager(salary, bonusSalary, threshold);
        PrintingMachine pm = new PrintingMachine(maxPaperCapacity, colorPrint, pagesPerMin);




        pm.loadPaper(100000);
        printingHouse.addPrintingMachine(pm);
        printingHouse.addOperator(op1);
        printingHouse.addManager(mg1);
        printingHouse.startPrint();

        double revenueNumber = printingHouse.calculateRevenue();
        mg1.setRevenue(revenueNumber);
        double expensesNumber = printingHouse.calculateExpenses();

            String expenses = "Expenses of the printing house come out to be: "+expensesNumber+"\n";
            String revenue = "Revenue of the printing house is: "+revenueNumber;


            writer.write(expenses);
            writer.write(revenue);
            writer.close();

        }catch (Exception e){
            System.out.println("File error occurred");
            e.printStackTrace();
        }


    }
}