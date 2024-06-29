package com.trbaxter.github.fractionalcomputationapi.testdata.derivative;

import com.trbaxter.github.fractionalcomputationapi.testdata.config.TestDataConfig;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

/**
 * CaputoDerivativeTestData provides a collection of test data for testing the Caputo derivative
 * computations. It includes various combinations of polynomial coefficients and fractional orders.
 */
public final class CaputoDerivativeTestData {

  private static final Map<String, Map<Double, String>> expectedValues =
      TestDataConfig.configureExpectedValues();

  private CaputoDerivativeTestData() {
    throw new UnsupportedOperationException("Utility class for test data");
  }

  /**
   * Provides a stream of arguments representing different combinations of polynomial coefficients
   * and fractional orders for testing Caputo derivative computations.
   *
   * @return a stream of arguments for parameterized tests.
   */
  public static Stream<Arguments> polynomialExpressions() {
    return expectedValues.entrySet().stream()
        .flatMap(
            entry ->
                entry.getValue().entrySet().stream()
                    .map(
                        alphaEntry ->
                            Arguments.of(
                                entry.getKey(), alphaEntry.getKey(), 3, alphaEntry.getValue())));
  }
}
