package com.trbaxter.github.fractionalcomputationapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotNaNValidator implements ConstraintValidator<NotNaN, Double> {

  @Override
  public void initialize(NotNaN constraintAnnotation) {}

  @Override
  public boolean isValid(Double value, ConstraintValidatorContext context) {
    return value != null && !value.isNaN();
  }
}
