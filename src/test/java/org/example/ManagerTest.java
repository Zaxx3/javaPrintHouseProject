package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private Manager manager;

    @BeforeEach
    void setUp() {
        manager = new Manager(4000, 0.1, 10000);
    }

    @Test
    void testConstructor() {
        assertEquals(4000, manager.getBaseSalary());
    }

    @Test
    void testSetRevenue() {
        manager.setRevenue(15000);
        assertEquals(15000, manager.revenue);
    }

    @Test
    void testCalculateSalaryBelowThreshold() {
        manager.setRevenue(8000);
        assertEquals(4000, manager.calculateSalary());
    }

    @Test
    void testCalculateSalaryAboveThreshold() {
        manager.setRevenue(15000);
        assertEquals(5500, manager.calculateSalary());
    }
}
