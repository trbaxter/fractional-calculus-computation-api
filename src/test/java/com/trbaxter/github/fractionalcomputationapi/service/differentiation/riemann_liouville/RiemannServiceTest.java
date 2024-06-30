package com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.testdata.GammaTestData;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RiemannServiceTest {

  private RiemannService derivativeService;

  @BeforeEach
  public void setUp() {
    derivativeService =
        new RiemannService(new RiemannComputationService(), new RiemannFormattingService());
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

  @Test
  public void testAlphaZeroAndEmptyComputedTerms() {
    List<Term> terms = new ArrayList<>();
    BigDecimal alpha = BigDecimal.ZERO;

    RiemannComputationService computationService = new RiemannComputationService();
    List<Term> computedTerms = computationService.computeTerms(terms, alpha);

    assertEquals(1, computedTerms.size());
    assertEquals(BigDecimal.ZERO, computedTerms.getFirst().coefficient());
    assertEquals(BigDecimal.ZERO, computedTerms.getFirst().power());
  }

  @Test
  public void testAlphaZeroAndNonEmptyComputedTerms() {
    List<Term> terms = List.of(new Term(BigDecimal.valueOf(2), BigDecimal.ONE));
    BigDecimal alpha = BigDecimal.ZERO;

    RiemannComputationService computationService = new RiemannComputationService();
    List<Term> computedTerms = computationService.computeTerms(terms, alpha);

    assertEquals(1, computedTerms.size());
    assertEquals(BigDecimal.valueOf(2), computedTerms.getFirst().coefficient());
    assertEquals(BigDecimal.ONE, computedTerms.getFirst().power());
  }

  @Test
  public void testAlphaZeroAndSkippedTerms() {
    List<Term> terms = List.of(new Term(BigDecimal.ZERO, BigDecimal.ONE));
    BigDecimal alpha = BigDecimal.ZERO;

    RiemannComputationService computationService = new RiemannComputationService();
    List<Term> computedTerms = computationService.computeTerms(terms, alpha);

    assertEquals(1, computedTerms.size());
    assertEquals(BigDecimal.ZERO, computedTerms.getFirst().coefficient());
    assertEquals(BigDecimal.ONE, computedTerms.getFirst().power());
  }

  @Test
  public void testGammaDenominatorZero() {
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

      RiemannComputationService computationService = new RiemannComputationService();
      List<Term> result = computationService.computeTerms(terms, alpha);

      assertTrue(result.isEmpty());
    }
  }

  @Test
  public void testExceptionInGammaComputation() {
    List<Term> terms = List.of(new Term(BigDecimal.ONE, BigDecimal.valueOf(2)));
    BigDecimal alpha = BigDecimal.valueOf(1.1);

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(any(BigDecimal.class)))
          .thenThrow(new RuntimeException("Test exception"));

      RiemannComputationService computationService = new RiemannComputationService();
      List<Term> result = computationService.computeTerms(terms, alpha);

      assertTrue(result.isEmpty());
    }
  }
}
