package com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo;

import static com.trbaxter.github.fractionalcomputationapi.testdata.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CaputoDerivativeServiceTest {

  @Autowired private CaputoDerivativeService caputoDerivativeService;

  @Autowired private CaputoDerivativeFormattingService formattingService;

  @Test
  public void testComputeDerivative_SimplePolynomial() {
    double[] coefficients = {3.0, 2.0, 1.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(new BigDecimal(gamma_3));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
          .thenReturn(new BigDecimal(gamma_2_point_5));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
          .thenReturn(new BigDecimal(gamma_2));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
          .thenReturn(new BigDecimal(gamma_1_point_5));

      String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "4.514x^1.5 + 2.257x^0.5";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeDerivative_NegativeCoefficients() {
    double[] coefficients = {-3.0, -2.0, -1.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(new BigDecimal(gamma_3));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
          .thenReturn(new BigDecimal(gamma_2_point_5));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
          .thenReturn(new BigDecimal(gamma_2));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
          .thenReturn(new BigDecimal(gamma_1_point_5));

      String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "-4.514x^1.5 - 2.257x^0.5";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeDerivative_ConstantTerm() {
    double[] coefficients = {3.0};
    double alpha = 0.5;

    String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
    String expected = "0";

    assertEquals(expected, result);
  }

  @Test
  public void testComputeDerivative_PositiveAndNegativeCoefficients() {
    double[] coefficients = {3.0, -2.0, 1.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(new BigDecimal(gamma_3));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
          .thenReturn(new BigDecimal(gamma_2_point_5));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
          .thenReturn(new BigDecimal(gamma_2));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
          .thenReturn(new BigDecimal(gamma_1_point_5));

      String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "4.514x^1.5 - 2.257x^0.5";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeDerivative_WholePositiveCoefficients_IntegerAlpha() {
    double[] coefficients = {1, 3, -6, 0, 0, 2};
    double alpha = 1.0;

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(6)))
          .thenReturn(new BigDecimal(gamma_6));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(5)))
          .thenReturn(new BigDecimal(gamma_5));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(4)))
          .thenReturn(new BigDecimal(gamma_4));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(new BigDecimal(gamma_3));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
          .thenReturn(new BigDecimal(gamma_2));

      String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "5x^4 + 12x^3 - 18x^2";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeDerivative_ZeroCoefficient() {
    double[] coefficients = {0.0, 2.0, -1.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
          .thenReturn(new BigDecimal(gamma_2));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
          .thenReturn(new BigDecimal(gamma_1_point_5));

      String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "2.257x^0.5";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeDerivative_ZeroPolynomial() {
    double[] coefficients = {0.0, 0.0, 0.0};
    double alpha = 0.5;

    String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
    String expected = "0";

    assertEquals(expected, result);
  }

  @Test
  public void testComputeDerivative_DifferentAlpha() {
    double[] coefficients = {3.0, 0.0, 1.0};
    double alpha = 1.5;

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(new BigDecimal(gamma_3));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
          .thenReturn(new BigDecimal(gamma_1_point_5));

      String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "6.770x^0.5";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeDerivative_NegativeExponentSuccessfulOmit() {
    double[] coefficients = {1.0, 1.0};
    double alpha = 1.5;

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(new BigDecimal(gamma_3));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
          .thenReturn(new BigDecimal(gamma_1_point_5));

      String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "0";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testIntegerAlpha() {
    double[] coefficients = {1, 2, 3};
    double alpha = 2;

    String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
    String expected = "2";

    assertEquals(expected, result);
  }

  @Test
  public void testNonIntegerAlpha() {
    double[] coefficients = {1, 2, 3};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> mockedMathUtils = mockStatic(MathUtils.class)) {
      mockedMathUtils.when(() -> MathUtils.gamma(any(BigDecimal.class))).thenReturn(BigDecimal.ONE);

      String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
      assertNotNull(result);
    }
  }

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

      String result = caputoDerivativeService.evaluateExpression(coefficients, alpha);
      assertNotNull(result);
      // Ensure the logger captured the error
      assertTrue(logMessages.stream().anyMatch(msg -> msg.contains("Gamma function error")));
    } finally {
      logger.removeHandler(testHandler);
    }
  }
}
