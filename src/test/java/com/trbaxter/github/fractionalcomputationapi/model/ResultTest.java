package com.trbaxter.github.fractionalcomputationapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultTest {

  private Result result;

  @BeforeEach
  public void setUp() {
    result = new Result("initialExpression");
  }

  @Test
  public void testSetExpression() {
    String newExpression = "newExpression";
    result.setExpression(newExpression);
    assertEquals(
        newExpression, result.getExpression(), "The expression should be updated to the new value");
  }
}
