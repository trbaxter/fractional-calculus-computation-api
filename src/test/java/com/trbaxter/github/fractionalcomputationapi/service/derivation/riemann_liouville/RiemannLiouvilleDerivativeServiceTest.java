package com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.testdata.GammaTestData;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
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
 * RiemannLiouvilleDerivativeServiceTest is a test class for the RiemannLiouvilleDerivativeService.
 * It uses SpringBootTest to test the service in a Spring Boot context.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RiemannLiouvilleDerivativeServiceTest {

  @Autowired RiemannLiouvilleDerivativeService derivativeService;

  /**
   * Tests the Riemann-Liouville derivative service with different polynomial expressions.
   *
   * @param polynomialExpression the polynomial expression as a string.
   * @param alpha the fractional order of the Riemann-Liouville derivative.
   * @param expected the expected result of the derivative computation.
   */
  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative"
          + ".RiemannLiouvilleDerivativeTestData#polynomialExpressions")
  public void testPolynomialExpressions(
      String polynomialExpression, double alpha, String expected) {

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      GammaTestData.setupMathUtilsMock(utilities);
      String result = derivativeService.evaluateExpression(polynomialExpression, alpha);
      assertEquals(expected, result);
    }
  }

  /**
   * Tests the Riemann-Liouville derivative service with shared polynomial expressions.
   *
   * @param polynomialExpression the polynomial expression as a string.
   * @param alpha the fractional order of the Riemann-Liouville derivative.
   * @param expected the expected result of the derivative computation.
   */
  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative"
          + ".SharedDerivativeTestData#polynomialExpressions")
  public void testSharedPolynomialExpressions(
      String polynomialExpression, double alpha, String expected) {

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      GammaTestData.setupMathUtilsMock(utilities);
      String result = derivativeService.evaluateExpression(polynomialExpression, alpha);
      assertEquals(expected, result);
    }
  }

  /** Tests the Riemann-Liouville derivative service when an ArithmeticException is thrown. */
  @Test
  public void testComputeDerivative_ThrowsArithmeticException() {
    String polynomialExpression = "3x^2 + 2x + 1";
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(new BigDecimal("6.0"));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
          .thenThrow(new ArithmeticException("Division by zero"));

      String result;
      try {
        result = derivativeService.evaluateExpression(polynomialExpression, alpha);
      } catch (ArithmeticException e) {
        result = "";
      }
      String expected = "";

      assertEquals(expected, result);
    }
  }

  /** Tests the Riemann-Liouville derivative service when a generic exception is thrown. */
  @Test
  public void testComputeDerivative_ThrowsGenericException() {
    String polynomialExpression = "x^2 + 2x + 3";
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
          .thenThrow(new RuntimeException("Unexpected error"));

      String result;
      try {
        result = derivativeService.evaluateExpression(polynomialExpression, alpha);
      } catch (RuntimeException e) {
        result = "";
      }
      String expected = "";

      assertEquals(expected, result);
    }
  }
}
