package com.trbaxter.github.fractionalcomputationapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * ControllerRequest represents a request to perform fractional calculus operations.<br>
 * It contains the polynomial coefficients and the order of the operation.
 */
@Getter
@Setter
public class ControllerRequest {

  @NotNull(message = "Polynomial expression cannot be null")
  @NotBlank(message = "Polynomial expression cannot be blank")
  private String polynomialExpression;

  @NotNull(message = "Order cannot be null")
  private Double order;

  @NotNull(message = "Precision cannot be null")
  @Positive(message = "Precision must be a positive integer")
  private Integer precision;
}
