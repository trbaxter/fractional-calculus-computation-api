package com.trbaxter.github.fractionalcomputationapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputationServiceTest {

    private ComputationService computationService;

    @BeforeEach
    public void setUp() {
        computationService = new ComputationService();
    }

    @Test
    public void testDerivativeConstantTerm() {
        double[] coefficients = {3.0}; // Represents a constant value of 3.0
        double alpha = 0.5;

        String result = computationService.caputoFractionalDerivative(coefficients, alpha);
        String expected = ""; // Derivative of a constant should be zero

        assertEquals(expected, result);
    }

    @Test
    public void testDerivativeNonZeroCoefficients() {
        double[] coefficients = {3.0, 2.0, 1.0}; // Represents the polynomial 3x^2 + 2x + 1
        double alpha = 0.5;

        String result = computationService.caputoFractionalDerivative(coefficients, alpha);
        String expected = "4.514x^1.500 + 2.257x^0.500";

        assertEquals(expected, result);
    }

    @Test
    public void testDerivativeDifferentAlpha() {
        double[] coefficients = {3.0, 0.0, 1.0}; // Represents the polynomial 3x^2 + 1
        double alpha = 1.5;

        String result = computationService.caputoFractionalDerivative(coefficients, alpha);
        String expected = "6.770x^0.500";

        assertEquals(expected, result);
    }

    @Test
    public void testDerivativeZeroCoefficients() {
        double[] coefficients = {0.0, 0.0, 0.0}; // Represents an empty expression
        double alpha = 0.5;

        String result = computationService.caputoFractionalDerivative(coefficients, alpha);
        String expected = "";

        assertEquals(expected, result);
    }
}
