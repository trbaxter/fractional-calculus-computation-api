package com.trbaxter.github.fractionalcomputationapi.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class NotNaNValidatorTest {

  private NotNaNValidator notNaNValidator;

  @Mock private ConstraintValidatorContext context;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    notNaNValidator = new NotNaNValidator();
  }

  @Test
  void testIsValid_NullValue() {
    assertFalse(notNaNValidator.isValid(null, context));
  }

  @Test
  void testIsValid_NaNValue() {
    assertFalse(notNaNValidator.isValid(Double.NaN, context));
  }

  @Test
  void testIsValid_ValidValue() {
    assertTrue(notNaNValidator.isValid(10.0, context));
  }
}
