package com.trbaxter.github.fractionalcomputationapi.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.trbaxter.github.fractionalcomputationapi.exception.UndefinedGammaFunctionException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * MathUtilsTest is a test class for the MathUtils utility class. It tests various cases for the
 * gamma function and ensures proper behavior of the MathUtils class.
 */
@ExtendWith(MockitoExtension.class)
class MathUtilsTest {

  private static final int SCALE = 15;

  @Test
  void testGammaFunctionPositiveValues() {
    assertGammaFunction(
        new BigDecimal("5"), new BigDecimal("24.000000000000000"), "positive values");
  }

  @Test
  void testGammaFunctionHalfValues() {
    BigDecimal expected =
        BigDecimal.valueOf(Math.sqrt(Math.PI)).setScale(SCALE, RoundingMode.HALF_UP);
    assertGammaFunction(new BigDecimal("0.5"), expected, "half values");
  }

  @Test
  void testGammaFunctionNegativeValues() {
    assertGammaFunction(
        new BigDecimal("-0.5"), new BigDecimal("-3.544907701811032"), "negative values");
  }

  @Test
  void testGammaFunctionNearZero() {
    assertGammaFunction(
        new BigDecimal("0.01"), new BigDecimal("99.432585119150600"), "values near zero");
  }

  @Test
  void testGammaFunctionSpecialCase() {
    assertGammaFunction(
        BigDecimal.ONE, BigDecimal.ONE.setScale(SCALE, RoundingMode.HALF_UP), "special case");
  }

  @Test
  void testGamma_NullInput() {
    assertThrowsWithMessage(
        IllegalArgumentException.class,
        () -> MathUtils.gamma(null),
        "Input for gamma function must not be null");
  }

  @Test
  void testGammaFunctionZeroValue() {
    BigDecimal zeroValue = BigDecimal.ZERO;
    UndefinedGammaFunctionException thrown =
        assertThrows(
            UndefinedGammaFunctionException.class,
            () -> MathUtils.gamma(zeroValue),
            "Gamma function is undefined for input: " + zeroValue);
    assertTrue(thrown.getMessage().contains("Gamma function is undefined for input: " + zeroValue));
  }

  @Test
  void testGammaFunctionNegativeInteger() {
    BigDecimal negativeInteger = BigDecimal.valueOf(-1);
    UndefinedGammaFunctionException thrown =
        assertThrows(
            UndefinedGammaFunctionException.class,
            () -> MathUtils.gamma(negativeInteger),
            "Gamma function is undefined for input: " + negativeInteger);
    assertTrue(
        thrown.getMessage().contains("Gamma function is undefined for input: " + negativeInteger));
  }

  @Test
  void testGammaFunctionInvalidInput() {
    BigDecimal invalidInput = mock(BigDecimal.class);
    when(invalidInput.doubleValue()).thenThrow(new NumberFormatException("Invalid input"));

    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> MathUtils.gamma(invalidInput),
            "Expected gamma() to throw IllegalArgumentException, but it didn't");

    assertTrue(thrown.getMessage().contains("Invalid input"));
  }

  @Test
  void testComputeGammaRatioZeroDenominator() {
    BigDecimal numerator = BigDecimal.ONE;
    BigDecimal denominator = BigDecimal.ZERO;

    UndefinedGammaFunctionException thrown =
        assertThrows(
            UndefinedGammaFunctionException.class,
            () -> MathUtils.computeGammaRatio(numerator, denominator),
            "Gamma function is undefined for input: " + denominator);
    assertTrue(
        thrown.getMessage().contains("Gamma function is undefined for input: " + denominator));
  }

  @Test
  void testComputeGammaRatioInvalidInput() {
    BigDecimal invalidNumerator = mock(BigDecimal.class);
    BigDecimal denominator = BigDecimal.ONE;

    when(invalidNumerator.doubleValue()).thenThrow(new NumberFormatException("Invalid input"));

    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> MathUtils.computeGammaRatio(invalidNumerator, denominator),
            "Error computing gamma ratio");

    assertTrue(thrown.getMessage().contains("Invalid input"));
  }

  @Test
  void testComputeGammaRatioValidInputs() {
    BigDecimal numerator = new BigDecimal("5");
    BigDecimal denominator = new BigDecimal("3");

    BigDecimal expectedRatio =
        MathUtils.gamma(numerator)
            .divide(MathUtils.gamma(denominator), MathContext.DECIMAL128)
            .setScale(SCALE, RoundingMode.HALF_UP);

    BigDecimal result = MathUtils.computeGammaRatio(numerator, denominator);
    assertEquals(
        expectedRatio,
        result.setScale(SCALE, RoundingMode.HALF_UP),
        "Gamma ratio calculation failed for valid inputs");
  }

  @Test
  void testMathUtilsConstructorThrowsException() {
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
          "Utility class for math calculations",
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
  void testGammaFunctionWithInvalidInput() {
    BigDecimal invalidInput = mock(BigDecimal.class);
    when(invalidInput.doubleValue()).thenThrow(new NumberFormatException("Invalid input"));

    IllegalArgumentException thrownException =
        assertThrows(IllegalArgumentException.class, () -> MathUtils.gamma(invalidInput));
    assertTrue(
        thrownException.getMessage().contains("Invalid input"),
        "Exception message mismatch. Actual message: " + thrownException.getMessage());
  }

  private void assertGammaFunction(BigDecimal input, BigDecimal expected, String message) {
    BigDecimal result = MathUtils.gamma(input).setScale(SCALE, RoundingMode.HALF_UP);
    assertEquals(expected, result, "Gamma function failed for " + message);
  }

  private <T extends Throwable> void assertThrowsWithMessage(
      Class<T> exceptionClass, Executable executable, String expectedMessage) {
    T thrown = assertThrows(exceptionClass, executable);
    assertTrue(thrown.getMessage().contains(expectedMessage));
  }
}
