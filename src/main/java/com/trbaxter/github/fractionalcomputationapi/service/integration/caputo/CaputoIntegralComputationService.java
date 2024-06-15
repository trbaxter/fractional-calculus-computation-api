package com.trbaxter.github.fractionalcomputationapi.service.integration.caputo;

import static org.apache.commons.math3.special.Gamma.gamma;

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
public class CaputoIntegralComputationService {
  private static final Logger logger =
      Logger.getLogger(CaputoIntegralComputationService.class.getName());

  public List<Term> computeTerms(double[] coefficients, BigDecimal alpha) {
    List<Term> terms = new ArrayList<>();
    int degree = coefficients.length - 1;

    if (alpha.stripTrailingZeros().scale() <= 0) {
      computeIntegerOrderTerms(coefficients, alpha.intValue(), terms, degree);
    } else {
      computeFractionalOrderTerms(coefficients, alpha, terms, degree);
    }

    terms.sort(Comparator.comparing(Term::power).reversed());
    return terms;
  }

  private void computeIntegerOrderTerms(
      double[] coefficients, int intAlpha, List<Term> terms, int degree) {
    for (int i = 0; i <= degree; i++) {
      BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
      if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
        int newDegree = degree - i + intAlpha;
        BigDecimal newCoefficient =
            coefficient.divide(
                BigDecimal.valueOf(gamma(newDegree + 1) / gamma(newDegree - intAlpha + 1)),
                MathContext.DECIMAL128);
        terms.add(new Term(newCoefficient, BigDecimal.valueOf(newDegree)));
      }
    }
  }

  private void computeFractionalOrderTerms(
      double[] coefficients, BigDecimal alpha, List<Term> terms, int degree) {
    for (int i = 0; i <= degree; i++) {
      BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
      if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
        try {
          BigDecimal k = BigDecimal.valueOf(degree - i);
          BigDecimal gammaNumerator = MathUtils.gamma(k.add(alpha).add(BigDecimal.ONE));
          BigDecimal gammaDenominator = MathUtils.gamma(k.add(BigDecimal.ONE));
          logger.info(
              String.format(
                  "Term %d: gammaNumerator = %s, gammaDenominator = %s",
                  i, gammaNumerator, gammaDenominator));

          if (gammaDenominator.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal gammaCoefficient =
                gammaNumerator.divide(gammaDenominator, MathContext.DECIMAL128);
            BigDecimal newCoefficient = coefficient.multiply(gammaCoefficient);
            BigDecimal newPower = k.add(alpha);
            logger.info(
                String.format(
                    "Term %d: newCoefficient = %s, newPower = %s", i, newCoefficient, newPower));
            terms.add(new Term(newCoefficient, newPower));
          }
        } catch (Exception e) {
          logger.severe(String.format("Error computing term %d: %s", i, e.getMessage()));
        }
      }
    }
  }
}
