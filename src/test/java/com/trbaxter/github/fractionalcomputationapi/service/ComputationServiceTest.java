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
        double[] coefficients = {3.0}; // Represents the polynomial 3
        double alpha = 0.5;

        // Print the coefficients and alpha for debugging
        System.out.println("Testing Caputo Fractional Derivative for Single Term");
        System.out.println("Coefficients: 3.0");
        System.out.println("Alpha: " + alpha);

        String result = computationService.caputoFractionalDerivative(coefficients, alpha);
        String expected = ""; // Derivative of a constant should be zero

        // Print the result for debugging
        System.out.println("Result: " + result);
        System.out.println("Expected: " + expected);

        assertEquals(expected, result);
    }

//    @Test
//    public void testDerivativeNonZeroCoefficients() {
//        double[] coefficients = {3.0, 2.0, 1.0}; // Represents the polynomial 3x^2 + 2x + 1
//        double alpha = 0.5;
//
//        // Print the coefficients and alpha for debugging
//        System.out.println("Testing Caputo Fractional Derivative for Non-Zero Coefficients");
//        System.out.println("Coefficients: 3.0, 2.0, 1.0");
//        System.out.println("Alpha: " + alpha);
//
//        String result = computationService.caputoFractionalDerivative(coefficients, alpha);
//        String expected = "4.514x^1.500 + 2.257x^0.500"; // Adjusted expected result
//
//        // Print the result for debugging
//        System.out.println("Result: " + result);
//        System.out.println("Expected: " + expected);
//
//        assertEquals(expected, result);
//    }

    @Test
    public void testDerivativeDifferentAlpha() {
        double[] coefficients = {3.0, 0.0, 1.0}; // Represents the polynomial 3x^2 + 1
        double alpha = 1.5;

        // Print the coefficients and alpha for debugging
        System.out.println("Testing Caputo Fractional Derivative for Different Alpha");
        System.out.println("Coefficients: 3.0, 0.0, 1.0");
        System.out.println("Alpha: " + alpha);

        String result = computationService.caputoFractionalDerivative(coefficients, alpha);
        String expected = "6.770x^0.500"; // Adjusted expected result

        // Print the result for debugging
        System.out.println("Result: " + result);
        System.out.println("Expected: " + expected);

        assertEquals(expected, result);
    }

    @Test
    public void testDerivativeZeroCoefficients() {
        double[] coefficients = {0.0, 0.0, 0.0}; // Represents a zero polynomial
        double alpha = 0.5;

        // Print the coefficients and alpha for debugging
        System.out.println("Testing Caputo Fractional Derivative for Zero Coefficients");
        System.out.println("Coefficients: 0.0, 0.0, 0.0");
        System.out.println("Alpha: " + alpha);

        String result = computationService.caputoFractionalDerivative(coefficients, alpha);
        String expected = "";

        // Print the result for debugging
        System.out.println("Result: " + result);
        System.out.println("Expected: " + expected);

        assertEquals(expected, result);
    }
}
