package com.trbaxter.github.fractionalcomputationapi.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** NotNaNValidatorTest tests the functionality of the NotNaNValidator. */
@ExtendWith(MockitoExtension.class)
class NotNaNValidatorTest {

  private NotNaNValidator notNaNValidator;

  @Mock private ConstraintValidatorContext context;

  /** Sets up the test environment before each test. */
  @BeforeEach
  void setUp() {
    notNaNValidator = new NotNaNValidator();
  }

  /** Tests if the validator correctly identifies a null value as invalid. */
  @Test
  void testIsValid_NullValue() {
    assertFalse(notNaNValidator.isValid(null, context));
  }

  /** Tests if the validator correctly identifies NaN as invalid. */
  @Test
  void testIsValid_NaNValue() {
    assertFalse(notNaNValidator.isValid(Double.NaN, context));
  }

  /** Tests if the validator correctly identifies a valid double value. */
  @Test
  void testIsValid_ValidValue() {
    assertTrue(notNaNValidator.isValid(10.0, context));
  }
}
