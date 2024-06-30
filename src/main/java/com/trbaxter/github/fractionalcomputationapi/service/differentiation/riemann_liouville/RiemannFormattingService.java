package com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.BaseFormattingService;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class RiemannFormattingService extends BaseFormattingService {

  @Override
  protected boolean shouldSkipTerm(Term term) {
    return term.coefficient().compareTo(BigDecimal.ZERO) == 0;
  }

  @Override
  protected String getZeroPolynomialResult() {
    return "0";
  }
}
