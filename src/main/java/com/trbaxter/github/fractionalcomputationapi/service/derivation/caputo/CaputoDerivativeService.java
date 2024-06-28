package com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CaputoDerivativeService is a service that computes the Caputo fractional derivative of a
 * polynomial expression. It uses the CaputoDerivativeComputationService to compute the terms and
 * the CaputoDerivativeFormattingService to format the result.
 */
@Service
public class CaputoDerivativeService implements FractionalCalculusService {
  private final CaputoDerivativeComputationService termComputationService;
  private final CaputoDerivativeFormattingService termFormattingService;

  @Autowired
  public CaputoDerivativeService(
      CaputoDerivativeComputationService termComputationService,
      CaputoDerivativeFormattingService termFormattingService) {
    this.termComputationService = termComputationService;
    this.termFormattingService = termFormattingService;
  }

  @Override
  public String evaluateExpression(String polynomialExpression, double alpha, Integer precision) {
    int actualPrecision = (precision != null) ? precision : 3;
    List<Term> terms = ExpressionParser.parse(polynomialExpression);
    List<Term> computedTerms =
        termComputationService.computeTerms(terms, BigDecimal.valueOf(alpha));
    return termFormattingService.formatTerms(computedTerms, actualPrecision);
  }
}
