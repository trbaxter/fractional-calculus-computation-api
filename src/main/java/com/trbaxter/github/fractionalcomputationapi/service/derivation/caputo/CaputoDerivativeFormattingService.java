package com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CaputoDerivativeFormattingService {

  public String formatTerms(List<Term> terms) {

    // Derivative of 0 polynomial is always 0
    boolean allZeroCoefficients =
        terms.stream().allMatch(term -> term.coefficient().compareTo(BigDecimal.ZERO) == 0);
    if (allZeroCoefficients) {
      return "0";
    }

    StringBuilder result = new StringBuilder();

    for (Term term : terms) {

      // Skip terms with zero coefficients or negative exponents
      if (term.coefficient().compareTo(BigDecimal.ZERO) == 0
          || term.power().compareTo(BigDecimal.ZERO) < 0) {
        continue;
      }
      BigDecimal coefficient = term.coefficient().setScale(3, RoundingMode.HALF_UP);

      String coefficientString =
          coefficient.stripTrailingZeros().scale() <= 0
              ? coefficient.stripTrailingZeros().toPlainString()
              : coefficient.toPlainString().replaceAll("\\.000$", "");

      if (!result.isEmpty()) {
        if (coefficient.compareTo(BigDecimal.ZERO) > 0) {
          result.append(" + ");
        } else if (coefficient.compareTo(BigDecimal.ZERO) < 0) {
          result.append(" - ");
          coefficientString = coefficient.abs().toPlainString().replaceAll("\\.000$", "");
        }
      }

      boolean omitCoefficient =
          coefficient.abs().compareTo(BigDecimal.ONE) == 0
              && term.power().compareTo(BigDecimal.ZERO) != 0;

      if (!omitCoefficient) {
        result.append(coefficientString);
      } else if (coefficient.compareTo(BigDecimal.ONE.negate()) == 0 && result.isEmpty()) {
        result.append("-");
      }

      if (term.power().compareTo(BigDecimal.ZERO) != 0) {
        result.append("x");
        if (term.power().compareTo(BigDecimal.ONE) != 0) {
          String exponent = term.power().stripTrailingZeros().toPlainString();
          result.append("^").append(exponent);
        }
      }
    }

    if (result.isEmpty()) {
      return "0";
    }

    return result.toString();
  }
}
