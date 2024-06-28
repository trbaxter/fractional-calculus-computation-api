package com.trbaxter.github.fractionalcomputationapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

/**
 * ControllerRequest represents a request to perform fractional calculus operations.<br>
 * It contains the polynomial coefficients and the order of the operation.
 */
@Getter
@Setter
public class ControllerRequest {
  @NotBlank(message = "Polynomial expression must not be empty or null")
  private String polynomialExpression;

  @PositiveOrZero(message = "Order must be zero or positive")
  private double order;

  @Positive(message = "Precision must be a positive integer")
  private Integer precision;
}
