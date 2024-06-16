package com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville;

import static com.trbaxter.github.fractionalcomputationapi.testdata.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RiemannLiouvilleDerivativeServiceTest {

  @Autowired private RiemannLiouvilleDerivativeService riemannLiouvilleDerivativeService;

  @Test
  public void testDerivative_SimplePolynomial() {
    double[] coefficients = {3.0, 2.0, 1.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
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
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
          .thenReturn(new BigDecimal(gamma_1));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5)))
          .thenReturn(new BigDecimal(gamma_0_point_5));

      String result = riemannLiouvilleDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "4.514x^1.5 + 2.257x^0.5 + 0.564x^-0.5";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeDerivative_NegativeCoefficients() {
    double[] coefficients = {-3.0, -2.0, -1.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
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
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
          .thenReturn(new BigDecimal(gamma_1));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5)))
          .thenReturn(new BigDecimal(gamma_0_point_5));

      String result = riemannLiouvilleDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "-4.514x^1.5 - 2.257x^0.5 - 0.564x^-0.5";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeFractionalDerivative_ConstantTerm() {
    double[] coefficients = {3.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
          .thenReturn(new BigDecimal(gamma_1));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5)))
          .thenReturn(new BigDecimal(gamma_0_point_5));

      String result = riemannLiouvilleDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "1.693x^-0.5";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeIntegerDerivative_ConstantTerm() {
    double[] coefficients = {3.0};
    double alpha = 1.0;

    String result = riemannLiouvilleDerivativeService.evaluateExpression(coefficients, alpha);
    String expected = "0";

    assertEquals(expected, result);
  }

  @Test
  public void testComputeDerivative_ZeroPolynomial() {
    double[] coefficients = {0.0, 0.0, 0.0};
    double alpha = 0.5;

    String result = riemannLiouvilleDerivativeService.evaluateExpression(coefficients, alpha);
    String expected = "0";

    assertEquals(expected, result);
  }

  @Test
  public void testComputeDerivative_DifferentAlpha() {
    double[] coefficients = {3.0, 0.0, 1.0};
    double alpha = 1.5;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(new BigDecimal(gamma_3));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
          .thenReturn(new BigDecimal(gamma_1_point_5));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
          .thenReturn(new BigDecimal(gamma_1));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(-0.5)))
          .thenReturn(new BigDecimal(neg_gamma_0_point_5));

      String result = riemannLiouvilleDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "6.770x^0.5 - 0.282x^-1.5";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeDerivative_LargeAlpha() {
    double[] coefficients = {3.0, 0.0, 1.0};
    double alpha = 2.0;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(new BigDecimal(gamma_3));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
          .thenReturn(new BigDecimal(gamma_2));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
          .thenReturn(new BigDecimal(gamma_1));

      String result = riemannLiouvilleDerivativeService.evaluateExpression(coefficients, alpha);
      String expected = "6";

      assertEquals(expected, result);
    }
  }

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

      String result = "";
      try {
        result = riemannLiouvilleDerivativeService.evaluateExpression(coefficients, alpha);
      } catch (ArithmeticException e) {
        result = "";
      }
      String expected = "";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeDerivative_ThrowsGenericException() {
    double[] coefficients = {1.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
          .thenThrow(new RuntimeException("Unexpected error"));

      String result = "";
      try {
        result = riemannLiouvilleDerivativeService.evaluateExpression(coefficients, alpha);
      } catch (RuntimeException e) {
        result = "";
      }
      String expected = "";

      assertEquals(expected, result);
    }
  }
}
