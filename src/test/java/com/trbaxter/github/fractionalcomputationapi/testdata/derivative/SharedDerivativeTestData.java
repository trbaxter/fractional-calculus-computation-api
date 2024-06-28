package com.trbaxter.github.fractionalcomputationapi.testdata.derivative;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

/**
 * SharedDerivativeTestData provides a collection of test data for testing both Caputo and
 * Riemann-Liouville derivative computations that result in the same output. It includes various
 * combinations of polynomial coefficients and fractional orders.
 */
public final class SharedDerivativeTestData {

  /** Private constructor to prevent instantiation. */
  private SharedDerivativeTestData() {
    throw new UnsupportedOperationException("Utility class for test data");
  }

  /**
   * Provides a stream of arguments representing different combinations of polynomial coefficients
   * and fractional orders for testing Caputo and Riemann-Liouville derivative computations.
   *
   * @return a stream of arguments for parameterized tests.
   */
  public static Stream<Arguments> polynomialExpressions() {
    return Stream.of(
        Arguments.of("0", 0.0, 3, "0"),
        Arguments.of("0", 0.1, 3, "0"),
        Arguments.of("0", 0.2, 3, "0"),
        Arguments.of("0", 0.3, 3, "0"),
        Arguments.of("0", 0.4, 3, "0"),
        Arguments.of("0", 0.5, 3, "0"),
        Arguments.of("0", 0.6, 3, "0"),
        Arguments.of("0", 0.7, 3, "0"),
        Arguments.of("0", 0.8, 3, "0"),
        Arguments.of("0", 0.9, 3, "0"),
        Arguments.of("0", 1.0, 3, "0"),
        Arguments.of("0", 1.5, 3, "0"),
        Arguments.of("0", 2.0, 3, "0"),
        Arguments.of("0", 3.0, 3, "0"),
        Arguments.of("0", 5.0, 3, "0"),
        Arguments.of("0", 10.0, 3, "0"),
        Arguments.of("0", 100.0, 3, "0"),
        Arguments.of("1", 0.0, 3, "1"),
        Arguments.of("1", 1.0, 3, "0"),
        Arguments.of("1", 2.0, 3, "0"),
        Arguments.of("1", 3.0, 3, "0"),
        Arguments.of("1", 5.0, 3, "0"),
        Arguments.of("1", 10.0, 3, "0"),
        Arguments.of("1", 100.0, 3, "0"),
        Arguments.of("-1", 0.0, 3, "-1"),
        Arguments.of("-1", 1.0, 3, "0"),
        Arguments.of("-1", 2.0, 3, "0"),
        Arguments.of("-1", 3.0, 3, "0"),
        Arguments.of("-1", 5.0, 3, "0"),
        Arguments.of("-1", 10.0, 3, "0"),
        Arguments.of("-1", 100.0, 3, "0"),
        Arguments.of("0x + 0", 0.0, 3, "0"),
        Arguments.of("0x + 0", 0.1, 3, "0"),
        Arguments.of("0x + 0", 0.2, 3, "0"),
        Arguments.of("0x + 0", 0.3, 3, "0"),
        Arguments.of("0x + 0", 0.4, 3, "0"),
        Arguments.of("0x + 0", 0.5, 3, "0"),
        Arguments.of("0x + 0", 0.6, 3, "0"),
        Arguments.of("0x + 0", 0.7, 3, "0"),
        Arguments.of("0x + 0", 0.8, 3, "0"),
        Arguments.of("0x + 0", 0.9, 3, "0"),
        Arguments.of("0x + 0", 1.0, 3, "0"),
        Arguments.of("0x + 0", 1.5, 3, "0"),
        Arguments.of("0x + 0", 2.0, 3, "0"),
        Arguments.of("0x + 0", 3.0, 3, "0"),
        Arguments.of("0x + 0", 5.0, 3, "0"),
        Arguments.of("0x + 0", 10.0, 3, "0"),
        Arguments.of("0x + 0", 100.0, 3, "0"),
        Arguments.of("x + 1", 0.0, 3, "x + 1"),
        Arguments.of("x + 1", 1.0, 3, "1"),
        Arguments.of("x + 1", 2.0, 3, "0"),
        Arguments.of("x + 1", 3.0, 3, "0"),
        Arguments.of("x + 1", 5.0, 3, "0"),
        Arguments.of("x + 1", 10.0, 3, "0"),
        Arguments.of("x + 1", 100.0, 3, "0"),
        Arguments.of("-x - 1", 0.0, 3, "-x - 1"),
        Arguments.of("-x - 1", 1.0, 3, "-1"),
        Arguments.of("-x - 1", 2.0, 3, "0"),
        Arguments.of("-x - 1", 3.0, 3, "0"),
        Arguments.of("-x - 1", 5.0, 3, "0"),
        Arguments.of("-x - 1", 10.0, 3, "0"),
        Arguments.of("-x - 1", 100.0, 3, "0"),
        Arguments.of("-x + 1", 0.0, 3, "-x + 1"),
        Arguments.of("-x + 1", 1.0, 3, "-1"),
        Arguments.of("-x + 1", 2.0, 3, "0"),
        Arguments.of("-x + 1", 3.0, 3, "0"),
        Arguments.of("-x + 1", 5.0, 3, "0"),
        Arguments.of("-x + 1", 10.0, 3, "0"),
        Arguments.of("-x + 1", 100.0, 3, "0"),
        Arguments.of("x - 1", 0.0, 3, "x - 1"),
        Arguments.of("x - 1", 1.0, 3, "1"),
        Arguments.of("x - 1", 2.0, 3, "0"),
        Arguments.of("x - 1", 3.0, 3, "0"),
        Arguments.of("x - 1", 5.0, 3, "0"),
        Arguments.of("x - 1", 10.0, 3, "0"),
        Arguments.of("x - 1", 100.0, 3, "0"),
        Arguments.of("x + 0", 0.0, 3, "x"),
        Arguments.of("x + 0", 1.0, 3, "1"),
        Arguments.of("x + 0", 2.0, 3, "0"),
        Arguments.of("x + 0", 3.0, 3, "0"),
        Arguments.of("x + 0", 5.0, 3, "0"),
        Arguments.of("x + 0", 10.0, 3, "0"),
        Arguments.of("x + 0", 100.0, 3, "0"),
        Arguments.of("0x + 1", 0.0, 3, "1"),
        Arguments.of("0x + 1", 1.0, 3, "0"),
        Arguments.of("0x + 1", 2.0, 3, "0"),
        Arguments.of("0x + 1", 3.0, 3, "0"),
        Arguments.of("0x + 1", 5.0, 3, "0"),
        Arguments.of("0x + 1", 10.0, 3, "0"),
        Arguments.of("0x + 1", 100.0, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 0.0, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 0.1, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 0.2, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 0.3, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 0.4, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 0.5, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 0.6, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 0.7, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 0.8, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 0.9, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 1.0, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 1.5, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 2.0, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 3.0, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 5.0, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 10.0, 3, "0"),
        Arguments.of("0x^2 + 0x + 0", 100.0, 3, "0"),
        Arguments.of("x^2 + x + 1", 0.0, 3, "x^2 + x + 1"),
        Arguments.of("x^2 + x + 1", 1.0, 3, "2x + 1"),
        Arguments.of("x^2 + x + 1", 2.0, 3, "2"),
        Arguments.of("x^2 + x + 1", 3.0, 3, "0"),
        Arguments.of("x^2 + x + 1", 5.0, 3, "0"),
        Arguments.of("x^2 + x + 1", 10.0, 3, "0"),
        Arguments.of("x^2 + x + 1", 100.0, 3, "0"),
        Arguments.of("-x^2 - x - 1", 0.0, 3, "-x^2 - x - 1"),
        Arguments.of("-x^2 - x - 1", 1.0, 3, "-2x - 1"),
        Arguments.of("-x^2 - x - 1", 2.0, 3, "-2"),
        Arguments.of("-x^2 - x - 1", 3.0, 3, "0"),
        Arguments.of("-x^2 - x - 1", 5.0, 3, "0"),
        Arguments.of("-x^2 - x - 1", 10.0, 3, "0"),
        Arguments.of("-x^2 - x - 1", 100.0, 3, "0"),
        Arguments.of("-x^2 + x + 1", 0.0, 3, "-x^2 + x + 1"),
        Arguments.of("-x^2 + x + 1", 1.0, 3, "-2x + 1"),
        Arguments.of("-x^2 + x + 1", 2.0, 3, "-2"),
        Arguments.of("-x^2 + x + 1", 3.0, 3, "0"),
        Arguments.of("-x^2 + x + 1", 5.0, 3, "0"),
        Arguments.of("-x^2 + x + 1", 10.0, 3, "0"),
        Arguments.of("-x^2 + x + 1", 100.0, 3, "0"),
        Arguments.of("x^2 - x + 1", 0.0, 3, "x^2 - x + 1"),
        Arguments.of("x^2 - x + 1", 1.0, 3, "2x - 1"),
        Arguments.of("x^2 - x + 1", 2.0, 3, "2"),
        Arguments.of("x^2 - x + 1", 3.0, 3, "0"),
        Arguments.of("x^2 - x + 1", 5.0, 3, "0"),
        Arguments.of("x^2 - x + 1", 10.0, 3, "0"),
        Arguments.of("x^2 - x + 1", 100.0, 3, "0"),
        Arguments.of("x^2 + x - 1", 0.0, 3, "x^2 + x - 1"),
        Arguments.of("x^2 + x - 1", 1.0, 3, "2x + 1"),
        Arguments.of("x^2 + x - 1", 2.0, 3, "2"),
        Arguments.of("x^2 + x - 1", 3.0, 3, "0"),
        Arguments.of("x^2 + x - 1", 5.0, 3, "0"),
        Arguments.of("x^2 + x - 1", 10.0, 3, "0"),
        Arguments.of("x^2 + x - 1", 100.0, 3, "0"),
        Arguments.of("x^2 + x + 0", 0.0, 3, "x^2 + x"),
        Arguments.of("x^2 + x + 0", 1.0, 3, "2x + 1"),
        Arguments.of("x^2 + x + 0", 2.0, 3, "2"),
        Arguments.of("x^2 + x + 0", 3.0, 3, "0"),
        Arguments.of("x^2 + x + 0", 5.0, 3, "0"),
        Arguments.of("x^2 + x + 0", 10.0, 3, "0"),
        Arguments.of("x^2 + x + 0", 100.0, 3, "0"),
        Arguments.of("x^2 + 0x + 1", 0.0, 3, "x^2 + 1"),
        Arguments.of("x^2 + 0x + 1", 1.0, 3, "2x"),
        Arguments.of("x^2 + 0x + 1", 2.0, 3, "2"),
        Arguments.of("x^2 + 0x + 1", 3.0, 3, "0"),
        Arguments.of("x^2 + 0x + 1", 5.0, 3, "0"),
        Arguments.of("x^2 + 0x + 1", 10.0, 3, "0"),
        Arguments.of("x^2 + 0x + 1", 100.0, 3, "0"),
        Arguments.of("0x^2 + x + 1", 0.0, 3, "x + 1"),
        Arguments.of("0x^2 + x + 1", 1.0, 3, "1"),
        Arguments.of("0x^2 + x + 1", 2.0, 3, "0"),
        Arguments.of("0x^2 + x + 1", 3.0, 3, "0"),
        Arguments.of("0x^2 + x + 1", 5.0, 3, "0"),
        Arguments.of("0x^2 + x + 1", 10.0, 3, "0"),
        Arguments.of("0x^2 + x + 1", 100.0, 3, "0"));
  }
}
