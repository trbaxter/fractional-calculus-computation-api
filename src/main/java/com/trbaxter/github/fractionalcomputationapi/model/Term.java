package com.trbaxter.github.fractionalcomputationapi.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Term represents a singular term in a polynomial expression, consisting of a coefficient and a
 * power.
 */
public record Term(BigDecimal coefficient, BigDecimal power) {
  public Term {
    Objects.requireNonNull(coefficient, "Coefficient must not be null");
    Objects.requireNonNull(power, "Power must not be null");
  }
}
