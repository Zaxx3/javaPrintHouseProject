package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    private Operator operator;

    @BeforeEach
    void setUp() {
        operator = new Operator(3000);
    }

    @Test
    void testConstructor() {
        assertEquals(3000, operator.getBaseSalary());
    }

    @Test
    void testCalculateSalary() {
        assertEquals(3000, operator.calculateSalary());
    }
}
