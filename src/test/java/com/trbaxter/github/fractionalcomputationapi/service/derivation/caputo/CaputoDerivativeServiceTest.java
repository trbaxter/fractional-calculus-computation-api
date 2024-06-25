package com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.testdata.GammaTestData;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * CaputoDerivativeServiceTest is a test class for the CaputoDerivativeService. It uses
 * SpringBootTest to test the service in a Spring Boot context.
 */
@SpringBootTest
public class CaputoDerivativeServiceTest {

  @Autowired private CaputoDerivativeService derivativeService;

  /**
   * Tests the Caputo derivative service with different coefficient combinations.
   *
   * @param coefficientString the coefficients of the polynomial as a comma-separated string.
   * @param alpha the fractional order of the Caputo derivative.
   * @param expected the expected result of the derivative computation.
   */
  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative"
          + ".CaputoDerivativeTestData#coefficientCombinations")
  public void testCaputoCoefficientCombinations(
      String coefficientString, double alpha, String expected) {
    double[] coefficients =
        Arrays.stream(coefficientString.split(",")).mapToDouble(Double::parseDouble).toArray();

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      GammaTestData.setupMathUtilsMock(utilities);
      String result = derivativeService.evaluateExpression(coefficients, alpha);
      assertEquals(expected, result);
    }
  }

  /**
   * Tests the Caputo derivative service with shared coefficient combinations.
   *
   * @param coefficientString the coefficients of the polynomial as a comma-separated string.
   * @param alpha the fractional order of the Caputo derivative.
   * @param expected the expected result of the derivative computation.
   */
  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative"
          + ".SharedDerivativeTestData#coefficientCombinations")
  public void testSharedCoefficientCombinations(
      String coefficientString, double alpha, String expected) {
    double[] coefficients =
        Arrays.stream(coefficientString.split(",")).mapToDouble(Double::parseDouble).toArray();

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      GammaTestData.setupMathUtilsMock(utilities);
      String result = derivativeService.evaluateExpression(coefficients, alpha);
      assertEquals(expected, result);
    }
  }

  /**
   * Tests the handling of an exception thrown by the gamma function in the Caputo derivative
   * service.
   */
  @Test
  public void testGammaFunctionException() {
    double[] coefficients = {1, 2, 3};
    double alpha = 0.5;

    Logger logger = Logger.getLogger(CaputoDerivativeComputationService.class.getName());
    List<String> logMessages = new ArrayList<>();
    Handler testHandler =
        new Handler() {
          @Override
          public void publish(LogRecord record) {
            logMessages.add(record.getMessage());
          }

          @Override
          public void flush() {}

          @Override
          public void close() throws SecurityException {}
        };

    logger.setLevel(Level.SEVERE);
    logger.addHandler(testHandler);

    try (MockedStatic<MathUtils> mockedMathUtils = mockStatic(MathUtils.class)) {
      mockedMathUtils
          .when(() -> MathUtils.gamma(any(BigDecimal.class)))
          .thenThrow(new ArithmeticException("Gamma function error"));

      String result = derivativeService.evaluateExpression(coefficients, alpha);
      assertNotNull(result);
      assertTrue(logMessages.stream().anyMatch(msg -> msg.contains("Gamma function error")));
    } finally {
      logger.removeHandler(testHandler);
    }
  }
}
