package com.trbaxter.github.fractionalcomputationapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/** TermTest tests the creation and behavior of Term instances. */
class TermTest {

  /**
   * Provides valid Term instances for parameterized tests.
   *
   * @return a stream of valid Term arguments.
   */
  private static Stream<Arguments> provideValidTerms() {
    return Stream.of(
        Arguments.of(new BigDecimal("3.5"), new BigDecimal("2.0")),
        Arguments.of(new BigDecimal("0.0"), new BigDecimal("0.0")),
        Arguments.of(new BigDecimal("-1.5"), new BigDecimal("3.0")));
  }

  /**
   * Tests the creation of Term instances with valid inputs.
   *
   * @param coefficient the coefficient of the term.
   * @param power the power of the term.
   */
  @ParameterizedTest
  @MethodSource("provideValidTerms")
  void testTermRecord(BigDecimal coefficient, BigDecimal power) {
    Term term = new Term(coefficient, power);
    assertEquals(coefficient, term.coefficient(), "Coefficient should be set correctly");
    assertEquals(power, term.power(), "Power should be set correctly");
  }

  /** Tests the creation of Term instances with null coefficient. */
  @Test
  void testTermRecordNullCoefficient() {
    assertThrows(
        NullPointerException.class,
        this::createTermWithNullCoefficient,
        "Should throw NullPointerException when coefficient is null");
  }

  /** Tests the creation of Term instances with null power. */
  @Test
  void testTermRecordNullExponent() {
    assertThrows(
        NullPointerException.class,
        this::createTermWithNullExponent,
        "Should throw NullPointerException when power is null");
  }

  /** Helper method to create a Term with null coefficient. */
  private void createTermWithNullCoefficient() {
    new Term(null, new BigDecimal("2.0"));
  }

  /** Helper method to create a Term with null power. */
  private void createTermWithNullExponent() {
    new Term(new BigDecimal("3.5"), null);
  }
}
