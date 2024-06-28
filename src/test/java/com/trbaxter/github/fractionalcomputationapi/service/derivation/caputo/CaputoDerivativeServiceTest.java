package com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.testdata.GammaTestData;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * CaputoDerivativeServiceTest is a test class for the CaputoDerivativeService. It uses
 * SpringBootTest to test the service in a Spring Boot context.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CaputoDerivativeServiceTest {

  private final CaputoDerivativeService derivativeService =
      new CaputoDerivativeService(
          new CaputoDerivativeComputationService(), new CaputoDerivativeFormattingService());

  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative.CaputoDerivativeTestData#polynomialExpressions")
  public void testCaputoPolynomialExpressions(
      String polynomialExpression, double alpha, Integer precision, String expected) {
    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      GammaTestData.setupMathUtilsMock(utilities);
      String result = derivativeService.evaluateExpression(polynomialExpression, alpha, precision);
      assertEquals(expected, result);
    }
  }

  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative.SharedDerivativeTestData#polynomialExpressions")
  public void testSharedPolynomialExpressions(
      String polynomialExpression, double alpha, Integer precision, String expected) {
    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      GammaTestData.setupMathUtilsMock(utilities);
      String result = derivativeService.evaluateExpression(polynomialExpression, alpha, precision);
      assertEquals(expected, result);
    }
  }

  @Test
  public void testGammaFunctionException() {
    String polynomialExpression = "x^2 + 2x + 3";
    double alpha = 0.5;
    Integer precision = 2;

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

      String result = derivativeService.evaluateExpression(polynomialExpression, alpha, precision);
      assertNotNull(result);
      assertTrue(logMessages.stream().anyMatch(msg -> msg.contains("Gamma function error")));
    } finally {
      logger.removeHandler(testHandler);
    }
  }
}
