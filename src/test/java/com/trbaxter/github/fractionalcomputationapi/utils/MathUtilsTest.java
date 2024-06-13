package com.trbaxter.github.fractionalcomputationapi.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MathUtilsTest {

    @Test
    public void testGammaFunctionPositiveValues() {
        BigDecimal input = new BigDecimal("5");
        BigDecimal expected = new BigDecimal("24.000000000000000");
        BigDecimal result = MathUtils.gamma(input).setScale(15, RoundingMode.HALF_UP);
        assertEquals(expected, result);
    }

    @Test
    public void testGammaFunctionHalfValues() {
        BigDecimal input = new BigDecimal("0.5");
        BigDecimal expected = BigDecimal.valueOf(Math.sqrt(Math.PI)).setScale(15, RoundingMode.HALF_UP);
        BigDecimal result = MathUtils.gamma(input).setScale(15, RoundingMode.HALF_UP);
        assertEquals(expected, result);
    }

    @Test
    public void testGammaFunctionNegativeValues() {
        BigDecimal input = new BigDecimal("-0.5");
        BigDecimal expected = new BigDecimal("-3.5449077018110318").setScale(15, RoundingMode.HALF_UP);
        BigDecimal result = MathUtils.gamma(input).setScale(15, RoundingMode.HALF_UP);
        assertEquals(expected, result);
    }

    @Test
    public void testGammaFunctionNearZero() {
        BigDecimal input = new BigDecimal("0.01");
        BigDecimal expected = new BigDecimal("99.432585119150600").setScale(15, RoundingMode.HALF_UP);
        BigDecimal result = MathUtils.gamma(input).setScale(15, RoundingMode.HALF_UP);
        assertEquals(expected, result);
    }

    @Test
    public void testGammaFunctionSpecialCase() {
        BigDecimal input = new BigDecimal("1");
        BigDecimal expected = BigDecimal.ONE.setScale(15, RoundingMode.HALF_UP);
        BigDecimal result = MathUtils.gamma(input).setScale(15, RoundingMode.HALF_UP);
        assertEquals(expected, result);
    }

    @Test
    public void testMathUtilsConstructor() {
        Constructor<MathUtils> constructor = null;
        try {
            constructor = MathUtils.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
            fail("Constructor should throw an exception");
        } catch (InvocationTargetException e) {
            assertEquals(UnsupportedOperationException.class, e.getCause().getClass());
            assertEquals("Utility class for math operations", e.getCause().getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        } finally {
            if (constructor != null) {
                constructor.setAccessible(false);
            }
        }
    }
}