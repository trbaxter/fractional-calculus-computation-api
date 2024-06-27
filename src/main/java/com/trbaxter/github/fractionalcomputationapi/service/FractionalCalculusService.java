package com.trbaxter.github.fractionalcomputationapi.service;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.util.List;

/**
 * FractionalCalculusService defines the contract for services that perform fractional<br>
 * calculus operations on polynomial expressions.
 */
public interface FractionalCalculusService {
  String evaluateExpression(List<Term> terms, double alpha);

  String evaluateExpression(String polynomialExpression, double alpha);
}
