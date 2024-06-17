package com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.derivation.BaseFormattingService;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class RiemannLiouvilleFormattingService extends BaseFormattingService {

  @Override
  protected boolean shouldSkipTerm(Term term) {

    // Skip terms with zero coefficients
    return term.coefficient().compareTo(BigDecimal.ZERO) == 0;
  }

  @Override
  protected String getZeroPolynomialResult() {

    // Derivative of 0 polynomial is always 0
    return "0";
  }
}
