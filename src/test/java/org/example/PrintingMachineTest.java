package org.example;

import org.example.models.PrintingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PrintingMachineTest {

    private PrintingMachine printingMachine;

    @BeforeEach
    void setUp() {
        printingMachine = new PrintingMachine(10000, true, 20);
    }

    @Test
    void testConstructor() {
        assertEquals(0, printingMachine.getLoadedPaper());
        assertTrue(printingMachine.isColorPrint());
    }

    @Test
    void testLoadPaper() {
        printingMachine.loadPaper(5000);
        assertEquals(5000, printingMachine.getLoadedPaper());
    }

    @Test
    void testAddIssue() {
        printingMachine.loadPaper(10000);
        printingMachine.addIssue("Issue 1", 100, 1000);

        Map<String, Integer> printedIssues = printingMachine.getPrintedIssues();
        assertEquals(1, printedIssues.size());
        assertTrue(printedIssues.containsKey("Issue 1"));
        assertEquals(100, printedIssues.get("Issue 1"));

        assertEquals(1000, printingMachine.getPagesPrinted());
        assertEquals(9000, printingMachine.getLoadedPaper());
    }

    @Test
    void testGetPagesPrinted() {
        printingMachine.loadPaper(10000);
        printingMachine.addIssue("Issue 1", 100, 1000);
        printingMachine.addIssue("Issue 2", 200, 2000);

        assertEquals(3000, printingMachine.getPagesPrinted());
    }

    @Test
    void testGetPrintedIssues() {
        printingMachine.loadPaper(10000);
        printingMachine.addIssue("Issue 1", 100, 1000);

        Map<String, Integer> printedIssues = printingMachine.getPrintedIssues();
        assertEquals(1, printedIssues.size());
        assertEquals(100, printedIssues.get("Issue 1"));
    }

    @Test
    void testIsColorPrint() {
        assertTrue(printingMachine.isColorPrint());
    }

    @Test
    void testLoadPaperBeyondCapacity() {
        printingMachine.loadPaper(15000); // Exceeds max capacity
        assertEquals(15000, printingMachine.getLoadedPaper()); // The current implementation does not check for max capacity, update if necessary
    }
}
