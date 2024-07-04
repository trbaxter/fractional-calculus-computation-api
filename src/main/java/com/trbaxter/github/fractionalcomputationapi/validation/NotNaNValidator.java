package com.trbaxter.github.fractionalcomputationapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * NotNaNValidator is a validator to ensure a given Double value is not NaN.
 *
 * <p>This validator checks that the provided value is not null and is a valid number.
 */
public class NotNaNValidator implements ConstraintValidator<NotNaN, Double> {

  /**
   * Validates that the given value is not NaN.
   *
   * @param value the value to validate
   * @param context the context in which the constraint is evaluated
   * @return true if the value is not null and not NaN, false otherwise
   */
  @Override
  public boolean isValid(Double value, ConstraintValidatorContext context) {
    return value != null && !value.isNaN();
  }
}
