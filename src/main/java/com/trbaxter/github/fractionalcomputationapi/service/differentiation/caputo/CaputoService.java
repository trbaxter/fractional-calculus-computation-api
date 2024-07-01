package com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CaputoService computes the Caputo fractional derivative of a polynomial expression.<br>
 * It uses the CaputoComputationService to compute the terms and the CaputoFormattingService<br>
 * to format the result.
 */
@Service
public class CaputoService implements FractionalCalculusService {
  private final CaputoComputationService computationService;
  private final CaputoFormattingService formattingService;

  @Autowired
  public CaputoService(
      CaputoComputationService computationService, CaputoFormattingService formattingService) {
    this.computationService = computationService;
    this.formattingService = formattingService;
  }

  @Override
  public String evaluateExpression(String polynomialExpression, double alpha, Integer precision) {
    List<Term> terms = parseExpression(polynomialExpression);
    List<Term> computedTerms = computationService.computeTerms(terms, BigDecimal.valueOf(alpha));
    return formattingService.formatTerms(computedTerms, precision);
  }
}
