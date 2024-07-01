package com.trbaxter.github.fractionalcomputationapi.service.integration;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
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
public class IntegrationService implements FractionalCalculusService {
  private final IntegralComputationService termComputationService;
  private final IntegralFormattingService termFormattingService;

  @Autowired
  public IntegrationService(
      IntegralComputationService termComputationService,
      IntegralFormattingService termFormattingService) {
    this.termComputationService = termComputationService;
    this.termFormattingService = termFormattingService;
  }

  @Override
  public String evaluateExpression(String polynomialExpression, double alpha, Integer precision) {
    List<Term> terms = parseExpression(polynomialExpression);
    List<Term> computedTerms =
        termComputationService.computeTerms(terms, BigDecimal.valueOf(alpha));
    return termFormattingService.formatTerms(computedTerms, alpha, precision);
  }
}
