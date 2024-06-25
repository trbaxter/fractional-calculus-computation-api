package com.trbaxter.github.fractionalcomputationapi.service.integration.caputo;

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
public class CaputoIntegrationService implements FractionalCalculusService {
  private final CaputoIntegralComputationService termComputationService;
  private final CaputoIntegralFormattingService termFormattingService;

  /**
   * Constructs a CaputoIntegrationService with the specified computation and formatting services.
   *
   * @param termComputationService the service for computing the terms of the Caputo integral.
   * @param termFormattingService the service for formatting the terms of the Caputo integral.
   */
  @Autowired
  public CaputoIntegrationService(
      CaputoIntegralComputationService termComputationService,
      CaputoIntegralFormattingService termFormattingService) {
    this.termComputationService = termComputationService;
    this.termFormattingService = termFormattingService;
  }

  /**
   * Evaluates the Caputo fractional integral of a polynomial expression with the given coefficients
   * and order alpha.
   *
   * @param coefficients the coefficients of the polynomial, must not be null.
   * @param alpha the fractional order of the Caputo integral, must be zero or positive.
   * @return the result of the Caputo fractional integral computation as a String.
   */
  @Override
  public String evaluateExpression(double[] coefficients, double alpha) {
    List<Term> terms = termComputationService.computeTerms(coefficients, BigDecimal.valueOf(alpha));
    return termFormattingService.formatTerms(terms, alpha);
  }
}
