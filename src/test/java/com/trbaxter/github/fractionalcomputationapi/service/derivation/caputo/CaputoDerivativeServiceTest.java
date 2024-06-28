package com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.testdata.GammaTestData;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * CaputoDerivativeServiceTest is a test class for the CaputoDerivativeService.<br>
 * It uses SpringBootTest to test the service in a Spring Boot context.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CaputoDerivativeServiceTest {

  private CaputoDerivativeService derivativeService;

  @BeforeEach
  public void setUp() {
    derivativeService =
        new CaputoDerivativeService(
            new CaputoDerivativeComputationService(), new CaputoDerivativeFormattingService());
  }

  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative.CaputoDerivativeTestData#polynomialExpressions")
  public void testCaputoPolynomialExpressions(
      String polynomialExpression, double alpha, Integer precision, String expected) {
    testEvaluateExpression(polynomialExpression, alpha, precision, expected);
  }

  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative.SharedDerivativeTestData#polynomialExpressions")
  public void testSharedPolynomialExpressions(
      String polynomialExpression, double alpha, Integer precision, String expected) {
    testEvaluateExpression(polynomialExpression, alpha, precision, expected);
  }

  private void testEvaluateExpression(
      String polynomialExpression, double alpha, Integer precision, String expected) {
    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      GammaTestData.setupMathUtilsMock(utilities);
      String result = derivativeService.evaluateExpression(polynomialExpression, alpha, precision);
      assertEquals(expected, result);
    }
  }
}
