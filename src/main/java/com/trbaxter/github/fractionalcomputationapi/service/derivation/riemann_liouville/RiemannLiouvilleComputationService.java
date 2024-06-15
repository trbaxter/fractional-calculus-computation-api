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

  public List<Term> computeTerms(double[] coefficients, BigDecimal alpha) {
    List<Term> terms = new ArrayList<>();
    int degree = coefficients.length - 1;

    // Differentiate if alpha is an integer
    if (alpha.stripTrailingZeros().scale() <= 0) {
      computeIntegerOrderDerivativeTerms(coefficients, alpha.intValue(), terms, degree);
    } else {
      computeFractionalOrderDerivativeTerms(coefficients, alpha, terms, degree);
    }

    terms.sort(Comparator.comparing(Term::power).reversed());

    // If alpha is a whole number and no terms are left, return a zero term
    if (alpha.stripTrailingZeros().scale() <= 0 && terms.isEmpty()) {
      terms.add(new Term(BigDecimal.ZERO, BigDecimal.ZERO));
    }

    return terms;
  }

  private void computeIntegerOrderDerivativeTerms(
      double[] coefficients, int intAlpha, List<Term> terms, int degree) {
    for (int i = 0; i <= degree; i++) {
      BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
      int currentDegree = degree - i;

      if (coefficient.compareTo(BigDecimal.ZERO) != 0 && currentDegree >= intAlpha) {
        BigDecimal newCoefficient = coefficient;
        for (int j = 0; j < intAlpha; j++) {
          newCoefficient = newCoefficient.multiply(BigDecimal.valueOf(currentDegree - j));
        }
        int newDegree = currentDegree - intAlpha;
        if (newDegree >= 0) {
          terms.add(new Term(newCoefficient, BigDecimal.valueOf(newDegree)));
        }
      }
    }
  }

  private void computeFractionalOrderDerivativeTerms(
      double[] coefficients, BigDecimal alpha, List<Term> terms, int degree) {
    for (int i = 0; i <= degree; i++) {
      BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
      BigDecimal k = BigDecimal.valueOf(degree - i);

      if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
        try {
          BigDecimal gammaNumerator = MathUtils.gamma(k.add(BigDecimal.ONE));
          BigDecimal gammaDenominator = MathUtils.gamma(k.add(BigDecimal.ONE).subtract(alpha));
          logger.info(
              String.format(
                  "Term %d: gammaNumerator = %s, gammaDenominator = %s",
                  i, gammaNumerator, gammaDenominator));

          if (gammaDenominator.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal gammaCoefficient =
                gammaNumerator.divide(gammaDenominator, MathContext.DECIMAL128);
            BigDecimal newCoefficient = coefficient.multiply(gammaCoefficient);
            BigDecimal newPower = k.subtract(alpha);
            terms.add(new Term(newCoefficient, newPower));
          }
        } catch (ArithmeticException e) {
          logger.severe(String.format("Arithmetic error computing term %d: %s", i, e.getMessage()));
        } catch (Exception e) {
          logger.severe(String.format("Unexpected error computing term %d: %s", i, e.getMessage()));
        }
      }
    }
  }
}
