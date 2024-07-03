package com.trbaxter.github.fractionalcomputationapi.model;

import com.trbaxter.github.fractionalcomputationapi.validation.NotNaN;
import com.trbaxter.github.fractionalcomputationapi.validation.ValidationOrder;
import jakarta.validation.GroupSequence;
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
@GroupSequence({ControllerRequest.class, ValidationOrder.First.class, ValidationOrder.Second.class})
public class ControllerRequest {

  @NotNull(message = "Polynomial expression cannot be null", groups = ValidationOrder.First.class)
  @NotBlank(
      message = "Polynomial expression cannot be blank",
      groups = ValidationOrder.Second.class)
  private String polynomialExpression;

  @NotNull(message = "Order cannot be null", groups = ValidationOrder.First.class)
  @NotNaN(message = "Order cannot be NaN", groups = ValidationOrder.Second.class)
  private Double order;

  @NotNull(message = "Precision cannot be null", groups = ValidationOrder.First.class)
  @Positive(message = "Precision must be a positive integer", groups = ValidationOrder.Second.class)
  private Integer precision;
}
