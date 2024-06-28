package com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RiemannLiouvilleDerivativeServiceTest {

  private RiemannLiouvilleDerivativeService derivativeService;

  @BeforeEach
  public void setUp() {
    derivativeService =
        new RiemannLiouvilleDerivativeService(
            new RiemannLiouvilleComputationService(), new RiemannLiouvilleFormattingService());
  }

  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative.RiemannLiouvilleDerivativeTestData#polynomialExpressions")
  public void testPolynomialExpressions(
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