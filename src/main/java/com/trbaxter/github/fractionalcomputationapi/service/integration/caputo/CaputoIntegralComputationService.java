package com.trbaxter.github.fractionalcomputationapi.service.integration.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * CaputoIntegralComputationService provides methods to compute the terms of the <br>
 * Caputo fractional integral for polynomial expressions.
 */
@Service
public class CaputoIntegralComputationService {
  private static final Logger logger =
      LoggerFactory.getLogger(CaputoIntegralComputationService.class);

  public List<Term> computeTerms(List<Term> terms, BigDecimal alpha) {
    if (alpha.compareTo(BigDecimal.ZERO) == 0) {
      return terms;
    }

    List<Term> computedTerms = new ArrayList<>();
    boolean isIntegerAlpha = alpha.stripTrailingZeros().scale() <= 0;

    for (Term term : terms) {
      BigDecimal coefficient = term.coefficient();
      BigDecimal k = term.power();

      if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
        computeTerm(alpha, isIntegerAlpha, computedTerms, coefficient, k);
      }
    }

    computedTerms.sort(Comparator.comparing(Term::power).reversed());
    return computedTerms;
  }

  private void computeTerm(
      BigDecimal alpha,
      boolean isIntegerAlpha,
      List<Term> computedTerms,
      BigDecimal coefficient,
      BigDecimal k) {
    try {
      if (isIntegerAlpha) {
        computeIntegerOrderTerms(coefficient, k, alpha.intValue(), computedTerms);
      } else {
        computeFractionalOrderTerms(coefficient, k, alpha, computedTerms);
      }
    } catch (ArithmeticException e) {
      logger.error("Arithmetic error computing term {}: {}", k, e.getMessage());
      throw e;
    } catch (Exception e) {
      logger.error("Unexpected error computing term {}: {}", k, e.getMessage());
      throw e;
    }
  }

  private void computeIntegerOrderTerms(
      BigDecimal coefficient, BigDecimal k, int intAlpha, List<Term> computedTerms) {
    BigDecimal newCoefficient = coefficient;
    BigDecimal newPower = k;

    for (int i = 1; i <= intAlpha; i++) {
      newPower = newPower.add(BigDecimal.ONE);
      newCoefficient = newCoefficient.divide(newPower, MathContext.DECIMAL128);
    }

    computedTerms.add(new Term(newCoefficient, newPower));
  }

  private void computeFractionalOrderTerms(
      BigDecimal coefficient, BigDecimal k, BigDecimal alpha, List<Term> computedTerms) {
    BigDecimal gammaNumerator = MathUtils.gamma(k.add(BigDecimal.ONE));
    BigDecimal gammaDenominator = MathUtils.gamma(k.add(alpha).add(BigDecimal.ONE));
    logger.info(
        "Term {}: gammaNumerator = {}, gammaDenominator = {}", k, gammaNumerator, gammaDenominator);

    if (gammaDenominator.compareTo(BigDecimal.ZERO) != 0) {
      BigDecimal gammaCoefficient = gammaNumerator.divide(gammaDenominator, MathContext.DECIMAL128);
      BigDecimal newCoefficient = coefficient.multiply(gammaCoefficient);
      BigDecimal newPower = k.add(alpha);
      logger.info("Term {}: newCoefficient = {}, newPower = {}", k, newCoefficient, newPower);
      computedTerms.add(new Term(newCoefficient, newPower));
    }
  }
}
