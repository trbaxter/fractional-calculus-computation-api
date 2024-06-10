package com.trbaxter.github.fractionalcomputationapi.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MathUtils.class)
public class MathUtilsTest {

    @Test
    public void testGammaFunctionForPositiveInput() {
        // Test value greater than 0.5
        double result = MathUtils.gamma(5.0);
        assertEquals(24.0, result, 0.0001);
    }

    @Test
    public void testGammaFunctionForReflectionFormula() {
        // Test value less than 0.5
        double result = MathUtils.gamma(0.1);
        assertEquals(9.513507698668732, result, 0.0001);
    }

    @Test
    public void testGammaFunctionForEdgeCase() {
        // Test value close to 0.5
        double result = MathUtils.gamma(0.5);
        assertEquals(Math.sqrt(Math.PI), result, 0.0001);
    }

    @Test
    public void testGammaFunctionForNegativeInput() {
        // Test value using the reflection formula
        double result = MathUtils.gamma(-0.5);
        assertEquals(-3.544907701811032, result, 0.0001);
    }
}
