package com.trbaxter.github.fractionalcomputationapi.testdata.derivative;

import com.trbaxter.github.fractionalcomputationapi.testdata.config.TestDataConfig;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

/**
 * SharedDerivativeTestData provides a collection of test data for testing both Caputo and
 * Riemann-Liouville derivative computations that result in the same output. It includes various
 * combinations of polynomial coefficients and fractional orders.
 */
public final class SharedDerivativeTestData {

  private static final Map<String, Map<Double, String>> expectedValues =
      TestDataConfig.configureExpectedValues();

  private SharedDerivativeTestData() {
    throw new UnsupportedOperationException("Utility class for test data");
  }

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
