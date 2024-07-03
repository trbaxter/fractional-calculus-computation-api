package com.trbaxter.github.fractionalcomputationapi.service;

import com.trbaxter.github.fractionalcomputationapi.exception.BadRequestException;
import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import java.util.List;

/**
 * Interface for services that perform fractional calculus operations.
 *
 * <p>Implementations of this interface provide methods to evaluate polynomial expressions and
 * compute their fractional derivatives or integrals.
 */
public interface FractionalCalculusService {

  /**
   * Parses the given polynomial expression into a list of terms.
   *
   * @param polynomialExpression the polynomial expression to parse
   * @return a list of terms representing the parsed polynomial expression
   * @throws BadRequestException if the polynomial expression contains invalid characters
   */
  default List<Term> parseExpression(String polynomialExpression) {
    try {
      return ExpressionParser.parse(polynomialExpression);
    } catch (IllegalArgumentException e) {
      throw new BadRequestException("Polynomial expression contains invalid characters.");
    }
  }

  /**
   * Evaluates the polynomial expression with the given fractional order and precision.
   *
   * @param polynomialExpression the polynomial expression to evaluate
   * @param alpha the fractional order of the operation
   * @param precision the precision to use in the evaluation
   * @return the result of the evaluation as a string
   */
  String evaluateExpression(String polynomialExpression, double alpha, Integer precision);
}
