package com.trbaxter.github.fractionalcomputationapi.testdata.integral;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CaputoIntegrationTestData provides a collection of test data for testing the Caputo integral
 * computations.<br>
 * It includes various combinations of polynomial coefficients and fractional orders.
 */
public final class IntegrationTestData {

  private static final Logger logger = LoggerFactory.getLogger(IntegrationTestData.class);
  private static final Map<String, Map<Double, String>> expectedValues = new HashMap<>();

  static {
    try {
      addGeneralCases();
      addSpecificExpectedValues();
    } catch (Exception e) {
      logger.error("Error initializing CaputoIntegrationTestData", e);
      throw e; // rethrow the exception to prevent test execution with invalid data
    }
  }

  private static void addGeneralCases() {
    addExpectedValues(
        "1",
        new double[] {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.5, 2.0, 3.0, 5.0},
        new String[] {
          "1",
          "1.051x^0.1 + C",
          "1.089x^0.2 + C",
          "1.114x^0.3 + C",
          "1.127x^0.4 + C",
          "1.128x^0.5 + C",
          "1.119x^0.6 + C",
          "1.101x^0.7 + C",
          "1.074x^0.8 + C",
          "1.040x^0.9 + C",
          "x + C",
          "0.752x^1.5 + C",
          "0.500x^2 + Cx + D",
          "0.167x^3 + 0.500Cx^2 + Dx + E",
          "0.008x^5 + 0.042Cx^4 + 0.167Dx^3 + 0.500Ex^2 + Fx + G"
        });
    addExpectedValues(
        "-1",
        new double[] {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.5, 2.0, 3.0, 5.0},
        new String[] {
          "-1",
          "-1.051x^0.1 + C",
          "-1.089x^0.2 + C",
          "-1.114x^0.3 + C",
          "-1.127x^0.4 + C",
          "-1.128x^0.5 + C",
          "-1.119x^0.6 + C",
          "-1.101x^0.7 + C",
          "-1.074x^0.8 + C",
          "-1.040x^0.9 + C",
          "-x + C",
          "-0.752x^1.5 + C",
          "-0.500x^2 + Cx + D",
          "-0.167x^3 + 0.500Cx^2 + Dx + E",
          "-0.008x^5 + 0.042Cx^4 + 0.167Dx^3 + 0.500Ex^2 + Fx + G"
        });
    addExpectedValues(
        "x + 1",
        new double[] {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.5},
        new String[] {
          "x + 1",
          "0.956x^1.1 + 1.051x^0.1 + C",
          "0.908x^1.2 + 1.089x^0.2 + C",
          "0.857x^1.3 + 1.114x^0.3 + C",
          "0.805x^1.4 + 1.127x^0.4 + C",
          "0.752x^1.5 + 1.128x^0.5 + C",
          "0.699x^1.6 + 1.119x^0.6 + C",
          "0.647x^1.7 + 1.101x^0.7 + C",
          "0.596x^1.8 + 1.074x^0.8 + C",
          "0.547x^1.9 + 1.040x^0.9 + C",
          "0.500x^2 + x + C",
          "0.301x^2.5 + 0.752x^1.5 + C"
        });
    addExpectedValues(
        "-x - 1",
        new double[] {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.5},
        new String[] {
          "-x - 1",
          "-0.956x^1.1 - 1.051x^0.1 + C",
          "-0.908x^1.2 - 1.089x^0.2 + C",
          "-0.857x^1.3 - 1.114x^0.3 + C",
          "-0.805x^1.4 - 1.127x^0.4 + C",
          "-0.752x^1.5 - 1.128x^0.5 + C",
          "-0.699x^1.6 - 1.119x^0.6 + C",
          "-0.647x^1.7 - 1.101x^0.7 + C",
          "-0.596x^1.8 - 1.074x^0.8 + C",
          "-0.547x^1.9 - 1.040x^0.9 + C",
          "-0.500x^2 - x + C",
          "-0.301x^2.5 - 0.752x^1.5 + C"
        });
  }

  private static void addSpecificExpectedValues() {
    addExpectedValues(
        "x^2 + x + 1",
        new double[] {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.5},
        new String[] {
          "x^2 + x + 1",
          "0.910x^2.1 + 0.956x^1.1 + 1.051x^0.1 + C",
          "0.825x^2.2 + 0.908x^1.2 + 1.089x^0.2 + C",
          "0.745x^2.3 + 0.857x^1.3 + 1.114x^0.3 + C",
          "0.671x^2.4 + 0.805x^1.4 + 1.127x^0.4 + C",
          "0.602x^2.5 + 0.752x^1.5 + 1.128x^0.5 + C",
          "0.538x^2.6 + 0.699x^1.6 + 1.119x^0.6 + C",
          "0.480x^2.7 + 0.647x^1.7 + 1.101x^0.7 + C",
          "0.426x^2.8 + 0.596x^1.8 + 1.074x^0.8 + C",
          "0.377x^2.9 + 0.547x^1.9 + 1.040x^0.9 + C",
          "0.333x^3 + 0.500x^2 + x + C",
          "0.172x^3.5 + 0.301x^2.5 + 0.752x^1.5 + C"
        });
  }

  private static void addExpectedValues(String polynomial, double[] alphas, String[] results) {
    if (alphas.length != results.length) {
      logger.error("Mismatch in lengths: alphas = {}, results = {}", alphas.length, results.length);
      throw new IllegalArgumentException("Lengths of alphas and results arrays must match.");
    }
    for (int i = 0; i < alphas.length; i++) {
      expectedValues.computeIfAbsent(polynomial, k -> new HashMap<>()).put(alphas[i], results[i]);
    }
  }

  /**
   * Provides a stream of arguments representing different combinations of polynomial coefficients
   * and fractional orders for testing Caputo integral computations.
   *
   * @return a stream of arguments for parameterized tests.
   */
  public static Stream<Arguments> polynomialExpressions() {
    return expectedValues.entrySet().stream()
        .flatMap(
            entry ->
                entry.getValue().entrySet().stream()
                    .map(e -> Arguments.of(entry.getKey(), e.getKey(), 3, e.getValue())));
  }
}
