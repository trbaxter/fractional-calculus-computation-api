package com.trbaxter.github.fractionalcomputationapi.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.service.integration.caputo.CaputoIntegralComputationService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.caputo.CaputoIntegrationService;
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
      "com.trbaxter.github.fractionalcomputationapi.testdata.integral.CaputoIntegrationTestData#polynomialExpressions")
  public void testCaputoPolynomialExpressions(
      String polynomialExpression, double alpha, Integer precision, String expected) {
    runTestWithMockedMathUtils(
        () -> {
          String result =
              integrationService.evaluateExpression(polynomialExpression, alpha, precision);
          assertEquals(expected, result);
        });
  }

  @Test
  public void testComputeIntegral_ThrowsArithmeticException() {
    runTestWithMockedMathUtils(
        () -> {
          Mockito.when(MathUtils.gamma(any(BigDecimal.class)))
              .thenThrow(new ArithmeticException("Gamma function error"));

          String result = "";
          try {
            result = integrationService.evaluateExpression("x^2 + 2x + 3", 0.5, 2);
          } catch (ArithmeticException e) {
            // Expected exception caught, result should remain as ""
          }

          assertEquals("", result);
        });
  }

  /** Tests the Caputo integral computation service when a generic exception is thrown. */
  @Test
  public void testComputeIntegral_ThrowsGenericException() {
    runTestWithMockedMathUtils(
        () -> {
          Mockito.when(MathUtils.gamma(any(BigDecimal.class)))
              .thenThrow(new RuntimeException("Unexpected error"));

          String result = "";
          try {
            result = integrationService.evaluateExpression("1x^2 + 2x + 3", 0.5, 2);
          } catch (Exception e) {
            // Expected exception caught, result should remain as ""
          }

          assertEquals("", result);
        });
  }

  private void runTestWithMockedMathUtils(Runnable testLogic) {
    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      GammaTestData.setupMathUtilsMock(utilities);
      testLogic.run();
    }
  }
}
