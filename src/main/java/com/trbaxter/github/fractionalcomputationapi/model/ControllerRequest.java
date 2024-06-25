package com.trbaxter.github.fractionalcomputationapi.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * ControllerRequest represents a request to perform fractional calculus operations.<br>
 * It contains the polynomial coefficients and the order of the operation.
 */
@Getter
@Setter
public class ControllerRequest {
  @NotNull(message = "Coefficients must not be null")
  @Size(min = 1, message = "At least one coefficient must be provided")
  private double[] coefficients;

  @PositiveOrZero(message = "Order must be zero or positive")
  private double order;
}
