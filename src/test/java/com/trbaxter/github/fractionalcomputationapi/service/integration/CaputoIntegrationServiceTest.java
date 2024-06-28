package com.trbaxter.github.fractionalcomputationapi.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.integration.caputo.CaputoIntegralComputationService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.caputo.CaputoIntegrationService;
import com.trbaxter.github.fractionalcomputationapi.testdata.GammaTestData;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * CaputoIntegrationServiceTest is a test class for the CaputoIntegrationService. It uses
 * SpringBootTest to test the service in a Spring Boot context.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CaputoIntegrationServiceTest {

  @Autowired private CaputoIntegrationService integrationService;

  @Autowired private CaputoIntegralComputationService computationService;

  /**
   * Tests the Caputo integration service with different polynomial expressions.
   *
   * @param polynomialExpression the polynomial expression as a string.
   * @param alpha the fractional order of the Caputo integral.
   * @param expected the expected result of the integral computation.
   */
  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.integral"
          + ".CaputoIntegrationTestData#polynomialExpressions")
  public void testCaputoPolynomialExpressions(
      String polynomialExpression, double alpha, Integer precision, String expected) {

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      GammaTestData.setupMathUtilsMock(utilities);
      String result = integrationService.evaluateExpression(polynomialExpression, alpha, precision);
      assertEquals(expected, result);
    }
  }

  /**
   * Tests the Caputo integral computation service when an ArithmeticException is thrown by the
   * gamma function.
   */
  @Test
  public void testGammaFunctionException() {
    String polynomialExpression = "1x^2 + 2x + 3";
    BigDecimal alpha = BigDecimal.valueOf(0.5);

    Logger logger = Logger.getLogger(CaputoIntegralComputationService.class.getName());
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

    List<Term> terms = ExpressionParser.parse(polynomialExpression);

    try (MockedStatic<MathUtils> mockedMathUtils = mockStatic(MathUtils.class)) {
      mockedMathUtils
          .when(() -> MathUtils.gamma(any(BigDecimal.class)))
          .thenThrow(new ArithmeticException("Gamma function error"));

      List<Term> result = new ArrayList<>();
      try {
        result = computationService.computeTerms(terms, alpha);
      } catch (ArithmeticException e) {
        // Expected exception
      }

      assertNotNull(result);
      assertTrue(result.isEmpty()); // Ensure no terms are computed when exception occurs
      assertTrue(logMessages.stream().anyMatch(msg -> msg.contains("Gamma function error")));
    } finally {
      logger.removeHandler(testHandler);
    }
  }

  /** Tests the Caputo integral computation service when a generic exception is thrown. */
  @Test
  public void testComputeIntegral_ThrowsArithmeticException() {
    String polynomialExpression = "x^2 + 2x + 3";
    double alpha = 0.5;
    Integer precision = 2;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(any(BigDecimal.class)))
          .thenThrow(new ArithmeticException("Gamma function error"));

      String result = "";
      try {
        result = integrationService.evaluateExpression(polynomialExpression, alpha, precision);
      } catch (ArithmeticException e) {
        // Expected exception caught, result should remain as ""
      }

      String expected = "";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeIntegral_ThrowsGenericException() {
    String polynomialExpression = "1x^2 + 2x + 3";
    double alpha = 0.5;
    Integer precision = 2;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(any(BigDecimal.class)))
          .thenThrow(new RuntimeException("Unexpected error"));

      String result = "";
      try {
        result = integrationService.evaluateExpression(polynomialExpression, alpha, precision);
      } catch (Exception e) {
        // Expected exception caught, result should remain as ""
      }

      String expected = "";

      assertEquals(expected, result);
    }
  }
}
