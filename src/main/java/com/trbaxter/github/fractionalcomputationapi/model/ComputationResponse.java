package com.trbaxter.github.fractionalcomputationapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComputationResponse {

  private String expression;

  public ComputationResponse(String expression) {
    this.expression = expression;
  }
}
