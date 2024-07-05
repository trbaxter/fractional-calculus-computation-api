package com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville;

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

@Service
public class RiemannComputationService {

  private static final Logger logger = LoggerFactory.getLogger(RiemannComputationService.class);

  public List<Term> computeTerms(List<Term> terms, BigDecimal alpha) {
    List<Term> computedTerms = new ArrayList<>();

    if (alpha.compareTo(BigDecimal.ZERO) == 0) {
      computedTerms.addAll(terms);
    } else if (alpha.stripTrailingZeros().scale() <= 0) {
      computeIntegerOrderDerivativeTerms(terms, alpha.intValue(), computedTerms);
    } else {
      computeFractionalOrderDerivativeTerms(terms, alpha, computedTerms);
    }

    computedTerms.sort(Comparator.comparing(Term::power).reversed());
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
          BigDecimal gammaDenominator = MathUtils.gamma(k.subtract(alpha).add(BigDecimal.ONE));
          logger.info(
              "Term with power {}: gammaNumerator = {}, gammaDenominator = {}",
              k,
              gammaNumerator,
              gammaDenominator);

          if (gammaDenominator.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal gammaCoefficient =
                gammaNumerator.divide(gammaDenominator, MathContext.DECIMAL128);
            BigDecimal newCoefficient = coefficient.multiply(gammaCoefficient);
            BigDecimal newPower = k.subtract(alpha);
            logger.info(
                "Computed Term: newCoefficient = {}, newPower = {}", newCoefficient, newPower);
            computedTerms.add(new Term(newCoefficient, newPower));
          }
        } catch (Exception e) {
          logger.error("Error computing term with power {}: {}", k, e.getMessage(), e);
        }
      }
    }
  }
}
