package org.example;




import org.example.models.*;
import org.example.services.PrintingHouse;
import org.example.exceptions.InvalidPageSizeException;
import org.example.exceptions.InvalidPaperTypeException;
import org.example.exceptions.InvalidPrintTypeException;
import org.example.exceptions.NotEnoughPaperException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.util.*;


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

        }catch (FileNotFoundException e) {
            System.out.println("File error occurred");
            e.printStackTrace();
        }catch (InvalidPageSizeException | InvalidPaperTypeException | InvalidPrintTypeException | NotEnoughPaperException e) {
            System.out.println("Printing error occurred: " + e.getMessage());
            e.printStackTrace();
        }catch (IOException e) {
            System.out.println("File writing error occurred");
            e.printStackTrace();
        }


    }
}