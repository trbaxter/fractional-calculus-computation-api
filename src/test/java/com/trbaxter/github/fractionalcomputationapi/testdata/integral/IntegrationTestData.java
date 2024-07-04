package com.trbaxter.github.fractionalcomputationapi.testdata.integral;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.yaml.snakeyaml.Yaml;

/**
 * CaputoIntegrationTestData provides a collection of test data for testing the Caputo integral
 * computations.<br>
 * It includes various combinations of polynomial coefficients and fractional orders.
 */
public final class IntegrationTestData {
  private static final Map<String, Map<Double, String>> expectedValues = new HashMap<>();

  static {
    try (InputStream input =
        IntegrationTestData.class.getResourceAsStream("/integration_test_data.yaml")) {
      if (input == null) {
        throw new IllegalStateException("YAML file not found: integration_test_data.yaml");
      }
      Yaml yaml = new Yaml();
      Map<String, Object> rawValues = yaml.load(input);

      Map<String, Map<String, Object>> nestedMap =
          (Map<String, Map<String, Object>>) rawValues.get("expectedValues");

      for (Map.Entry<String, Map<String, Object>> entry : nestedMap.entrySet()) {
        String polynomial = entry.getKey();
        Map<Double, String> alphaMap =
            entry.getValue().entrySet().stream()
                .collect(
                    Collectors.toMap(
                        e -> {
                          try {
                            return Double.parseDouble(
                                e.getKey().replace("alpha_", "").replace("_", "."));
                          } catch (NumberFormatException nfe) {
                            throw new IllegalArgumentException(
                                "Invalid key format: " + e.getKey(), nfe);
                          }
                        },
                        e -> e.getValue().toString()));
        expectedValues.put(polynomial, alphaMap);
      }

    } catch (Exception e) {
      throw new ExceptionInInitializerError(
          "Failed to load integration test data YAML file: " + e.getMessage());
    }
  }

  private IntegrationTestData() {
    throw new UnsupportedOperationException("Utility class for test data");
  }

  public static Stream<Arguments> polynomialExpressions() {
    return expectedValues.entrySet().stream()
        .flatMap(
            entry ->
                entry.getValue().entrySet().stream()
                    .map(e -> Arguments.of(entry.getKey(), e.getKey(), 3, e.getValue())));
  }
}
