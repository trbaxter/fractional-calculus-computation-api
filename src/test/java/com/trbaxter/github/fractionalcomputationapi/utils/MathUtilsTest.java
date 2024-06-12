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

    @Test
    public void testGamma() {
        // Gamma(0.5) should be sqrt(pi)
        assertEquals(Math.sqrt(Math.PI), MathUtils.gamma(0.5), 0.0001);

        // Gamma(1) should be 1
        assertEquals(1.0, MathUtils.gamma(1), 0.0001);

        // Gamma(2) should be 1! = 1
        assertEquals(1.0, MathUtils.gamma(2), 0.0001);

        // Gamma(3) should be 2! = 2
        assertEquals(2.0, MathUtils.gamma(3), 0.0001);

        // Gamma(4) should be 3! = 6
        assertEquals(6.0, MathUtils.gamma(4), 0.0001);

        // Gamma(2.5) should be approximately 1.329
        assertEquals(1.3293403881791384, MathUtils.gamma(2.5), 0.0001);

        // Gamma(1.5) should be approximately 0.886
        assertEquals(0.8862269254527586, MathUtils.gamma(1.5), 0.0001);
    }
}
