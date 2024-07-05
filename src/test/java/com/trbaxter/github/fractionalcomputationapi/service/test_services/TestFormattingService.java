package com.trbaxter.github.fractionalcomputationapi.service.test_services;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.BaseFormattingService;

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
