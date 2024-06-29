package com.trbaxter.github.fractionalcomputationapi.testdata.derivative;

import com.trbaxter.github.fractionalcomputationapi.testdata.config.TestDataConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

/**
 * RiemannLiouvilleDerivativeTestData provides a collection of test data for testing the
 * Riemann-Liouville derivative computations. It includes various combinations of polynomial
 * coefficients and fractional orders.
 */
public final class RiemannLiouvilleDerivativeTestData {

  private static final Map<String, Map<Double, String>> expectedValues =
      TestDataConfig.configureExpectedValues();

  static {
    double[] alphas = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.5};

    addProblematicCases();
    addGeneralCases(alphas);
    addSpecificExpectedValues();
  }

  /** Private constructor to prevent instantiation. */
  private RiemannLiouvilleDerivativeTestData() {
    throw new UnsupportedOperationException("Utility class for test data");
  }

  private static void addProblematicCases() {
    addExpectedValue("1", 0.1, "0.936x^-0.1");
    addExpectedValue("1", 0.2, "0.859x^-0.2");
    addExpectedValue("1", 0.3, "0.770x^-0.3");
    addExpectedValue("1", 0.4, "0.672x^-0.4");
    addExpectedValue("1", 0.5, "0.564x^-0.5");
    addExpectedValue("1", 0.6, "0.451x^-0.6");
    addExpectedValue("1", 0.7, "0.334x^-0.7");
    addExpectedValue("1", 0.8, "0.218x^-0.8");
    addExpectedValue("1", 0.9, "0.105x^-0.9");
    addExpectedValue("1", 1.5, "-0.282x^-1.5");

    addExpectedValue("-x + 1", 0.1, "-1.040x^0.9 + 0.936x^-0.1");
    addExpectedValue("-x + 1", 0.2, "-1.074x^0.8 + 0.859x^-0.2");
    addExpectedValue("-x + 1", 0.3, "-1.101x^0.7 + 0.770x^-0.3");
    addExpectedValue("-x + 1", 0.4, "-1.119x^0.6 + 0.672x^-0.4");
    addExpectedValue("-x + 1", 0.5, "-1.128x^0.5 + 0.564x^-0.5");
    addExpectedValue("-x + 1", 0.6, "-1.127x^0.4 + 0.451x^-0.6");
    addExpectedValue("-x + 1", 0.7, "-1.114x^0.3 + 0.334x^-0.7");
    addExpectedValue("-x + 1", 0.8, "-1.089x^0.2 + 0.218x^-0.8");
    addExpectedValue("-x + 1", 0.9, "-1.051x^0.1 + 0.105x^-0.9");
    addExpectedValue("-x + 1", 1.5, "-0.564x^-0.5 - 0.282x^-1.5");

    addExpectedValue("-1", 0.1, "-0.936x^-0.1");
    addExpectedValue("-1", 0.2, "-0.859x^-0.2");
    addExpectedValue("-1", 0.3, "-0.770x^-0.3");
    addExpectedValue("-1", 0.4, "-0.672x^-0.4");
    addExpectedValue("-1", 0.5, "-0.564x^-0.5");
    addExpectedValue("-1", 0.6, "-0.451x^-0.6");
    addExpectedValue("-1", 0.7, "-0.334x^-0.7");
    addExpectedValue("-1", 0.8, "-0.218x^-0.8");
    addExpectedValue("-1", 0.9, "-0.105x^-0.9");
    addExpectedValue("-1", 1.5, "0.282x^-1.5");

    addExpectedValue("0x + 1", 0.1, "0.936x^-0.1");
    addExpectedValue("0x + 1", 0.2, "0.859x^-0.2");
    addExpectedValue("0x + 1", 0.3, "0.770x^-0.3");
    addExpectedValue("0x + 1", 0.4, "0.672x^-0.4");
    addExpectedValue("0x + 1", 0.5, "0.564x^-0.5");
    addExpectedValue("0x + 1", 0.6, "0.451x^-0.6");
    addExpectedValue("0x + 1", 0.7, "0.334x^-0.7");
    addExpectedValue("0x + 1", 0.8, "0.218x^-0.8");
    addExpectedValue("0x + 1", 0.9, "0.105x^-0.9");
    addExpectedValue("0x + 1", 1.5, "-0.282x^-1.5");

    addExpectedValue("x + 0", 0.1, "1.040x^0.9");
    addExpectedValue("x + 0", 0.2, "1.074x^0.8");
    addExpectedValue("x + 0", 0.3, "1.101x^0.7");
    addExpectedValue("x + 0", 0.4, "1.119x^0.6");
    addExpectedValue("x + 0", 0.5, "1.128x^0.5");
    addExpectedValue("x + 0", 0.6, "1.127x^0.4");
    addExpectedValue("x + 0", 0.7, "1.114x^0.3");
    addExpectedValue("x + 0", 0.8, "1.089x^0.2");
    addExpectedValue("x + 0", 0.9, "1.051x^0.1");
    addExpectedValue("x + 0", 1.5, "0.564x^-0.5");

    addExpectedValue("x - 1", 0.1, "1.040x^0.9 - 0.936x^-0.1");
    addExpectedValue("x - 1", 0.2, "1.074x^0.8 - 0.859x^-0.2");
    addExpectedValue("x - 1", 0.3, "1.101x^0.7 - 0.770x^-0.3");
    addExpectedValue("x - 1", 0.4, "1.119x^0.6 - 0.672x^-0.4");
    addExpectedValue("x - 1", 0.5, "1.128x^0.5 - 0.564x^-0.5");
    addExpectedValue("x - 1", 0.6, "1.127x^0.4 - 0.451x^-0.6");
    addExpectedValue("x - 1", 0.7, "1.114x^0.3 - 0.334x^-0.7");
    addExpectedValue("x - 1", 0.8, "1.089x^0.2 - 0.218x^-0.8");
    addExpectedValue("x - 1", 0.9, "1.051x^0.1 - 0.105x^-0.9");
    addExpectedValue("x - 1", 1.5, "0.564x^-0.5 + 0.282x^-1.5");

    addExpectedValue("-x - 1", 0.1, "-1.040x^0.9 - 0.936x^-0.1");
    addExpectedValue("-x - 1", 0.2, "-1.074x^0.8 - 0.859x^-0.2");
    addExpectedValue("-x - 1", 0.3, "-1.101x^0.7 - 0.770x^-0.3");
    addExpectedValue("-x - 1", 0.4, "-1.119x^0.6 - 0.672x^-0.4");
    addExpectedValue("-x - 1", 0.5, "-1.128x^0.5 - 0.564x^-0.5");
    addExpectedValue("-x - 1", 0.6, "-1.127x^0.4 - 0.451x^-0.6");
    addExpectedValue("-x - 1", 0.7, "-1.114x^0.3 - 0.334x^-0.7");
    addExpectedValue("-x - 1", 0.8, "-1.089x^0.2 - 0.218x^-0.8");
    addExpectedValue("-x - 1", 0.9, "-1.051x^0.1 - 0.105x^-0.9");
    addExpectedValue("-x - 1", 1.5, "-0.564x^-0.5 + 0.282x^-1.5");

    addExpectedValue("x + 1", 0.1, "1.040x^0.9 + 0.936x^-0.1");
    addExpectedValue("x + 1", 0.2, "1.074x^0.8 + 0.859x^-0.2");
    addExpectedValue("x + 1", 0.3, "1.101x^0.7 + 0.770x^-0.3");
    addExpectedValue("x + 1", 0.4, "1.119x^0.6 + 0.672x^-0.4");
    addExpectedValue("x + 1", 0.5, "1.128x^0.5 + 0.564x^-0.5");
    addExpectedValue("x + 1", 0.6, "1.127x^0.4 + 0.451x^-0.6");
    addExpectedValue("x + 1", 0.7, "1.114x^0.3 + 0.334x^-0.7");
    addExpectedValue("x + 1", 0.8, "1.089x^0.2 + 0.218x^-0.8");
    addExpectedValue("x + 1", 0.9, "1.051x^0.1 + 0.105x^-0.9");
    addExpectedValue("x + 1", 1.5, "0.564x^-0.5 - 0.282x^-1.5");
  }

  private static void addGeneralCases(double[] alphas) {
    String[] basicTerms = {"1", "-1", "0x + 1"};
    for (String term : basicTerms) {
      for (double alpha : alphas) {
        if (!expectedValues.containsKey(term) || !expectedValues.get(term).containsKey(alpha)) {
          addExpectedValue(term, alpha, formatGeneralCase(term, alpha));
        }
      }
    }

    String[] singleVariableTerms = {"x + 1", "-x - 1", "-x + 1", "x - 1", "x + 0"};
    for (String term : singleVariableTerms) {
      for (double alpha : alphas) {
        if (!expectedValues.containsKey(term) || !expectedValues.get(term).containsKey(alpha)) {
          addExpectedValue(term, alpha, formatSingleVariableCase(term, alpha));
        }
      }
    }
  }

  private static void addSpecificExpectedValues() {
    addExpectedValue("x^2 + x + 1", 0.1, "1.094x^1.9 + 1.040x^0.9 + 0.936x^-0.1");
    addExpectedValue("x^2 + x + 1", 0.2, "1.193x^1.8 + 1.074x^0.8 + 0.859x^-0.2");
    addExpectedValue("x^2 + x + 1", 0.3, "1.295x^1.7 + 1.101x^0.7 + 0.770x^-0.3");
    addExpectedValue("x^2 + x + 1", 0.4, "1.399x^1.6 + 1.119x^0.6 + 0.672x^-0.4");
    addExpectedValue("x^2 + x + 1", 0.5, "1.505x^1.5 + 1.128x^0.5 + 0.564x^-0.5");
    addExpectedValue("x^2 + x + 1", 0.6, "1.610x^1.4 + 1.127x^0.4 + 0.451x^-0.6");
    addExpectedValue("x^2 + x + 1", 0.7, "1.714x^1.3 + 1.114x^0.3 + 0.334x^-0.7");
    addExpectedValue("x^2 + x + 1", 0.8, "1.815x^1.2 + 1.089x^0.2 + 0.218x^-0.8");
    addExpectedValue("x^2 + x + 1", 0.9, "1.911x^1.1 + 1.051x^0.1 + 0.105x^-0.9");
    addExpectedValue("x^2 + x + 1", 1.5, "2.257x^0.5 + 0.564x^-0.5 - 0.282x^-1.5");
  }

  private static String formatGeneralCase(String term, double alpha) {
    double coefficient = term.equals("1") ? 1 : -1;
    return String.format("%.3f", coefficient * Math.pow(alpha, -alpha))
        + "x^"
        + String.format("%.1f", -alpha);
  }

  private static String formatSingleVariableCase(String term, double alpha) {
    double coefficient = term.startsWith("-") ? -1 : 1;
    return String.format("%.3f", coefficient * Math.pow(alpha, 1 - alpha))
        + "x^"
        + String.format("%.1f", 1 - alpha);
  }

  private static void addExpectedValue(String polynomial, double alpha, String result) {
    expectedValues.computeIfAbsent(polynomial, k -> new HashMap<>()).put(alpha, result);
  }

  /**
   * Provides a stream of arguments representing different combinations of polynomial coefficients
   * and fractional orders for testing Riemann-Liouville derivative computations.
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
