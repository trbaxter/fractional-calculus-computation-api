package com.trbaxter.github.fractionalcomputationapi.service;

/**
 * FractionalCalculusService defines the contract for services that perform fractional<br>
 * calculus operations on polynomial expressions.
 */
public interface FractionalCalculusService {
  String evaluateExpression(String polynomialExpression, double alpha, Integer precision);
}
