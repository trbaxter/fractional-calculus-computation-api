package com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RiemannLiouvilleFormattingService {

  public String formatTerms(List<Term> terms) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < terms.size(); i++) {
      Term term = terms.get(i);
      if (i > 0) {
        if (term.coefficient().compareTo(BigDecimal.ZERO) > 0) {
          result.append(" + ");
        } else {
          result.append(" - ");
        }
        result.append(term.coefficient().abs().setScale(3, RoundingMode.HALF_UP));
      } else {
        if (term.coefficient().compareTo(BigDecimal.ZERO) < 0) {
          result.append("- ");
          result.append(term.coefficient().abs().setScale(3, RoundingMode.HALF_UP));
        } else {
          result.append(term.coefficient().setScale(3, RoundingMode.HALF_UP));
        }
      }

      // Check if power is zero, and if so, only append the coefficient
      if (term.power().compareTo(BigDecimal.ZERO) != 0) {
        // Remove the trailing zeros from exponents in output
        String exponent = term.power().stripTrailingZeros().toPlainString();
        result.append("x^").append(exponent);
      }
    }
    return result.toString();
  }
}
