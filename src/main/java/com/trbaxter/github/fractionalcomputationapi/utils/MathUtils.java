package com.trbaxter.github.fractionalcomputationapi.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.commons.math3.special.Gamma;

/**
 * MathUtils is a utility class that provides mathematical operations, including the gamma function.
 * It is not intended to be instantiated.
 */
public final class MathUtils {

  /** Private constructor to prevent instantiation. */
  private MathUtils() {
    throw new UnsupportedOperationException("Utility class for math operations");
  }

  /**
   * Computes the gamma function for the given input.
   *
   * @param z the input value for which to compute the gamma function, must not be null.
   * @return the computed gamma value as a BigDecimal, scaled to 15 decimal places.
   * @throws IllegalArgumentException if the input is invalid.
   */
  public static BigDecimal gamma(BigDecimal z) {
    if (z == null) {
      throw new IllegalArgumentException("Input for gamma function must not be null");
    }

    try {
      double value = z.doubleValue();
      double gammaValue = Gamma.gamma(value);
      return BigDecimal.valueOf(gammaValue).setScale(15, RoundingMode.HALF_UP);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid input for gamma function: " + z, e);
    }
  }
}
