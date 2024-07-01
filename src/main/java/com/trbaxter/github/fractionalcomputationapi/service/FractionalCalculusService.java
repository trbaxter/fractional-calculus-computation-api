package com.trbaxter.github.fractionalcomputationapi.service;

import com.trbaxter.github.fractionalcomputationapi.exception.BadRequestException;
import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import java.util.List;

/**
 * FractionalCalculusService defines the contract for services that perform fractional<br>
 * calculus operations on polynomial expressions.
 */
public interface FractionalCalculusService {

  default List<Term> parseExpression(String polynomialExpression) {
    try {
      return ExpressionParser.parse(polynomialExpression);
    } catch (IllegalArgumentException e) {
      throw new BadRequestException("Polynomial expression contains invalid characters.");
    }
  }

  String evaluateExpression(String polynomialExpression, double alpha, Integer precision);
}
