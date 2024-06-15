package com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
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
  public String evaluateExpression(double[] coefficients, double alpha) {
    List<Term> terms = computationService.computeTerms(coefficients, BigDecimal.valueOf(alpha));
    return formattingService.formatTerms(terms);
  }
}
