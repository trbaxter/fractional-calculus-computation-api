package com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.derivation.BaseFormattingService;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class CaputoDerivativeFormattingService extends BaseFormattingService {

  @Override
  protected boolean shouldSkipTerm(Term term) {

    // Skip terms with zero coefficients or negative exponents
    return term.coefficient().compareTo(BigDecimal.ZERO) == 0
        || term.power().compareTo(BigDecimal.ZERO) < 0;
  }

  @Override
  protected String getZeroPolynomialResult() {

    // Derivative of 0 polynomial is always 0
    return "0";
  }
}
