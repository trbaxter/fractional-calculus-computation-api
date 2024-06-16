package com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RiemannLiouvilleFormattingService {

  public String formatTerms(List<Term> terms) {
    // Check if all terms are zero, meaning the polynomial is zero
    if (terms.isEmpty()
        || terms.stream().allMatch(term -> term.coefficient().compareTo(BigDecimal.ZERO) == 0)) {
      return "0";
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < terms.size(); i++) {
      Term term = terms.get(i);
      BigDecimal coefficient = term.coefficient().setScale(3, RoundingMode.HALF_UP);

      // Convert coefficient to string and remove ".000" if it exists
      String coefficientStr = coefficient.toPlainString();
      if (coefficientStr.endsWith(".000")) {
        coefficientStr = coefficientStr.substring(0, coefficientStr.length() - 4);
      }

      // Handle negative zero
      if (coefficientStr.equals("-0")) {
        coefficientStr = "0";
      }

      if (i > 0) {
        if (coefficient.compareTo(BigDecimal.ZERO) > 0) {
          result.append(" + ");
        } else if (coefficient.compareTo(BigDecimal.ZERO) < 0) {
          result.append(" - ");
          coefficientStr = coefficient.abs().toPlainString();
          if (coefficientStr.endsWith(".000")) {
            coefficientStr = coefficientStr.substring(0, coefficientStr.length() - 4);
          }
        }
      } else {
        if (coefficient.compareTo(BigDecimal.ZERO) < 0) {
          result.append("-");
          coefficientStr = coefficient.abs().toPlainString();
          if (coefficientStr.endsWith(".000")) {
            coefficientStr = coefficientStr.substring(0, coefficientStr.length() - 4);
          }
        }
      }

      if (coefficient.abs().compareTo(BigDecimal.ONE) != 0
          || term.power().compareTo(BigDecimal.ZERO) == 0) {
        result.append(coefficientStr);
      }

      // If power is zero only append the coefficient
      if (term.power().compareTo(BigDecimal.ZERO) != 0) {
        result.append("x");
        if (term.power().compareTo(BigDecimal.ONE) != 0) {
          // Remove the trailing zeros from exponents in output
          String exponent = term.power().stripTrailingZeros().toPlainString();
          result.append("^").append(exponent);
        }
      }
    }
    return result.toString();
  }
}
