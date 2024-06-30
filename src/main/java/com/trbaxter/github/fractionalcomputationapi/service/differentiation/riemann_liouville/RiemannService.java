package com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiemannService implements FractionalCalculusService {

  private final RiemannComputationService computationService;
  private final RiemannFormattingService formattingService;

  @Autowired
  public RiemannService(
      RiemannComputationService computationService, RiemannFormattingService formattingService) {
    this.computationService = computationService;
    this.formattingService = formattingService;
  }

  @Override
  public String evaluateExpression(String polynomialExpression, double alpha, Integer precision) {
    List<Term> terms = ExpressionParser.parse(polynomialExpression);
    List<Term> computedTerms = computationService.computeTerms(terms, BigDecimal.valueOf(alpha));
    return formattingService.formatTerms(computedTerms, precision);
  }
}
