package org.example.services;

import org.example.exceptions.InvalidPrintTypeException;
import org.example.exceptions.NotEnoughPaperException;
import org.example.models.*;


import java.util.*;

public class PrintingHouse {

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
    final Set<Operator> operatorList = new HashSet<>();
    final Set<Manager> managerList = new HashSet<>();
    private final List<Issue> issues = new ArrayList<>();
    final List<PrintingMachine> printingMachinesList = new ArrayList<>();


    public PrintingHouse(int amountPaperBought, Issue issue, int discountThreshold, double discount) {
        this.amountPaperBought = amountPaperBought;
        this.discountThreshold = discountThreshold;
        this.discount = discount;
        issues.add(issue);
        pageSizeTable = issue.getPageSizeTable();
        paperTypeTable = issue.getPaperTypeTable();
    }

    public void addPrintingMachine(PrintingMachine machine) {
        printingMachinesList.add(machine);
    }

    public void addOperator(Operator op) {
        operatorList.add(op);
    }

    public void addManager(Manager mg) {
        managerList.add(mg);
    }


    public void setPaperPrice(double basePriceNormalPaper, double basePriceGlossyPaper, double basePriceNewspaperPaper) {
        this.basePriceNormalPaper = basePriceNormalPaper;
        this.basePriceGlossyPaper = basePriceGlossyPaper;
        this.basePriceNewspaperPaper = basePriceNewspaperPaper;
        setPriceTable();
    }


    private void setPriceTable() {
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


    public void startPrint() {
        int printingMachines = printingMachinesList.size();
        for (Issue issue : issues) {
            int pagerPerIssue = issue.getPages() * issue.getAmount();
            for (int i = 0; i < printingMachines; i++) {
                if (pagerPerIssue <= printingMachinesList.get(i).getLoadedPaper()) {
                    if (issue.getIsColored() == printingMachinesList.get(i).isColorPrint()) {
                        printingMachinesList.get(i).addIssue(issue.getTitle(), issue.getAmount(), pagerPerIssue);

                    } else if (i + 1 == printingMachines) {
                        throw new InvalidPrintTypeException("Invalid print type for color");
                    }
                } else if (i + 1 == printingMachines) {
                    throw new NotEnoughPaperException("Not enough paper in machine");
                }


            }
        }
    }

    public double calculateRevenue() {
        double revenue = 0;
        double pricePerIssue;
        for (Issue issue : issues) {
            String pageType = issue.getPageSize() + " " + issue.getPaperType();
            double pricePerPage = priceTable.get(pageType);
            if (issue.getAmount() < discountThreshold) {
                pricePerIssue = pricePerPage * issue.getPages();
            } else {
                pricePerIssue = pricePerPage * issue.getPages();
                double discountedPrice = pricePerIssue - (((pricePerPage * issue.getPages()) * discount) / 100);
                pricePerIssue = discountedPrice;
            }
            revenue += pricePerIssue * issue.getAmount();
        }
        return revenue;
    }

    public double calculateExpenses() {
        double opExpenses = 0;
        double mgExpenses = 0;
        double paperExpenses;

        for (Operator operator : operatorList) {
            opExpenses += operator.getBaseSalary();
        }
        for (Manager manager : managerList) {
            mgExpenses += manager.calculateSalary();
        }
        paperExpenses = amountPaperBought * basePriceNormalPaper;

        opExpenses += mgExpenses + paperExpenses;
        return opExpenses;

    }


}
