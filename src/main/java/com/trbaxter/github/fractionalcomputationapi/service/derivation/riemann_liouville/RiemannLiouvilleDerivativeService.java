package com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiemannLiouvilleDerivativeService implements FractionalCalculusService {

  private final RiemannLiouvilleComputationService computationService;
  private final RiemannLiouvilleFormattingService formattingService;

  @Autowired
  public RiemannLiouvilleDerivativeService(
      RiemannLiouvilleComputationService computationService,
      RiemannLiouvilleFormattingService formattingService) {
    this.computationService = computationService;
    this.formattingService = formattingService;
  }

  @Override
  public String evaluateExpression(String polynomialExpression, double alpha, Integer precision) {
    int actualPrecision = (precision != null) ? precision : 3;
    List<Term> terms = ExpressionParser.parse(polynomialExpression);
    List<Term> computedTerms = computationService.computeTerms(terms, BigDecimal.valueOf(alpha));
    return formattingService.formatTerms(computedTerms, actualPrecision);
  }
}
