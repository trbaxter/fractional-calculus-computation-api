package com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.testdata.GammaTestData;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class CaputoServiceTest {

  private CaputoService derivativeService;

  @BeforeEach
  public void setUp() {
    derivativeService =
        new CaputoService(new CaputoComputationService(), new CaputoFormattingService());
  }

  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative.CaputoDerivativeTestData#polynomialExpressions")
  void testCaputoPolynomialExpressions(
      String polynomialExpression, double alpha, Integer precision, String expected) {
    testEvaluateExpression(polynomialExpression, alpha, precision, expected);
  }

  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata.derivative.SharedDerivativeTestData#polynomialExpressions")
  void testSharedPolynomialExpressions(
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

  @Test
  void testGammaDenominatorZero() {
    List<Term> terms = List.of(new Term(BigDecimal.ONE, BigDecimal.valueOf(2)));
    BigDecimal alpha = BigDecimal.valueOf(1.1);

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(any(BigDecimal.class)))
          .thenAnswer(
              invocation -> {
                BigDecimal argument = invocation.getArgument(0);
                if (argument.compareTo(BigDecimal.valueOf(3)) == 0) {
                  return BigDecimal.valueOf(2); // gammaNumerator
                } else {
                  return BigDecimal.ZERO; // gammaDenominator
                }
              });

      CaputoComputationService computationService = new CaputoComputationService();
      List<Term> result = computationService.computeTerms(terms, alpha);

      assertTrue(result.isEmpty());
    }
  }

  @Test
  void testExceptionInGammaComputation() {
    List<Term> terms = List.of(new Term(BigDecimal.ONE, BigDecimal.valueOf(2)));
    BigDecimal alpha = BigDecimal.valueOf(1.1);

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(any(BigDecimal.class)))
          .thenThrow(new RuntimeException("Test exception"));

      CaputoComputationService computationService = new CaputoComputationService();
      List<Term> result = computationService.computeTerms(terms, alpha);

      assertTrue(result.isEmpty());
    }
  }
}
