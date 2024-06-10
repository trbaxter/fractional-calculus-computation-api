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
    public void testCaputoFractionalDerivativeZeroCoefficients() {
        double[] coefficients = {0.0, 0.0, 0.0};
        double alpha = 0.5;

        String result = computationService.caputoFractionalDerivative(coefficients, alpha);
        String expected = "";

        assertEquals(expected, result);
    }

}
