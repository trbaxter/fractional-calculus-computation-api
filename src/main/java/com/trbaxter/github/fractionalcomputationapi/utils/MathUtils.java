package com.trbaxter.github.fractionalcomputationapi.utils;

import com.trbaxter.github.fractionalcomputationapi.exception.UndefinedGammaFunctionException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.apache.commons.math3.special.Gamma;

/** Utility class for common gamma function calculations. */
public final class MathUtils {

  private MathUtils() {
    throw new UnsupportedOperationException("Utility class for math calculations");
  }

  /**
   * Computes the gamma function for the given input.
   *
   * @param z the input value for which to compute the gamma function, must not be null.
   * @return the computed gamma value as a BigDecimal, scaled to 15 decimal places.
   * @throws IllegalArgumentException if the input is invalid.
   * @throws UndefinedGammaFunctionException if the input is 0 or a negative integer.
   */
  public static BigDecimal gamma(BigDecimal z) {
    if (z == null) {
      throw new IllegalArgumentException("Input for gamma function must not be null");
    }

    double value = z.doubleValue();

    if (value == 0 || (value < 0 && value == Math.floor(value))) {
      throw new UndefinedGammaFunctionException("Gamma function is undefined for input: " + z);
    }

    double gammaValue = Gamma.gamma(value);
    return BigDecimal.valueOf(gammaValue).setScale(15, RoundingMode.HALF_UP);
  }

  /**
   * Computes the ratio of gamma functions.
   *
   * @param numerator the input value for the numerator gamma function.
   * @param denominator the input value for the denominator gamma function.
   * @return the computed ratio as a BigDecimal.
   * @throws UndefinedGammaFunctionException if the gamma function is undefined for the input.
   */
  public static BigDecimal computeGammaRatio(BigDecimal numerator, BigDecimal denominator) {
    BigDecimal gammaNumerator = gamma(numerator);
    BigDecimal gammaDenominator = gamma(denominator);

    if (gammaDenominator.compareTo(BigDecimal.ZERO) == 0) {
      throw new UndefinedGammaFunctionException(
          "Gamma function is undefined for input: " + denominator);
    }

    return gammaNumerator.divide(gammaDenominator, MathContext.DECIMAL128);
  }
}
