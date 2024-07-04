package com.trbaxter.github.fractionalcomputationapi.testdata.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public final class TestDataConfig {

  private TestDataConfig() {
    throw new UnsupportedOperationException("Utility class for test data");
  }

  public static Map<String, Map<Double, String>> configureExpectedValues() {
    Map<String, Map<Double, String>> expectedValues = new HashMap<>();
    Yaml yaml = new Yaml();
    try (InputStream input =
        TestDataConfig.class.getResourceAsStream("/caputo_derivative_test_data.yaml")) {
      if (input == null) {
        throw new IllegalStateException("YAML file not found: test_data.yaml");
      }
      Map<String, Map<String, Map<Double, String>>> rawData = yaml.load(input);
      Map<String, Map<Double, String>> loadedValues = rawData.get("expectedValues");

      if (loadedValues != null) {
        expectedValues.putAll(loadedValues);
      }
    } catch (Exception e) {
      throw new ExceptionInInitializerError("Failed to load test data YAML file");
    }
    return expectedValues;
  }
}
