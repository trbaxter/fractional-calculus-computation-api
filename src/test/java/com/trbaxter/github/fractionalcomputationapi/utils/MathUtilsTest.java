package com.trbaxter.github.fractionalcomputationapi.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * MathUtilsTest is a test class for the MathUtils utility class. It tests various cases for the
 * gamma function and ensures proper behavior of the MathUtils class.
 */
public class MathUtilsTest {

  private static final int SCALE = 15;

  @Test
  public void testGammaFunctionPositiveValues() {
    assertGammaFunction(
        new BigDecimal("5"), new BigDecimal("24.000000000000000"), "positive values");
  }

  @Test
  public void testGammaFunctionHalfValues() {
    BigDecimal expected =
        BigDecimal.valueOf(Math.sqrt(Math.PI)).setScale(SCALE, RoundingMode.HALF_UP);
    assertGammaFunction(new BigDecimal("0.5"), expected, "half values");
  }

  @Test
  public void testGammaFunctionNegativeValues() {
    assertGammaFunction(
        new BigDecimal("-0.5"), new BigDecimal("-3.544907701811032"), "negative values");
  }

  @Test
  public void testGammaFunctionNearZero() {
    assertGammaFunction(
        new BigDecimal("0.01"), new BigDecimal("99.432585119150600"), "values near zero");
  }

  @Test
  public void testGammaFunctionSpecialCase() {
    assertGammaFunction(
        BigDecimal.ONE, BigDecimal.ONE.setScale(SCALE, RoundingMode.HALF_UP), "special case");
  }

  @Test
  public void testGamma_NullInput() {
    assertThrows(
        IllegalArgumentException.class,
        () -> MathUtils.gamma(null),
        "Input for gamma function must not be null");
  }

  private void assertGammaFunction(BigDecimal input, BigDecimal expected, String message) {
    BigDecimal result = MathUtils.gamma(input).setScale(SCALE, RoundingMode.HALF_UP);
    assertEquals(expected, result, "Gamma function failed for " + message);
  }

  @Test
  public void testMathUtilsConstructorThrowsException() {
    Constructor<MathUtils> constructor = null;
    try {
      constructor = MathUtils.class.getDeclaredConstructor();
      constructor.setAccessible(true);
      Executable executable = constructor::newInstance;
      InvocationTargetException thrown =
          assertThrows(
              InvocationTargetException.class,
              executable,
              "Constructor did not throw an exception");
      assertInstanceOf(
          UnsupportedOperationException.class,
          thrown.getCause(),
          "Unexpected exception type thrown");
      assertEquals(
          "Utility class for math operations",
          thrown.getCause().getMessage(),
          "Unexpected exception message");
    } catch (Exception e) {
      fail("Unexpected exception: " + e);
    } finally {
      if (constructor != null) {
        constructor.setAccessible(false);
      }
    }
  }

  @Test
  public void testGammaFunctionWithInvalidInput() {
    BigDecimal invalidInput = mock(BigDecimal.class);
    when(invalidInput.doubleValue()).thenThrow(new NumberFormatException("Invalid input"));

    IllegalArgumentException thrownException =
        assertThrows(IllegalArgumentException.class, () -> MathUtils.gamma(invalidInput));
    assertTrue(
        thrownException.getMessage().contains("Invalid input for gamma function"),
        "Exception message mismatch");
  }
}
