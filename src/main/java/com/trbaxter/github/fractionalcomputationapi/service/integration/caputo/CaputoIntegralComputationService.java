package com.trbaxter.github.fractionalcomputationapi.service.integration.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * CaputoIntegralComputationService provides methods to compute the terms of the Caputo fractional
 * integral for polynomial expressions.
 */
@Service
public class CaputoIntegralComputationService {
  private static final Logger logger =
      Logger.getLogger(CaputoIntegralComputationService.class.getName());

  public List<Term> computeTerms(List<Term> terms, BigDecimal alpha) {
    List<Term> computedTerms = new ArrayList<>();

    if (alpha.compareTo(BigDecimal.ZERO) == 0) {
      return terms; // No change for alpha = 0
    }

    boolean isIntegerAlpha = alpha.stripTrailingZeros().scale() <= 0;

    for (Term term : terms) {
      BigDecimal coefficient = term.coefficient();
      BigDecimal k = term.power();

      if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
        try {
          if (isIntegerAlpha) {
            int intAlpha = alpha.intValue();
            computeIntegerOrderTerms(coefficient, k, intAlpha, computedTerms);
          } else {
            computeFractionalOrderTerms(coefficient, k, alpha, computedTerms);
          }
        } catch (ArithmeticException e) {
          logger.severe(
              String.format("Arithmetic error computing term %s: %s", term, e.getMessage()));
          throw e; // Re-throw the ArithmeticException to ensure it is caught in the test
        } catch (Exception e) {
          logger.severe(
              String.format("Unexpected error computing term %s: %s", term, e.getMessage()));
          throw e; // Re-throw the generic Exception to ensure it is caught in the test
        }
      }
    }

    computedTerms.sort(Comparator.comparing(Term::power).reversed());
    return computedTerms;
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
        String.format(
            "Term %s: gammaNumerator = %s, gammaDenominator = %s",
            k, gammaNumerator, gammaDenominator));

    if (gammaDenominator.compareTo(BigDecimal.ZERO) != 0) {
      BigDecimal gammaCoefficient = gammaNumerator.divide(gammaDenominator, MathContext.DECIMAL128);
      BigDecimal newCoefficient = coefficient.multiply(gammaCoefficient);
      BigDecimal newPower = k.add(alpha);
      logger.info(
          String.format(
              "Term %s: newCoefficient = %s, newPower = %s", k, newCoefficient, newPower));
      computedTerms.add(new Term(newCoefficient, newPower));
    }
  }
}
