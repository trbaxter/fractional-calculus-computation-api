package com.trbaxter.github.fractionalcomputationapi.testdata;

import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;
import org.mockito.MockedStatic;
import org.yaml.snakeyaml.Yaml;

/**
 * GammaTestData provides precomputed values for the gamma function to be used in unit tests. It
 * includes a method to set up mock values for the MathUtils class.
 */
public final class GammaTestData {

  private static final Map<String, String> gammaValues;

  static {
    try (InputStream input = GammaTestData.class.getResourceAsStream("/gamma_values.yaml")) {
      if (input == null) {
        throw new IllegalStateException("YAML file not found: gamma_values.yaml");
      }
      Yaml yaml = new Yaml();
      Map<String, Object> rawValues = yaml.load(input);
      gammaValues =
          rawValues.entrySet().stream()
              .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().toString()));
    } catch (Exception e) {
      throw new ExceptionInInitializerError("Failed to load gamma values YAML file");
    }
  }

  /** Private constructor to prevent instantiation. */
  private GammaTestData() {
    throw new UnsupportedOperationException("Utility class for test data");
  }

  /**
   * Sets up mock values for the MathUtils class.
   *
   * @param utilities the MockedStatic object for MathUtils.
   */
  public static void setupMathUtilsMock(MockedStatic<MathUtils> utilities) {
    gammaValues.forEach(
        (key, value) -> {
          try {
            BigDecimal argument;
            if (key.startsWith("NEG_GAMMA_")) {
              argument = new BigDecimal(key.replace("NEG_GAMMA_", "-").replace("_", "."));
            } else {
              argument = new BigDecimal(key.replace("GAMMA_", "").replace("_", "."));
            }
            utilities.when(() -> MathUtils.gamma(argument)).thenReturn(new BigDecimal(value));
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid key format: " + key, e);
          }
        });
  }
}
