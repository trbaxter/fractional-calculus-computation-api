package com.trbaxter.github.fractionalcomputationapi.service;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.BaseFormattingService;

public class TestFormattingService extends BaseFormattingService {

  @Override
  protected boolean shouldSkipTerm(Term term) {
    return true;
  }

  @Override
  protected String getZeroPolynomialResult() {
    return "0";
  }
}
