package com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.BaseFormattingService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * CaputoDerivativeFormattingService is a service that provides formatting for the results of Caputo
 * fractional derivative computations. It extends the BaseFormattingService to apply specific rules
 * for Caputo derivatives.
 */
@Service
public class CaputoFormattingService extends BaseFormattingService {

  /**
   * Determines whether a term should be skipped based on its coefficient and power.
   *
   * @param term the term of the polynomial to be evaluated.
   * @return true if the term should be skipped, false otherwise.
   */
  @Override
  protected boolean shouldSkipTerm(Term term) {
    return term.coefficient().compareTo(BigDecimal.ZERO) == 0
        || term.power().compareTo(BigDecimal.ZERO) < 0;
  }

  /**
   * Provides the result for the derivative of a zero polynomial.
   *
   * @return a String representing the derivative of a zero polynomial.
   */
  @Override
  protected String getZeroPolynomialResult() {
    return "0";
  }

  @Override
  public String formatTerms(List<Term> terms, int precision) {
    return super.formatTerms(terms, precision);
  }
}
