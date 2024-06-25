package com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville;

import static com.trbaxter.github.fractionalcomputationapi.testdata.GammaTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.testdata.GammaTestData;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * RiemannLiouvilleDerivativeServiceTest is a test class for the RiemannLiouvilleDerivativeService.
 * It uses SpringBootTest to test the service in a Spring Boot context.
 */
@SpringBootTest
public class RiemannLiouvilleDerivativeServiceTest {

  @Autowired RiemannLiouvilleDerivativeService derivativeService;

  /**
   * Tests the Riemann-Liouville derivative service with different coefficient combinations.
   *
   * @param coefficientString the coefficients of the polynomial as a comma-separated string.
   * @param alpha the fractional order of the Riemann-Liouville derivative.
   * @param expected the expected result of the derivative computation.
   */
  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative"
          + ".RiemannLiouvilleDerivativeTestData#coefficientCombinations")
  public void testCoefficientCombinations(String coefficientString, double alpha, String expected) {
    double[] coefficients =
        Arrays.stream(coefficientString.split(",")).mapToDouble(Double::parseDouble).toArray();

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      GammaTestData.setupMathUtilsMock(utilities);
      String result = derivativeService.evaluateExpression(coefficients, alpha);
      assertEquals(expected, result);
    }
  }

  /**
   * Tests the Riemann-Liouville derivative service with shared coefficient combinations.
   *
   * @param coefficientString the coefficients of the polynomial as a comma-separated string.
   * @param alpha the fractional order of the Riemann-Liouville derivative.
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

  /** Tests the Riemann-Liouville derivative service when an ArithmeticException is thrown. */
  @Test
  public void testComputeDerivative_ThrowsArithmeticException() {
    double[] coefficients = {3.0, 2.0, 1.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(new BigDecimal(gamma_3));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
          .thenThrow(new ArithmeticException("Division by zero"));

      String result;
      try {
        result = derivativeService.evaluateExpression(coefficients, alpha);
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
    double[] coefficients = {1.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
          .thenThrow(new RuntimeException("Unexpected error"));

      String result;
      try {
        result = derivativeService.evaluateExpression(coefficients, alpha);
      } catch (RuntimeException e) {
        result = "";
      }
      String expected = "";

      assertEquals(expected, result);
    }
  }
}
