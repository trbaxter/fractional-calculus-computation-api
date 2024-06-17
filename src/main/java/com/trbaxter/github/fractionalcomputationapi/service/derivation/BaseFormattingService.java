package com.trbaxter.github.fractionalcomputationapi.service.derivation;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public abstract class BaseFormattingService {

  public String formatTerms(List<Term> terms) {
    if (allZeroCoefficients(terms)) {
      return getZeroPolynomialResult();
    }

    StringBuilder result = new StringBuilder();

    for (Term term : terms) {
      if (shouldSkipTerm(term)) {
        continue;
      }

      String coefficientString = formatCoefficient(term.coefficient());
      appendTerm(result, term, coefficientString);
    }

    if (result.isEmpty()) {
      return getZeroPolynomialResult();
    }

    return result.toString();
  }

  private boolean allZeroCoefficients(List<Term> terms) {
    return terms.stream().allMatch(term -> term.coefficient().compareTo(BigDecimal.ZERO) == 0);
  }

  protected abstract boolean shouldSkipTerm(Term term);

  protected abstract String getZeroPolynomialResult();

  private String formatCoefficient(BigDecimal coefficient) {
    BigDecimal scaledCoefficient = coefficient.setScale(3, RoundingMode.HALF_UP);
    return scaledCoefficient.stripTrailingZeros().scale() <= 0
        ? scaledCoefficient.stripTrailingZeros().toPlainString()
        : scaledCoefficient.toPlainString().replaceAll("\\.000$", "");
  }

  private void appendTerm(StringBuilder result, Term term, String coefficientString) {
    BigDecimal coefficient = term.coefficient().setScale(3, RoundingMode.HALF_UP);

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
}
