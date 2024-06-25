package com.trbaxter.github.fractionalcomputationapi.service.derivation;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * BaseFormattingService is an abstract class that provides common functionality for formatting
 * polynomial terms. It includes methods to format the terms, check for zero coefficients, and
 * append terms to a result string.
 */
public abstract class BaseFormattingService {

  /**
   * Formats the given list of polynomial terms into a string representation.
   *
   * @param terms the list of polynomial terms to format.
   * @return the formatted string representation of the polynomial terms.
   */
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

  /**
   * Checks if all the terms have zero coefficients.
   *
   * @param terms the list of polynomial terms to check.
   * @return true if all terms have zero coefficients, false otherwise.
   */
  private boolean allZeroCoefficients(List<Term> terms) {
    return terms.stream().allMatch(term -> term.coefficient().compareTo(BigDecimal.ZERO) == 0);
  }

  /**
   * Determines whether a term should be skipped based on its coefficient and power.
   *
   * @param term the term to be evaluated.
   * @return true if the term should be skipped, false otherwise.
   */
  protected abstract boolean shouldSkipTerm(Term term);

  /**
   * Provides the result for the derivative of a zero polynomial.
   *
   * @return a string representing the result for a zero polynomial.
   */
  protected abstract String getZeroPolynomialResult();

  /**
   * Formats the coefficient by scaling it to three decimal places and stripping trailing zeros.
   *
   * @param coefficient the coefficient to format.
   * @return the formatted coefficient as a string.
   */
  private String formatCoefficient(BigDecimal coefficient) {
    BigDecimal scaledCoefficient = coefficient.setScale(3, RoundingMode.HALF_UP);
    return scaledCoefficient.stripTrailingZeros().scale() <= 0
        ? scaledCoefficient.stripTrailingZeros().toPlainString()
        : scaledCoefficient.toPlainString().replaceAll("\\.000$", "");
  }

  /**
   * Appends a term to the result string with appropriate formatting for the coefficient and power.
   *
   * @param result the StringBuilder to append the term to.
   * @param term the term to append.
   * @param coefficientString the formatted coefficient string.
   */
  private void appendTerm(StringBuilder result, Term term, String coefficientString) {
    BigDecimal coefficient = term.coefficient().setScale(3, RoundingMode.HALF_UP);

    if (coefficient.compareTo(BigDecimal.ZERO) == 0) {
      return;
    }

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
