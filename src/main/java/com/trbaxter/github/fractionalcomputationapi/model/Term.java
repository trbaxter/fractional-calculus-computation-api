package com.trbaxter.github.fractionalcomputationapi.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Term represents a singular term in a polynomial expression, consisting of a coefficient and a
 * power.
 *
 * @param coefficient the coefficient of the term, must not be null
 * @param power the power of the term, must not be null
 */
public record Term(BigDecimal coefficient, BigDecimal power) {
  /**
   * Constructs a Term record with the specified coefficient and power.
   *
   * @param coefficient the coefficient of the term
   * @param power the power of the term
   * @throws NullPointerException if either coefficient or power is null
   */
  public Term {
    Objects.requireNonNull(coefficient, "Coefficient must not be null");
    Objects.requireNonNull(power, "Power must not be null");
  }
}
