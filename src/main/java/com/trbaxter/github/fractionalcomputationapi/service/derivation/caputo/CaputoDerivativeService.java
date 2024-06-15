package com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public String evaluateExpression(double[] coefficients, double alpha) {
    List<Term> terms = termComputationService.computeTerms(coefficients, BigDecimal.valueOf(alpha));
    return termFormattingService.formatTerms(terms);
  }
}
