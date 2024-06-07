package com.trbaxter.github.fractionalcomputationapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MathOperationServiceTest {

    @Autowired
    private MathOperationsService mathOperationsService;

    @Test
    public void testPerformCalculations_order1() {
        List<Double> coefficients = List.of(3.0, 2.0, -5.0);
        List<Double> exponents = List.of(2.0, 1.0, 0.0);
        double order = 1.0;

        List<Double> newCoefficients = new ArrayList<>();
        List<Double> newExponents = new ArrayList<>();

        mathOperationsService.performCalculations(coefficients, exponents, order, newCoefficients, newExponents);

        assertEquals(3, newCoefficients.size());
        assertEquals(6.0, newCoefficients.get(0));
        assertEquals(2.0, newCoefficients.get(1));
        assertEquals(0.0, newCoefficients.get(2));

        assertEquals(3, newExponents.size());
        assertEquals(1.0, newExponents.get(0));
        assertEquals(0.0, newExponents.get(1));
        assertEquals(0.0, newExponents.get(2));
    }

    @Test
    public void testPerformCalculations_order2() {
        List<Double> coefficients = List.of(3.0, 2.0, -5.0);
        List<Double> exponents = List.of(2.0, 1.0, 0.0);
        double order = 2.0;

        List<Double> newCoefficients = new ArrayList<>();
        List<Double> newExponents = new ArrayList<>();

        mathOperationsService.performCalculations(coefficients, exponents, order, newCoefficients, newExponents);

        assertEquals(3, newCoefficients.size());
        assertEquals(6.0, newCoefficients.get(0));
        assertEquals(0.0, newCoefficients.get(1));
        assertEquals(0.0, newCoefficients.get(2));

        assertEquals(3, newExponents.size());
        assertEquals(0.0, newExponents.get(0));
        assertEquals(0.0, newExponents.get(1));
        assertEquals(0.0, newExponents.get(2));
    }

    @Test
    public void testPerformCalculationsInvalidOrder() {
        List<Double> coefficients = List.of(3.0, 2.0, -5.0);
        List<Double> exponents = List.of(2.0, 1.0, 0.0);
        double order = 1.1;

        List<Double> newCoefficients = new ArrayList<>();
        List<Double> newExponents = new ArrayList<>();

        assertThrows(IllegalArgumentException.class,
        () -> mathOperationsService.performCalculations(coefficients, exponents, order, newCoefficients, newExponents));
    }

    @Test
    public void testReassembleExpandedExpression() {
        List<Double> coefficients = List.of(6.0, 2.0, 0.0);
        List<Character> variables = List.of('x', 'x', ' ');
        List<Double> exponents = List.of(1.0, 0.0, 0.0);

        String result = mathOperationsService.reassembleExpandedExpression(coefficients, variables, exponents);
        assertEquals("6x + 2", result);
    }
}
