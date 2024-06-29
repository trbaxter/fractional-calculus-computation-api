package com.trbaxter.github.fractionalcomputationapi.testdata.config;

import java.util.HashMap;
import java.util.Map;

public final class TestDataConfig {

  private TestDataConfig() {
    throw new UnsupportedOperationException("Utility class for test data");
  }

  public static Map<String, Map<Double, String>> configureExpectedValues() {
    Map<String, Map<Double, String>> expectedValues = new HashMap<>();
    double[] alphas = {
      0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.5, 2.0, 3.0, 5.0, 10.0, 100.0
    };

    addExpectedValuesForZero(expectedValues, alphas);
    addExpectedValuesForOne(expectedValues);
    addExpectedValuesForNegativeOne(expectedValues);
    addExpectedValuesForZeroPolynomial(expectedValues, alphas);
    addExpectedValuesForLinearPolynomial(expectedValues);
    addExpectedValuesForQuadraticPolynomial(expectedValues);

    return expectedValues;
  }

  private static void addExpectedValuesForZero(
      Map<String, Map<Double, String>> expectedValues, double[] alphas) {
    addExpectedValue(expectedValues, "0", alphas, "0");
  }

  private static void addExpectedValuesForOne(Map<String, Map<Double, String>> expectedValues) {
    addExpectedValue(expectedValues, "1", new double[] {0.0}, "1");
    addExpectedValue(expectedValues, "1", new double[] {1.0, 2.0, 3.0, 5.0, 10.0, 100.0}, "0");
  }

  private static void addExpectedValuesForNegativeOne(
      Map<String, Map<Double, String>> expectedValues) {
    addExpectedValue(expectedValues, "-1", new double[] {0.0}, "-1");
    addExpectedValue(expectedValues, "-1", new double[] {1.0, 2.0, 3.0, 5.0, 10.0, 100.0}, "0");
  }

  private static void addExpectedValuesForZeroPolynomial(
      Map<String, Map<Double, String>> expectedValues, double[] alphas) {
    addExpectedValue(expectedValues, "0x + 0", alphas, "0");
  }

  private static void addExpectedValuesForLinearPolynomial(
      Map<String, Map<Double, String>> expectedValues) {
    addExpectedValue(expectedValues, "x + 1", new double[] {0.0}, "x + 1");
    addExpectedValue(expectedValues, "x + 1", new double[] {1.0}, "1");
    addExpectedValue(expectedValues, "x + 1", new double[] {2.0, 3.0, 5.0, 10.0, 100.0}, "0");

    addExpectedValue(expectedValues, "-x - 1", new double[] {0.0}, "-x - 1");
    addExpectedValue(expectedValues, "-x - 1", new double[] {1.0}, "-1");
    addExpectedValue(expectedValues, "-x - 1", new double[] {2.0, 3.0, 5.0, 10.0, 100.0}, "0");

    addExpectedValue(expectedValues, "-x + 1", new double[] {0.0}, "-x + 1");
    addExpectedValue(expectedValues, "-x + 1", new double[] {1.0}, "-1");
    addExpectedValue(expectedValues, "-x + 1", new double[] {2.0, 3.0, 5.0, 10.0, 100.0}, "0");

    addExpectedValue(expectedValues, "x - 1", new double[] {0.0}, "x - 1");
    addExpectedValue(expectedValues, "x - 1", new double[] {1.0}, "1");
    addExpectedValue(expectedValues, "x - 1", new double[] {2.0, 3.0, 5.0, 10.0, 100.0}, "0");

    addExpectedValue(expectedValues, "x + 0", new double[] {0.0}, "x");
    addExpectedValue(expectedValues, "x + 0", new double[] {1.0}, "1");
    addExpectedValue(expectedValues, "x + 0", new double[] {2.0, 3.0, 5.0, 10.0, 100.0}, "0");

    addExpectedValue(expectedValues, "0x + 1", new double[] {0.0}, "1");
    addExpectedValue(expectedValues, "0x + 1", new double[] {1.0, 2.0, 3.0, 5.0, 10.0, 100.0}, "0");
  }

  private static void addExpectedValuesForQuadraticPolynomial(
      Map<String, Map<Double, String>> expectedValues) {
    double[] higherOrderAlphas = {3.0, 5.0, 10.0, 100.0};

    addExpectedValue(
        expectedValues,
        "0x^2 + 0x + 0",
        new double[] {
          0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.5, 2.0, 3.0, 5.0, 10.0, 100.0
        },
        "0");

    addExpectedValue(expectedValues, "x^2 + x + 1", new double[] {0.0}, "x^2 + x + 1");
    addExpectedValue(expectedValues, "x^2 + x + 1", new double[] {1.0}, "2x + 1");
    addExpectedValue(expectedValues, "x^2 + x + 1", new double[] {2.0}, "2");
    addExpectedValue(expectedValues, "x^2 + x + 1", higherOrderAlphas, "0");

    addExpectedValue(expectedValues, "-x^2 - x - 1", new double[] {0.0}, "-x^2 - x - 1");
    addExpectedValue(expectedValues, "-x^2 - x - 1", new double[] {1.0}, "-2x - 1");
    addExpectedValue(expectedValues, "-x^2 - x - 1", new double[] {2.0}, "-2");
    addExpectedValue(expectedValues, "-x^2 - x - 1", higherOrderAlphas, "0");

    addExpectedValue(expectedValues, "-x^2 + x + 1", new double[] {0.0}, "-x^2 + x + 1");
    addExpectedValue(expectedValues, "-x^2 + x + 1", new double[] {1.0}, "-2x + 1");
    addExpectedValue(expectedValues, "-x^2 + x + 1", new double[] {2.0}, "-2");
    addExpectedValue(expectedValues, "-x^2 + x + 1", higherOrderAlphas, "0");

    addExpectedValue(expectedValues, "x^2 - x + 1", new double[] {0.0}, "x^2 - x + 1");
    addExpectedValue(expectedValues, "x^2 - x + 1", new double[] {1.0}, "2x - 1");
    addExpectedValue(expectedValues, "x^2 - x + 1", new double[] {2.0}, "2");
    addExpectedValue(expectedValues, "x^2 - x + 1", higherOrderAlphas, "0");

    addExpectedValue(expectedValues, "x^2 + x", new double[] {0.0}, "x^2 + x");
    addExpectedValue(expectedValues, "x^2 + x", new double[] {1.0}, "2x + 1");
    addExpectedValue(expectedValues, "x^2 + x", new double[] {2.0}, "2");
    addExpectedValue(expectedValues, "x^2 + x", higherOrderAlphas, "0");

    addExpectedValue(expectedValues, "x^2 + 0x + 1", new double[] {0.0}, "x^2 + 1");
    addExpectedValue(expectedValues, "x^2 + 0x + 1", new double[] {1.0}, "2x");
    addExpectedValue(expectedValues, "x^2 + 0x + 1", new double[] {2.0}, "2");
    addExpectedValue(expectedValues, "x^2 + 0x + 1", higherOrderAlphas, "0");

    addExpectedValue(expectedValues, "0x^2 + x + 1", new double[] {0.0}, "x + 1");
    addExpectedValue(expectedValues, "0x^2 + x + 1", new double[] {1.0}, "1");
    addExpectedValue(
        expectedValues, "0x^2 + x + 1", new double[] {2.0, 3.0, 5.0, 10.0, 100.0}, "0");
  }

  private static void addExpectedValue(
      Map<String, Map<Double, String>> expectedValues,
      String expression,
      double[] alphas,
      String result) {
    for (double alpha : alphas) {
      expectedValues.computeIfAbsent(expression, k -> new HashMap<>()).put(alpha, result);
    }
  }
}
