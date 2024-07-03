package com.trbaxter.github.fractionalcomputationapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** ResultTest tests the functionality of the Result record. */
class ResultTest {

  private Result result;

  /** Sets up a new Result instance before each test. */
  @BeforeEach
  public void setUp() {
    result = new Result("initialExpression");
  }

  /** Tests the constructor and getter of the Result record. */
  @Test
  void testConstructorAndGetter() {
    assertEquals(
        "initialExpression",
        result.expression(),
        "The expression should be correctly set by the constructor");
  }
}
