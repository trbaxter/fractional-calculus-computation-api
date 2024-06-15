package com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CaputoDerivativeFormattingService {

  public String formatTerms(List<Term> terms) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < terms.size(); i++) {
      Term term = terms.get(i);
      BigDecimal coefficient = term.coefficient().setScale(3, RoundingMode.HALF_UP);

      String coefficientString =
          coefficient.stripTrailingZeros().scale() <= 0
              ? coefficient.stripTrailingZeros().toPlainString()
              : coefficient.toPlainString().replaceAll("\\.000$", "");

      if (i > 0) {
        if (coefficient.compareTo(BigDecimal.ZERO) > 0) {
          result.append(" + ");
        } else {
          result.append(" - ");
        }
        if (coefficient.abs().compareTo(BigDecimal.ONE) != 0) {
          result.append(coefficient.abs().toPlainString().replaceAll("\\.000$", ""));
        }
      } else {
        if (coefficient.compareTo(BigDecimal.ZERO) < 0) {
          result.append(coefficientString);
        } else {
          if (coefficient.compareTo(BigDecimal.ONE) != 0) {
            result.append(coefficientString);
          }
        }
      }

      if (term.power().compareTo(BigDecimal.ZERO) != 0) {
        result.append("x");
        if (term.power().compareTo(BigDecimal.ONE) != 0) {
          String exponent = term.power().stripTrailingZeros().toPlainString();
          result.append("^").append(exponent);
        }
      }
    }
    return result.toString();
  }
}
