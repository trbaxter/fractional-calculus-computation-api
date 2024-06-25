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

/**
 * CaputoIntegralComputationService provides methods to compute the terms of the Caputo fractional
 * integral for polynomial expressions.
 */
@Service
public class CaputoIntegralComputationService {
  private static final Logger logger =
      Logger.getLogger(CaputoIntegralComputationService.class.getName());

  /**
   * Computes the terms of the Caputo fractional integral for the given polynomial coefficients and
   * order alpha.
   *
   * @param coefficients the coefficients of the polynomial, must not be null.
   * @param alpha the order of the Caputo fractional integral, must not be null.
   * @return a list of computed terms of the Caputo fractional integral.
   */
  public List<Term> computeTerms(double[] coefficients, BigDecimal alpha) {
    List<Term> terms = new ArrayList<>();
    int degree = coefficients.length - 1;

    if (alpha.compareTo(BigDecimal.ZERO) == 0) {
      for (int i = 0; i <= degree; i++) {
        BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
        BigDecimal power = BigDecimal.valueOf(degree - i);
        terms.add(new Term(coefficient, power));
      }
    } else if (alpha.stripTrailingZeros().scale() <= 0) {
      computeIntegerOrderTerms(coefficients, alpha.intValue(), terms, degree);
    } else {
      computeFractionalOrderTerms(coefficients, alpha, terms, degree);
    }

    terms.sort(Comparator.comparing(Term::power).reversed());
    return terms;
  }

  /**
   * Computes the terms of the integer-order integral for the given polynomial coefficients and
   * order intAlpha.
   *
   * @param coefficients the coefficients of the polynomial, must not be null.
   * @param intAlpha the integer order of the integral.
   * @param terms the list to which computed terms will be added.
   * @param degree the degree of the polynomial.
   */
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

  /**
   * Computes the terms of the fractional-order integral for the given polynomial coefficients and
   * order alpha.
   *
   * @param coefficients the coefficients of the polynomial, must not be null.
   * @param alpha the fractional order of the integral, must not be null.
   * @param terms the list to which computed terms will be added.
   * @param degree the degree of the polynomial.
   */
  private void computeFractionalOrderTerms(
      double[] coefficients, BigDecimal alpha, List<Term> terms, int degree) {
    for (int i = 0; i <= degree; i++) {
      BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
      if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
        try {
          BigDecimal k = BigDecimal.valueOf(degree - i);
          BigDecimal gammaNumerator = MathUtils.gamma(k.add(BigDecimal.ONE));
          BigDecimal gammaDenominator = MathUtils.gamma(k.add(alpha).add(BigDecimal.ONE));
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
