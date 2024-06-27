package com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class RiemannLiouvilleComputationService {

  private static final Logger logger =
      Logger.getLogger(RiemannLiouvilleComputationService.class.getName());

  public List<Term> computeTerms(List<Term> terms, BigDecimal alpha) {
    List<Term> computedTerms = new ArrayList<>();

    // Differentiate if alpha is an integer
    if (alpha.stripTrailingZeros().scale() <= 0) {
      computeIntegerOrderDerivativeTerms(terms, alpha.intValue(), computedTerms);
    } else {
      computeFractionalOrderDerivativeTerms(terms, alpha, computedTerms);
    }

    computedTerms.sort(Comparator.comparing(Term::power).reversed());

    // If alpha is a whole number and no terms are left, return a zero term
    if (alpha.stripTrailingZeros().scale() <= 0 && computedTerms.isEmpty()) {
      computedTerms.add(new Term(BigDecimal.ZERO, BigDecimal.ZERO));
    }

    return computedTerms;
  }

  private void computeIntegerOrderDerivativeTerms(
      List<Term> terms, int intAlpha, List<Term> computedTerms) {
    for (Term term : terms) {
      BigDecimal coefficient = term.coefficient();
      BigDecimal currentDegree = term.power();

      if (coefficient.compareTo(BigDecimal.ZERO) != 0
          && currentDegree.compareTo(BigDecimal.valueOf(intAlpha)) >= 0) {
        BigDecimal newCoefficient = coefficient;
        for (int j = 0; j < intAlpha; j++) {
          newCoefficient = newCoefficient.multiply(currentDegree.subtract(BigDecimal.valueOf(j)));
        }
        BigDecimal newDegree = currentDegree.subtract(BigDecimal.valueOf(intAlpha));
        if (newDegree.compareTo(BigDecimal.ZERO) >= 0) {
          computedTerms.add(new Term(newCoefficient, newDegree));
        }
      }
    }
  }

  private void computeFractionalOrderDerivativeTerms(
      List<Term> terms, BigDecimal alpha, List<Term> computedTerms) {
    for (Term term : terms) {
      BigDecimal coefficient = term.coefficient();
      BigDecimal k = term.power();

      if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
        try {
          BigDecimal gammaNumerator = MathUtils.gamma(k.add(BigDecimal.ONE));
          BigDecimal gammaDenominator = MathUtils.gamma(k.add(BigDecimal.ONE).subtract(alpha));
          logger.info(
              String.format(
                  "Term with power %s: gammaNumerator = %s, gammaDenominator = %s",
                  k, gammaNumerator, gammaDenominator));

          if (gammaDenominator.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal gammaCoefficient =
                gammaNumerator.divide(gammaDenominator, MathContext.DECIMAL128);
            BigDecimal newCoefficient = coefficient.multiply(gammaCoefficient);
            BigDecimal newPower = k.subtract(alpha);
            logger.info(
                String.format(
                    "Computed Term: newCoefficient = %s, newPower = %s", newCoefficient, newPower));
            computedTerms.add(new Term(newCoefficient, newPower));
          }
        } catch (ArithmeticException e) {
          logger.severe(
              String.format(
                  "Arithmetic error computing term with power %s: %s", k, e.getMessage()));
          throw e;
        } catch (Exception e) {
          logger.severe(
              String.format(
                  "Unexpected error computing term with power %s: %s", k, e.getMessage()));
          throw new RuntimeException(e);
        }
      }
    }
  }
}
