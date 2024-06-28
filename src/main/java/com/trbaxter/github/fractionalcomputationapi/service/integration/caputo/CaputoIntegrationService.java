package com.trbaxter.github.fractionalcomputationapi.service.integration.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CaputoIntegrationService is a service that computes the Caputo fractional integral of a
 * polynomial expression. It uses the CaputoIntegralComputationService to compute the terms and the
 * CaputoIntegralFormattingService to format the result.
 */
@Service
public class CaputoIntegrationService implements FractionalCalculusService {
  private final CaputoIntegralComputationService termComputationService;
  private final CaputoIntegralFormattingService termFormattingService;

  @Autowired
  public CaputoIntegrationService(
      CaputoIntegralComputationService termComputationService,
      CaputoIntegralFormattingService termFormattingService) {
    this.termComputationService = termComputationService;
    this.termFormattingService = termFormattingService;
  }

  @Override
  public String evaluateExpression(String polynomialExpression, double alpha, Integer precision) {
    int actualPrecision = (precision != null) ? precision : 3;
    List<Term> terms = ExpressionParser.parse(polynomialExpression);
    List<Term> computedTerms =
        termComputationService.computeTerms(terms, BigDecimal.valueOf(alpha));
    return termFormattingService.formatTerms(computedTerms, alpha, actualPrecision);
  }
}
