package com.trbaxter.github.fractionalcomputationapi.service.integration.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public String evaluateExpression(double[] coefficients, double alpha) {
    List<Term> terms = termComputationService.computeTerms(coefficients, BigDecimal.valueOf(alpha));
    return termFormattingService.formatTerms(terms, alpha);
  }
}
