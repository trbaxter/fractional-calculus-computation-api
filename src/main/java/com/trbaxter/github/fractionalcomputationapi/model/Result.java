package com.trbaxter.github.fractionalcomputationapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

  private String expression;

  public Result(String expression) {
    this.expression = expression;
  }
}
