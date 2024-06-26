package org.example;

import org.example.models.Issue;
import org.example.models.Manager;
import org.example.models.Operator;
import org.example.models.PrintingMachine;
import org.example.services.PrintingHouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

class PrintingHouseTest {

    private PrintingHouse printingHouse;
    private Issue issue;
    private PrintingMachine machine;
    private Operator operator;
    private Manager manager;

    @BeforeEach
    void setUp() {
        issue = new Issue("Book", "Test Issue", 100, 10, "A4", "normal", false);
        printingHouse = new PrintingHouse(1000, issue, 50, 10.0);

        machine = new PrintingMachine(2000, true, 30);
        operator = new Operator(3000);
        manager = new Manager(4000, 500, 10000);
    }

    @Test
    void testAddPrintingMachine() {
        printingHouse.addPrintingMachine(machine);
        assertEquals(1, printingHouse.printingMachinesList.size());
    }

    @Test
    void testAddOperator() {
        printingHouse.addOperator(operator);
        assertEquals(1, printingHouse.operatorList.size());
    }

    @Test
    void testAddManager() {
        printingHouse.addManager(manager);
        assertEquals(1, printingHouse.managerList.size());
    }

    @Test
    void testSetPaperPrice() {
        printingHouse.setPaperPrice(1.0, 2.0, 0.5);
        Map<String, Double> priceTable = printingHouse.priceTable;
        assertEquals(4, priceTable.get("A4 normal"));
        assertEquals(8, priceTable.get("A4 glossy"));
        assertEquals(2, priceTable.get("A4 newspaper"));
    }



    @Test
    void testCalculateRevenue() {
        printingHouse.setPaperPrice(1.0, 2.0, 0.5);
        double revenue = printingHouse.calculateRevenue();
        assertEquals(3600, revenue);
    }

    @Test
    void testCalculateExpenses() {
        printingHouse.addOperator(operator);
        printingHouse.addManager(manager);
        printingHouse.setPaperPrice(1.0, 2.0, 0.5);
        double expenses = printingHouse.calculateExpenses();
        assertEquals(1000.0 + 3000.0 + 4000.0, expenses); // Adjust the expected value based on the setup
    }
}
